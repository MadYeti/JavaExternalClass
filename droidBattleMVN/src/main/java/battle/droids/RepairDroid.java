package battle.droids;

import battle.interfaces.Friendable;
import battle.strategy.AttackBehavior;
import battle.strategy.PoisonAttack;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class RepairDroid extends Droid implements Friendable {

    public RepairDroid() {
        this.attackBehavior = new PoisonAttack();
        this.attackDamage = (int)(Math.random() * 30 + 10);
        this.armor = (int)(Math.random() * 20);
        this.health = (int)(Math.random() * 100 + 10);
        this.name = "RepairDroid";
    }

    public RepairDroid(int attackDamage, int armor, int health) {
        this.attackBehavior = new PoisonAttack();
        this.attackDamage = attackDamage;
        this.armor = armor;
        this.health = health;
        this.name = "RepairDroid";
    }

    @Override
    public RepairDroid build(){
        return this;
    }

    @Override
    public String toString() {
        /*
        return "RepairDroid{" +
                "attackDamage=" + attackDamage +
                ", armor=" + armor +
                ", health=" + health +
                '}';*/
        return "RepairDroid" +
                "," + attackDamage +
                "," + armor +
                "," + health;
    }
}
