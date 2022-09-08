package droids;

public class Droid {
    private String name;
    private int health;
    private int damage;
    private int defence;
    private int accuracy;
    private int critChance;

    public Droid(String name, int health, int damage, int defence, int accuracy, int critChance) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defence = defence;
        this.accuracy = accuracy;
        this.critChance = critChance;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHit(int damage) {
        int actualDamage = damage - this.defence;

        if (actualDamage < 0) {
            actualDamage = 0;
        }

        this.health -= actualDamage;

        if (health < 0) {
            health = 0;
        }

        return actualDamage;
    }

    public int getDamage() {
        int actualDamage = damage;
        if (Math.random() < (double) this.critChance / 100) {
            actualDamage *= 2;
        }
        if (Math.random() >= (double) this.accuracy / 100) {
            actualDamage = 0;
        }
        return actualDamage;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefence() {
        return this.defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getCritChance() {
        return this.critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    @Override
    public String toString() {
        return name + " health = " + health;
    }
}
