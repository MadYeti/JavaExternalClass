package battle.mvc;

import battle.registration.AuthorizationController;
import battle.registration.RegistrationController;

import java.io.IOException;

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

    public void launchProgram() {
        view.printWelcomeWindow();
        int logInParameter;
        while(true) {
            try {
                logInParameter = model.logInProcess();
                if(logInParameter == 1 || logInParameter == 2){
                    break;
                }
                view.printVerificationWrongNumber();
            } catch (IOException e) {
                e.printStackTrace();
            } catch(NumberFormatException e){
                view.printVerificationWrongNumber();
            }
        }
        view.printInputLogin();
        model.enterLogin();
        view.printInputPassword();
        if(logInParameter == 1){
            while (!RegistrationController.validatePassword(model.enterPassword())){
                view.printInvalidPassword();
            }
            RegistrationController registrationController = new RegistrationController();
            registrationController.registrateUser(model.getLogin(), model.getPassword());
            view.printRegistrationComplete();
            launchProgram();
        }else {
            model.enterPassword();
            AuthorizationController authorizationController = new AuthorizationController();
            if(authorizationController.authorizeUser(model.getLogin(), model.getPassword()) && authorizationController.isAdmin(model.getLogin(), model.getPassword())){
                this.setModel(new AdminModel());
                this.setView(new AdminView());
            }else if(authorizationController.authorizeUser(model.getLogin(), model.getPassword())){
                initFighters();
                view.printPickingDroid();
                try {
                    while (model.getYourDroid() != null) {
                        view.printInvalidPickingDroidInput();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    view.printInvalidPickingDroidInput();
                }
            }else{
                launchProgram();
            }
        }
    }

    public void startBattle(){
        view.printWinnerOfTheFirstRound(model.firstRoundFight().getName());
        view.printWinnerOfTheSecondRound(model.secondRoundFight().getName());
        view.printWinnerOfTheBattle(model.finalRoundFight().getName());
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void initFighters(){
        model.chooseFighters();
        for(int i = 0; i < model.getFighters().length; i++){
            view.printFighters(i+1, model.getFighters()[i].getName());
        }
    }
}
