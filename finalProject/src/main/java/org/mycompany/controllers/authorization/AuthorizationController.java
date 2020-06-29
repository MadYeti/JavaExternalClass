package org.mycompany.controllers.authorization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller used for authorization data validation
 */
public class AuthorizationController {

    public AuthorizationController(){

    }

    /**
     * Validate input email and password according to some criteria
     * @param email users email
     * @param password users password
     * @return true if data is correct, false otherwise
     */
    public boolean validateData(String email, String password){
        return !(email.isEmpty() || email.length() > 40 || !validateEmail(email) ||
                password.isEmpty() || password.length() < 6 || password.length() > 40);
    }

    /**
     * Checks if entered email matches with regex
     * @param email users email
     * @return true if email matched with regex, false otherwise
     */
    public static boolean validateEmail(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

}
