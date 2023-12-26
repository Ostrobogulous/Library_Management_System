package book;

import utils.InputHelper;

import java.util.Map;

public class Book {
    private String ISBN;
    private String title;
    private Category category;
    private Author author;
    private int publicationYear;
    private int pageCount;

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

    public Book() {
        this.ISBN = utils.ISBNHelper.generateISBN();
        Map<String, String> bookInfoMap = InputHelper.bookInfoInput();
        this.title = bookInfoMap.get("title");
        this.category = Category.findCategory(bookInfoMap.get("category"));
        this.author = Author.getAuthor(bookInfoMap.get("author"));
        this.publicationYear = Integer.parseInt(bookInfoMap.get("publicationYear"));
        this.pageCount = Integer.parseInt(bookInfoMap.get("pageCount"));
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
}