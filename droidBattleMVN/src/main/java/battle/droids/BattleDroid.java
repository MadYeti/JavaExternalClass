package battle.droids;

import battle.interfaces.Unfriendable;
import battle.strategy.Attack;
import battle.strategy.AttackBehavior;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class BattleDroid extends Droid implements Unfriendable {

    public BattleDroid() {
        this.attackBehavior = new Attack();
        this.attackDamage = (int)(Math.random() * 40 + 10);
        this.armor = (int)(Math.random() * 25);
        this.health = (int)(Math.random() * 120 + 10);
        this.name = "BattleDroid";
    }

    public BattleDroid(int attackDamage, int armor, int health) {
        this.attackBehavior = new Attack();
        this.attackDamage = attackDamage;
        this.armor = armor;
        this.health = health;
        this.name = "BattleDroid";
    }

    @Override
    public BattleDroid build(){
        return this;
    }

    @Override
    public String toString() {
        /*
        return "BattleDroid{" +
                "attackDamage=" + attackDamage +
                ", armor=" + armor +
                ", health=" + health +
                '}';*/
        return "BattleDroid" +
                "," + attackDamage +
                "," + armor +
                "," + health;
    }
}
