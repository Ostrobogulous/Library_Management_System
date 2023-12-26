package subscription;

public enum Tier {
    PREMIUM("Premium", "Highly privileged tier, 20 books can be borrowed at a time, return duration is 50 days for each book.", 20, 50, 179.99),
    NORMAL("Normal", "Standard tier, 5 books can be borrowed at a time, return duration is 28 days.", 5, 28, 129.99),
    BASIC("Basic", "Basic tier, 3 books can be borrowed at a time, return duration is 14 days.", 3, 14, 109.99);

    private final String name;
    private final String description;
    private final int maxBorrowLimit; // Maximum number of books that can be borrowed at a time
    private final int returnDuration;  // Duration for returning books in days
    private final double price; // Price for the tier

    Tier(String name, String description, int maxBorrowLimit, int returnDuration, double price) {
        this.name = name;
        this.description = description;
        this.maxBorrowLimit = maxBorrowLimit;
        this.returnDuration = returnDuration;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxBorrowLimit() {
        return maxBorrowLimit;
    }

    public int getReturnDuration() {
        return returnDuration;
    }

    public double getPrice() {
        return price;
    }
}
