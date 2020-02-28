package battle.helper;

import battle.droids.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MadYeti on 28.02.2020.
 */
public class BuilderPattern {

    public static void main(String[] args) {

        Droid battleDroid = new BattleDroid().addAttackDamage(50).addArmor(10).addHealth(150).build();
        Droid repairDroid = new RepairDroid().addAttackDamage(30).addArmor(12).addHealth(130).build();
        Droid superBattleDroid = new SuperBattleDroid().addAttackDamage(70).addArmor(5).addHealth(100).build();
        Droid tankDroid = new TankDroid().addAttackDamage(40).addArmor(20).addHealth(200).build();

        List<Droid> droids = new ArrayList<>();
        droids.add(battleDroid);
        droids.add(repairDroid);
        droids.add(superBattleDroid);
        droids.add(tankDroid);

        System.out.println(Arrays.toString(droids.toArray()));

    }

}
