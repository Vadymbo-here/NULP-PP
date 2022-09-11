package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public Droid startFight() throws InterruptedException, IOException {
        // Clear the log File
        FileWriter fileWriter = new FileWriter("battleLog.txt", false);
        fileWriter.close();

        do {
            prepareRound();
            int actualDamage = doFight();
            printRoundInfo(actualDamage);

            TimeUnit.SECONDS.sleep(1);
        } while (defender.isAlive());

        return attacker;
    }

    private void prepareRound() throws IOException {
        initFighters();
        currentRound++;

        FileWriter fileWriter = new FileWriter("battleLog.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        System.out.println("-------------------------------------");
        System.out.println("Round " + currentRound);

        printWriter.println("-------------------------------------");
        printWriter.println("Round " + currentRound);

        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }

    private int doFight() {
        attacker.setHealth(attacker.getHealth() + arena.getEffect());
        defender.setHealth(defender.getHealth() + arena.getEffect());
        return defender.getHit(attacker.getDamage());
    }

    private void printRoundInfo(int actualDamage) throws IOException {
        FileWriter fileWriter = new FileWriter("battleLog.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        System.out.println(defender.getName() + " get hit with " + actualDamage + " damage");
        System.out.println("Defender: " + defender);
        System.out.println("Attacker: " + attacker);

        printWriter.println(defender.getName() + " get hit with " + actualDamage + " damage");
        printWriter.println("Defender: " + defender);
        printWriter.println("Attacker: " + attacker);

        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
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
