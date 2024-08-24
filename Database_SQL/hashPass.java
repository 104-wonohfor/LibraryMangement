package Data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hashPass {
    public static void main(String[] args) {
        // String passWord = "sinhvienHUST@2021";
        String passWord = "DocGia00";
        // String passWord = "LibrarianHUST@2024";
        // String passWord = "1";
        // String passWord = "ReaderHUST@2024";

        byte[] temp = passWord.getBytes();
        byte[] hashData = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hashData = md.digest(temp);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        StringBuilder hashPass = new StringBuilder();

        for (byte item : hashData) {
            hashPass.append(Byte.toUnsignedInt(item));
        }

        System.out.println(hashPass.toString());
    }
}