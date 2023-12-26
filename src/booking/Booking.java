package booking;

import book.Book;
import subscription.Subscription;

import java.util.Calendar;
import java.util.Date;

public class Booking {
    private static int IDCounter = 1;
    private final int ID;
    private final Subscription subscription;
    private final Book book;
    private final Date start;
    private Date end;

    public Booking(Book book, Subscription subscription, Date date) {
        this.ID = IDCounter++;
        this.book = book;
        this.subscription = subscription;
        this.start = date;
    }

    public int getID() {
        return ID;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public Book getBook() {
        return book;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date date) {
        end = date;
    }

    public boolean isExpired() {
        Date currentDate = Calendar.getInstance().getTime();
        int numberOfDays = subscription.getMaxBorrowLimit();
        Date expirationDate = utils.DateHelper.addDays(start, numberOfDays);
        return currentDate.after(expirationDate);
    }
}
