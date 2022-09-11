package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import arenas.Arena;
import droids.Droid;

/**
 * TeamVsTeamController
 */
public class TeamVsTeamController {

    private ArrayList<Droid> firstTeam;
    private ArrayList<Droid> secondTeam;
    private ArrayList<Droid> attacker;
    private ArrayList<Droid> defender;
    private Arena arena;
    private int currentRound = 0;

    public TeamVsTeamController(ArrayList<Droid> firstTeam, ArrayList<Droid> secondTeam, Arena arena) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.arena = arena;
    }

    public ArrayList<Droid> startFight() throws InterruptedException, IOException { // CHECK
        // Clear the log File
        FileWriter fileWriter = new FileWriter("battleLog.txt", false);
        fileWriter.close();

        do {
            prepareRound();
            doFight();
            printRoundInfo();

            TimeUnit.SECONDS.sleep(1);
        } while (isTeamAlive(defender));

        return attacker;
    }

    public boolean isTeamAlive(ArrayList<Droid> arr) { // CHECK
        boolean alive = false;
        for (var each : arr) {
            if (each.isAlive()) {
                alive = true;
            }
        }
        return alive;
    }

    private void prepareRound() throws IOException { // CHECK
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

    private void doFight() throws IOException {
        // ARENA EFFECTS FOR EVERYNE
        for (var each : firstTeam) {
            each.setHealth(each.getHealth() + arena.getEffect());
        }
        for (var each : secondTeam) {
            each.setHealth(each.getHealth() + arena.getEffect());
        }

        // BATTLE SYSTEM
        int teamDamage = 0;
        for (Droid droid : attacker) {
            if (droid.getHealth() > 0) {
                teamDamage += droid.getDamage();
            }
        }

        Droid target = new Droid("BOT", 10000, 1);
        for (Droid droid : defender) {
            if (target.getHealth() > droid.getHealth() && droid.getHealth() > 0) {
                target = droid;
            }
        }

        target.getHit(teamDamage);

        FileWriter fileWriter = new FileWriter("battleLog.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        System.out.println(target.getName() + " get hit with " + teamDamage + " damage by team.");
        printWriter.println(target.getName() + " get hit with " + teamDamage + " damage by team.");
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }

    private void printRoundInfo() throws IOException {
        FileWriter fileWriter = new FileWriter("battleLog.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        System.out.println("TEAM <Defender>: ");
        printWriter.println("TEAM <Defender>: ");
        for (var each : defender) {
            System.out.println(each);
            printWriter.println(each);

        }
        System.out.println("TEAM <Attacker>: ");
        printWriter.println("TEAM <Attacker>: ");
        for (var each : attacker) {
            System.out.println(each);
            printWriter.println(each);
        }
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }

    private void initFighters() { // CHECK
        Random random = new Random();
        if (random.nextBoolean()) {
            attacker = firstTeam;
            defender = secondTeam;
        } else {
            attacker = secondTeam;
            defender = firstTeam;
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}