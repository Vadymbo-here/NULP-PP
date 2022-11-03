import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import utils.*;
import utils.Log;

import banking.Bank;
import commands.*;
import commands.BankCommands.*;
import commands.UserCommands.*;
import userclass.*;

public class Menu {
    protected static ArrayList<Bank> banksBank = new ArrayList<>();
    protected static Bank userBank;
    protected static Map<String, Command> menuItemsU;
    protected static Map<String, Command> menuItemsA;

    /**
     * @param user - user on whose deps will be operations
     */
    public static void initUser(User user) {
        Log.init();
        Log.logger.info("Proggram STARTED");

        if (JsonWorker.getJsonStringF().isEmpty()) {
            Log.logger.severe("No database founded!!");
            CrashReportSender.sendReport(
                    "CRITICAL error. Something happened to DATABASE\n\nTry to find an error. Code cannot find and data inside!!");
            System.out.println("Critical error occupied. Sorry, contact a manager Telegram - @SneyB");
            System.exit(1);
        }

        System.out
                .println("Welcome to our DEPOSITS picekr.\n\nWe will help you to finaly pick the best option for you!");
        System.out.println("Register into system.\t If screen freeze hit [Enter]");
        System.out.println("Do you already have an account? [Yes/No]");
        String input = MyScanner.inp();
        switch (input) {
            case "yes":
            case "Yes":
            case "y":
            case "Y":
                Log.logger.info("User picked LOG-in");
                do {
                    Log.logger.info("Attempt of loggin.");
                    user = user.login();
                    if (user.getUserID() == 0) {
                        System.out.println("Incorrect data. Check your password or login\n\n");
                    }
                } while (user.getUserID() == 0);
                Log.logger.info("Login passed successfully.");
                break;
            case "no":
            case "No":
            case "n":
            case "N":
                Log.logger.info("User picked Register");
                user.register();
                Log.logger.info("End of registration");
                break;
            default:
                System.out.println("Wrong input.");
                initUser(user);
                break;
        }
        initInfo(user);
        genMenu(user);
    }

    /**
     * @param user - user on whose deps will be operations
     */
    public static void initInfo(User user) {

        banksBank = JsonWorker.getBankList();

        if (user.getRole() != 0) { // Anyone higher than user
            for (int i = 0; i < banksBank.size(); i++) {
                if (banksBank.get(i).getBankID() == user.getBankID()) {
                    userBank = banksBank.get(i);
                }
            }
        }

        // COMANDS
        menuItemsU = new HashMap<>();
        menuItemsA = new HashMap<>();
        menuItemsU.put("help me", new HelpCommand(menuItemsU));
        menuItemsU.put("create dep", new UserCreateDepCom(user, banksBank));
        menuItemsU.put("change dep", new UserChangeDepCom(user, banksBank));
        menuItemsU.put("del dep", new UserDeleteDepCom(user, banksBank));
        menuItemsU.put("show deals", new UserShowDealsCom(user));
        menuItemsU.put("show dep", new UserShowDepsCom(user));
        menuItemsU.put("exit here", new ExitCommand());
        menuItemsA.put("help me", new HelpCommand(menuItemsA));
        menuItemsA.put("create dep", new BankCreateDepCom(userBank));
        menuItemsA.put("change dep", new BankChangeDepCom(userBank));
        menuItemsA.put("del dep", new BankDeleteDepCom(userBank));
        menuItemsA.put("show dep", new BankShowDepsCom(userBank));
        menuItemsA.put("exit here", new ExitCommand());
    }

    /**
     * @param user - user on whose deps will be operations
     */
    public static void genMenu(User user) {
        while (true) {
            Command com = null;
            System.out.println("<---------------------------->");
            System.out.println(
                    "Pick your option\n\tShow depsits. - show dep\n\tCreate deposit. - create dep\n\tChange deposit. - change dep\n\tDelete deposit. - del dep\n\tOR use \"help me\"");
            String pick = MyScanner.inp();
            Log.logger.info("USER pick: " + pick);
            String[] userInput = pick.split("[^a-zA-Z0-9.\\{\\}]+(?![^\\{]*\\})");

            if (userInput.length < 2) {
                System.out.println("Wrong Command. Try \"help me\" to see available command with their description.");
                Log.logger.warning("Inputed wrong command.");
                continue;
            } else {
                com = menuItemsU.get(userInput[0] + " " + userInput[1]);
            }
            if (com == null) {
                System.out.println("Wrong Command. Try \"help me\" to see available command with their description.");
                Log.logger.warning("Inputed wrong command.");
                continue;
            } else {
                Log.logger.info("Command captured succesfully!");
                switch (user.getRole()) {
                    case 0:
                        String output = menuItemsU.get(userInput[0] + " " + userInput[1]).execute(userInput);
                        System.out.println(output);
                        Log.logger.warning(output);
                        break;

                    case 1:
                        String output2 = menuItemsA.get(userInput[0] + " " + userInput[1]).execute(userInput);
                        System.out.println(output2);
                        Log.logger.warning(output2);
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
