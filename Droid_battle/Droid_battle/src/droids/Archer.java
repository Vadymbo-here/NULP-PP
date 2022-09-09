package droids;

/**
 * Archer
 */
public class Archer extends Droid {

    private int accuracy;
    private int dexerity;

    public Archer(String name, int health, int damage, int accuracy, int dexerity) {
        super(name, health, damage);
        this.accuracy = accuracy;
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

        // Arrow missed
        if (Math.random() >= (double) this.accuracy / 100) {
            System.out.printf("%s missed a shot!\n", this.getName());
            actualDamage = 0;
        }
        return actualDamage;
    }
}