package utils;

import book.Book;
import subscription.Tier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputHelper {
    public static Tier tierInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Subscription Tiers:");
        for (Tier tier : Tier.values()) {
            System.out.println(tier.getName() + ": " + tier.getDescription() + " - $" + tier.getPrice());
        }
        Tier selectedTier = null;
        while (true) {
            System.out.print("Enter subscription tier: ");
            String tierName = scanner.nextLine();
            for (Tier tier : Tier.values()) {
                if (tier.getName().equalsIgnoreCase(tierName)) {
                    selectedTier = tier;
                    break;
                }
            }
            if (selectedTier != null) {
                return selectedTier;
            } else {
                System.out.println("Invalid subscription tier.");
            }
        }
    }

    public static String[] userInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        return new String[]{name, email};
    }

    public static String subscriptionIDInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter subscription ID: ");
        String subscriptionID = scanner.nextLine();
        return subscriptionID;
    }

    public static String[] adminInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        return new String[]{email, password};
    }

    public static String categoryNameInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter category: ");
        String categoryName = scanner.nextLine();
        return categoryName;
    }

    public static String bookTitleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();
        return bookTitle;
    }

    public static String bookInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title or ISBN: ");
        return scanner.nextLine();
    }

    public static int bookingIDInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter booking ID: ");
        int bookingID = scanner.nextInt();
        return bookingID;
    }

    public static Map<String, String> bookInfoInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Enter book author: ");
        String bookAuthor = scanner.nextLine();
        System.out.print("Enter book category: ");
        String bookCategory = scanner.nextLine();
        System.out.print("Enter book publication year: ");
        String bookPublicationYear = scanner.nextLine();
        System.out.print("Enter book page count: ");
        String bookPageCount = scanner.nextLine();
        Map<String, String> bookInfoMap = new HashMap<>();
        bookInfoMap.put("title", bookTitle);
        bookInfoMap.put("author", bookAuthor);
        bookInfoMap.put("category", bookCategory);
        bookInfoMap.put("publicationYear", bookPublicationYear);
        bookInfoMap.put("pageCount", bookPageCount);
        return bookInfoMap;
    }

    public static int quantityInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        return quantity;
    }

    public static int handleMultipleBooks(List<Book> foundBooks) {
        for (int i = 0; i < foundBooks.size(); i++) {
            System.out.printf("%d: ", i + 1);
            foundBooks.get(i).displayInformation();
        }
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the index of the book you want: ");
            int index = scanner.nextInt();
            if (index >= 1 && index <= foundBooks.size()) {
                return index - 1;
            }
            System.out.println("Invalid index. Please try again.");
        }
    }

    public static Map<String, String> authorTalkInput() {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> userInputMap = new HashMap<>();
        System.out.print("Enter Title: ");
        userInputMap.put("title", scanner.nextLine());
        System.out.print("Enter Author's Name: ");
        userInputMap.put("author", scanner.nextLine());
        System.out.print("Enter Topic: ");
        userInputMap.put("topic", scanner.nextLine());
        System.out.print("Enter Start Time (yyyy-MM-dd HH:mm): ");
        userInputMap.put("startTime", scanner.nextLine());
        System.out.print("Enter Period (number of minutes): ");
        userInputMap.put("period", scanner.nextLine());
        return userInputMap;
    }

    public static int choiceInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

}
