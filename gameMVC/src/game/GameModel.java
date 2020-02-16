package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MadYeti on 06.02.2020.
 */
public class GameModel {

    private int lowerLimit;
    private int higherLimit;
    private int numberToFind;
    private int guessNumber;
    private int triesAmount;
    private boolean isNumberFinded;
    private List<Integer> mentionedNumbers = null;

    public GameModel(){
        isNumberFinded = false;
        triesAmount = 0;
        mentionedNumbers = new ArrayList<Integer>();
    }

    public boolean isNumberFinded(){
        return isNumberFinded;
    }

    public void setNumberFinded(boolean isNumberFinded){
        this.isNumberFinded = isNumberFinded;
    }

    public int getNumberToFind() {
        return numberToFind;
    }

    public List<Integer> getMentionedNumbers(){
        return mentionedNumbers;
    }

    public void setNumberToFind(int numberToFind){
        this.numberToFind = numberToFind;
    }

    public int getTriesAmount(){
        return triesAmount;
    }

    public void increaseTriesAmount(){
        triesAmount++;
    }

    public void addMentionedNumberToList(int mentionedNumber){
        mentionedNumbers.add(mentionedNumber);
    }

    public int getLowerLimit(){
        return lowerLimit;
    }

    public int getHigherLimit(){
        return higherLimit;
    }

    public void setLowerLimit(int lowerLimit){
        this.lowerLimit = lowerLimit;
    }

    public void setHigherLimit(int higherLimit){
        this.higherLimit = higherLimit;
    }

    public int getGuessNumber(){
        return guessNumber;
    }

    public void setGuessNumber(int guessNumber){
        this.guessNumber = guessNumber;
    }

    public boolean checkNumberIsFinded(int guessNumber){
        if(numberToFind == guessNumber){
            return true;
        }
        return false;
    }
}
