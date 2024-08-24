/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Thang
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    public static Connection DAO_DB(){
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databasename=QuanLyThuVien;"
                    + "username=sa;password=123456789;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(dbURL);
            
//            if (conn != null) {
//                System.out.println("Ket noi toi SQL sever thanh cong");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        DAO_DB();
    }
}
