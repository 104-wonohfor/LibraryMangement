package model;

public class ReaderCard extends Reader {
    private String cardPIN;
    private String cardStatus;
    private String dateIssued;
    private String dateExpired;

    public ReaderCard() throws Exception {
        super();
    }

    public ReaderCard(String cardPIN, String cardStatus, String dateIssued, String dateExpired, 
                      String cardID, String name, String dob, String address, String gender, 
                      String phoneNumber, String email, String idNumber, String password) {
        super(cardID, name, dob, address, gender, phoneNumber, email, idNumber, password);
        this.cardPIN = cardPIN;
        this.cardStatus = cardStatus;
        this.dateIssued = dateIssued;
        this.dateExpired = dateExpired;
    }

    public String getCardPIN() {
        return cardPIN;
    }

    public void setCardPIN(String cardPIN) {
        this.cardPIN = cardPIN;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }  
}
