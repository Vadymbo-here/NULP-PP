package droids;

public class Droid {
    protected String name;
    protected int health;
    protected int damage;
    private int maxHealth;

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.maxHealth = health;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int getHit(int damage) {

        this.health -= damage;

        if (health < 0) {
            health = 0;
        }

        return damage;
    }

    public int getDamage() {
        return this.damage;
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
        if (this.health + health > this.maxHealth) {
            this.health = maxHealth;
        } else {
            this.health = health;
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return this.name + " health = [" + this.health + " / " + this.maxHealth + "]";
    }
}
