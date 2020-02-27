package battle.droids;

import battle.strategy.AttackBehavior;

/**
 * Created by MadYeti on 16.02.2020.
 */
public abstract class Droid {

    AttackBehavior attackBehavior;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public void performAttack(Droid defender, int damage){
        attackBehavior.attack(defender, damage);
    }

    public int getArmor() {
        return armor;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setName(String name) {
        this.name = name;
    }
}
