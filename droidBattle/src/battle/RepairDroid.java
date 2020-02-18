package battle;

/**
 * Created by MadYeti on 16.02.2020.
 */
public class RepairDroid extends Droid {

    public RepairDroid() {
        this.attackDamage = (int)(Math.random() * 30 + 10);
        this.armor = (int)(Math.random() * 20);
        this.health = (int)(Math.random() * 100 + 10) + this.armor;
        this.name = "RepairDroid";
    }

    @Override
    public void attack(Droid droid){
        droid.setHealth(droid.getHealth() - this.getAttackDamage());
        this.health += 20;
    }

}
