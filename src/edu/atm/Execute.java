package edu.atm;

import java.util.Arrays;
import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {
        try {
            System.out.println("Введите 2 натуральных числа: сумму для размена и количество разменных купюр.");
            int sum;
            Scanner input = new Scanner(System.in);
            try {
                sum = input.nextInt();
            } catch (Exception e) {
                throw new Exception("Ошибка ввода: Сумма должна быть натуральной и меньшей 2 миллиардов.");
            }

            int numberOfBanknotes = input.nextInt();
            Integer[] banknotes = new Integer[numberOfBanknotes];
            System.out.println("Введите ценность купюр.");
            for (int i = 0; i < numberOfBanknotes; i++) {
                banknotes[i] = input.nextInt();
                if (banknotes[i] < 0) {
                    throw new Exception("Ошибка ввода: ценность купюр должна быть натуральной.");
                }
            }
            Arrays.sort(banknotes);
            if (sum < 1) {
                throw new Exception("Ошибка ввода: сумма должна быть натуральной.");
            }
            System.out.println(new AllCombinations(sum, banknotes).computation().toString());
        } catch (Exception e) {
            if (e.getMessage() == null) {
                System.out.println("Ошибка ввода.");
            } else {
                System.out.println(e.getMessage());
            }
        }
    }
}
