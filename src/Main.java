import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static String calc(String input) {
        int result;
        String resultRoman;
        String expression = input.replace(" ", "");
        char[] operators = {'+', '-', '*', '/'};
        char[] charsExpression = input.toCharArray();
        char action = ' ';
        for (char ch: charsExpression) {
            if (action == ' ') {
                for (int i = 0; i < operators.length; i++) {
                    if (ch == operators[i]) {
                        action = operators[i];
                        break;
                    }
                }
            } else {
                break;
            }
        }
        String[] expArr = expression.split("\\" + action);
        if (expArr.length != 2) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new Error("Должно быть два операнда");
            }
        }
        if (expArr[0].matches("\\d+") && expArr[1].matches("\\d+")) {
            int a = parseInt(expArr[0]);
            int b = parseInt(expArr[1]);
            arabicRange(a, b);
            result = arithmeticOperation(a, b, action);
        } else {
            String[] romanUnits = {"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            String[] romanTens = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
            int firstRomanNum = 0;
            int secondRomanNum = 0;
            for (int i = 0; i < romanUnits.length; i++) {
                if (romanUnits[i].equals(expArr[0])) {
                    firstRomanNum = i;
                    break;
                }
            }
            for (int i = 0; i < romanUnits.length; i++) {
                if (romanUnits[i].equals(expArr[1])) {
                    secondRomanNum = i;
                    break;
                }
            }
            if (firstRomanNum == 0 || secondRomanNum == 0) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new Error("Операнды должны быть одинаковых типов: арабские или римские");
                }
            } else {
                arabicRange(firstRomanNum, secondRomanNum);
                result = arithmeticOperation(firstRomanNum, secondRomanNum, action);
            }
            if (result > 0 && result < 11) {
                resultRoman = romanUnits[result];

            } else if (result < 1) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new Error("Результат операции с римскими числами не может быть отрицательным или равным 0");
                }
            } else {
                    int firstResultNum = (result / 10) - 1;
                    int secondResultNum = result % 10;
                    resultRoman = romanTens[firstResultNum] + romanUnits[secondResultNum];
                }

            return resultRoman;
        }

        return Integer.toString(result);
    }


    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение: ");
        String inputExpression = console.nextLine();
        String output = calc(inputExpression);
        System.out.println("Выражение: " + inputExpression + " равно: " + output);
    }

    public static boolean arabicRange(int a, int b) {
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new Error("Числа должны быть в диапазоне от 1 до 10 или римские от I до X");
            }
        } else {
            return true;
        }
    }

    static int arithmeticOperation(int a, int b, char x) {
        switch (x) {
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '*' -> {
                return a * b;
            }
            case '/' -> {
                return a / b;
            }
            default -> {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new Error("Неверное выражение");
                }
            }
        }
    }
}