package controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import arenas.Arena;
import droids.Droid;

public class DuelController {

    private Droid firstDroid;
    private Droid secondDroid;
    private Droid attacker;
    private Droid defender;
    private Arena arena;
    private int currentRound = 0;

    public DuelController(Droid droid1, Droid droid2, Arena arena) {
        this.firstDroid = droid1;
        this.secondDroid = droid2;
        this.arena = arena;
    }

    public Droid startFight() throws InterruptedException {
        do {
            prepareRound();
            int actualDamage = doFight();
            printRoundInfo(actualDamage);

            TimeUnit.SECONDS.sleep(1);
        } while (defender.isAlive());

        return attacker;
    }

    private void prepareRound() {
        initFighters();
        currentRound++;
        System.out.println("-------------------------------------");
        System.out.println("Round " + currentRound);
    }

    private int doFight() {
        firstDroid.setHealth(attacker.getHealth() + arena.getEffect());
        secondDroid.setHealth(defender.getHealth() + arena.getEffect());
        return defender.getHit(attacker.getDamage());
    }

    private void printRoundInfo(int actualDamage) {
        System.out.println(defender.getName() + " get hit with " + actualDamage + " damage");
        System.out.println("Defender " + defender);
        System.out.println("Attacker " + attacker);
    }

    private void initFighters() {
        Random random = new Random();
        if (random.nextBoolean()) {
            attacker = firstDroid;
            defender = secondDroid;
        } else {
            attacker = secondDroid;
            defender = firstDroid;
        }
    }

}
