package edu.atm;

import java.util.*;

class CreateTest {
    private int sum;
    private int numberOfBanknotes;
    private Integer[] banknotes;

    CreateTest(int sum, Integer[] banknotes) {
        ArrayList<Integer> check = new ArrayList<>(Arrays.asList(banknotes));
        Set<Integer> set = new HashSet<>(check);
        set.remove(0);
        check.clear();
        check.addAll(set);
        this.banknotes = new Integer[set.size()];
        this.banknotes = check.toArray(this.banknotes);
        this.sum = sum;
        this.numberOfBanknotes = this.banknotes.length;
    }

    private static void converte(Combination nominal, ExchangeOptions allCombinations, int sum, int currentSum, int current) {
        if ((nominal.combination.size() == 0) || (currentSum > sum)) {
            return;
        }
        if (currentSum == sum) {
            allCombinations.add(nominal.cleanUp());
            return;
        }
        Combination tempNominal = nominal.copy();
        int counter = 0;
        for (Integer tempBank : nominal.combination.keySet()) {
            if (counter >= current) {
                tempNominal.put(tempBank);
                currentSum += tempBank;
                converte(tempNominal, allCombinations, sum, currentSum, counter);
                tempNominal.take(tempBank);
                currentSum -= tempBank;
            }
            counter++;
        }
    }

    public ExchangeOptions create() {
        try {
            Combination nominal = new Combination(banknotes, 0);
            ExchangeOptions allCombinations = new ExchangeOptions();
            converte(nominal, allCombinations, sum, 0, 0);
            return allCombinations;
        } catch (Exception e) {
            System.out.println(e);
            return new ExchangeOptions();
        }
    }
}