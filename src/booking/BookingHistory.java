package booking;

import book.Book;
import subscription.Subscription;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookingHistory {
    private Map<Integer, Booking> bookings;

    public BookingHistory() {
        this.bookings = new HashMap<>();
    }

    public Booking getBooking(int bookingId) {
        return bookings.get(bookingId);
    }

    public Booking addBooking(Book book, Subscription subscription) {
        Date date = Calendar.getInstance().getTime();
        Booking booking = new Booking(book, subscription, date);
        bookings.put(booking.getID(), booking);
        return booking;
    }

    public boolean finishBooking(int bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            Date endDate = Calendar.getInstance().getTime();
            booking.setEnd(endDate);
            return true;
        }
        return false;
    }

    public boolean lateReturn(int bookingId, int returnDuration) {
        Booking booking = bookings.get(bookingId);
        return booking.isExpired();
    }

    public Book getBook(int bookingId) {
        Booking booking = bookings.get(bookingId);
        return booking.getBook();
    }

    public void printCustomerBookingHistory(Subscription subscription) {
        for (Booking booking : bookings.values()) {
            if (booking.getSubscription().equals(subscription)) {
                System.out.printf("Book Title: '%s' | Borrow Date: '%s'", booking.getBook().getTitle(), booking.getStart());
                if (booking.getEnd() != null) {
                    System.out.printf(" | Return Date: '%s'\n", booking.getEnd());
                } else {
                    System.out.println(" | Not Returned Yet.");
                }
            }

        }
    }
}
