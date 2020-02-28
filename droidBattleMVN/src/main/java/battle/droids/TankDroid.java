package battle.droids;

import battle.interfaces.Friendable;
import battle.strategy.Attack;
import battle.strategy.AttackBehavior;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class TankDroid extends Droid implements Friendable {

    public TankDroid() {
        this.attackBehavior = new Attack();
        this.attackDamage = (int)(Math.random() * 40 + 10);
        this.armor = (int)(Math.random() * 25);
        this.health = (int)(Math.random() * 200 + 10);
        this.name = "TankDroid";
    }

    public TankDroid(int attackDamage, int armor, int health) {
        this.attackBehavior = new Attack();
        this.attackDamage = attackDamage;
        this.armor = armor;
        this.health = health;
        this.name = "TankDroid";
    }

    @Override
    public TankDroid build(){
        return this;
    }

    @Override
    public String toString() {
        /*
        return "TankDroid{" +
                "attackDamage=" + attackDamage +
                ", armor=" + armor +
                ", health=" + health +
                '}';*/
        return "TankDroid" +
                "," + attackDamage +
                "," + armor +
                "," + health;
    }
}
