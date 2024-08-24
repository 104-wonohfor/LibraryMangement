package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AuthorDAO {
    private Connection connection = DAO.DAO_DB();

    //List<String> authors = new AuthorDAO().getAuthorsByBookID(book.getBookID());
    // book.setAuthors(authors);

    public List<String> getAuthorsByBookID(String bookID) {
        List<String> authors = new ArrayList<>();

        try {
            String query = "SELECT AuthorName FROM Author WHERE AuthorID IN (SELECT AuthorID FROM BookAuthor WHERE BookID = ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                authors.add(resultSet.getString("AuthorName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authors;
    }
}
