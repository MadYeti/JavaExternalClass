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



    }

}
