import book.Book;
import book.BookItem;
import book.Category;
import booking.Booking;
import booking.BookingHistory;
import subscription.Subscription;
import subscription.SubscriptionManagement;
import subscription.Tier;
import user.Admin;
import utils.InputHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Library {

    private String name;
    private Map<String, BookItem> books;
    private BookingHistory bookingHistory;
    private SubscriptionManagement subscriptionManagement;
    private List<Admin> admins;
    private List<AuthorTalk> authorTalks;

    public Library(String name) {
        this.name = name;
        books = new HashMap<>();
        admins = new ArrayList<>();
        authorTalks = new ArrayList<>();
        this.bookingHistory = new BookingHistory();
        this.subscriptionManagement = new SubscriptionManagement();
    }

    public String getName() {
        return name;
    }

    public BookItem getBookItem(String ISBN) {
        return books.get(ISBN);
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        for (BookItem bookItem : this.books.values()) {
            books.add(bookItem.getBook());
        }
        return books;
    }

    public void addBook(Book book, int quantity) {
        BookItem item;
        if (books.containsKey(book.getISBN())) {
            item = books.get(book.getISBN());
            item.addQuantity(quantity);
        } else {
            item = new BookItem(book, quantity);
        }
        books.put(book.getISBN(), item);
    }

    public void addBook(Book book) {
        if (!books.containsKey(book.getISBN())) {
            BookItem item = new BookItem(book, 0);
            books.put(book.getISBN(), item);
        }
    }

    public void removeBook(Book book, int quantity) {
        BookItem bookItem = books.get(book.getISBN());
        if (bookItem != null) {
            bookItem.removeQuantity(quantity);
        }
    }

    public void removeBook(Book book) {
        books.remove(book.getISBN());
    }

    public Book findBook(String string) {
        if (utils.ISBNHelper.isValidISBN(string)) {
            BookItem bookItem = books.get(string);
            if (bookItem != null) {
                return bookItem.getBook();
            }
            return null;
        }
        List<Book> foundBooks = new ArrayList<>();
        for (BookItem bookItem : books.values()) {
            if (bookItem.getBook().getTitle().equalsIgnoreCase(string)) {
                foundBooks.add(bookItem.getBook());
            }
        }
        if (foundBooks.size() == 1) {
            return foundBooks.get(0);
        } else if (foundBooks.size() > 1) {
            int x = InputHelper.handleMultipleBooks(foundBooks);
            return foundBooks.get(Math.min(x, foundBooks.size() - 1));
        }
        return null;
    }

    public Subscription findSubscriptionById(String subscriptionId) {
        return subscriptionManagement.getSubscriptionByID(subscriptionId);
    }

    public void addSubscription(String userName, String email, Tier tier) {
        if (subscriptionManagement.findEmail(email)) {
            System.out.println("This email is already registered.");
            return;
        }
        Subscription subscription = subscriptionManagement.createSubscription(userName, email, tier);
        System.out.println("Subscription created successfully!");
        System.out.println("Customer: " + subscription.getCustomerName());
        System.out.println("Subscription Tier: " + subscription.getTier().getName());
        System.out.println("Maximum borrow limit: " + subscription.getMaxBorrowLimit());
        System.out.println("Subscription ID: " + subscription.getSubscriptionID());
        System.out.println("Subscription expiry date: " + utils.DateHelper.formatDate(subscription.getExpiryDate()));
    }

    public void removeSubscription(String subscriptionID) {
        Subscription subscription = findSubscriptionById(subscriptionID);
        if (subscription == null) {
            System.out.println("There is no such subscription with this ID.");
            return;
        }
        subscriptionManagement.cancelSubscription(subscription);
        System.out.println("Subscription cancelled!");
    }

    public void borrowBook(String subscriptionID, String string) {
        Subscription subscription = findSubscriptionById(subscriptionID);
        if (subscription == null) {
            System.out.println("There is no such subscription with this ID.");
            return;
        }
        if (subscription.isExpired()) {
            System.out.println("Your subscription is expired!");
            return;
        }
        if (subscription.borrowedBooksCount() >= subscription.getMaxBorrowLimit()) {
            System.out.println("Maximum borrow limit reached!");
            if (subscription.getTier() != Tier.PREMIUM) {
                System.out.println("Upgrade your tier to be able to borrow more books.");
            }
            return;
        }

        Book book = findBook(string);
        if (book != null) {
            if (subscription.checkBook(book) && subscription.getTier() != Tier.PREMIUM) {
                System.out.println("Sorry, you cannot borrow the same book more than once simultaneously, you need premium subscription to be able to do that.");
                return;
            }
            BookItem bookItem = books.get(book.getISBN());
            if (bookItem.getAvailableQuantity() > 0) {
                bookItem.decrementAvailability();
                Booking booking = bookingHistory.addBooking(book, subscription);
                subscriptionManagement.borrowBook(subscription, booking);
                System.out.printf("Book '%s' has been borrowed by '%s'.\n", book.getTitle(), subscription.getCustomerName());
                System.out.println("Booking ID: " + booking.getID());
            } else {
                System.out.printf("Sorry, the book '%s' is not available for borrowing.\n", book.getTitle());
            }
        } else {
            System.out.printf("Book with title or ISBN '%s' is not found in the library.\n", string);
        }
    }

    public void returnBook(String subscriptionID, int bookingID) {
        Subscription subscription = findSubscriptionById(subscriptionID);
        if (subscription == null) {
            System.out.println("There is no such subscription with this ID.");
            return;
        }
        if (bookingHistory.finishBooking(bookingID)) {
            if (bookingHistory.lateReturn(bookingID, subscription.getReturnDuration())) {
                System.out.println("Late return, fees must be paid");
            }
            Book book = bookingHistory.getBook(bookingID);
            BookItem bookItem = books.get(book.getISBN());
            bookItem.incrementAvailability();
            subscriptionManagement.returnBook(subscription, bookingID);
            System.out.println("Thanks for your borrow!");
        } else {
            System.out.println("Booking with such ID is not registered in our system.");
        }
    }

    public void showBorrowedBooks(String subscriptionID) {
        Subscription subscription = findSubscriptionById(subscriptionID);
        if (subscription == null) {
            System.out.println("There is no such subscription with this ID.");
            return;
        }
        List<Book> borrowedBooks = subscriptionManagement.getBorrowedBooks(subscription);
        if (borrowedBooks.isEmpty()) {
            System.out.println("Customer has no borrowed books at the moment!");
            return;
        }
        System.out.printf("Customer %s borrowed books:\n", subscription.getCustomerName());
        for (Book book : borrowedBooks) {
            book.displayInformation();
        }
    }

    public boolean authenticateAsAnAdmin(String email, String password) {
        for (Admin admin : admins) {
            if (admin.getEmail().equalsIgnoreCase(email) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void showAdmins() {
        System.out.printf("Admins in '%s' library:\n", getName());
        for (Admin admin : admins) {
            System.out.println(admin.getName());
        }
        System.out.println();
    }

    public void showAvailableBooks() {
        System.out.printf("Books in '%s' library: \n", name);
        for (BookItem bookItem : books.values()) {
            if (bookItem.getAvailableQuantity() > 0) {
                bookItem.getBook().displayInformation();
                System.out.println();
            }
        }
        System.out.println();
    }

    public void showAvailableBooks(String categoryName) {
        Category category = Category.findCategory(categoryName);
        System.out.println("Books with category: " + category.getName());
        for (BookItem bookItem : books.values()) {
            if (bookItem.getAvailableQuantity() > 0 && bookItem.getBook().getCategory() == category) {
                bookItem.getBook().displayInformation();
                System.out.println();
            }
        }
        System.out.println();
    }

    // this method shows books with their availability for admins
    public void showAvailableBooksWithAvailability() {
        System.out.printf("Books in '%s' library: \n", name);
        for (BookItem bookItem : books.values()) {
            bookItem.getBook().displayInformation();
            System.out.println("Availability: " + bookItem.getAvailableQuantity());
        }
        System.out.println();
    }

    public void addAuthorTalk(AuthorTalk authorTalk) {
        authorTalks.add(authorTalk);
        System.out.printf("Author talk '%s' added successfully!\n", authorTalk.getTalkTitle());
    }

    public void showUpcomingAuthorTalks() {
        if (authorTalks.isEmpty()) {
            System.out.println("No upcoming author talks at the moment.");
            return;
        }
        System.out.println("Upcoming Author Talks:");
        for (AuthorTalk authorTalk : authorTalks) {
            if (authorTalk.isUpcoming()) {
                authorTalk.displayInformation();
            }
        }
    }

    public void instantiateBooks(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String title = data[0].trim();
                String category = data[1].trim();
                String author = data[2].trim();
                int publishedYear = Integer.parseInt(data[3].trim());
                int pageCount = Integer.parseInt(data[4].trim());
                int quantity = Integer.parseInt(data[5].trim());
                Book book = new Book(title, category, author, publishedYear, pageCount);
                addBook(book, quantity);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void instantiateAdmins(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0].trim();
                String email = data[1].trim();
                String password = data[2].trim();
                Admin admin = new Admin(name, email, password);
                admins.add(admin);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

}
