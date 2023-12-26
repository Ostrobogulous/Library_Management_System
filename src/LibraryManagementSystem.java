import book.Book;
import subscription.BookingReceipt;
import subscription.Subscription;
import subscription.Tier;
import utils.DateHelper;
import utils.InputHelper;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;


public class LibraryManagementSystem {
    private static final Logger logger = Logger.getLogger(LibraryManagementSystem.class.getName());
    private final Library library;

    LibraryManagementSystem(Library library) {
        this.library = library;
        library.instantiateBooks("data/books.csv");
        logger.info("Books instantiated successfully.");
        library.instantiateAdmins("data/admins.csv");
        logger.info("Admins instantiated successfully.");
        System.out.println();
    }

    public static void main(String[] args) {
        Library library = new Library("FTW");
        LibraryManagementSystem lms = new LibraryManagementSystem(library);
        lms.run();
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void subscribe() {
        String[] userInput = InputHelper.userInput();
        Tier tier = InputHelper.tierInput();
        library.addSubscription(userInput[0], userInput[1], tier);
    }

    public void unsubscribe() {
        String subscriptionID = InputHelper.subscriptionIDInput();
        library.removeSubscription(subscriptionID);
    }

    public void viewSubscriptionDetails() {
        String subscriptionID = InputHelper.subscriptionIDInput();
        Subscription subscription = library.findSubscriptionById(subscriptionID);
        if (subscription != null) {
            subscription.viewSubscriptionDetails();
        } else {
            System.out.println("There is no such subscription with this ID.");
        }
    }

    public void borrowBook() {
        String subscriptionID = InputHelper.subscriptionIDInput();
        String bookInput = InputHelper.bookInput();
        library.borrowBook(subscriptionID, bookInput);
    }

    public void returnBook() {
        String subscriptionID = InputHelper.subscriptionIDInput();
        Subscription subscription = library.findSubscriptionById(subscriptionID);
        if (subscription != null) {
            Map<Integer, BookingReceipt> bookingReceipts = subscription.getBookingReceipts();
            if (bookingReceipts.isEmpty()) {
                System.out.println("You have no bookings at the moment.");
                return;
            }
            if (!bookingReceipts.isEmpty()) {
                for (BookingReceipt bookingReceipt : bookingReceipts.values()) {
                    System.out.printf("Booking ID: %d | Book title: %s\n", bookingReceipt.getBookingID(), bookingReceipt.getBook().getTitle());
                }
            }
            int bookingID = InputHelper.bookingIDInput();
            if (bookingReceipts.containsKey(bookingID)) {
                library.returnBook(subscriptionID, bookingID);
            } else {
                System.out.println("You have no such booking with this ID.");
            }
        } else {
            System.out.println("There is no such subscription with this ID.");
        }
    }

    public void viewAvailableBooks() {
        library.showAvailableBooks();
    }

    public void viewAvailableBooksByCategory() {
        String category = InputHelper.categoryNameInput();
        library.showAvailableBooks(category);
    }

    public void viewBorrowedBooks() {
        String subscriptionID = InputHelper.subscriptionIDInput();
        library.showBorrowedBooks(subscriptionID);
    }

    public void viewUpcomingAuthorTalks() {
        library.showUpcomingAuthorTalks();
    }

    public void goBack() {
        // empty block
    }

    public void addBook() {
        Book book = new Book();
        int quantity = InputHelper.quantityInput();
        library.addBook(book, quantity);
        System.out.printf("%d copies of book '%s' are added to the library.\n", quantity, book.getTitle());
    }

    public void addBookQuantity() {
        String bookTitle = InputHelper.bookTitleInput();
        Book book = library.findBook(bookTitle);
        if (book != null) {
            int quantity = InputHelper.quantityInput();
            library.addBook(book, quantity);
            System.out.printf("%d copies of book '%s' are added to the library.\n", quantity, bookTitle);
        } else {
            System.out.printf("Book with title '%s' doesn't exist in the library.", bookTitle);
        }
    }

    public void removeBook() {
        String bookTitle = InputHelper.bookTitleInput();
        Book book = library.findBook(bookTitle);
        if (book != null) {
            System.out.println("If you want to remove all copies enter -1.");
            int quantity = InputHelper.quantityInput();
            if (quantity == -1) {
                library.removeBook(book);
                System.out.printf("All copies of book '%s' are removed from the library.\n", bookTitle);
            } else {
                library.removeBook(book, quantity);
                System.out.printf("%d copies of book '%s' are removed from the library.\n", quantity, bookTitle);
            }
        } else {
            System.out.printf("Book with title '%s' doesn't exist in the library.", bookTitle);
        }
    }

    public void addAuthorTalk() {
        Map<String, String> authorTalkMap = InputHelper.authorTalkInput();
        Date startDate;
        try {
            startDate = DateHelper.parseStringToDate(authorTalkMap.get("startTime"));
        } catch (ParseException e) {
            System.err.println("Error parsing the date.");
            return;
        }
        AuthorTalk authorTalk = new AuthorTalk(authorTalkMap.get("title"), authorTalkMap.get("author"), authorTalkMap.get("topic"), startDate, Integer.parseInt(authorTalkMap.get("period")));
        library.addAuthorTalk(authorTalk);
    }

    public void viewBooks() {
        library.showAvailableBooksWithAvailability();
    }

    public void logout() {
        // empty block
    }

    public void modifyAuthorTalk() {
        System.out.println("This feature is not available yet.");
    }


    private void runCustomerMenu() {
        while (true) {
            sleep();
            System.out.println("===================================================");
            for (CustomerAction action : CustomerAction.values()) {
                System.out.println(action.ordinal() + 1 + "| " + action.getAction());
            }
            int choice = InputHelper.choiceInput();
            if (CustomerAction.isValidAction(choice)) {
                CustomerAction selectedAction = CustomerAction.values()[choice - 1];
                if (selectedAction == CustomerAction.GO_BACK) {
                    break;
                }
                selectedAction.getAssociatedFunction().accept(this);
            }
        }
    }

    private void runAdminMenu() {
        String[] adminInput = InputHelper.adminInput();
        if (library.authenticateAsAnAdmin(adminInput[0], adminInput[1])) {
            while (true) {
                sleep();
                System.out.println("===================================================");
                ;
                for (AdminAction action : AdminAction.values()) {
                    System.out.println(action.ordinal() + 1 + "| " + action.getAction());
                }
                int choice = InputHelper.choiceInput();
                if (AdminAction.isValidAction(choice)) {
                    AdminAction selectedAction = AdminAction.values()[choice - 1];
                    if (selectedAction == AdminAction.LOGOUT) {
                        break;
                    }
                    selectedAction.getAssociatedFunction().accept(this);
                }
            }
        } else {
            System.out.println("Wrong coordinates!");
        }
    }

    private void run() {
        System.out.println("Welcome to the " + library.getName() + " library management system!");
        while (true) {
            sleep();
            System.out.println("Choose an option:");
            System.out.print("1| Customer Menu \n2| Admin menu\n3| Exit\n");
            int choice = InputHelper.choiceInput();
            if (choice == 1) {
                runCustomerMenu();
            } else if (choice == 2) {
                runAdminMenu();
            } else if (choice == 3) {
                System.out.println("Exiting the library management system.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
