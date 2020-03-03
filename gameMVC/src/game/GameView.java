package game;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MadYeti on 06.02.2020.
 */
public class GameView {

    private static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("messages", new Locale("en", "EN"));

    private static final String OPENS_SQUARE_BRACKET = "[";
    private static final String CLOSING_SQUARE_BRACKET = "]";
    private static final String COMMA_SING = ",";

    private static final String INPUT_LANGUAGE_NUMBER = "input.int.language.number";
    private static final String INPUT_NUMBER_TO_FIND = "input.number.to.find";
    private static final String WRONG_RANGE_DATA = "wrong.range.data";
    private static final String INPUT_NUMBER_BEEN_PICKED = "input.int.number.been.picked";
    private static final String WRONG_DATA_TYPE = "wrong.data.type";
    private static final String CONGRATULATION = "congratulation";
    private static final String INPUT_NUMBER_GRATER = "input.number.grater";
    private static final String INPUT_NUMBER_LOWER = "input.number.lower";
    private static final String GAME_DETAILS_INPUT_NUMBER = "game.details.input.number";
    private static final String GAME_DETAILS_PICKED_NUMBERS = "game.details.picked.numbers";
    private static final String GAME_DETAILS_RANGE = "game.details.range";
    private static final String GAME_DETAILS_ATTEMPTS_AMOUNT = "game.details.attempts.amount";

    public GameView(){

    }

    public static void setResourceBundle(ResourceBundle resourceBundle) {
        GameView.resourceBundle = resourceBundle;
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printNumberToFindMessage(){
        printMessage(resourceBundle.getString(INPUT_NUMBER_TO_FIND));
    }

    public void printChooseLanguageOption(){
        printMessage(resourceBundle.getString(INPUT_LANGUAGE_NUMBER));
    }

    public void printNumberOutOfRange(){
        printMessage(resourceBundle.getString(WRONG_RANGE_DATA));
    }

    public void printNumberAlreadyBeenPicked(){
        printMessage(resourceBundle.getString(INPUT_NUMBER_BEEN_PICKED));
    }

    public void printIncorrectInputType(){
        printMessage(resourceBundle.getString(WRONG_DATA_TYPE));
    }

    public void printCongratulation(){
        printMessage(resourceBundle.getString(CONGRATULATION));
    }

    public void printPickedNumberIsUpward(){
        printMessage(resourceBundle.getString(INPUT_NUMBER_GRATER));
    }

    public void printPickedNumberIsUnder(){
        printMessage(resourceBundle.getString(INPUT_NUMBER_LOWER));
    }

    public void printGameDetails(int choosenNumber, List<Integer> mentionedNumbers, int lowerLimit, int higherLimit, int triesAmount){

        printMessage(resourceBundle.getString(GAME_DETAILS_INPUT_NUMBER) + choosenNumber + "\n"
                    + resourceBundle.getString(GAME_DETAILS_PICKED_NUMBERS) + Arrays.toString(mentionedNumbers.toArray()) + "\n"
                    + resourceBundle.getString(GAME_DETAILS_RANGE)
                    + resourceBundle.getString(OPENS_SQUARE_BRACKET) + lowerLimit
                    + resourceBundle.getString(COMMA_SING) + higherLimit
                    + resourceBundle.getString(CLOSING_SQUARE_BRACKET) + "\n"
                    + resourceBundle.getString(GAME_DETAILS_ATTEMPTS_AMOUNT) + triesAmount);
    }

}
