package model;

import java.util.List;

public class Book {
    private String bookTitle;
    private String bookID;
    private String bookCategory;
    private List<String> authors;
    private int yearPublished;
    private String bookPublisher;
    private String bookTopic;
    private String bookDescription;
    private String bookNotes;
    private int bookQuantity;

    public Book() {
    }

    public Book(String bookTitle, String bookID, String bookCategory, 
                List<String> authors, int yearPublished, String bookPublisher, 
                String bookTopic, String bookDescription, String bookNotes, int bookQuantity) {
        this.bookTitle = bookTitle;
        this.bookID = bookID;
        this.bookCategory = bookCategory;
        this.authors = authors;
        this.yearPublished = yearPublished;
        this.bookPublisher = bookPublisher;
        this.bookTopic = bookTopic;
        this.bookDescription = bookDescription;
        this.bookNotes = bookNotes;
        this.bookQuantity = bookQuantity;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getBookTopic() {
        return bookTopic;
    }

    public void setBookTopic(String bookTopic) {
        this.bookTopic = bookTopic;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookNotes() {
        return bookNotes;
    }

    public void setBookNotes(String bookNotes) {
        this.bookNotes = bookNotes;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}
