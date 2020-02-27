package battle.helper;

import battle.droids.Droid;
import battle.factory.DroidFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {

    public static void main(String[] args) {

        List<Droid> droids = new ArrayList<>();
        droids.add(DroidFactory.createDroid("BattleDroid"));
        droids.add(DroidFactory.createDroid("RepairDroid"));
        droids.add(DroidFactory.createDroid("TankDroid"));
        droids.add(DroidFactory.createDroid("SuperBattleDroid"));

        Arrays.sort(droids.toArray());

    }

}
