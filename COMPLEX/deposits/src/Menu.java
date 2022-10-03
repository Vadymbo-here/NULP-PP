import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import utils.MyScanner;

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

    public static void main() {

    }

    /**
     * @param user - user on whose deps will be operations
     */
    public static void initUser(User user) {
        System.out
                .println("Welcome to our DEPOSITS picekr.\n\nWe will help you to finaly pick the best option for you!");
        System.out.println("Register into system.\t If screen freeze hit [Enter]");
        user.register();
        initInfo(user);
        genMenu(user);
    }

    /**
     * @param user - user on whose deps will be operations
     */
    public static void initInfo(User user) {

        // BANKS
        banksBank.add(new Bank(12202, "GrandC"));

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
        menuItemsU.put("create dep", new UserCreateDepCom(user));
        menuItemsU.put("change dep", new UserChangeDepCom(user));
        menuItemsU.put("del dep", new UserDeleteDepCom(user));
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
                    "Pick your option\n\tShow depsits. - show dep\n\tCreate deposit. - create dep\n\tChange deposit. - change dep\n\tDelete deposit. - del dep");
            String pick = MyScanner.inp();
            String[] userInput = pick.split(" ");
            if (userInput.length < 2) {
                System.out.println("Wrong Command. Try \"Help\" to see available command with their description.");
                continue;
            } else {
                com = menuItemsU.get(userInput[0] + " " + userInput[1]);
            }
            if (com == null) {
                System.out.println("Wrong Command. Try \"Help\" to see available command with their description.");
                continue;
            } else {
                switch (user.getRole()) {
                    case 0:
                        menuItemsU.get(userInput[0] + " " + userInput[1]).execute(userInput);
                        break;

                    case 1:
                        menuItemsA.get(userInput[0] + " " + userInput[1]).execute(userInput);
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
