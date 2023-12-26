package utils;

import java.util.Random;

public class ISBNHelper {
    public static String generateISBN() {
        String prefix = "978";
        StringBuilder digits = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            digits.append(random.nextInt(10));
        }
        int checkDigit = calculateCheckDigit(prefix + digits.toString());
        return prefix + digits.toString() + checkDigit;
    }

    private static int calculateCheckDigit(String isbn) {
        int weightedSum = 0;
        for (int i = 0; i < isbn.length(); i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            weightedSum += digit * (i % 2 == 0 ? 1 : 3);
        }
        int checkDigit = 10 - (weightedSum % 10);
        return checkDigit == 10 ? 0 : checkDigit;
    }

    public static boolean isValidISBN(String isbn) {
        if (isbn.length() != 13) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            char digit = isbn.charAt(i);
            if (!Character.isDigit(digit)) {
                return false;
            }
            sum += (i % 2 == 0) ? Character.getNumericValue(digit) : 3 * Character.getNumericValue(digit);
        }

        int checkDigit = Character.getNumericValue(isbn.charAt(12));

        return (sum + checkDigit) % 10 == 0;
    }
}
