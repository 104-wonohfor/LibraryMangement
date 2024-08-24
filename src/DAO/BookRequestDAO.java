package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BookRequest;

import java.time.LocalDate;

public class BookRequestDAO {
    private Connection connection = DAO.DAO_DB();

    public List<BookRequest> getAllBookRequest() {
        List<BookRequest> bookRequests = new ArrayList<>();
        
        try {
            String query = "EXEC USP_LoadTableBookRequest";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookRequest bookRequest = new BookRequest();

                List<String> bookCode = new ArrayList<>();
                String[] bookCodeArray = resultSet.getString("BookCode").split(",");
                for (String code : bookCodeArray) {
                    bookCode.add(code);
                }

                bookRequest.setBookRequestID(resultSet.getInt("RequestID"));
                bookRequest.setBookCode(bookCode);
                bookRequest.setCardID(resultSet.getString("CardID"));
                bookRequest.setStatus(resultSet.getString("Status"));
                bookRequest.setBorrowDate(resultSet.getString("BorrowDate"));
                bookRequest.setDueDate(resultSet.getString("DueDate"));
                bookRequest.setReturnDate(resultSet.getString("ReturnDate"));
                bookRequest.setPenaltyFee(resultSet.getDouble("PenaltyFee"));
                bookRequest.setNote(resultSet.getString("Note"));

                bookRequests.add(bookRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookRequests;
    }

    public BookRequest getBookRequestByID(String requestID) {
        BookRequest bookRequest = new BookRequest();

        try {
            String query = "SELECT * FROM BookRequest WHERE BookRequestID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, requestID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                List<String> bookCode = new ArrayList<>();
                String[] bookCodeArray = resultSet.getString("ListBookCode").split(",");
                for (String code : bookCodeArray) {
                    bookCode.add(code);
                }

                bookRequest.setBookRequestID(resultSet.getInt("BookRequestID"));
                bookRequest.setBookCode(bookCode);
                bookRequest.setCardID(resultSet.getString("ReaderCardID"));
                bookRequest.setStatus(resultSet.getString("RequestStatus"));
                bookRequest.setBorrowDate(resultSet.getString("DateBorrowed"));
                bookRequest.setDueDate(resultSet.getString("DateDue"));
                bookRequest.setReturnDate(resultSet.getString("DateReturned"));
                bookRequest.setPenaltyFee(resultSet.getDouble("PenaltyFee"));
                bookRequest.setNote(resultSet.getString("RequestNotes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookRequest;
    }

    public boolean acceptBookRequest(String requestID, String status, LocalDate dateBorrowed, LocalDate dateDue) {
        try {
            String query = "Update BookRequest SET RequestStatus = ?, DateBorrowed = ?, DateDue = ? WHERE BookRequestID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, dateBorrowed.toString());
            preparedStatement.setString(3, dateDue.toString());
            preparedStatement.setString(4, requestID);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean rejectBookRequest(String requestID, String status) {
        try {
            String query = "Update BookRequest SET RequestStatus = ? WHERE BookRequestID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, requestID);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendBookRequest(String readerCardID, String bookCopyID, String status) {
        try {
            String query = "INSERT INTO BookRequest (ListBookCode, ReaderCardID, RequestStatus) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookCopyID);
            preparedStatement.setString(2, readerCardID);
            preparedStatement.setString(3, status);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBookRequestBorrowed(String requestID) {
        try {
            String query = "UPDATE BookRequest SET RequestStatus = 'Borrowed' WHERE BookRequestID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, requestID);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBookRequestReturned(String requestID, LocalDate dateReturned, double penaltyFee, String note) {
        try {
            String query = "UPDATE BookRequest SET RequestStatus = 'Returned', DateReturned = ?, PenaltyFee = ?, RequestNotes = ? WHERE BookRequestID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dateReturned.toString());
            preparedStatement.setDouble(2, penaltyFee);
            preparedStatement.setString(3, note);
            preparedStatement.setString(4, requestID);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addBookRequest(String listBookCode, String cardID, String status, String note) {
        try {
            String query = "INSERT INTO BookRequest (ListBookCode, ReaderCardID, RequestStatus, RequestNotes) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, listBookCode);
            preparedStatement.setString(2, cardID);
            preparedStatement.setString(3, status);
            preparedStatement.setString(4, note);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isReaderBorrowing(String cardID) {
        try {
            String query = "SELECT * FROM BookRequest WHERE ReaderCardID = ? AND RequestStatus = 'Borrowed' OR RequestStatus = 'Accepted'";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cardID);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BookRequest> getAllBookRequestByCardID(String cardID) {
        List<BookRequest> bookRequests = new ArrayList<>();

        try {
            String query = "SELECT * FROM BookRequest WHERE ReaderCardID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cardID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookRequest bookRequest = new BookRequest();

                List<String> bookCode = new ArrayList<>();
                String[] bookCodeArray = resultSet.getString("ListBookCode").split(",");
                for (String code : bookCodeArray) {
                    bookCode.add(code);
                }

                bookRequest.setBookRequestID(resultSet.getInt("BookRequestID"));
                bookRequest.setBookCode(bookCode);
                bookRequest.setCardID(resultSet.getString("ReaderCardID"));
                bookRequest.setStatus(resultSet.getString("RequestStatus"));
                bookRequest.setBorrowDate(resultSet.getString("DateBorrowed"));
                bookRequest.setDueDate(resultSet.getString("DateDue"));
                bookRequest.setReturnDate(resultSet.getString("DateReturned"));
                bookRequest.setPenaltyFee(resultSet.getDouble("PenaltyFee"));
                bookRequest.setNote(resultSet.getString("RequestNotes"));

                bookRequests.add(bookRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookRequests;
    }

    public boolean cancelRequest(int requestID) {
        try {
            String query = "UPDATE BookRequest SET RequestStatus = 'Cancelled' WHERE BookRequestID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, requestID);
            preparedStatement.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<BookRequest> searchBookRequest(String search1, String search2, String search3, String search4, 
                                                    String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                                    String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        ArrayList<BookRequest> bookRequests = new ArrayList<>();
        String query = buildSearchQuery(search1, search2, search3, search4, 
                                        searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                        searchBoolean1, searchBoolean2, searchBoolean3);

        System.out.println(query);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            bookRequests = getBookRequestsFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookRequests;
    }

    private String convertNameFieldHelper(String searchChoice) {
        switch (searchChoice) {
            case "Request ID":
                return "br.BookRequestID";
            case "Card ID":
                return "br.ReaderCardID";
            case "Status":
                return "br.RequestStatus";
            case "Note":
                return "br.RequestNotes";
            default:
                return "";
        }
    }

    private String buildSearchQuery(String search1, String search2, String search3, String search4, 
                                    String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                    String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        StringBuilder query = new StringBuilder()
                                        .append("SELECT br.BookRequestID AS 'Request ID', ")
                                        .append("br.ListBookCode AS 'Book Code', ")
                                        .append("br.ReaderCardID AS 'Card ID', ")
                                        .append("br.RequestStatus AS 'Status', ")
                                        .append("br.DateBorrowed AS 'Borrow Date', ")
                                        .append("br.DateDue AS 'Due Date', ")
                                        .append("br.DateReturned AS 'Return Date', ")
                                        .append("br.PenaltyFee AS 'Penalty Fee', ")
                                        .append("br.RequestNotes AS 'Note' ")
                                        .append("FROM BookRequest br ")
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

        query.append(";");

        return query.toString();
    }

    private ArrayList<BookRequest> getBookRequestsFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<BookRequest> bookRequests = new ArrayList<>();

        while (resultSet.next()) {
            List<String> bookCode = new ArrayList<>();
            String[] bookCodeArray = resultSet.getString("Book Code").split(",");
            for (String code : bookCodeArray) {
                bookCode.add(code);
            }

            BookRequest bookRequest = new BookRequest();
            bookRequest.setBookRequestID(resultSet.getInt("Request ID"));
            bookRequest.setBookCode(bookCode);
            bookRequest.setCardID(resultSet.getString("Card ID"));
            bookRequest.setStatus(resultSet.getString("Status"));
            bookRequest.setBorrowDate(resultSet.getString("Borrow Date"));
            bookRequest.setDueDate(resultSet.getString("Due Date"));
            bookRequest.setReturnDate(resultSet.getString("Return Date"));
            bookRequest.setPenaltyFee(resultSet.getDouble("Penalty Fee"));
            bookRequest.setNote(resultSet.getString("Note"));

            bookRequests.add(bookRequest);
        }

        return bookRequests;
    }
}
