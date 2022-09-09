package droids;

/**
 * Tank
 */
public class Tank extends Droid {
    private int protection;
    private final double defKoef = 0.2;

    public Tank(String name, int health, int damage, int protection) {
        super(name, health, damage);
        this.protection = protection;
    }

    public int getHit(int damage) {
        int actualDamage = damage - (int) (protection * defKoef);
        this.health -= actualDamage;

        if (health < 0) {
            health = 0;
        }

        return actualDamage;
    }

    public int getDamage() {
        return this.damage;
    }

}