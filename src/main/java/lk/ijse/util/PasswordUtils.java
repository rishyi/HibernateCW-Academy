package lk.ijse.util;

import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

    public class PasswordUtils {
        // Encrypt the password before saving it in the database
        public static String encryptPassword(String password) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }

        // Verify the password entered during login
        public static boolean checkPassword(String inputPassword, String hashedPassword) {
            if (hashedPassword == null || hashedPassword.isEmpty()) {
                throw new IllegalArgumentException("Stored password is null or empty");
            }
            return BCrypt.checkpw(inputPassword, hashedPassword);
        }

        }




