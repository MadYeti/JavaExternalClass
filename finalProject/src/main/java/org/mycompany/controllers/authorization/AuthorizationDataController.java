package org.mycompany.controllers.authorization;


import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthorizationDataController {

    public AuthorizationDataController(){

    }

    public boolean validateData(String email, String password){
        return !(email.isEmpty() || email.length() > 40 || !validateEmail(email) ||
                password.isEmpty() || password.length() < 6 || password.length() > 40);
    }

    public static boolean validateEmail(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

}
