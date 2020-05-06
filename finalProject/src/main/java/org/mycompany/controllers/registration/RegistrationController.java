package org.mycompany.controllers.registration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController {

    private String errorEmailMessage = null;
    private String errorPasswordMessage = null;
    private String errorRetypedPasswordMessage = null;
    private boolean error = false;
    private static Logger logger = Logger.getLogger(RegistrationController.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }


    public RegistrationController(){

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
            errorEmailMessage = "Email is empty";
        }else if(email.length() > 40) {
            error = true;
            errorEmailMessage = "Email is too long";
        }else if(!validateEmail(email)) {
            error = true;
            errorEmailMessage = "Email is incorrect";
        }

        if(password.isEmpty()) {
            error = true;
            errorPasswordMessage = "Password is empty";
        }else if(password.length() < 6) {
            error = true;
            errorPasswordMessage = "Password must be atleast 6 characters long or longer";
        }else if(password.length() > 40) {
            error = true;
            errorPasswordMessage = "Password is too long";
        }else if(!validatePassword(password)){
            error = true;
            errorEmailMessage = "Invalid password. Your password must contain one lowercase letter, one uppercase letter, one number and one symbol ~!*()_<>@#$%^&+= and be atleast 6 characters long.";
        }

        if(retypedPassword.isEmpty()) {
            error = true;
            errorRetypedPasswordMessage = "Retyped password is empty";
        }else if(!retypedPassword.equals(password)) {
            error = true;
            errorRetypedPasswordMessage = "Retyped password is not matched with password field";
        }

        return error;
    }

    public boolean validatePasswords(String password, String retypedPassword){
        if(password.isEmpty()) {
            error = true;
            errorPasswordMessage = "Password is empty";
        }else if(password.length() < 6) {
            error = true;
            errorPasswordMessage = "Password must be atleast 6 characters long or longer";
        }else if(password.length() > 40) {
            error = true;
            errorPasswordMessage = "Password is too long";
        }else if(!validatePassword(password)){
            error = true;
            errorEmailMessage = "Invalid password. Your password must contain one lowercase letter, one uppercase letter, one number and one symbol ~!*()_<>@#$%^&+= and be atleast 6 characters long.";
        }

        if(retypedPassword.isEmpty()) {
            error = true;
            errorRetypedPasswordMessage = "Retyped password is empty";
        }else if(!retypedPassword.equals(password)) {
            error = true;
            errorRetypedPasswordMessage = "Retyped password is not matched with password field";
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
