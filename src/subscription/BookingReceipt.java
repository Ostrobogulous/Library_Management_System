package subscription;

import book.Book;

import java.util.Date;

public class BookingReceipt {
    private int bookingID;
    private Book book;
    private Date borrowDate;

    public BookingReceipt(int bookingID, Book book, Date borrowDate) {
        this.bookingID = bookingID;
        this.book = book;
        this.borrowDate = borrowDate;
    }

    public void printReceipt() {
        System.out.println("Booking Receipt");
        System.out.println("Booking ID: " + this.bookingID);
        System.out.println("Book: " + this.book.getTitle());
        System.out.println("Borrow Date: " + this.borrowDate);
    }

    public int getBookingID() {
        return this.bookingID;
    }


    public Book getBook() {
        return this.book;
    }

    public Date getBorrowDate() {
        return this.borrowDate;
    }
}

