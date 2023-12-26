package book;

public class BookItem {
    Book book;
    int quantity;
    int availableQuantity;

    public BookItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void incrementAvailability() {
        availableQuantity++;
    }

    public void decrementAvailability() {
        availableQuantity--;
    }

    public void addQuantity(int value) {
        quantity += value;
        availableQuantity += value;
    }

    public void removeQuantity(int value) {
        quantity = Math.max(0, quantity - value);
        availableQuantity = Math.max(0, availableQuantity - value);
    }
}
