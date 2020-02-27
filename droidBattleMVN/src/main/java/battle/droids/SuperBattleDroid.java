package battle.droids;

import battle.interfaces.Unfriendable;
import battle.strategy.Attack;
import battle.strategy.AttackBehavior;
import battle.strategy.DoubleAttack;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class SuperBattleDroid extends Droid implements Unfriendable {

    public SuperBattleDroid() {
        this.attackBehavior = new DoubleAttack();
        this.attackDamage = (int)(Math.random() * 40 + 10);
        this.armor = (int)(Math.random() * 25);
        this.health = (int)(Math.random() * 120 + 10);
        this.name = "SuperBattleDroid";
    }

}
