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

import model.Librarian;

/**
 *
 * @author Thang
 */
public class LibrarianDAO {
    private Connection connection = DAO.DAO_DB();

    public boolean checkLogin(String username, String password) {
        try {
            String query = "EXEC USP_LoginLibrarian ?, ?";

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


    public String getLibrarianNameAndID(String username) {
        try {
            String query = "SELECT LibrarianIdentificationNumber, LibrarianName FROM Librarian WHERE LibrarianPhoneNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("LibrarianIdentificationNumber") + " - " + resultSet.getString("LibrarianName");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    // get all Librarians
    public List<Librarian> getAllLibrarians() {
        List<Librarian> librarians = new ArrayList<>();

        try {
            String query = "EXEC USP_LoadTableLibrarian";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Librarian librarian = new Librarian();

                librarian.setDepartment(resultSet.getString("Department"));
                librarian.setPosition(resultSet.getString("Position"));
                librarian.setWorkplace(resultSet.getString("Workplace"));
                librarian.setName(resultSet.getString("Name"));
                librarian.setDob(resultSet.getString("DOB"));
                librarian.setAddress(resultSet.getString("Address"));
                librarian.setGender(resultSet.getString("Gender"));
                librarian.setPhoneNumber(resultSet.getString("Phone Number"));
                librarian.setEmail(resultSet.getString("Email"));
                librarian.setIdNumber(resultSet.getString("Identification Number"));

                librarians.add(librarian);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return librarians;
    }

    // Get Librarian by ID
    public Librarian getLibrarianByIDNumber(String idNumber) {
        Librarian librarian = null;
        try {
            librarian = new Librarian();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String query = "SELECT * FROM Librarian WHERE LibrarianIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                librarian.setDepartment(resultSet.getString("LibrarianDepartment"));
                librarian.setPosition(resultSet.getString("LibrarianPosition"));
                librarian.setWorkplace(resultSet.getString("LibrarianWorkplace"));
                librarian.setName(resultSet.getString("LibrarianName"));
                librarian.setDob(resultSet.getString("LibrarianDOB"));
                librarian.setAddress(resultSet.getString("LibrarianAddress"));
                librarian.setGender(resultSet.getString("LibrarianGender"));
                librarian.setPhoneNumber(resultSet.getString("LibrarianPhoneNumber"));
                librarian.setEmail(resultSet.getString("LibrarianEmail"));
                librarian.setIdNumber(resultSet.getString("LibrarianIdentificationNumber"));

                return librarian;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return librarian;
    }

    public boolean resetPassword(String idNumber) {
        try {
            String query = "UPDATE Librarian SET LibrarianPassword = '$2a$10$eImiTXuWVxfM37uY4JANjO0qTgvWKy4iJfILSeRA863kmUZsO77gy' WHERE LibrarianIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            preparedStatement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean editLibrarian(Librarian librarian) {
        try {
            String query = "EXEC USP_UpdateLibrarian ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, librarian.getName());
            preparedStatement.setString(2, librarian.getDob());
            preparedStatement.setString(3, librarian.getAddress());
            preparedStatement.setString(4, librarian.getGender());
            preparedStatement.setString(5, librarian.getPhoneNumber());
            preparedStatement.setString(6, librarian.getEmail());
            preparedStatement.setString(7, librarian.getIdNumber());
            preparedStatement.setString(8, librarian.getDepartment());
            preparedStatement.setString(9, librarian.getPosition());
            preparedStatement.setString(10, librarian.getWorkplace());

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean addLibrarian(Librarian librarian) {
        try {
            String query = "EXEC USP_InsertLibrarian ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, librarian.getName());
            preparedStatement.setString(2, librarian.getDob());
            preparedStatement.setString(3, librarian.getAddress());
            preparedStatement.setString(4, librarian.getGender());
            preparedStatement.setString(5, librarian.getPhoneNumber());
            preparedStatement.setString(6, librarian.getEmail());
            preparedStatement.setString(7, librarian.getIdNumber());
            preparedStatement.setString(8, librarian.getDepartment());
            preparedStatement.setString(9, librarian.getPosition());
            preparedStatement.setString(10, librarian.getWorkplace());

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteLibrarian(String idNumber) {
        try {
            String query = "DELETE FROM Librarian WHERE LibrarianIdentificationNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            preparedStatement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }          

    public String getPasswordByIDNumber(String idNumber) {
        try {
            String query = "SELECT LibrarianPassword FROM Librarian WHERE LibrarianIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("LibrarianPassword");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public boolean editPasswordByIDNumber(String idNumber, String password) {
        

        try {
            String query = "UPDATE Librarian SET LibrarianPassword = ? WHERE LibrarianIdentificationNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, idNumber);
            preparedStatement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getPhoneNumberByIDNumber(String idNumber) {
        try {
            String query = "SELECT LibrarianPhoneNumber FROM Librarian WHERE LibrarianIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("LibrarianPhoneNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public String getEmailByIDNumber(String idNumber) {
        try {
            String query = "SELECT LibrarianEmail FROM Librarian WHERE LibrarianIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("LibrarianEmail");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public String getIDNumberByIDNumber(String idNumber) {
        try {
            String query = "SELECT LibrarianIdentificationNumber FROM Librarian WHERE LibrarianIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getString("LibrarianIdentificationNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NULL";
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        try {
            String query = "SELECT * FROM Librarian WHERE LibrarianPhoneNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkEmail(String email) {
        try {
            String query = "SELECT * FROM Librarian WHERE LibrarianEmail = ?";

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
            String query = "SELECT * FROM Librarian WHERE LibrarianIdentificationNumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Librarian> searchLibrarian(String search1, String search2, String search3, String search4, 
                                                String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                                String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        ArrayList<Librarian> librarians = new ArrayList<>();
        String query = buildSearchQuery(search1, search2, search3, search4, 
                                        searchChoice1, searchChoice2, searchChoice3, searchChoice4, 
                                        searchBoolean1, searchBoolean2, searchBoolean3);

        System.out.println(query);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            librarians = getLibrariansFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return librarians;
    }

    private String convertNameFieldHelper(String searchChoice) {
        switch (searchChoice) {
            case "Name":
                return "LibrarianName";
            case "Phone Number":
                return "LibrarianPhoneNumber";
            case "ID Number":
                return "LibrarianIdentificationNumber";
            case "Department":
                return "Department";
            default:
                return "";
        }
    }

    private String buildSearchQuery(String search1, String search2, String search3, String search4, 
                                    String searchChoice1, String searchChoice2, String searchChoice3, String searchChoice4, 
                                    String searchBoolean1, String searchBoolean2, String searchBoolean3) {
        StringBuilder query = new StringBuilder()
                                        .append("SELECT * FROM Librarian WHERE ");               

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

    private ArrayList<Librarian> getLibrariansFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Librarian> librarians = new ArrayList<>();

        while (resultSet.next()) {
            Librarian librarian = null;
            try {
                librarian = new Librarian();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            librarian.setName(resultSet.getString("LibrarianName"));
            librarian.setDob(resultSet.getString("LibrarianDOB"));
            librarian.setAddress(resultSet.getString("LibrarianAddress"));
            librarian.setGender(resultSet.getString("LibrarianGender"));
            librarian.setPhoneNumber(resultSet.getString("LibrarianPhoneNumber"));
            librarian.setEmail(resultSet.getString("LibrarianEmail"));
            librarian.setIdNumber(resultSet.getString("LibrarianIdentificationNumber"));
            librarian.setDepartment(resultSet.getString("LibrarianDepartment"));
            librarian.setPosition(resultSet.getString("LibrarianPosition"));
            librarian.setWorkplace(resultSet.getString("LibrarianWorkplace"));

            librarians.add(librarian);
        }

        return librarians;
    }
}
