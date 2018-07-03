package ru.job4j.marketdepth;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Currency;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Биржевой стакан.
 * <p>Хранит биржевые стаканы в карте {@link Map}{@code <K, V>} по следующему
 * принципу: в ключе {@code K} содержаться сами стаканы, а в значении {@code V}
 * содержаться очереди из заявок, соответствующие идентификатору ценной бумаги
 * и цене.</p>
 * <table border cellpadding=3 cellspacing=1 width=300>
 * <caption>{@link TreeMap}{@code <OrderProxy, Queue<Order>>}</caption>
 * <tr>
 * <td>ISIN, ASK|BID, price, volume</td>
 * <td>Order #3<br/>
 * Order #235<br/>
 * Order #256<br/>
 * Order #349<br/>
 * Order #564<br/>
 * </td>
 * </tr>
 * </table>
 */
public class MarketDepth {
	/**
	 * Единственный экземпляр биржевого саткана.
	 */
	private static MarketDepth instance;

	/**
	 * Валюта, в которой отображается цена заявок в стакане.
	 */
	private Currency currency;

	/**
	 * Хранилище биржевых стаканов.
	 */
	private Map<OrderProxy, Queue<Order>> marketDepth = new TreeMap<>();

	/**
	 * Генерируемый идентификатор заявки.
	 */
	private static int newOrderId;

	/**
	 * Конструктор с заданием валюты для цен в стакане.
	 *
	 * @param currency Валюта цен в стакане.
	 */
	private MarketDepth(Currency currency) {
		this.currency = currency;
	}

	/**
	 * Получение синглтон экземпляра приложения.
	 *
	 * @param currency Валюта, с которой работает биржевой стакан.
	 * @return Экземпляр биржевого стакана.
	 */
	public static MarketDepth getInstance(Currency currency) {
		if (instance != null) {
			instance.currency = currency;
		} else {
			instance = new MarketDepth(currency);
		}
		return instance;
	}

	/**
	 * Выставление(снятие) заявки на(с) тороги(ов).
	 *
	 * @param isin      Идентификатор ценной бумаги (12 символов).
	 * @param trader    Держатель заявки.
	 * @param priceType Тип цены.
	 * @param price     Цена товара/услуги.
	 * @param volume    Объём товара/услуг.
	 * @param action    Действие над заявкой (выставить или снять).
	 * @return true, если операция с заявкой прошла успешно, иначе - false.
	 */
	public boolean putOrder(String isin, String trader, PriceType priceType, BigDecimal price, int volume, Action action) {
		boolean result = false;
		if (action == Action.ADD) {
			result = add(new Order(++newOrderId, isin, trader, priceType, price, volume));
		} else if (action == Action.DELETE) {
			result = delete(isin, trader, priceType, price, volume);
		}
		return result;
	}

	/**
	 * Добавление заявки в стакан.
	 * <p>Данное действие выполняется соответственно следующим шагам:</p>
	 * <ol>
	 * <li>Поиск стакана одного эмитента.</li>
	 * <li>Поиск всех выгодных заявок с противоположным типом цены.</li>
	 * <li>Если выгодные заявки с противоположным типом цены есть, то в цикле
	 * производим взаимовычитание объёмов добавляемой заявки и тех,
	 * которые находятся в {@code Queue<Order>}. Взаимовычитание также
	 * уменьшает объём в {@code OrderProxy}.</li>
	 * <li>Если у добавляемой заявки объём больше 0, то она добавляется
	 * в биржевой стакан.</li>
	 * </ol>
	 *
	 * @param order Заявка.
	 * @return true, если добавление заявки прошло успешно, иначе - false.
	 */
	private boolean add(Order order) {
		boolean result = false;
		Map<OrderProxy, Queue<Order>> issuerMarket = marketDepth.entrySet()
				.stream().filter(e -> order.getIsin().equals(e.getKey().getIsin()))
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldVal, newVal) -> oldVal, LinkedHashMap::new));

		Supplier<PriceType> oppositePriceType =
				() -> order.getPriceType() == PriceType.ASK
						? PriceType.BID : PriceType.ASK;
		BiPredicate<BigDecimal, BigDecimal> priceCompare =
				(d1, d2) -> order.getPriceType() == PriceType.BID
						? d1.compareTo(d2) <= 0 : d1.compareTo(d2) >= 0;
		Function<PriceType, Comparator<? super Map.Entry<OrderProxy, Queue<Order>>>> mapComparator =
				(pt) -> pt == PriceType.BID
						? Map.Entry.comparingByKey(Comparator.reverseOrder())
						: Map.Entry.comparingByKey();

		Map<OrderProxy, Queue<Order>> oppositeBeneficialOrders =
				issuerMarket.entrySet().stream()
						.filter(entry -> entry.getKey().getPriceType() == oppositePriceType.get()
								&& priceCompare.test(entry.getKey().getPrice(), order.getPrice()))
						.sorted(mapComparator.apply(order.getPriceType()))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
								(oldVal, newVal) -> oldVal, LinkedHashMap::new));

		if (oppositeBeneficialOrders != null && !oppositeBeneficialOrders.isEmpty()) {
			for (Iterator<Map.Entry<OrderProxy, Queue<Order>>> eItr =
				 oppositeBeneficialOrders.entrySet().iterator();
				 eItr.hasNext() && order.getVolume() != 0;) {
				Map.Entry<OrderProxy, Queue<Order>> nextEntry = eItr.next();
				for (Iterator<Order> oItr = nextEntry.getValue().iterator();
					 oItr.hasNext() && order.getVolume() != 0;) {
					Order o = oItr.next();
					if (order.getTrader().equals(o.getTrader())) {
						continue;
					}
					if (order.getVolume() >= o.getVolume()) {
						order.decreaseVolume(o.getVolume());
						nextEntry.getKey().addVolume(order.getVolume() - o.getVolume());
						oItr.remove();
					} else {
						o.decreaseVolume(order.getVolume());
						nextEntry.getKey().decreaseVolume(order.getVolume());
						order.decreaseVolume(order.getVolume());
					}
				}
				if (nextEntry.getValue().isEmpty()) {
					marketDepth.remove(nextEntry.getKey());
				}
			}
		}

		if (order.getVolume() != 0) {
			OrderProxy proxy = new OrderProxy(
					order.getIsin(), order.getPriceType(), order.getPrice());
			Map.Entry<OrderProxy, Queue<Order>> entry = null;
			for (Map.Entry<OrderProxy, Queue<Order>> e : marketDepth.entrySet()) {
				if (e.getKey().equals(proxy)) {
					entry = e;
					break;
				}
			}
			if (entry != null) {
				entry.getValue().offer(order);
				entry.getKey().addVolume(order.getVolume());
			} else {
				Queue<Order> q = new LinkedList<>();
				q.offer(order);
				proxy.addVolume(order.getVolume());
				marketDepth.put(proxy, q);
			}
			result = true;
		}
		return result;
	}

	/**
	 * Удаление заявки из стакана.
	 * <p>Удаление происходит с того конца очереди, в который добавляются новые
	 * элементы. Учитываются заявки определённого трэйдера. Если передаваемый
	 * объём для удаления превышает объём у первой найденной заявки, то заявка
	 * удаляется и алгоритм переходит к следующей заявке для уменьшения объёма.</p>
	 *
	 * @param isin      Идентификатор ценной бумаги (12 символов).
	 * @param trader    Держатель заявки.
	 * @param priceType Тип цены.
	 * @param price     Цена товара/услуги.
	 * @param volume    Объём товара/услуг.
	 * @return true, если удаление заявки прошло успешно, иначе - false.
	 */
	private boolean delete(String isin, String trader, PriceType priceType, BigDecimal price, int volume) {
		boolean result = false;
		OrderProxy proxy = new OrderProxy(isin, priceType, price);
		Map.Entry<OrderProxy, Queue<Order>> entry = null;
		for (Map.Entry<OrderProxy, Queue<Order>> e : marketDepth.entrySet()) {
			if (e.getKey().equals(proxy)) {
				entry = e;
				break;
			}
		}
		if (entry != null) {
			LinkedList<Order> traderOrders = (LinkedList<Order>) entry.getValue();
			OrderProxy entryProxy = entry.getKey();
			for (ListIterator<Order> itr = traderOrders.listIterator(traderOrders.size());
				 itr.hasPrevious() && volume > 0;) {
				Order o = itr.previous();
				if (!o.getTrader().equals(trader)) {
					continue;
				}
				if (volume >= o.getVolume()) {
					volume -= o.getVolume();
					entryProxy.decreaseVolume(o.getVolume());
					itr.remove();
				} else {
					o.decreaseVolume(volume);
					entryProxy.decreaseVolume(volume);
					volume = 0;
				}
				result = true;
			}
			if (traderOrders.isEmpty()) {
				marketDepth.remove(proxy);
			}
		}
		return result;
	}

	/**
	 * Получить биржевой стакан в строковом виде.
	 *
	 * @param isin Идентификатор ценной бумаги.
	 * @return Биржевой стакан в строковом представлении.
	 */
	public String getAsString(String isin) {
		String nl = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("|Покупка| Цена,")
				.append(currency).append(" |Продажа|");
		sb.append(nl).append("----------------------------").append(nl);

		Map<OrderProxy, Queue<Order>> isins =
				marketDepth.entrySet().stream()
						.filter(e -> e.getKey().getIsin().equals(isin))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
								(oldVal, newVal) -> oldVal, LinkedHashMap::new));
		for (OrderProxy p : isins.keySet()) {
			if (p.getPriceType() == PriceType.ASK) {
				sb.append(String.format("|%7s|%10s|%7d|", "",
						p.getPrice().toString(), p.getVolume())).append(nl);
			} else if (p.getPriceType() == PriceType.BID) {
				sb.append(String.format("|%7d|%10s|%7s|",
						p.getVolume(), p.getPrice().toString(), "")).append(nl);
			}
		}
		return sb.delete(sb.length() - nl.length(), sb.length()).toString();
	}
}
