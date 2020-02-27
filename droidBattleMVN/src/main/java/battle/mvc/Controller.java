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
    private AdminModel adminModel;
    private AdminView adminView;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void launchProgram() {
        view.printWelcomeWindow();
        String logInParameter = logInIssue();
        view.printInputLogin();
        model.enterLogin();
        view.printInputPassword();
        if(logInParameter.equals("reg")){
            while (!RegistrationController.validatePassword(model.enterPassword())){
                view.printInvalidPassword();
            }
            model.registrationIssue();
            view.printRegistrationComplete();
            launchProgram();
        }else if(logInParameter.equals("auth")) {
            model.enterPassword();
            AuthorizationController authorizationController = new AuthorizationController();
            if(authorizationController.authorizeUser(model.getLogin(), model.getPassword())){
                showFighters();
                if(authorizationController.isAdmin()){
                    adminModel = new AdminModel();
                    adminView = new AdminView();
                    adminView.printOperationsToDo();
                    performAdminOperations();
                    launchProgram();
                }else{
                    startBattle();
                }
            }else{
                launchProgram();
            }
        }
    }

    public void initFighters(){
        model.chooseFighters();
    }

    public String logInIssue(){
        String logInParameter = null;
        try {
            logInParameter = model.logInProcess();
            while(true) {
                if(logInParameter.equals("reg") || logInParameter.equals("auth")){
                   break;
                }
                view.printVerificationWrongParameter();
                logInParameter = model.logInProcess();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logInParameter;
    }

    public void startBattle(){
        view.printPickingDroid();
        try {
            while (model.pickYourDroid() == null) {
                view.printInvalidPickingDroidInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            view.printInvalidPickingDroidInput();
        }
        view.printWinnerOfTheFirstRound(model.firstRoundFight().getName());
        view.printWinnerOfTheSecondRound(model.secondRoundFight().getName());
        view.printWinnerOfTheBattle(model.finalRoundFight().getName());
        if(model.getYourDroid().equals(model.getChampion())){
            view.printYourDroidChampion();
            return;
        }
        view.printYourDroidLoser();
    }

    public void showFighters(){
        for(int i = 0; i < model.getFighters().length; i++){
            view.printFighters(i+1, model.getFighters()[i].getName());
        }
    }

    public int pickDroidNumber(){
        int number = 0;
        while(true) {
            try {
                number = adminModel.pickNumber();
                if (number == 1 || number == 2 || number == 3 || number == 4) {
                    return number;
                }
                adminView.printIncorrectNumber();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                adminView.printIncorrectNumber();
            }
        }
    }

    public void performAdminOperations(){
        switch(adminModel.enterAdminOperation()){
            case "read":
                adminView.printReadOperation();
                int number = pickDroidNumber();
                try {
                    adminView.printDroidIndicators(model.getFighters()[number - 1].getName(),
                            model.getFighters()[number - 1].getAttackDamage(),
                            model.getFighters()[number - 1].getHealth(),
                            model.getFighters()[number - 1].getArmor());
                }catch(NullPointerException e){
                    adminView.printNullDroid();
                }
                break;
            case "add":
                adminView.printAddOperationNumber();
                int numberToReplace = pickDroidNumber();
                adminView.printAddOperation();
                model.getFighters()[numberToReplace - 1] = adminModel.createDroid(adminModel.enterDroidName());
                showFighters();
                break;
            case "modify":
                try {
                    adminView.printModifyOperation();
                    int droidNumber = pickDroidNumber();
                    adminView.printSetDroidAttack();
                    try {
                        model.getFighters()[droidNumber - 1].setAttackDamage(adminModel.setIndicator());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        adminView.printNotANumber();
                        model.getFighters()[droidNumber - 1].setAttackDamage(10);
                    }
                    adminView.printSetDroidArmor();
                    try {
                        model.getFighters()[droidNumber - 1].setArmor(adminModel.setIndicator());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        adminView.printNotANumber();
                        model.getFighters()[droidNumber - 1].setArmor(10);
                    }
                    adminView.printSetDroidHealth();
                    try {
                        model.getFighters()[droidNumber - 1].setHealth(adminModel.setIndicator());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        adminView.printNotANumber();
                        model.getFighters()[droidNumber - 1].setHealth(10);
                    }
                }catch (NullPointerException e){
                    adminView.printNullDroid();
                }
                break;
            case "delete":
                adminView.printDeleteOperation();
                int numberToDelete = pickDroidNumber();
                model.getFighters()[numberToDelete - 1].setName("DeletedDroid");
                model.getFighters()[numberToDelete - 1].setHealth(0);
                model.getFighters()[numberToDelete - 1].setArmor(0);
                model.getFighters()[numberToDelete - 1].setAttackDamage(0);
                adminView.printDeleteDroid();
                break;
        }
    }
}
