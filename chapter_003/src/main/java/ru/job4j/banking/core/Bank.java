package ru.job4j.banking.core;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Just simple emulate of bank system.
 */
public class Bank {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(Bank.class);

	/**
	 * Accounts of user of bank.
	 */
	private Map<User, List<Account>> usersAccounts = new TreeMap<>();

	/**
	 * Gets users accounts.
	 *
	 * @return All users with all theirs accounts.
	 */
	public Map<User, List<Account>> getUsersAccounts() {
		return this.usersAccounts;
	}

	/**
	 * Adds a new user to bank system.
	 * <p>If no argument user in map then he will be added. For approval
	 * we use {@link Map#containsKey()} and return opposite result.
	 * <p>It adds only user and he has no accounts.
	 *
	 * @param user user of bank.
	 *
	 * @return Returns true if user was added succesfully to bank system
	 *         otherwise false.
	 */
	public boolean addUser(User user) {
		boolean result = this.usersAccounts.containsKey(user);
		this.usersAccounts.putIfAbsent(user, new ArrayList<>());
		return !result;
	}

	/**
	 * Deletes a user from bank system with all his accounts.
	 * <p>If user is in map then it will removed from it, therefore we can
	 * return result using {@link Map#containsKey()}. If method throws exception
	 * it will be in line contains <tt>remove</tt> method.
	 * <p>It is sad. Bye bye!
	 *
	 * @param user user of bank.
	 *
	 * @return Returns true if user was deleted succesfully from bank system
	 *         otherwise false.
	 */
	public List<Account> deleteUser(User user) {
		return this.usersAccounts.remove(user);
	}

	/**
	 * Adds an account to list of accounts of user.
	 * <p>Searching of user is performing by his passport data.
	 * <p>If same account already exists then returns false.
	 *
	 * @param passport Passport data of user.
	 * @param account New account of user must be added to the list.
	 *
	 * @return Returns true if user account was added otherwise false.
	 */
	public boolean addUserAccount(String passport, Account account) {
		boolean result = false;

		List<Account> userAccounts = this.getUserAccounts(passport);
		if (!userAccounts.contains(account)) {
			result = userAccounts.add(account);
		}

		return result;
	}

	/**
	 * Deletes one user account if it exists.
	 *
	 * @param passport Passport data of user.
	 * @param account Account of user must be deleted from the list.
	 *
	 * @return Returns true if user account was deleted otherwise false.
	 */
	public Account deleteUserAccount(String passport, Account account) {
		List<Account> userAccounts = this.getUserAccounts(passport);
		userAccounts.remove(account);

		return account;
	}

	/**
	 * Gets all user accounts.
	 *
	 * @param passport Passport data of user.
	 * @return List of all user accounts.
	 */
	public List<Account> getUserAccounts(String passport) {
		List<Account> userAccounts = null;

		for (Map.Entry<User, List<Account>> entry : this.usersAccounts.entrySet()) {
			if (passport.equals(entry.getKey().getPassport())) {
				userAccounts = entry.getValue();
			}
		}

		if (userAccounts == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Пользователь по паспорту ").append(passport).append(" не найден. \n")
				.append("Проверьте, пожалуйста, правильность введённого номер паспорта.");
			throw new NoSuchUserException(sb.toString());
		}

		return userAccounts;
	}

	/**
	 * Transfer of amount of money from user account to another account of the
	 * same user or from one user account to another account of other user.
	 *
	 * @param srcPassport
	 *         Passport data of user from the account of which money
	 *         will be tranferred.
	 * @param srcRequisites
	 *         Requisites of user account from which money will be transferred.
	 * @param destPassport
	 *         Passport data of user to the account of which money
	 *         will be tranferred.
	 * @param destRequisites
	 *         Requisites of user account to which money will be transferred.
	 * @param amount
	 *         Amount of money must be tranferred.
	 *
	 * @return Returns true if money were tranferred succesfully to another
	 * account.
	 */
	public boolean transferMoney(String srcPassport, String srcRequisites,
			String destPassport, String destRequisites, double amount) {

		// Проверяем, чтобы сумма перевода была больше 0.
		if (amount <= 0.0) {
			return false;
		}

		// Проверяем, чтобы реквизиты не были равны.
		if (srcRequisites.equals(destRequisites)) {
			return false;
		}

		boolean isSuccess = false;

		// Ищем отправителя.
		User srcuser = this.getUser(srcPassport);
		// Если отправитель был найден, то получаем доступ к его счёту.
		Account srcAccount = this.getUserAccount(srcuser, srcRequisites);
		// Проверяем, чтобы передаваемая сумма не превышала суммы на счету.
		if (amount > srcAccount.getValue()) {
			return false;
		}
		// Ищем получателя, проверив не является ли отправитель и
		// получатель одним и тем же лицом.
		User destuser = srcPassport.equals(destPassport)
				? srcuser : this.getUser(destPassport);
		// Получаем доступ к счёту получателя.
		Account destAccount = this.getUserAccount(destuser, destRequisites);

		// Начисляем переданную сумму на счёт получателя.
		destAccount.setValue(destAccount.getValue() + amount);
		// Соответственно списываем передаваемую сумму со счёта отправителя.
		srcAccount.setValue(srcAccount.getValue() - amount);
		isSuccess = true;

		return isSuccess;
	}

	/**
	 * Gets a user by passport.
	 *
	 * @param passport Passport data of user for searching him.
	 *
	 * @return user with required passport.
	 */
	public User getUser(String passport) {
		User user = null;

		for (Map.Entry<User, List<Account>> entry : this.usersAccounts.entrySet()) {
			if (passport.equals(entry.getKey().getPassport())) {
				user = entry.getKey();
				break;
			}
		}

		if (user == null) {
			throw new NoSuchUserException("Пользователь с номером паспорта "
					+ passport + " не найден!");
		}

		return user;
	}

	/**
	 * Gets account of user by account reqisites.
	 *
	 * @param user user of bank.
	 * @param requisites Requisites of account.
	 *
	 * @return Account of user.
	 */
	public Account getUserAccount(User user, String requisites) {
		List<Account> accounts = this.usersAccounts.get(user);
		Account result = null;

		if (!accounts.isEmpty()) {
			for (Account account : accounts) {
				if (requisites.equals(account.getRequisites())) {
					result = account;
					break;
				}
			}
		}

		if (result == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Счёт с реквизитами '")
					.append(requisites).append("' у пользователя ")
					.append(user.getName()).append(" не найден!");
			throw new NoSuchUserAccountException(sb.toString());
		}

		return result;
	}
}
