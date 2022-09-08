package arenas;

public class Arena {

    private int dealDamage = 0;
    private int dealHeal = 0;

    public Arena() {
    }

    public Arena(int dealDamage, int dealHeal) {
        this.dealDamage = dealDamage;
        this.dealHeal = dealHeal;
    }

    public int getEffect() {
        return this.dealDamage + this.dealHeal;
    }

}
