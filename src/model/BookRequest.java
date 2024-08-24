package model;

import java.util.List;

public class BookRequest {
    private int bookRequestID;
    private List<String> bookCode;
    private String cardID;
    private String status;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private Double penaltyFee;
    private String note;

    public BookRequest() {
    }

    public BookRequest(int bookRequestID, List<String> bookCode, String cardID, String status, String borrowDate, String dueDate, String returnDate, Double penaltyFee, String note) {
        this.bookRequestID = bookRequestID;
        this.bookCode = bookCode;
        this.cardID = cardID;
        this.status = status;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.penaltyFee = penaltyFee;
        this.note = note;
    }

    public int getBookRequestID() {
        return bookRequestID;
    }

    public void setBookRequestID(int bookRequestID) {
        this.bookRequestID = bookRequestID;
    }

    public List<String> getBookCode() {
        return bookCode;
    }

    public void setBookCode(List<String> bookCode) {
        this.bookCode = bookCode;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Double getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Double penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
