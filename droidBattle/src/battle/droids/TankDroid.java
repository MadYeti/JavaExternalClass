package battle.droids;

import battle.strategy.Attack;
import battle.strategy.AttackBehavior;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class TankDroid extends Droid {

    public TankDroid() {
        this.attackBehavior = new Attack();
        this.attackDamage = (int)(Math.random() * 40 + 10);
        this.armor = (int)(Math.random() * 25);
        this.health = (int)(Math.random() * 200 + 10) + this.armor;
        this.name = "TankDroid";
    }

}
