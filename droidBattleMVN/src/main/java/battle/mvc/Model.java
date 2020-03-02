package battle.mvc;

import battle.droids.*;
import battle.factory.DroidFactory;
import battle.registration.RegistrationController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by MadYeti on 18.02.2020.
 */
public class Model {

    private final int FIGHTERS_AMOUNT = 4;

    private String[] robotCollection = {"BattleDroid", "RepairDroid", "TankDroid", "SuperBattleDroid"};
    private Droid[] fighters = new Droid[FIGHTERS_AMOUNT];
    private Droid winnerOfTheFirstRound = null;
    private Droid winnerOfTheSecondRound = null;
    private Droid champion = null;
    private String login = null;
    private String password = null;
    protected BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private Droid yourDroid = null;

    public Model(){

    }

    public Droid[] getFighters() {
        return fighters;
    }

    public void chooseFighters(){
        for(int i = 0; i < fighters.length; i++){
            int random = (int)(Math.random() * robotCollection.length);
            fighters[i] = DroidFactory.createDroid(robotCollection[random]);
        }
    }

    public Droid firstRoundFight(){
        while(true){
            fighters[0].performAttack(fighters[1], fighters[0].getAttackDamage());
            if(fighters[1].getHealth() <= 0){
                winnerOfTheFirstRound = fighters[0];
                break;
            }
            fighters[1].performAttack(fighters[0], fighters[1].getAttackDamage());
            if(fighters[0].getHealth() <= 0){
                winnerOfTheFirstRound = fighters[1];
                break;
            }
        }
        return winnerOfTheFirstRound;
    }

    public Droid secondRoundFight(){
        while(true){
            fighters[2].performAttack(fighters[3], fighters[2].getAttackDamage());
            if(fighters[3].getHealth() <= 0){
                winnerOfTheSecondRound = fighters[2];
                break;
            }
            fighters[3].performAttack(fighters[2], fighters[3].getAttackDamage());
            if(fighters[2].getHealth() <= 0){
                winnerOfTheSecondRound = fighters[3];
                break;
            }
        }
        return winnerOfTheSecondRound;
    }

    public Droid finalRoundFight(){
        while(true){
            winnerOfTheFirstRound.performAttack(winnerOfTheSecondRound, winnerOfTheFirstRound.getAttackDamage());
            if(winnerOfTheSecondRound.getHealth() <= 0){
                return champion = winnerOfTheFirstRound;
            }
            winnerOfTheSecondRound.performAttack(winnerOfTheFirstRound, winnerOfTheSecondRound.getAttackDamage());
            if(winnerOfTheFirstRound.getHealth() <= 0){
                return champion = winnerOfTheSecondRound;
            }
        }
    }

    public String logInProcess() throws IOException {
        return bufferedReader.readLine();
    }

    public String enterLogin(){
        try {
            login = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return login;
    }

    public String enterPassword(){
        try {
            password = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Droid pickYourDroid() throws IOException {
        int number = Integer.parseInt(bufferedReader.readLine());
        if(number >= 1 && number <= 4){
            return yourDroid = fighters[number - 1];
        }
        return yourDroid;
    }

    public Droid getYourDroid() {
        return yourDroid;
    }

    public Droid getChampion() {
        return champion;
    }

    public String getSelectedLanguage() throws IOException {
        return bufferedReader.readLine();
    }

    public void registrationIssue(){
        RegistrationController registrationController = new RegistrationController();
        registrationController.registrateUser(login, password);
    }
}
