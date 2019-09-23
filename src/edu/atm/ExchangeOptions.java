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

class Combination {
    TreeMap<Integer, Integer> combination;

    Combination(TreeMap<Integer, Integer> combination) {
        this.combination = (TreeMap<Integer, Integer>) combination.clone();
    }

    Combination(Integer nominal, Integer number) {
        combination = new TreeMap<>(Comparator.reverseOrder());
        combination.put(nominal, number);
    }

    Combination(Integer[] nominal) {
        combination = new TreeMap<>(Comparator.reverseOrder());
        Set<Integer> set = new HashSet<>(Arrays.asList(nominal));
        for (Integer banknotes : set) {
            combination.put(banknotes, 1);
        }
    }

    Combination(Integer[] nominal, Integer value) {
        combination = new TreeMap<>(Comparator.reverseOrder());
        Set<Integer> set = new HashSet<>(Arrays.asList(nominal));
        for (Integer banknotes : set) {
            combination.put(banknotes, value);
        }
    }

    void put(Integer banknote) {
        if (combination.containsKey(banknote)) {
            combination.replace(banknote, combination.get(banknote) + 1);
        } else {
            combination.put(banknote, 1);
        }
    }

    Combination cleanUp() {
        Combination result = this.copy();
        combination.forEach((Integer nominal, Integer value) -> {
            if (value < 1) {
                result.combination.remove(nominal);
            }
        });
        return result;
    }

    void take(Integer banknote) {
        if (combination.containsKey(banknote)) {
            if (combination.get(banknote) >= 1) {
                combination.replace(banknote, combination.get(banknote) - 1);
            }
        }
    }

    Integer get(Integer banknote) {
        return combination.getOrDefault(banknote, 0);
    }

    Combination copy() {
        return new Combination((TreeMap<Integer, Integer>) combination.clone());
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Integer t : combination.keySet()) {
            for (int i = 0; i < combination.get(t); i++) {
                res.append(t).append(" ");
            }
        }
        return (res.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Combination)) {
            return (false);
        }
        return (this.combination.equals(((Combination) o).combination));
    }
}