package org.mycompany.controllers.registration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegistrationDataController {

    private String errorEmailMessage = null;
    private String errorPasswordMessage = null;
    private String errorRetypedPasswordMessage = null;
    private boolean error = false;
    private static Logger logger = Logger.getLogger(RegistrationDataController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }


    public RegistrationDataController(){

    }

    public static String getHash(String data){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
        md5.update(StandardCharsets.UTF_8.encode(data));
        String hash = String.format("%032x", new BigInteger(md5.digest()));
        md5.reset();
        return hash;
    }

    public static boolean validatePassword(String password) {
        String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[~!*()_<>@#$%^&+=]).{6,}";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static boolean validateEmail(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public boolean validateData(String email, String password, String retypedPassword){

        if(email.isEmpty()) {
            error = true;
            errorEmailMessage = "1";
        }else if(email.length() > 40) {
            error = true;
            errorEmailMessage = "2";
        }else if(!validateEmail(email)) {
            error = true;
            errorEmailMessage = "3";
        }

        if(password.isEmpty()) {
            error = true;
            errorPasswordMessage = "1";
        }else if(password.length() < 6) {
            error = true;
            errorPasswordMessage = "2";
        }else if(password.length() > 40) {
            error = true;
            errorPasswordMessage = "3";
        }else if(!validatePassword(password)){
            error = true;
            errorPasswordMessage = "4";
        }

        if(retypedPassword.isEmpty()) {
            error = true;
            errorRetypedPasswordMessage = "1";
        }else if(!retypedPassword.equals(password)) {
            error = true;
            errorRetypedPasswordMessage = "2";
        }

        return error;
    }

    public boolean validatePasswords(String password, String retypedPassword){
        if(password.isEmpty()) {
            error = true;
            errorPasswordMessage = "1";
        }else if(password.length() < 6) {
            error = true;
            errorPasswordMessage = "2";
        }else if(password.length() > 40) {
            error = true;
            errorPasswordMessage = "3";
        }else if(!validatePassword(password)){
            error = true;
            errorPasswordMessage = "4";
        }

        if(retypedPassword.isEmpty()) {
            error = true;
            errorRetypedPasswordMessage = "1";
        }else if(!retypedPassword.equals(password)) {
            error = true;
            errorRetypedPasswordMessage = "2";
        }

        return error;
    }

    public String getErrorEmailMessage() {
        return errorEmailMessage;
    }

    public String getErrorPasswordMessage() {
        return errorPasswordMessage;
    }

    public String getErrorRetypedPasswordMessage() {
        return errorRetypedPasswordMessage;
    }
}
