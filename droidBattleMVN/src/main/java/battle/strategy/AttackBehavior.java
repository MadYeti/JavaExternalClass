package battle.strategy;

import battle.droids.Droid;

public interface AttackBehavior {

    void attack(Droid defender, int damage);

}
