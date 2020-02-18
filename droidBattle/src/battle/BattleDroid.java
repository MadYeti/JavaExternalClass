package battle;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class BattleDroid extends Droid{

    public BattleDroid() {
        this.attackDamage = (int)(Math.random() * 40 + 10);
        this.armor = (int)(Math.random() * 25);
        this.health = (int)(Math.random() * 120 + 10) + this.armor;
        this.name = "BattleDroid";
    }

    @Override
    public void attack(Droid droid){
        droid.setHealth(droid.getHealth() - this.getAttackDamage());
    }

}
