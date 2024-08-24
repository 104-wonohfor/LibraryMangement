package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Book;

import java.util.ArrayList;

public class BookDAO {
    private Connection connection = DAO.DAO_DB();

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try {
            String query = "EXEC USP_LoadTableBook";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();

                book.setBookID(resultSet.getString("ID"));
                book.setBookTitle(resultSet.getString("Title"));
                book.setBookCategory(resultSet.getString("Category"));
                book.setYearPublished(resultSet.getInt("Year Published"));
                book.setBookPublisher(resultSet.getString("Publisher"));
                book.setBookTopic(resultSet.getString("Topic"));
                book.setBookDescription(resultSet.getString("Description"));
                book.setBookNotes(resultSet.getString("Notes"));
                book.setBookQuantity(resultSet.getInt("Quantity"));

                List<String> authors = new AuthorDAO().getAuthorsByBookID(book.getBookID());
                book.setAuthors(authors);
            
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;           
    }

    public Book getBookByID(String bookID) {
        Book book = new Book();

        try {
            String query = "SELECT * FROM Book WHERE BookID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                book.setBookID(resultSet.getString("BookID"));
                book.setBookTitle(resultSet.getString("BookTitle"));
                book.setBookCategory(resultSet.getString("BookCategory"));
                book.setYearPublished(resultSet.getInt("YearPublished"));
                book.setBookPublisher(resultSet.getString("BookPublisher"));
                book.setBookTopic(resultSet.getString("BookTopic"));
                book.setBookDescription(resultSet.getString("BookDescription"));
                book.setBookNotes(resultSet.getString("BookNotes"));
                book.setBookQuantity(resultSet.getInt("BookQuantity"));

                List<String> authors = new AuthorDAO().getAuthorsByBookID(book.getBookID());
                book.setAuthors(authors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    public boolean updateBook(Book book) {
        try {
            String query = "EXEC USP_UpdateBook ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getBookID());
            preparedStatement.setString(2, book.getBookTitle());
            preparedStatement.setString(3, book.getBookCategory());
            preparedStatement.setInt(4, book.getYearPublished());
            preparedStatement.setString(5, book.getBookPublisher());
            preparedStatement.setString(6, book.getBookTopic());
            preparedStatement.setString(7, book.getBookDescription());
            preparedStatement.setString(8, book.getBookNotes());
            preparedStatement.setInt(9, book.getBookQuantity());
            preparedStatement.setString(10, book.getAuthors().get(0));

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean addBook(Book book) {
        try {
            String query = "EXEC USP_InsertBook ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getBookID());
            preparedStatement.setString(2, book.getBookTitle());
            preparedStatement.setString(3, book.getBookCategory());
            preparedStatement.setInt(4, book.getYearPublished());
            preparedStatement.setString(5, book.getBookPublisher());
            preparedStatement.setString(6, book.getBookTopic());
            preparedStatement.setString(7, book.getBookDescription());
            preparedStatement.setString(8, book.getBookNotes());
            preparedStatement.setInt(9, book.getBookQuantity());
            preparedStatement.setString(10, book.getAuthors().get(0));

            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean isBookCopyBorrowed(String bookID) {
        try {
            String query = "SELECT * FROM BookCopy WHERE BookID = ? AND BookStatus = 'Borrowed'";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteBook(String bookID) {
        try {
            String query = "EXEC USP_DeleteBook ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookID);

            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean decreaseBookQuantity(String bookID) {
        try {
            String query = "EXEC USP_DecreaseBookQuantity ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Book> searchBook(String search1, String search2, String search3, String search4, 
                                      String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                      String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        ArrayList<Book> books = new ArrayList<>();
        String query = buildSearchQuery(search1, search2, search3, search4, 
                                        searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                        searchBoolean1, searchBoolean2, searchBoolean3);

        System.out.println(query);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            books = getBooksFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
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
                                        .append("b.BookCategory AS 'Category', ")
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

        query.append(" GROUP BY b.BookID, b.BookCategory, b.BookTitle, b.BookTopic, b.BookDescription, b.BookNotes, b.BookPublisher, b.YearPublished, b.BookQuantity;");

        return query.toString();
    }

    private ArrayList<Book> getBooksFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();

        while (resultSet.next()) {
            Book book = new Book();
            book.setBookID(resultSet.getString("ID"));
            book.setBookTitle(resultSet.getString("Title"));
            book.setBookCategory(resultSet.getString("Category"));
            book.setYearPublished(resultSet.getInt("Year Published"));
            book.setBookPublisher(resultSet.getString("Publisher"));
            book.setBookTopic(resultSet.getString("Topic"));
            book.setBookDescription(resultSet.getString("Description"));
            book.setBookNotes(resultSet.getString("Notes"));
            book.setBookQuantity(resultSet.getInt("Quantity"));

            List<String> authors = new AuthorDAO().getAuthorsByBookID(book.getBookID());
            book.setAuthors(authors);

            books.add(book);
        }

        return books;
    }

}
