package battle.helper;

import battle.droids.Droid;
import battle.factory.DroidFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MadYeti on 27.02.2020.
 */
public class HelperSerializable {

    public static void main(String[] args) {

        List<Droid> droids = new ArrayList<Droid>();
        droids.add(DroidFactory.createDroid("BattleDroid"));
        droids.add(DroidFactory.createDroid("RepairDroid"));
        droids.add(DroidFactory.createDroid("TankDroid"));
        droids.add(DroidFactory.createDroid("SuperBattleDroid"));


    }

}
