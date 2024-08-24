package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NumberReadRequestDAO {
    Connection conn = DAO.DAO_DB();
    
    public int getNumberReadRequest(String ID) {
        try {
            String sql = "SELECT numReadRequest FROM NumberReadRequest WHERE LibrarianIdentificationNumber = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, ID);
            ResultSet result = statement.executeQuery();
    
            if (result.next()) {
                return result.getInt("numReadRequest");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Update numReadRequest của ID
    public void updateNumberReadRequest(String ID, int numReadRequest) {
        try {
            String sql = "UPDATE NumberReadRequest SET numReadRequest = ? WHERE LibrarianIdentificationNumber = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numReadRequest);
            statement.setString(2, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // insert ID và numReadRequest(mặc định khi thêm là 0) vào bảng NumberReadRequest
    public void insertNumberReadRequest(String ID) {
        try {
            String sql = "INSERT INTO NumberReadRequest(LibrarianIdentificationNumber, numReadRequest) VALUES(?, 0)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
