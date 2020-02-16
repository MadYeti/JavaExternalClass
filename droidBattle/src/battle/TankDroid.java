package battle;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class TankDroid extends Droid {

    public TankDroid() {
        this.attackDamage = attackDamage;
        this.armor = armor;
        this.health = health + this.armor;
        this.name = "TankDroid";
    }

    @Override
    public void attack(Droid droid) {
        droid.setHealth(droid.getHealth() - this.getAttackDamage());
    }
}
