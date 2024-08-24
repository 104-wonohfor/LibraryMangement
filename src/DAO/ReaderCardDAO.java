package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ReaderCard;

import java.time.LocalDate;

public class ReaderCardDAO {
    private Connection connection = DAO.DAO_DB();

    public List<ReaderCard> getAllReaderCards() {
        List<ReaderCard> readerCards = new ArrayList<>();

        try {
            String query = "EXEC USP_LoadTableReaderCardID";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ReaderCard readerCard = new ReaderCard();
                
                readerCard.setName(resultSet.getString("Name"));
                readerCard.setIdNumber(resultSet.getString("ID Number"));
                readerCard.setCardID(resultSet.getString("Card ID"));
                readerCard.setCardStatus(resultSet.getString("Status"));
                readerCard.setDateIssued(resultSet.getString("Date Issued"));
                readerCard.setDateExpired(resultSet.getString("Date Expired"));

                readerCards.add(readerCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return readerCards;
    }

    public ReaderCard getReaderCardByCardID(String cardID) {
        ReaderCard readerCard = null;
        try {
            readerCard = new ReaderCard();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String query = "SELECT Reader.ReaderName, Reader.ReaderIdentificationNumber, ReaderCardID.ReaderCardID, ReaderCardID.CardStatus, ReaderCardID.DateIssued, ReaderCardID.DateExpired FROM ReaderCardID, Reader WHERE ReaderCardID.ReaderCardID = ? AND ReaderCardID.ReaderCardID = Reader.ReaderCardID";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cardID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            readerCard.setName(resultSet.getString("ReaderName"));
            readerCard.setIdNumber(resultSet.getString("ReaderIdentificationNumber"));
            readerCard.setCardID(resultSet.getString("ReaderCardID"));
            readerCard.setCardStatus(resultSet.getString("CardStatus"));
            readerCard.setDateIssued(resultSet.getString("DateIssued"));
            readerCard.setDateExpired(resultSet.getString("DateExpired"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return readerCard;
    }    

    public String getLatestReaderCardID() {
        int latestReaderCardID = 0;

        try {
            String query = "SELECT MAX(CAST(SUBSTRING(ReaderCardID, 3, LEN(ReaderCardID)) AS INT)) AS LatestReaderCardID FROM dbo.ReaderCardID WHERE ReaderCardID LIKE 'DG%'";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            latestReaderCardID = resultSet.getInt("LatestReaderCardID");
        } catch (Exception e) {
            e.printStackTrace();
        }

        latestReaderCardID++;
        return "DG" + latestReaderCardID;
    }

    public boolean addReaderCard(String readerCardID, LocalDate dateIssued, LocalDate dateExpired) {
        try {
            String query = "INSERT INTO ReaderCardID (ReaderCardID, CardStatus, DateIssued, DateExpired) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, readerCardID);
            preparedStatement.setString(2, "Requested");

            // dateIssued and dateExpired can be null
            if (dateIssued == null) {
                preparedStatement.setNull(3, java.sql.Types.DATE);
            } else {
                preparedStatement.setString(3, dateIssued.toString());
            }

            if (dateExpired == null) {
                preparedStatement.setNull(4, java.sql.Types.DATE);
            } else {
                preparedStatement.setString(4, dateExpired.toString());
            }
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean checkCardIDExist(String cardID) {
        try {
            String query = "SELECT * FROM ReaderCardID WHERE ReaderCardID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cardID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean acceptRequest(String cardID, String dateIssued, String dateExpired) {
        try {
            String query = "UPDATE ReaderCardID SET CardStatus = 'Active', DateIssued = ?, DateExpired = ? WHERE ReaderCardID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dateIssued);
            preparedStatement.setString(2, dateExpired);
            preparedStatement.setString(3, cardID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean rejectRequest(String cardID) {
        try {
            String query = "UPDATE ReaderCardID SET CardStatus = 'Rejected', DateIssued = NULL, DateExpired = NULL WHERE ReaderCardID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cardID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean updateCardStatus(String cardID, String newStatus) {
        try {
            String query = "UPDATE ReaderCardID SET CardStatus = ? WHERE ReaderCardID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newStatus);
            preparedStatement.setString(2, cardID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean acceptRequestExtend(String cardID) {
        try {
            String query = "EXEC USP_AcceptRequestExtend ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cardID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean checkPIN(String cardID, String PIN) {
        try {
            String query = "SELECT * FROM ReaderCardID WHERE ReaderCardID = ? AND CardPIN = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cardID);
            preparedStatement.setString(2, PIN);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updatePIN(String cardID, String newPIN) {
        try {
            String query = "UPDATE ReaderCardID SET CardPIN = ? WHERE ReaderCardID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPIN);
            preparedStatement.setString(2, cardID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ArrayList<ReaderCard> searchReaderCard(String search1, String search2, String search3, String search4, 
                                                  String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                                  String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        ArrayList<ReaderCard> readerCards = new ArrayList<>();
        String query = buildSearchQuery(search1, search2, search3, search4, 
                                        searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                        searchBoolean1, searchBoolean2, searchBoolean3);

        System.out.println(query);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            readerCards = getReaderCardsFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return readerCards;
    }

    private String convertNameFieldHelper(String searchChoice) {
        switch (searchChoice) {
            case "Name":
                return "r.ReaderName";
            case "ID Number":
                return "r.ReaderIdentificationNumber";
            case "Card ID":
                return "r.ReaderCardID";
            case "Status":
                return "rc.CardStatus";
            default:
                return "";
        }
    }

private String buildSearchQuery(String search1, String search2, String search3, String search4, 
                                String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                String searchBoolean1, String searchBoolean2, String searchBoolean3) {
    StringBuilder query = new StringBuilder()
                                    .append("SELECT r.ReaderName, ")
                                    .append("r.ReaderIdentificationNumber, ")
                                    .append("r.ReaderCardID, ")
                                    .append("rc.CardStatus, ")
                                    .append("rc.DateIssued, ")
                                    .append("rc.DateExpired ")
                                    .append("FROM Reader r ")
                                    .append("JOIN ReaderCardID rc ON r.ReaderCardID = rc.ReaderCardID ")
                                    .append("WHERE ");

    boolean firstCondition = true;

    if (!search1.isEmpty() && !searchChoice1.equals("Any Field")) {
        if (!firstCondition) query.append(" ");
        query.append(convertNameFieldHelper(searchChoice1)).append(" LIKE N'%").append(search1).append("%'");
        firstCondition = false;
    }

    if (!search2.isEmpty() && !searchChoice2.equals("Any Field")) {
        if (!firstCondition) query.append(" ").append(searchBoolean1).append(" ");
        query.append(convertNameFieldHelper(searchChoice2)).append(" LIKE N'%").append(search2).append("%'");
        firstCondition = false;
    }

    if (!search3.isEmpty() && !searchChoice3.equals("Any Field")) {
        if (!firstCondition) query.append(" ").append(searchBoolean2).append(" ");
        query.append(convertNameFieldHelper(searchChoice3)).append(" LIKE N'%").append(search3).append("%'");
        firstCondition = false;
    }

    if (!search4.isEmpty() && !searchChoice4.equals("Any Field")) {
        if (!firstCondition) query.append(" ").append(searchBoolean3).append(" ");
        query.append(convertNameFieldHelper(searchChoice4)).append(" LIKE N'%").append(search4).append("%'");
    }

    return query.toString();
}

    private ArrayList<ReaderCard> getReaderCardsFromResultSet(ResultSet resultSet) {
        ArrayList<ReaderCard> readerCards = new ArrayList<>();

        try {
            while (resultSet.next()) {
                ReaderCard readerCard = new ReaderCard();
                
                readerCard.setName(resultSet.getString("ReaderName"));
                readerCard.setIdNumber(resultSet.getString("ReaderIdentificationNumber"));
                readerCard.setCardID(resultSet.getString("ReaderCardID"));
                readerCard.setCardStatus(resultSet.getString("CardStatus"));
                readerCard.setDateIssued(resultSet.getString("DateIssued"));
                readerCard.setDateExpired(resultSet.getString("DateExpired"));

                readerCards.add(readerCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return readerCards;
    }

    // public ArrayList<Reader> searchReader(String search1, String search2, String search3, String search4, 
    //                                         String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
    //                                         String searchBoolean1, String searchBoolean2, String searchBoolean3) {
    //     ArrayList<Reader> readers = new ArrayList<>();
    //     String query = buildSearchQuery(search1, search2, search3, search4, 
    //                                     searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
    //                                     searchBoolean1, searchBoolean2, searchBoolean3);

    //     System.out.println(query);

    //     try {
    //         PreparedStatement preparedStatement = connection.prepareStatement(query);
    //         ResultSet resultSet = preparedStatement.executeQuery();
    //         readers = getReadersFromResultSet(resultSet);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return readers;
    // }

    // private String convertNameFieldHelper(String searchChoice) {
    //     switch (searchChoice) {
    //         case "Name":
    //             return "ReaderName";
    //         case "Phone Number":
    //             return "ReaderPhoneNumber";
    //         case "ID Number":
    //             return "ReaderIdentificationNumber";
    //         case "Card ID":
    //             return "ReaderCardID";
    //         default:
    //             return "";
    //     }
    // }

    // private String buildSearchQuery(String search1, String search2, String search3, String search4, 
    //                                 String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
    //                                 String searchBoolean1, String searchBoolean2, String searchBoolean3) {
    //     StringBuilder query = new StringBuilder()
    //                                     .append("SELECT * FROM Reader WHERE ");               

    //     boolean firstCondition = true;

    //     if (!search1.isEmpty() && !searchChoice1.equals("Any Field")) {
    //         if (!firstCondition) query.append(" ");
    //         query.append(convertNameFieldHelper(searchChoice1)).append(" LIKE N'%").append(search1).append("%'");
    //         firstCondition = false;
    //     }

    //     if (!search2.isEmpty() && !searchChoice2.equals("Any Field")) {
    //         if (!firstCondition) query.append(" ").append(searchBoolean1).append(" ");
    //         query.append(convertNameFieldHelper(searchChoice2)).append(" LIKE N'%").append(search2).append("%'");
    //         firstCondition = false;
    //     }

    //     if (!search3.isEmpty() && !searchChoice3.equals("Any Field")) {
    //         if (!firstCondition) query.append(" ").append(searchBoolean2).append(" ");
    //         query.append(convertNameFieldHelper(searchChoice3)).append(" LIKE N'%").append(search3).append("%'");
    //         firstCondition = false;
    //     }

    //     if (!search4.isEmpty() && !searchChoice4.equals("Any Field")) {
    //         if (!firstCondition) query.append(" ").append(searchBoolean3).append(" ");
    //         query.append(convertNameFieldHelper(searchChoice4)).append(" LIKE N'%").append(search4).append("%'");
    //     }

    //     return query.toString();
    // }

    // private ArrayList<Reader> getReadersFromResultSet(ResultSet resultSet) throws SQLException {
    //     ArrayList<Reader> readers = new ArrayList<>();

    //     while (resultSet.next()) {
    //         Reader reader = new Reader();
    //         reader.setName(resultSet.getString("ReaderName"));
    //         reader.setDob(resultSet.getString("ReaderDOB"));
    //         reader.setAddress(resultSet.getString("ReaderAddress"));
    //         reader.setGender(resultSet.getString("ReaderGender"));
    //         reader.setPhoneNumber(resultSet.getString("ReaderPhoneNumber"));
    //         reader.setEmail(resultSet.getString("ReaderEmail"));
    //         reader.setIdNumber(resultSet.getString("ReaderIdentificationNumber"));
    //         reader.setCardID(resultSet.getString("ReaderCardID"));

    //         readers.add(reader);
    //     }

    //     return readers;
    // }
}
