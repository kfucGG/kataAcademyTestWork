package org.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static Calculating sum;
    public static Calculating dif;
    public static Calculating div;
    public static Calculating multi;

    public Main(){
        this.sum = (a , b) -> a + b;
        this.dif = (a , b) -> a - b;
        this.div = (a , b) -> a / b;
        this.multi = (a , b) -> a * b;
    }

    public static String calc(String userInput){
        ArithmeticOperation arithmeticOperation = getOperation(userInput);
        int[] numbers = getUserNumbers(userInput,arithmeticOperation);

        switch(arithmeticOperation){
            case DIVISION:
                return String.valueOf(div.operation(numbers[0], numbers[1]));
            case MULTIPLICATION:
                return String.valueOf(multi.operation(numbers[0], numbers[1]));
            case SUM:
                return String.valueOf(sum.operation(numbers[0], numbers[1]));
            case DIFFERENCE:
                return String.valueOf(dif.operation(numbers[0], numbers[1]));
        }

        return null;
    }

    public static ArithmeticOperation getOperation(String userInput){
        if(userInput.contains("+")) return ArithmeticOperation.SUM;
        if(userInput.contains("-")) return ArithmeticOperation.DIFFERENCE;
        if(userInput.contains("*")) return ArithmeticOperation.MULTIPLICATION;
        if(userInput.contains("/")) return ArithmeticOperation.DIVISION;

        return null;
    }

    public static int[] getUserNumbers(String userInput, ArithmeticOperation arithmeticOperation){
        userInput = userInput.replaceAll("\\s+", "");
        int[] numbers = new int[2];

        if(arithmeticOperation == ArithmeticOperation.DIFFERENCE){
            userInput = userInput.replaceAll("-", " ");
            String[] userNumbers = userInput.split(" ");
            numbers[0] = Integer.valueOf(userNumbers[0]);
            numbers[1] = Integer.valueOf(userNumbers[1]);
            return numbers;
        }else if(arithmeticOperation == ArithmeticOperation.MULTIPLICATION){
            userInput = userInput.replaceAll("\\*", " ");
            String[] userNumbers = userInput.split(" ");
            numbers[0] = Integer.valueOf(userNumbers[0]);
            numbers[1] = Integer.valueOf(userNumbers[1]);
            return numbers;
        }else if (arithmeticOperation == ArithmeticOperation.DIVISION){
            userInput = userInput.replaceAll("/", " ");
            String[] userNumbers = userInput.split(" ");
            numbers[0] = Integer.valueOf(userNumbers[0]);
            numbers[1] = Integer.valueOf(userNumbers[1]);
            return numbers;
        }else if(arithmeticOperation  == ArithmeticOperation.SUM){
            userInput = userInput.replaceAll("\\+", " ");
            String[] userNumbers = userInput.split(" ");
            numbers[0] = Integer.valueOf(userNumbers[0]);
            numbers[1] = Integer.valueOf(userNumbers[1]);
            return numbers;
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
