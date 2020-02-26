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
                model.chooseFighters();
                showFighters();
                if(authorizationController.isAdmin()){
                    Model adminModel = new AdminModel();
                    View adminView = new AdminView();
                    setModel(adminModel);
                    setView(adminView);
                    ((AdminView)view).printOperationsToDo();
                    performAdminOperations();
                }else{
                    startBattle();
                }
            }else{
                launchProgram();
            }
        }
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

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void showFighters(){
        for(int i = 0; i < model.getFighters().length; i++){
            view.printFighters(i+1, model.getFighters()[i].getName());
        }
    }

    public int pickDroidNumber(){
        int number = 0;
        try {
            while(true){
                number = ((AdminModel) model).pickNumber();
                if (number == 1|| number == 2 || number == 3 || number == 4){
                    return number;
                }
                ((AdminView)view).printIncorrectNumber();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            ((AdminView)view).printIncorrectNumber();
        }
        return number;
    }

    public void performAdminOperations(){
        int number = pickDroidNumber();
        switch(((AdminModel) model).enterAdminOperation()){
            case "read":
                ((AdminView) view).printReadOperation();
                ((AdminView) view).printDroidIndicators(((AdminModel) model).getDroidByNumber(number).getName(),
                        ((AdminModel) model).getDroidByNumber(number).getAttackDamage(),
                        ((AdminModel) model).getDroidByNumber(number).getHealth(),
                        ((AdminModel) model).getDroidByNumber(number).getArmor());
                break;
            case "add":
                ((AdminView) view).printAddOperation();
                pickDroidNumber();
                break;
            case "modify":
                ((AdminView) view).printModifyOperation();
                pickDroidNumber();
                break;
            case "delete":
                ((AdminView) view).printDeleteOperation();
                pickDroidNumber();
                break;
        }
    }
}
