package edu.atm;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class mainConverteTest {

    @org.junit.Test
    public void main() {
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
        sum = 100;
        numberOfBanknotes = 3;
        banknotes = new Integer[numberOfBanknotes];
        for (int j = 0; j < numberOfBanknotes; j++) {
            banknotes[j] = 1;
        }
        expected = new CreateTest(sum, banknotes).create();
        actual = new AllCombinations(sum, banknotes).computation();
        System.out.println(sum + "; купюры: " + Arrays.toString(banknotes));
        Assert.assertEquals(expected, actual);

        sum = 100;
        numberOfBanknotes = 3;
        banknotes = new Integer[numberOfBanknotes];
        for (int j = 0; j < numberOfBanknotes; j++) {
            banknotes[j] = j * 70;
        }
        expected = new CreateTest(sum, banknotes).create();
        actual = new AllCombinations(sum, banknotes).computation();
        System.out.println(sum + "; купюры: " + Arrays.toString(banknotes));
        Assert.assertEquals(expected, actual);
    }
}