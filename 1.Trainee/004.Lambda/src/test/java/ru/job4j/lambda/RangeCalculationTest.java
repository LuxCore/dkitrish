package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование расчётов, производимых над числами из даипазона.
 */
public class RangeCalculationTest {
    /**
     * Тест расчётов, производимых над числами из диапазона.
     * Линейная функция y = kx + b.
     */
    @Test
    public void testRangeLinearFunction() {
        RangeCalculation rangeCalculation = new RangeCalculation();
        List<Double> result = rangeCalculation.range(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    /**
     * Тест расчётов, производимых над числами из диапазона.
     * Квадратичная функция y = ax^2 + bx + c.
     */
    @Test
    public void testRangeQuadraticFunction() {
        RangeCalculation rangeCalculation = new RangeCalculation();
        List<Double> result = rangeCalculation.range(-1, 5, x -> Math.pow(2 * x, 2) + 4 * x + 6);
        List<Double> expected = Arrays.asList(6D, 6D, 14D, 30D, 54D, 86D);
        assertThat(result, is(expected));
    }

    /**
     * Тест расчётов, производимых над числами из диапазона.
     * Показательная функция y = a^x.
     */
    @Test
    public void testRangeExponentialFunction() {
        RangeCalculation rangeCalculation = new RangeCalculation();
        List<Double> result = rangeCalculation.range(3, 9, x -> Math.pow(2, x));
        List<Double> expected = Arrays.asList(8D, 16D, 32D, 64D, 128D, 256D);
        assertThat(result, is(expected));
    }
}