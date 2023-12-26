package book;

import java.util.Map;

public class Book {
    private final String ISBN;
    private final String title;
    private final Category category;
    private final Author author;
    private final int publicationYear;
    private final int pageCount;

    public Book(String title, Category category, String author, int publicationYear, int pageCount) {
        this.ISBN = utils.ISBNHelper.generateISBN();
        this.title = title;
        this.category = category;
        this.author = Author.getAuthor(author);
        this.publicationYear = publicationYear;
        this.pageCount = pageCount;
    }

    public Book(String title, String category, String author, int publicationYear, int pageCount) {
        this.ISBN = utils.ISBNHelper.generateISBN();
        this.title = title;
        this.category = Category.findCategory(category);
        this.author = Author.getAuthor(author);
        this.publicationYear = publicationYear;
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getISBN() {
        return ISBN;
    }

    public void displayInformation() {
        System.out.printf("%s | ISBN: %s | Category: %s | Author: %s | Pages: %d | publication: %d\n", getTitle(), getISBN(), getCategory().getName(), getAuthor().getName(), getPageCount(), getPublicationYear());
    }

    public static Book createInstance(Map<String, String> data) {
        return new Book(data.get("title"), data.get("category"), data.get("author"),
                Integer.parseInt(data.get("publicationYear")), Integer.parseInt(data.get("pageCount")));
    }
}