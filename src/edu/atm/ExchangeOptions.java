package edu.atm;

import java.util.*;

public class ExchangeOptions {
    private int number;
    private ArrayList<Combination> options;

    public ExchangeOptions(ArrayList<Combination> options) {
        this.options = new ArrayList<>();
        for (Combination combination : options) {
            this.options.add(combination.copy());
        }
        this.number = options.size();
    }

    public ExchangeOptions(Combination combination) {
        this.number = 1;
        this.options = new ArrayList<>();
        options.add(combination.copy());
    }

    public ExchangeOptions(Integer[] nominal) {
        options = new ArrayList<>();
        Set<Integer> set = new HashSet<>(Arrays.asList(nominal));
        for (Integer banknotes : set) {
            options.add(new Combination(banknotes, 1));
        }
        number = options.size();
    }

    public ExchangeOptions() {
        number = 0;
        options = null;
    }

    public ArrayList<Combination> getOptions() {
        return options;
    }

    public void add(Combination newCombination) {
        if (options == null) {
            options = new ArrayList<>();
            options.add(newCombination.copy());
            number = 1;
        } else if (!options.contains(newCombination)) {
            options.add(newCombination);
            number++;
        }
    }

    public void addOldCombinations(Integer currentBank, ExchangeOptions currentSum){
        this.getOptions().forEach(oldCombination -> {
            Combination currentCombination = oldCombination.copy();
            currentCombination.put(currentBank);
            currentSum.add(currentCombination);
        });
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ExchangeOptions)) {
            return (false);
        }
        ExchangeOptions tempO = (ExchangeOptions) o;
        if ((this.number == 0) && (tempO.number == 0)) {
            return (true);
        }
        if (this.number != (tempO).number) {
            return (false);
        }
        ArrayList<Combination> tempOptions = new ArrayList<>();
        for (int i = 0; i < (tempO).options.size(); i++) {
            tempOptions.add((tempO.options.get(i)).copy());
        }
        for (Combination tempCombination : this.options) {
            if (!tempOptions.contains(tempCombination)) {
                return (false);
            }
            tempOptions.remove(tempCombination);
        }
        return tempOptions.isEmpty();
    }

    @Override
    public String toString() {
        if (this.number == 0) {
            return "Подходящих комбинаций нет.";
        }
        StringBuilder res = new StringBuilder();
        res.append("Количество комбинаций: ").append(this.number).append("\n");
        this.options.forEach((tempCombination) -> {
            res.append(tempCombination.toString());
            res.append("\n");
        });
        return (res.toString());
    }
}