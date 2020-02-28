package battle.factory;

import battle.droids.*;

/**
 * Created by MadYeti on 22.02.2020.
 */
public class DroidFactory {

    public static Droid createDroid(String droidType){
        Droid droid = null;
        if(droidType.equals("BattleDroid")){
            droid = new BattleDroid();
        }else if (droidType.equals("RepairDroid")){
            droid = new RepairDroid();
        }else if (droidType.equals("TankDroid")){
            droid = new TankDroid();
        }else if(droidType.equals("SuperBattleDroid")){
            droid = new SuperBattleDroid();
        }
        return droid;
    }

    public static Droid createDroidWithParameters(String droidType, int attackDamage, int armor, int health){
        Droid droid = null;
        if(droidType.equals("BattleDroid")){
            droid = new BattleDroid(attackDamage, armor, health);
        }else if (droidType.equals("RepairDroid")){
            droid = new RepairDroid(attackDamage, armor, health);
        }else if (droidType.equals("TankDroid")){
            droid = new TankDroid(attackDamage, armor, health);
        }else if(droidType.equals("SuperBattleDroid")){
            droid = new SuperBattleDroid(attackDamage, armor, health);
        }
        return droid;
    }

}
