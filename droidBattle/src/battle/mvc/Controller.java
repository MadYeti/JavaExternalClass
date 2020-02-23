package battle.mvc;

import battle.registration.AuthorizationController;
import battle.registration.RegistrationController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MadYeti on 18.02.2020.
 */
public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void launchProgram(){
        view.printWelcomeWindow();
        int logInParameter = logInProcces();
        String[] data = inputLoginAndPassword();
        String login = data[0];
        String password = data[1];
        if(logInParameter == 1){
            RegistrationController registrationController = new RegistrationController();
            registrationController.registrateUser(login, password);
        }else {
            AuthorizationController authorizationController = new AuthorizationController();
            if(authorizationController.authorizeUser(login, password)){
                System.out.println("SUCCESS");
            }else{
                System.out.println("ERROR");
            }
        }
    }

    public String[] inputLoginAndPassword(){
        String[] data = new String[2];
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input your login:");
            data[0] = bufferedReader.readLine();
            System.out.println("Input your password:");
            data[1] = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean validatePassword(String password) {
        String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[~!*()_<>@#$%^&+=]).{6,}";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public void startBattle(){
        model.chooseFighters();
        for(int i = 0; i < model.getFighters().length; i++){
            view.printFighters(i+1, model.getFighters()[i].getName());
        }
        view.printWinnerOfTheFirstRound(model.firstRoundFight().getName());
        view.printWinnerOfTheSecondRound(model.secondRoundFight().getName());
        view.printWinnerOfTheBattle(model.finalRoundFight().getName());
    }

    public int logInProcces(){
        int number = 0;
        while(true){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                number = Integer.parseInt(bufferedReader.readLine());
                if(number == 1 || number == 2){
                    return number;
                }
                view.printVerificationWrongNumber();
            } catch (Exception e) {
                view.printVerificationWrongNumber();
            }
        }
    }

}
