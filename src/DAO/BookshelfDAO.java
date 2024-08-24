package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookshelfDAO {
    private Connection connection = DAO.DAO_DB();

    public boolean addBookshelf(String readerCardId) {
        try {
            String query = "INSERT INTO YourBookshelf (ReaderCardID) VALUES (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, readerCardId);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getListBookCodeByCardID(String readerCardId) {
        String listBookCode = null;
        try {
            String query = "SELECT ListBookCode FROM YourBookshelf WHERE ReaderCardID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, readerCardId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                listBookCode = resultSet.getString("ListBookCode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listBookCode;
    }

    public boolean addBookToBookshelf(String readerCardId, String bookCode) {
        try {
            String query = "EXEC USP_AddToYourBookshelf ?, ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, readerCardId);
            preparedStatement.setString(2, bookCode);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean removeBookFromBookshelf(String readerCardId, String bookCode) {
        try {
            String query = "EXEC USP_RemoveFromYourBookshelf ?, ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, readerCardId);
            preparedStatement.setString(2, bookCode);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isBookInBookshelf(String readerCardId, String bookCode) {
        try {
            String query = "SELECT * FROM YourBookshelf WHERE ReaderCardID = ? AND ListBookCode LIKE ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, readerCardId);
            preparedStatement.setString(2, "%" + bookCode + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
