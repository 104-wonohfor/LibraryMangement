/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reader;

/**
 *
 * @author Thang
 */
public class ReaderDAO {
    private Connection connection = DAO.DAO_DB();

    public boolean checkLogin(String username, String password) {
        try {
            String query = "EXEC USP_LoginReader ?, ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getReaderNameAndID(String username) {
        try {
            String query = "SELECT ReaderIdentificationNumber, ReaderName FROM Reader WHERE ReaderCardID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("ReaderIdentificationNumber") + " - " + resultSet.getString("ReaderName");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    // get all Readers
    public List<Reader> getAllReaders() {
        List<Reader> readers = new ArrayList<>();

        try {
            String query = "EXEC USP_LoadTableReader";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reader reader = new Reader();

                reader.setName(resultSet.getString("Name"));
                reader.setDob(resultSet.getString("DOB"));
                reader.setAddress(resultSet.getString("Address"));
                reader.setGender(resultSet.getString("Gender"));
                reader.setPhoneNumber(resultSet.getString("Phone Number"));
                reader.setEmail(resultSet.getString("Email"));
                reader.setIdNumber(resultSet.getString("Identification Number"));
                reader.setCardID(resultSet.getString("Card ID"));

                readers.add(reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return readers;
    }

    public Reader getReaderByIDNumber(String idNumber) {
        Reader reader = null;
        try {
            reader = new Reader();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String query = "SELECT * FROM Reader WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                reader.setName(resultSet.getString("ReaderName"));
                reader.setDob(resultSet.getString("ReaderDOB"));
                reader.setAddress(resultSet.getString("ReaderAddress"));
                reader.setGender(resultSet.getString("ReaderGender"));
                reader.setPhoneNumber(resultSet.getString("ReaderPhoneNumber"));
                reader.setEmail(resultSet.getString("ReaderEmail"));
                reader.setIdNumber(resultSet.getString("ReaderIdentificationNumber"));
                reader.setCardID(resultSet.getString("ReaderCardID"));

                return reader;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reader;
    }

    public String getReaderCardIDFromPersonIDNumber(String personIDNumber) {
        try {
            String query = "SELECT ReaderCardID FROM Reader WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, personIDNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("ReaderCardID");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public boolean addReader(String name, String dob, String address, String gender, String phoneNum, String email, String idNumber, String cardID) {
        try {
            String query = "INSERT INTO Reader (ReaderName, ReaderDOB, ReaderAddress, ReaderGender, ReaderPhoneNumber, ReaderEmail, ReaderIdentificationNumber, ReaderCardID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, dob);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4,gender);
            preparedStatement.setString(5, phoneNum);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, idNumber);
            preparedStatement.setString(8, cardID);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateReader(String idNumber, String address, String phoneNum) {
        try {
            String query = "UPDATE Reader SET ReaderAddress = ?, ReaderPhoneNumber = ? WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, phoneNum);
            preparedStatement.setString(3, idNumber);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean resetPassword(String idNumber) {
        try {
            String query = "UPDATE Reader SET ReaderPassword = '15410012614513998108181121232209311017111612' WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean editReader(Reader reader) {
        try {
            String query = "UPDATE Reader SET ReaderName = ?, ReaderDOB = ?, ReaderAddress = ?, ReaderGender = ?, ReaderPhoneNumber = ?, ReaderEmail = ?, ReaderCardID = ? WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, reader.getName());
            preparedStatement.setString(2, reader.getDob());
            preparedStatement.setString(3, reader.getAddress());
            preparedStatement.setString(4, reader.getGender());
            preparedStatement.setString(5, reader.getPhoneNumber());
            preparedStatement.setString(6, reader.getEmail());
            preparedStatement.setString(7, reader.getCardID());
            preparedStatement.setString(8, reader.getIdNumber());

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getIdNumberByEmail(String email) {
        try {
            String query = "SELECT ReaderIdentificationNumber FROM Reader WHERE ReaderEmail = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("ReaderIdentificationNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public boolean editPasswordByIDNumber(String idNumber, String newPassword) {
        try {
            String query = "UPDATE Reader SET ReaderPassword = ? WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, idNumber);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getPasswordByIDNumber(String idNumber) {
        try {
            String query = "SELECT ReaderPassword FROM Reader WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("ReaderPassword");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public boolean deleteReader(String idNumber) {
        try {
            String query = "EXEC USP_DeleteReader ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getPhoneNumberByIDNumber(String idNumber) {
        try {
            String query = "SELECT ReaderPhoneNumber FROM Reader WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("ReaderPhoneNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public String getEmailByIDNumber(String idNumber) {
        try {
            String query = "SELECT ReaderEmail FROM Reader WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("ReaderEmail");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public String getIDNumberByIDNumber(String idNumber) {
        try {
            String query = "SELECT ReaderIdentificationNumber FROM Reader WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("ReaderIdentificationNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public boolean checkPhoneNumber(String phoneNum) {
        try {
            String query = "SELECT * FROM Reader WHERE ReaderPhoneNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phoneNum);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkEmail(String email) {
        try {
            String query = "SELECT * FROM Reader WHERE ReaderEmail = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkIDNumber(String idNumber) {
        try {
            String query = "SELECT * FROM Reader WHERE ReaderIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getCardIdByEmail(String email) {
        try {
            String query = "SELECT ReaderCardID FROM Reader WHERE ReaderEmail = ?";
    
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                return resultSet.getString("ReaderCardID");
            } else {
                return "NULL";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return "NULL";
    }



    public ArrayList<Reader> searchReader(String search1, String search2, String search3, String search4, 
                                            String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                            String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        ArrayList<Reader> readers = new ArrayList<>();
        String query = buildSearchQuery(search1, search2, search3, search4, 
                                        searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                        searchBoolean1, searchBoolean2, searchBoolean3);

        System.out.println(query);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            readers = getReadersFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return readers;
    }

    private String convertNameFieldHelper(String searchChoice) {
        switch (searchChoice) {
            case "Name":
                return "ReaderName";
            case "Phone Number":
                return "ReaderPhoneNumber";
            case "ID Number":
                return "ReaderIdentificationNumber";
            case "Card ID":
                return "ReaderCardID";
            default:
                return "";
        }
    }

    private String buildSearchQuery(String search1, String search2, String search3, String search4, 
                                    String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                    String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        StringBuilder query = new StringBuilder()
                                        .append("SELECT * FROM Reader WHERE ");               

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

    private ArrayList<Reader> getReadersFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Reader> readers = new ArrayList<>();

        while (resultSet.next()) {
            Reader reader = null;
            try {
                reader = new Reader();
            } catch (Exception e) {
                e.printStackTrace();
            }

            reader.setName(resultSet.getString("ReaderName"));
            reader.setDob(resultSet.getString("ReaderDOB"));
            reader.setAddress(resultSet.getString("ReaderAddress"));
            reader.setGender(resultSet.getString("ReaderGender"));
            reader.setPhoneNumber(resultSet.getString("ReaderPhoneNumber"));
            reader.setEmail(resultSet.getString("ReaderEmail"));
            reader.setIdNumber(resultSet.getString("ReaderIdentificationNumber"));
            reader.setCardID(resultSet.getString("ReaderCardID"));

            readers.add(reader);
        }

        return readers;
    }
}
