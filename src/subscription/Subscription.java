package subscription;

import book.Book;
import user.Customer;

import java.util.*;


public class Subscription {
    private String subscriptionID;
    private Tier tier;
    private Customer customer;
    private Map<Integer, BookingReceipt> bookingReceipts;
    private Date creationDate;
    private Date expiryDate;

    static String generateId() {
        UUID id = UUID.randomUUID();
        String idString = id.toString();
        return idString;
    }

    static Date getExpiryDate(Date creationDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(creationDate);
        c.add(Calendar.YEAR, 1);
        return c.getTime();
    }

    public Subscription(Customer customer, Tier tier) {
        this.subscriptionID = generateId();
        this.tier = tier;
        this.customer = customer;
        this.bookingReceipts = new HashMap<>();
        this.creationDate = new Date();
        this.expiryDate = getExpiryDate(creationDate);
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getCustomerName() {
        return customer.getName();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Map<Integer, BookingReceipt> getBookingReceipts() {
        return bookingReceipts;
    }

    public int getMaxBorrowLimit() {
        return tier.getMaxBorrowLimit();
    }

    public int getReturnDuration() {
        return tier.getReturnDuration();
    }

    public int borrowedBooksCount() {
        return bookingReceipts.size();
    }

    public boolean checkBook(Book book) {
        for (BookingReceipt receipt : bookingReceipts.values()) {
            if (receipt.getBook().equals(book)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExpired() {
        return new Date().after(expiryDate);
    }

    public void addBookingReceipt(BookingReceipt receipt) {
        bookingReceipts.put(receipt.getBookingID(), receipt);
    }

    public void removeBookingReceipt(int bookingID) {
        bookingReceipts.remove(bookingID);
    }

    public void viewSubscriptionDetails() {
        System.out.println("Subscription ID: " + getSubscriptionID());
        System.out.println("Tier: " + getTier().getName());
        System.out.println("Customer Name: " + getCustomerName());
        System.out.println("Customer Email: " + getCustomer().getEmail());
        System.out.println("Creation Date: " + utils.DateHelper.formatDate(getCreationDate()));
        System.out.println("Expiry Date: " + utils.DateHelper.formatDate(getExpiryDate()));
        if (isExpired()) {
            System.out.println("Status: Expired");
        } else {
            System.out.println("Status: Active");
        }
        System.out.println();
    }
}
