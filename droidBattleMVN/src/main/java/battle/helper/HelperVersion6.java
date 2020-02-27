package battle.helper;

import battle.droids.Droid;
import battle.factory.DroidFactory;
import battle.interfaces.Friendable;
import battle.interfaces.Unfriendable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelperVersion6 {

    public static void main(String[] args) {

        Droid[] droids = new Droid[4];
        droids[0] = (DroidFactory.createDroid("BattleDroid"));
        droids[1] = (DroidFactory.createDroid("RepairDroid"));
        droids[2] = (DroidFactory.createDroid("TankDroid"));
        droids[3] = (DroidFactory.createDroid("SuperBattleDroid"));

        //Grouping by interfaces
        List<Friendable> friendableList = new ArrayList<Friendable>();
        List<Unfriendable> unfriendableList = new ArrayList<Unfriendable>();
        for(Droid droid: droids){
            if(droid instanceof Friendable){
                friendableList.add((Friendable) droid);
            }
            if(droid instanceof Unfriendable){
                unfriendableList.add((Unfriendable) droid);
            }
        }
        System.out.println(Arrays.toString(friendableList.toArray()));
        System.out.println(Arrays.toString(unfriendableList.toArray()));

        //Sorting
        System.out.println(Arrays.toString(droids));
        Arrays.sort(droids);
        System.out.println(Arrays.toString(droids));


    }

}
