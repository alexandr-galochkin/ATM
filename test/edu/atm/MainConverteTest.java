package edu.atm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MainConverteTest {

    @Test
    public void testIdenticalValue() {
        int sum = 10;
        Integer[] banknotes = {1, 2, 2};

        ExchangeOptions expected = new CreateTest(sum, banknotes).create();
        ExchangeOptions actual = new AllCombinations(sum, banknotes).computation();
        System.out.println(sum + "; купюры: " + Arrays.toString(banknotes));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNull() {
        int sum = 10;
        Integer[] banknotes = {0};
        ExchangeOptions actual = new AllCombinations(sum, banknotes).computation();
        System.out.println(sum + "; купюры: " + Arrays.toString(banknotes));
        Assert.assertEquals(new ExchangeOptions(), actual);
    }

    @Test
    public void testBigValues() {
        int sum = 10;
        Integer[] banknotes = {11, 12, 13};
        ExchangeOptions actual = new AllCombinations(sum, banknotes).computation();
        System.out.println(sum + "; купюры: " + Arrays.toString(banknotes));
        Assert.assertEquals(new ExchangeOptions(), actual);
    }

    @Test
    public void testCommonValues() {
        int sum = 27;
        Integer[] banknotes = {3, 9, 2};
        ExchangeOptions expected = new CreateTest(sum, banknotes).create();
        ExchangeOptions actual = new AllCombinations(sum, banknotes).computation();
        System.out.println(sum + "; купюры: " + Arrays.toString(banknotes));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRandomValues() {
        ExchangeOptions expected;
        ExchangeOptions actual;
        int sum;
        int numberOfBanknotes;
        Integer[] banknotes;
        for (int i = 0; i < 10; i++) {
            sum = (int) (Math.random() * 100 + 10);
            numberOfBanknotes = (int) (Math.random() * 5 + 1);
            banknotes = new Integer[numberOfBanknotes];
            for (int j = 0; j < numberOfBanknotes; j++) {
                banknotes[j] = (int) (Math.random() * 10 + 1);
            }
            expected = new CreateTest(sum, banknotes).create();
            actual = new AllCombinations(sum, banknotes).computation();
            System.out.println(sum + "; купюры: " + Arrays.toString(banknotes));
            Assert.assertEquals(expected, actual);

        }
    }
}