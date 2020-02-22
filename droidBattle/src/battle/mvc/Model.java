package battle.mvc;

import battle.droids.*;
import battle.factory.DroidFactory;

/**
 * Created by MadYeti on 18.02.2020.
 */
public class Model {

    private final int FIGHTERS_AMOUNT = 4;

    private String[] robotCollection = {"BattleDroid", "RepairDroid", "TankDroid", "SuperBattleDroid"};
    private Droid[] fighters = new Droid[FIGHTERS_AMOUNT];
    private Droid winnerOfTheFirstRound = null;
    private Droid winnerOfTheSecondRound = null;

    public Droid[] getFighters() {
        return fighters;
    }

    public void chooseFighters(){
        for(int i = 0; i < FIGHTERS_AMOUNT; i++){
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
                return winnerOfTheFirstRound;
            }
            winnerOfTheSecondRound.performAttack(winnerOfTheFirstRound, winnerOfTheSecondRound.getAttackDamage());
            if(winnerOfTheFirstRound.getHealth() <= 0){
                return winnerOfTheSecondRound;
            }
        }
    }
}
