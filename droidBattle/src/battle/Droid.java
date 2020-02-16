package battle;

/**
 * Created by MadYeti on 16.02.2020.
 */
public abstract class Droid {

    int attackDamage;
    int armor;
    int health;
    String name;

    public Droid() {
    }

    public String getName() {
        return name;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setHealth(int health) {
        this.health = health + armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public abstract void attack(Droid droid);
}
