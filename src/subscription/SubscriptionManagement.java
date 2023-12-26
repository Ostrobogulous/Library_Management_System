package subscription;

import book.Book;
import booking.Booking;
import user.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SubscriptionManagement {
    Map<String, Subscription> subscriptions;

    public SubscriptionManagement() {
        this.subscriptions = new HashMap<>();
    }

    public Subscription createSubscription(String userName, String userEmail, Tier tier) {
        Customer customer = new Customer(userName, userEmail);
        Subscription subscription = new Subscription(customer, tier);
        subscriptions.put(subscription.getSubscriptionID(), subscription);
        return subscription;
    }

    public void cancelSubscription(Subscription subscription) {
        subscriptions.remove(subscription.getSubscriptionID());
    }

    public boolean findEmail(String email) {
        for (Subscription subscription : subscriptions.values()) {
            if (subscription.getCustomer().getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Subscription getSubscriptionByID(String subscriptionID) {
        return subscriptions.get(subscriptionID);
    }

    public List<Book> getBorrowedBooks(Subscription subscription) {
        List<Book> borrowedBooks = new ArrayList<>();
        if (subscription.borrowedBooksCount() > 0) {
            for (BookingReceipt receipt : subscription.getBookingReceipts().values()) {
                borrowedBooks.add(receipt.getBook());
            }
        }
        return borrowedBooks;
    }

    public void borrowBook(Subscription subscription, Booking booking) {
        BookingReceipt bookingReceipt = new BookingReceipt(booking.getID(), booking.getBook(), booking.getStart());
        subscription.addBookingReceipt(bookingReceipt);
    }

    public void returnBook(Subscription subscription, int bookingID) {
        subscription.removeBookingReceipt(bookingID);
    }
}
