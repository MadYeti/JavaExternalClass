package battle.mvc;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MadYeti on 18.02.2020.
 */
public class View {

    protected static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("messages", new Locale("en", "EN"));

    public static final String SPACE_SING = "_";

    public static final String FIRST_ROUND_WINNER = "output.winner.first.round";
    public static final String SELECT_LANGUAGE = "input.pick.language";
    public static final String SECOND_ROUND_WINNER = "output.winner.second.round";
    public static final String CHAMPION_ANNOUNCED = "output.champion";
    public static final String FIGHTER_NUMBER = "output.fighter.number";
    public static final String WELCOME_WINDOW = "input.start.operation";
    public static final String VERIFICATION_WRONG_PARAMETER = "wrong.input.start.operation";
    public static final String INPUT_LOGIN = "input.login";
    public static final String INPUT_PASSWORD = "input.password";
    public static final String INVALID_PASSWORD_MESSAGE = "output.wrong.password.message";
    public static final String REGISTRATION_COMPLETE = "output.registration.complete";
    public static final String PICKING_DROID = "input.droid";
    public static final String INVALID_PICKING_DROID_INPUT = "wrong.input.droid.number";
    public static final String YOUR_DROID_CHAMPION = "output.your.droid.champion";
    public static final String YOUR_DROID_LOSER = "output.your.droid.loser";
    public static final String INVALID_LANGUAGE = "output.invalid.language";

    public View(){

    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printYourDroidChampion(){
        printMessage(resourceBundle.getString(YOUR_DROID_CHAMPION));
    }

    public void printInvalidLanguage(){
        printMessage(resourceBundle.getString(INVALID_LANGUAGE));
    }

    public void printSelectLanguage(){
        printMessage(resourceBundle.getString(SELECT_LANGUAGE));
    }

    public void printYourDroidLoser(){
        printMessage(resourceBundle.getString(YOUR_DROID_LOSER));
    }

    public void printPickingDroid(){
        printMessage(resourceBundle.getString(PICKING_DROID));
    }

    public void printInvalidPickingDroidInput(){
        printMessage(resourceBundle.getString(INVALID_PICKING_DROID_INPUT));
    }

    public void printFighters(int fighterNumber, String fighterName){
        printMessage(resourceBundle.getString(FIGHTER_NUMBER) + fighterNumber + resourceBundle.getString(SPACE_SING) + fighterName);
    }

    public void printWinnerOfTheFirstRound(String fighterName){
        printMessage(fighterName + resourceBundle.getString(FIRST_ROUND_WINNER));
    }

    public void printWinnerOfTheSecondRound(String fighterName){
        printMessage(fighterName + resourceBundle.getString(SECOND_ROUND_WINNER));
    }

    public void printWinnerOfTheBattle(String fighterName){
        printMessage(fighterName + resourceBundle.getString(CHAMPION_ANNOUNCED));
    }

    public void printWelcomeWindow(){
        printMessage(resourceBundle.getString(WELCOME_WINDOW));
    }

    public void printVerificationWrongParameter(){
        printMessage(resourceBundle.getString(VERIFICATION_WRONG_PARAMETER));
    }

    public void printInputLogin(){
        printMessage(resourceBundle.getString(INPUT_LOGIN));
    }

    public void printInputPassword(){
        printMessage(resourceBundle.getString(INPUT_PASSWORD));
    }

    public void printRegistrationComplete(){
        printMessage(resourceBundle.getString(REGISTRATION_COMPLETE));
    }

    public void printInvalidPassword(){
        printMessage(resourceBundle.getString(INVALID_PASSWORD_MESSAGE));
    }

    public static void setResourceBundle(ResourceBundle resourceBundle) {
        View.resourceBundle = resourceBundle;
    }
}
