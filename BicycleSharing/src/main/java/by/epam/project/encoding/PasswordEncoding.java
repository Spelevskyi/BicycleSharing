package by.epam.project.encoding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.util.Constants;

public class PasswordEncoding {

    private static Logger logger = LogManager.getLogger(PasswordEncoding.class);

    /**
     * Method for encoding user password
     * 
     * @param password - user password value
     * @return
     */
    public static String encode(String password) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest md5 = MessageDigest.getInstance(Constants.ENCODING_TYPE);
            byte[] bytes = md5.digest(password.getBytes());
            for (byte b : bytes) {
                stringBuilder.append(String.format("%02X", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("Can not encode user password!");
        }
        return stringBuilder.toString();
    }
}
