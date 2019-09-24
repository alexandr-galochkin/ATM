package edu.atm;

import java.util.*;

class AllCombinations {
    private int sum;
    private int numberOfBanknotes;
    private Integer[] banknotes;

    AllCombinations(int sum, Integer[] banknotes) {

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

    ExchangeOptions computation() {
        try {
            HashMap<Integer, ExchangeOptions> allSum = new HashMap<>(sum / banknotes[0]);
            for (int i = 0; i < numberOfBanknotes; i++) {
                allSum.put(banknotes[i], new ExchangeOptions(new Combination(banknotes[i], 1)));
            }
            for (int i = banknotes[0]; i < sum - banknotes[0] + 1; i++) {
                if (allSum.containsKey(i)) {
                    for (int j = 0; j < numberOfBanknotes; j++) {
                        Integer currentBank = banknotes[j];
                        if ((i + currentBank <= sum - banknotes[0] + 1) || (i + currentBank == sum)) {
                            ExchangeOptions currentSum = addNewSum(allSum, i + currentBank);
                            allSum.get(i).addOldCombinations(currentBank, currentSum);
                        }
                    }
                }
                allSum.remove(i);
            }
            if (allSum.get(sum) == null) {
                return new ExchangeOptions();
            }
            return allSum.get(sum);
        } catch (Exception e) {
            return new ExchangeOptions();
        }
    }

    private static ExchangeOptions addNewSum(HashMap<Integer, ExchangeOptions> allSum, Integer index){
        ExchangeOptions result;
        if (allSum.containsKey(index)) {
            result = allSum.get(index);
        } else {
            result = new ExchangeOptions();
            allSum.put(index, result);
        }
        return result;
    }
}
