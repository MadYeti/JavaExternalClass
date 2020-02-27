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

}
