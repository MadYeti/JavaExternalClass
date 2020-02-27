package battle.droids;

import battle.strategy.AttackBehavior;

/**
 * Created by MadYeti on 16.02.2020.
 */
public abstract class Droid implements Comparable<Droid>{

    Engine engine;
    AttackBehavior attackBehavior;
    int attackDamage;
    int armor;
    int health;
    String name;

    public Droid() {
    }

    @Override
    public int compareTo(Droid droid) {
        if((this.attackDamage + this.armor + this.health) > (droid.attackDamage + droid.armor + droid.health)){
            return 1;
        }else if((this.attackDamage + this.armor + this.health) == (droid.attackDamage + droid.armor + droid.health)){
            return 0;
        }
        return -1;
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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    class Engine{

        private boolean engineIsOn;

        public Engine(){

        }

        public boolean isEngineIsOn() {
            return engineIsOn;
        }

        public void setEngineIsOn(boolean engineIsOn) {
            this.engineIsOn = engineIsOn;
        }
    }

    @Override
    public String toString() {
        return "Droid{" +
                "attackDamage=" + attackDamage +
                ", armor=" + armor +
                ", health=" + health +
                '}';
    }
}
