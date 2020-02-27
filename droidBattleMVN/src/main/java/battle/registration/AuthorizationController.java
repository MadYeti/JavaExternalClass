package battle.registration;

import java.io.*;

/**
 * Created by MadYeti on 23.02.2020.
 */
public class AuthorizationController {

    private boolean isAdmin = false;

    public AuthorizationController(){

    }

    public boolean authorizeUser(String login, String password){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("users.txt")));
            String data;
            while ((data = bufferedReader.readLine()) != null){
                if(data.equals(login + ";" + RegistrationController.getHashPassword(password))){
                    isAdmin = false;
                    return true;
                }
                if(login.equals("admin") && password.equals("admin")){
                    isAdmin = true;
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
