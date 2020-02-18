package battle;

/**
 * Created by MadYeti on 18.02.2020.
 */
public class View {

    public static final String SPACE_SING = " ";

    public static final String FIRST_ROUND_WINNER = " wins first round";
    public static final String SECOND_ROUND_WINNER = " wins second round";
    public static final String CHAMPION_ANNOUNCED = " is the champion";
    public static final String FIGHTER_NUMBER = "Fighter #";

    public View(){

    }

    public void printMessage(String message){
        System.out.println(message);
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

}
