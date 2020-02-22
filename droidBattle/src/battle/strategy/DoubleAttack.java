package battle.strategy;

import battle.droids.Droid;

public class DoubleAttack implements AttackBehavior {

    @Override
    public void attack(Droid defender, int damage) {
        defender.setHealth(defender.getHealth() - (damage * 2));
    }
}
