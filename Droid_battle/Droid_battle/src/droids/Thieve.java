package droids;

/**
 * Thieve
 */
public class Thieve extends Droid {

    private int critChance;
    private int dexerity;

    public Thieve(String name, int health, int damage, int critChance, int dexerity) {
        super(name, health, damage);
        this.critChance = critChance;
        this.dexerity = dexerity;
    }

    @Override
    public int getHit(int damage) {
        int actualDamage = damage;

        // Damage avoided
        if (Math.random() < (double) this.dexerity / 100) {
            System.out.printf("%s godged, WOOW!!\n", this.getName());
            actualDamage = 0;
        }

        if (actualDamage < 0) {
            actualDamage = 0;
        }

        this.health -= actualDamage;

        if (health < 0) {
            health = 0;
        }

        return actualDamage;
    }

    @Override
    public int getDamage() {
        int actualDamage = this.damage;

        // Critical Hit
        if (Math.random() < (double) this.critChance / 100) {
            System.out.printf("%s CRIT!\n", this.getName());
            actualDamage *= 2;
        }
        return actualDamage;
    }
}