package org.example;

public class Main {
    public static void main(String[] args) {
        String str = "4.59 i vtyigcokb";
        String digits = str.replaceAll("[^0-9.]", "");
        System.out.println(Double.parseDouble(digits));

    }
}