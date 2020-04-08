package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Расчётные действия над числами из диапазона.
 */
public class RangeCalculation {
    /**
     * Действия над числами из диапазона.
     * @param start Начальное значение.
     * @param end Конечное значение.
     * @param func Функция, применяемая к числам.
     * @return Список преобразованных значений.
     * @noinspection checkstyle:EmptyBlock
     */
    public List<Double> range(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}
