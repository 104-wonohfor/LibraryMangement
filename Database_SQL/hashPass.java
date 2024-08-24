package Database_SQL;

import org.mindrot.jbcrypt.BCrypt;

public class hashPass {
    public static void main(String[] args) {
        //String passWord = "sinhvienHUST@2021";
        String passWord = "LibrarianHUST@2024";
        // String passWord = "1";
        //String passWord = "ReaderHUST@2024";

        // Fixed salt
        String fixedSalt = "$2a$10$eImiTXuWVxfM37uY4JANjQ"; 

        // Hash password using BCrypt with fixed salt
        String hashedPassword = BCrypt.hashpw(passWord, fixedSalt);

        // Print hashed password
        System.out.println(hashedPassword);
    }
}