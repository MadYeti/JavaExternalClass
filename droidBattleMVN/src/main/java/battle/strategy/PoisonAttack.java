package battle.strategy;

import battle.droids.Droid;

public class PoisonAttack implements AttackBehavior {

    @Override
    public void attack(Droid defender, int damage) {
        defender.setHealth(defender.getHealth() - damage - 20);
    }
}
