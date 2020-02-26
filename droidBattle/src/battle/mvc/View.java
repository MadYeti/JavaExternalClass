package battle.mvc;

/**
 * Created by MadYeti on 18.02.2020.
 */
public class View {

    public static final String SPACE_SING = " ";

    public static final String FIRST_ROUND_WINNER = " wins first round";
    public static final String SECOND_ROUND_WINNER = " wins second round";
    public static final String CHAMPION_ANNOUNCED = " is the champion";
    public static final String FIGHTER_NUMBER = "Fighter #";
    public static final String WELCOME_WINDOW = "Enter \"reg\" if you like to registrate yourself or \"auth\" if you want to log in:";
    public static final String VERIFICATION_WRONG_PARAMETER = "Please enter \"reg\" to sign up or \"auth\" to sigh in:";
    public static final String INPUT_LOGIN = "Input your login:";
    public static final String INPUT_PASSWORD = "Input your password:";
    public static final String INVALID_PASSWORD_MESSAGE = "Invalid password. Your password must contain one lowercase letter, one uppercase letter, one number and one symbol ~!*()_<>@#$%^&+= and be atleast 6 characters long.";
    public static final String REGISTRATION_COMPLETE= "Registration complete successfully";
    public static final String PICKING_DROID= "Pick your droid";
    public static final String INVALID_PICKING_DROID_INPUT= "Enter 1, 2, 3 or 4 to pick your droid";
    public static final String YOUR_DROID_CHAMPION= "Congratulation your droid is the champion";
    public static final String YOUR_DROID_LOSER= "Sorry your droid is the loser";

    public View(){

    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printYourDroidChampion(){
        printMessage(YOUR_DROID_CHAMPION);
    }

    public void printYourDroidLoser(){
        printMessage(YOUR_DROID_LOSER);
    }

    public void printPickingDroid(){
        printMessage(PICKING_DROID);
    }

    public void printInvalidPickingDroidInput(){
        printMessage(INVALID_PICKING_DROID_INPUT);
    }

    public void printFighters(int fighterNumber, String fighterName){
        printMessage(FIGHTER_NUMBER + fighterNumber + SPACE_SING + fighterName);
    }

    public void printWinnerOfTheFirstRound(String fighterName){
        printMessage(fighterName + FIRST_ROUND_WINNER);
    }

    public void printWinnerOfTheSecondRound(String fighterName){
        printMessage(fighterName + SECOND_ROUND_WINNER);
    }

    public void printWinnerOfTheBattle(String fighterName){
        printMessage(fighterName + CHAMPION_ANNOUNCED);
    }

    public void printWelcomeWindow(){
        printMessage(WELCOME_WINDOW);
    }

    public void printVerificationWrongParameter(){
        printMessage(VERIFICATION_WRONG_PARAMETER);
    }

    public void printInputLogin(){
        printMessage(INPUT_LOGIN);
    }

    public void printInputPassword(){
        printMessage(INPUT_PASSWORD);
    }

    public void printRegistrationComplete(){
        printMessage(REGISTRATION_COMPLETE);
    }

    public void printInvalidPassword(){
        printMessage(INVALID_PASSWORD_MESSAGE);
    }
}
