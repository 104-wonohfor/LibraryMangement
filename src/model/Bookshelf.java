package model;

import java.util.List;


public class Bookshelf {
    private String readerCardID;
    private List<BookCopy> listBookCode;

    public Bookshelf(String readerCardID, List<BookCopy> listBookCode) {
        this.readerCardID = readerCardID;
        this.listBookCode = listBookCode;
    }

    public String getReaderCardID() {
        return readerCardID;
    }

    public void setReaderCardID(String readerCardID) {
        this.readerCardID = readerCardID;
    }

    public List<BookCopy> getListBookCode() {
        return listBookCode;
    }

    public void setListBookCode(List<BookCopy> listBookCode) {
        this.listBookCode = listBookCode;
    }
}
