package ru.ilnik.garage.service;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String[] testStrings = {
                /* Following are valid phone number examples */
                "77(123)4567890", "1234567890", "+8-123-456-7890", "(123)456-7890",
                /* Following are invalid phone numbers */
                "(1234567890)","123)4567890", "12345678901", "(1)234567890",
                "(123)-4567890", "1", "12-3456-7890", "123-4567", "Hello world"};

        Pattern p = Pattern.compile("^((\\+7|7|8)?)+(?:\\(\\d{3}\\)|\\d{3}[-]*)\\d{3}[-]*\\d{4}");
//        Pattern p = Pattern.compile("^\\\\s?((\\\\+[1-9]{1,4}[ \\\\-]*)|(\\\\([0-9]{2,3}\\\\)[ \\\\-]*)|([0-9]{2,4})[ \\\\-]*)*?[0-9]{3,4}?[ \\\\-]*[0-9]{3,4}?\\\\s?");
        for (String str : testStrings) {
            if (p.matcher(str).matches()) {
                System.out.printf("%s is valid%n", str);
            } else {
                System.out.printf("%s is not valid%n", str);
            }
        }
    }
}
