package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BookCopy;

public class BookCopyDAO {
    private Connection connection = DAO.DAO_DB();

    public List<BookCopy> getAllBookCopies() {
        List<BookCopy> bookCopies = new ArrayList<>();

        try {
            String query = "EXEC USP_LoadTableBookCopy";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookCopy bookCopy = new BookCopy();

                bookCopy.setBookID(resultSet.getString("ID"));
                bookCopy.setBookTitle(resultSet.getString("Title"));
                bookCopy.setBookCategory(resultSet.getString("Category"));
                bookCopy.setYearPublished(resultSet.getInt("Year Published"));
                bookCopy.setBookPublisher(resultSet.getString("Publisher"));
                bookCopy.setBookTopic(resultSet.getString("Topic"));
                bookCopy.setBookDescription(resultSet.getString("Description"));
                bookCopy.setBookNotes(resultSet.getString("Notes"));
                bookCopy.setBookQuantity(resultSet.getInt("Quantity"));
                bookCopy.setCode(resultSet.getString("Code"));
                bookCopy.setLocation(resultSet.getString("Location"));
                bookCopy.setStatus(resultSet.getString("Status"));

                List<String> authors = new AuthorDAO().getAuthorsByBookID(bookCopy.getBookID());
                bookCopy.setAuthors(authors);
            
                bookCopies.add(bookCopy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookCopies;           
    }

    public BookCopy getBookCopyByCode(String code) {
        BookCopy bookCopy = new BookCopy();

        try {
            String query = "SELECT * FROM BookCopy WHERE BookCode = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bookCopy.setBookID(resultSet.getString("BookID"));
                bookCopy.setCode(resultSet.getString("BookCode"));
                bookCopy.setLocation(resultSet.getString("BookLocation"));
                bookCopy.setStatus(resultSet.getString("BookStatus"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookCopy;
    }

    public ArrayList<BookCopy> getBookCopyByBookID(String bookID) {
        ArrayList<BookCopy> bookCopies = new ArrayList<>();

        try {
            String query = "SELECT * FROM BookCopy WHERE BookID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookCopy bookCopy = new BookCopy();

                bookCopy.setBookID(resultSet.getString("BookID"));
                bookCopy.setCode(resultSet.getString("BookCode"));
                bookCopy.setLocation(resultSet.getString("BookLocation"));
                bookCopy.setStatus(resultSet.getString("BookStatus"));

                bookCopies.add(bookCopy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookCopies;
    }

    public boolean updateBookCopy(BookCopy bookCopy) {
        try {
            String query = "UPDATE BookCopy SET BookLocation = ?, BookStatus = ? WHERE BookCode = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookCopy.getLocation());
            preparedStatement.setString(2, bookCopy.getStatus());
            preparedStatement.setString(3, bookCopy.getCode());

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkBookStatusAvailable(String bookCode) {
        try {
            String query = "SELECT * FROM BookCopy WHERE BookCode = ? AND BookStatus = 'Available'";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateBookStatus(String bookCode, String status) {
        try {
            String query = "UPDATE BookCopy SET BookStatus = ? WHERE BookCode = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, bookCode);

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addBookCopy(String bookID) {
        try {
            String query = "EXEC USP_AddABookCopy ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookID);

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBookCopy(String bookCode) {
        try {
            String query = "DELETE FROM BookCopy WHERE BookCode = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookCode);

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public BookCopy getBookCopy(String bookCode) {
        BookCopy bookCopy = new BookCopy();

        try {
            String query = "SELECT b.BookID, b.BookTitle, b.BookCategory, b.YearPublished, b.BookPublisher, b.BookTopic, b.BookDescription, b.BookNotes, bc.BookCode, bc.BookLocation, bc.BookStatus FROM Book b JOIN BookCopy bc ON b.BookID = bc.BookID WHERE bc.BookCode = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bookCopy.setBookID(resultSet.getString("BookID"));
                bookCopy.setBookTitle(resultSet.getString("BookTitle"));
                bookCopy.setBookCategory(resultSet.getString("BookCategory"));
                bookCopy.setYearPublished(resultSet.getInt("YearPublished"));
                bookCopy.setBookPublisher(resultSet.getString("BookPublisher"));
                bookCopy.setBookTopic(resultSet.getString("BookTopic"));
                bookCopy.setBookDescription(resultSet.getString("BookDescription"));
                bookCopy.setBookNotes(resultSet.getString("BookNotes"));
                bookCopy.setCode(resultSet.getString("BookCode"));
                bookCopy.setLocation(resultSet.getString("BookLocation"));
                bookCopy.setStatus(resultSet.getString("BookStatus"));

                List<String> authors = new AuthorDAO().getAuthorsByBookID(bookCopy.getBookID());
                bookCopy.setAuthors(authors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookCopy;
    }
    

    public ArrayList<BookCopy> searchBookCopy(String search1, String search2, String search3, String search4, 
                                      String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                      String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        ArrayList<BookCopy> bookCopies = new ArrayList<>();
        String query = buildSearchQuery(search1, search2, search3, search4, 
                                        searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                        searchBoolean1, searchBoolean2, searchBoolean3);

        System.out.println(query);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            bookCopies = getBooksFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookCopies;
    }

    private String convertNameFieldHelper(String searchChoice) {
        switch (searchChoice) {
            case "ID":
                return "b.BookID";
            case "Title":
                return "b.BookTitle";
            case "Category":
                return "b.BookCategory";
            case "Author":
                return "a.AuthorName";
            case "Year Published":
                return "b.YearPublished";
            case "Publisher":
                return "b.BookPublisher";
            default:
                return "";
        }
        
    }

    private String buildSearchQuery(String search1, String search2, String search3, String search4, 
                                    String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                    String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        StringBuilder query = new StringBuilder()
                                        .append("SELECT b.BookTitle AS Title, ")
                                        .append("b.BookID AS ID, ")
                                        .append("bc.BookCode AS 'Code', ")
                                        .append("b.BookCategory AS 'Category', ")
                                        .append("bc.BookLocation AS 'Location', ")
                                        .append("bc.BookStatus AS 'Status', ")
                                        .append("STRING_AGG(a.AuthorName, ', ') AS 'Author', ")
                                        .append("b.YearPublished AS 'Year Published', ")
                                        .append("b.BookPublisher AS Publisher, ")
                                        .append("b.BookTopic AS Topic, ")
                                        .append("b.BookDescription AS Description, ")
                                        .append("b.BookNotes AS Notes, ")
                                        .append("b.BookQuantity AS Quantity ")
                                        .append("FROM Book b ")
                                        .append("LEFT JOIN BookAuthor ba ON b.BookID = ba.BookID ")
                                        .append("LEFT JOIN Author a ON ba.AuthorID = a.AuthorID ")
                                        .append("LEFT JOIN BookCopy bc ON b.BookID = bc.BookID ")
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

        query.append(" GROUP BY b.BookID, bc.BookCode, bc.BookLocation, bc.BookStatus, b.BookCategory, b.BookTitle, b.BookTopic, b.BookDescription, b.BookNotes, b.BookPublisher, b.YearPublished, b.BookQuantity;");

        return query.toString();
    }

    private ArrayList<BookCopy> getBooksFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<BookCopy> bookCopies = new ArrayList<>();

        while (resultSet.next()) {
            BookCopy bookCopy = new BookCopy();

            bookCopy.setBookID(resultSet.getString("ID"));
            bookCopy.setBookTitle(resultSet.getString("Title"));
            bookCopy.setBookCategory(resultSet.getString("Category"));
            bookCopy.setYearPublished(resultSet.getInt("Year Published"));
            bookCopy.setBookPublisher(resultSet.getString("Publisher"));
            bookCopy.setBookTopic(resultSet.getString("Topic"));
            bookCopy.setBookDescription(resultSet.getString("Description"));
            bookCopy.setBookNotes(resultSet.getString("Notes"));
            bookCopy.setBookQuantity(resultSet.getInt("Quantity"));
            bookCopy.setCode(resultSet.getString("Code"));
            bookCopy.setLocation(resultSet.getString("Location"));
            bookCopy.setStatus(resultSet.getString("Status"));

            List<String> authors = new AuthorDAO().getAuthorsByBookID(bookCopy.getBookID());
            bookCopy.setAuthors(authors);
        
            bookCopies.add(bookCopy);
        }

        return bookCopies;
    }
}
