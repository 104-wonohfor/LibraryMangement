package model;

import java.util.List;

public class BookCopy extends Book {
    private String code;
    private String location;
    private String status;
    
    public BookCopy() {
    }

    public BookCopy(String code, String location, String status) {
        this.code = code;
        this.location = location;
        this.status = status;
    }

    public BookCopy(String bookTitle, String bookID, String bookCategory, 
                    List<String> authors, int yearPublished, String bookPublisher, 
                    String bookTopic, String bookDescription, String bookNotes, int bookQuantity,
                    String code, String location, String status) {
        super(bookTitle, bookID, bookCategory, authors, yearPublished, bookPublisher, bookTopic, bookDescription, bookNotes, bookQuantity);
        this.code = code;
        this.location = location;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
