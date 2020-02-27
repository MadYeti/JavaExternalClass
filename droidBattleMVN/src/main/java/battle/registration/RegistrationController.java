package battle.registration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MadYeti on 22.02.2020.
 */
public class RegistrationController {

    public RegistrationController(){

    }

    public static String getHashPassword(String password){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(StandardCharsets.UTF_8.encode(password));
        String hash = String.format("%032x", new BigInteger(md5.digest()));
        md5.reset();
        return hash;
    }

    public void registrateUser(String login, String password){
        try {
            FileWriter fileWriter = new FileWriter(new File("users.txt"), true);
            fileWriter.write(login + ";" + getHashPassword(password) + "\r\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validatePassword(String password) {
        String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[~!*()_<>@#$%^&+=]).{6,}";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

}
