package org.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static Calculating sum;
    public static Calculating dif;
    public static Calculating div;
    public static Calculating multi;

    public static String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };
    public Main(){
        this.sum = (a , b) -> a + b;
        this.dif = (a , b) -> a - b;
        this.div = (a , b) -> a / b;
        this.multi = (a , b) -> a * b;
    }



    public static String romanCalculator(String[] numbers, ArithmeticOperation arithmeticOperation){
        int a = RomanCalculator.romanToNumber(numbers[0]);
        int b = RomanCalculator.romanToNumber(numbers[1]);

        if(a > 10 || b > 10) throw new ArithmeticException("Can not be bigger than 10");
        switch(arithmeticOperation){
            case DIVISION:
                return RomanCalculator.convertNumToRoman(a / b);
            case SUM:
                return RomanCalculator.convertNumToRoman(a + b);
            case DIFFERENCE:
                int difference = a - b;
                if(difference < 0) throw new ArithmeticException("Roman result can not be smaller than 0");
                return RomanCalculator.convertNumToRoman(a - b);
            case MULTIPLICATION:
                return RomanCalculator.convertNumToRoman(a * b);
        }

        return null;
    }

    public static void main(String[] args) {
        Main main = new Main();
        String result = calc(new Scanner(System.in).nextLine());
        System.out.println(result);
    }
    public static int isRoman(String numbers[]){
        int count = 0;
        for(int i = 0; i < numbers.length ; i++){
            for(int j = 0; j < roman.length; j++){
                if(numbers[i].contains(roman[j])) count++;
            }
        }
        return count;
    }
    public static String calc(String userInput){
        ArithmeticOperation arithmeticOperation = getOperation(userInput);
        String[] numbers = getUserNumbers(userInput,arithmeticOperation);
        if(numbers.length > 2) throw new ArithmeticException("More than 2 numbers");
        if(isRoman(numbers) == 2){
            return romanCalculator(numbers, arithmeticOperation);
        }else if(isRoman(numbers) == 1){
            throw new ArithmeticException("One of numbers is arabic, one is rome . Incorrect");
        }

        Arrays.stream(numbers).forEach(a -> {
            if(Integer.valueOf(a) > 10) throw new ArithmeticException("Can not be bigger than 10");
        });
        switch(arithmeticOperation){
            case DIVISION:
                return String.valueOf(div.operation(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1])));
            case MULTIPLICATION:
                return String.valueOf(multi.operation(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1])));
            case SUM:
                return String.valueOf(sum.operation(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1])));
            case DIFFERENCE:
                return String.valueOf(dif.operation(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1])));
        }

        return null;
    }

    public static ArithmeticOperation getOperation(String userInput){
        if(userInput.contains("+")) return ArithmeticOperation.SUM;
        if(userInput.contains("-")) return ArithmeticOperation.DIFFERENCE;
        if(userInput.contains("*")) return ArithmeticOperation.MULTIPLICATION;
        if(userInput.contains("/")) return ArithmeticOperation.DIVISION;
        else{
            throw new ArithmeticException("Not arithmetical operation");
        }
    }

    public static String[] getUserNumbers(String userInput, ArithmeticOperation arithmeticOperation){
        userInput = userInput.replaceAll("\\s+", "");
        int[] numbers = new int[2];

        if(arithmeticOperation == ArithmeticOperation.DIFFERENCE){
            userInput = userInput.replaceAll("-", " ");
            String[] userNumbers = userInput.split(" ");
            return userNumbers;
        }else if(arithmeticOperation == ArithmeticOperation.MULTIPLICATION){
            userInput = userInput.replaceAll("\\*", " ");
            String[] userNumbers = userInput.split(" ");
            return userNumbers;
        }else if (arithmeticOperation == ArithmeticOperation.DIVISION){
            userInput = userInput.replaceAll("/", " ");
            String[] userNumbers = userInput.split(" ");
            return userNumbers;
        }else if(arithmeticOperation  == ArithmeticOperation.SUM){
            userInput = userInput.replaceAll("\\+", " ");
            String[] userNumbers = userInput.split(" ");
            return userNumbers;
        }

        return null;
    }


}

enum ArithmeticOperation{
    SUM,
    DIVISION,
    MULTIPLICATION,
    DIFFERENCE
}
interface Calculating{

    int operation(int a , int b);
}

class RomanCalculator{


    public static String convertNumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }
        public static int romanToNumber (String roman) {
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }
}
