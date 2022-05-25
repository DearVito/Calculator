package org.example;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(calc("V - I"));
        } catch (Exception e) {
            System.out.println("sad");
        }


    }
    static boolean isInputCorrect(String str){
        try {
            if (str == null || str.length() < 5) {
                throw new Exception();
            }
            String[] strings = str.split(" ");
            Pattern patternRomanNumber = Pattern.compile("^(I[IVX]{0,1}I{0,1})$|^(VI{0,1}I{0,1}I{0,1})$|^X$");
            Pattern patternMathOperators = Pattern.compile("^[*+/-]$");
            Pattern patternArabicNumber = Pattern.compile("^[1-9]$|^10$");
            Matcher matcherRomanNumberFirst = patternRomanNumber.matcher(strings[0]);
            Matcher matcherRomanNumberSecond = patternRomanNumber.matcher(strings[2]);
            Matcher matcherMathOperators = patternMathOperators.matcher(strings[1]);
            Matcher matcherArabicNumberFirst = patternArabicNumber.matcher(strings[0]);
            Matcher matcherArabicNumberSecond = patternArabicNumber.matcher(strings[2]);
            return matcherMathOperators.matches() &&
                    ( matcherRomanNumberFirst.matches() && matcherRomanNumberSecond.matches() ||
                            matcherArabicNumberFirst.matches() && matcherArabicNumberSecond.matches() );
        } catch (Exception e) {
            return false;
        }

    }

    public static String calc(String input) throws Exception{
        boolean correctInput = isInputCorrect(input);
        if (!correctInput) throw new Exception();
        String[] operators = input.split(" ");
        Pattern arabicPattern = Pattern.compile("[1-9]");
        Matcher arabicMatcher = arabicPattern.matcher(operators[0]);
        int a = 0, b = 0, result = 0;
        boolean isNumbersArabic = false;
        if (arabicMatcher.matches()) {
            isNumbersArabic = true;
            a = Integer.parseInt(operators[0]);
            b = Integer.parseInt(operators[2]);
        } else if (!arabicMatcher.matches()) {

            a = RomanNumber.valueOf(operators[0]).arabicNumber;
            b = RomanNumber.valueOf(operators[2]).arabicNumber;
        }
        switch (operators[1]) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = a / b;
        }
        System.out.println(isNumbersArabic);
        if (isNumbersArabic) {
            return "" + result;
        } else {
            return fromArabicToRoman(result);
        }

    }

    static String fromArabicToRoman(int n) throws Exception{
        if (n < 0) {
            throw new Exception();
        }
        int[] arabicRepresentation = {100,90,50,40,10,9,5,4,1};
        String[] romanRepresentation = {"C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(n > 0 && i < romanRepresentation.length) {
            if (arabicRepresentation[i] <= n) {
                sb.append(romanRepresentation[i]);
                n -= arabicRepresentation[i];
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}