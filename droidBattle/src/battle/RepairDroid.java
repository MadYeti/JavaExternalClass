package battle;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class RepairDroid extends Droid {

    public RepairDroid() {
        this.attackDamage = attackDamage;
        this.armor = armor;
        this.health = health + this.armor;
        this.name = "RepairDroid";
    }

    @Override
    public void attack(Droid droid){
        droid.setHealth(droid.getHealth() - this.getAttackDamage());
        this.health += 20;
    }

}
