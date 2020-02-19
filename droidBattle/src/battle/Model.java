package battle;

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
            int random = (int)(Math.random() * robotCollection.length + 1);
            switch(random){
                case 1:
                    fighters[i] = new BattleDroid();
                    break;
                case 2:
                    fighters[i] = new RepairDroid();
                    break;
                case 3:
                    fighters[i] = new TankDroid();
                    break;
                case 4:
                    fighters[i] = new SuperBattleDroid();
                    break;
                default:
                    break;
            }
        }
    }

    public Droid firstRoundFight(){
        while(true){
            fighters[0].attack(fighters[1]);
            if(fighters[1].getHealth() <= 0){
                winnerOfTheFirstRound = fighters[0];
                break;
            }
            fighters[1].attack(fighters[0]);
            if(fighters[0].getHealth() <= 0){
                winnerOfTheFirstRound = fighters[1];
                break;
            }
        }
        return winnerOfTheFirstRound;
    }

    public Droid secondRoundFight(){
        while(true){
            fighters[2].attack(fighters[3]);
            if(fighters[3].getHealth() <= 0){
                winnerOfTheSecondRound = fighters[2];
                break;
            }
            fighters[3].attack(fighters[2]);
            if(fighters[2].getHealth() <= 0){
                winnerOfTheSecondRound = fighters[3];
                break;
            }
        }
        return winnerOfTheSecondRound;
    }

    public Droid finalRoundFight(){
        while(true){
            winnerOfTheFirstRound.attack(winnerOfTheSecondRound);
            if(winnerOfTheSecondRound.getHealth() <= 0){
                return winnerOfTheSecondRound;
            }
            winnerOfTheSecondRound.attack(winnerOfTheFirstRound);
            if(winnerOfTheFirstRound.getHealth() <= 0){
                return winnerOfTheSecondRound;
            }
        }
    }
}
