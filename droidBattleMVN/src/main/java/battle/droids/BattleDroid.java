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

}
