package battle;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class Main {

    public static void main(String[] args) {

        String[] robotCollection = {"BattleDroid", "RepairDroid", "TankDroid", "SuperBattleDroid"};
        Droid[] fighters = new Droid[4];

        for(int i = 0; i < 4; i++){
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
            System.out.println("Fighter #" + (i+1) + " " + fighters[i].getName());
        }

        Droid winnerOfTheFirstRound = null;
        Droid winnerOfTheSecondRound = null;

        //method fight(Droid droid1, Droid droid2)
        //first round
        while(true){
            fighters[0].attack(fighters[1]);
            if(fighters[1].getHealth() <= 0){
                System.out.println(fighters[0].getName() + " wins first round");
                winnerOfTheFirstRound = fighters[0];
                break;
            }
            fighters[1].attack(fighters[0]);
            if(fighters[0].getHealth() <= 0){
                System.out.println(fighters[1].getName() + " wins first round");
                winnerOfTheFirstRound = fighters[1];
                break;
            }
        }

        //second round
        while(true){
            fighters[2].attack(fighters[3]);
            if(fighters[3].getHealth() <= 0){
                System.out.println(fighters[2].getName() + " wins second round");
                winnerOfTheSecondRound = fighters[2];
                break;
            }
            fighters[3].attack(fighters[2]);
            if(fighters[2].getHealth() <= 0){
                System.out.println(fighters[3].getName() + " wins second round");
                winnerOfTheSecondRound = fighters[3];
                break;
            }
        }

        //final round
        while(true){
            winnerOfTheFirstRound.attack(winnerOfTheSecondRound);
            if(winnerOfTheSecondRound.getHealth() <= 0){
                System.out.println(winnerOfTheFirstRound.getName() + " is the champion");
                break;
            }
            winnerOfTheSecondRound.attack(winnerOfTheFirstRound);
            if(winnerOfTheFirstRound.getHealth() <= 0){
                System.out.println(winnerOfTheSecondRound.getName() + " is the champion");
                break;
            }
        }

    }

}
