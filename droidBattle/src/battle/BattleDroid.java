package battle;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class BattleDroid extends Droid{

    public BattleDroid() {
        this.attackDamage = attackDamage;
        this.armor = armor;
        this.health = health + this.armor;
        this.name = "BattleDroid";
    }

    @Override
    public void attack(Droid droid){
        droid.setHealth(droid.getHealth() - this.getAttackDamage());
    }

}
