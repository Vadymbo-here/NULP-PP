package userclass;

import java.util.ArrayList;

import banking.Bank;
import banking.DepCase;
import banking.Deposit;
import commands.Command;
import utils.MyScanner;

public class User {
    // ------------COMMAND SECTION----------------
    Command command;

    public void setCommand(Command com) {
        this.command = com;
    }

    public void executeCommand(String[] arr) {
        command.execute(arr);
    }

    // ------------COMMAND SECTION----------------

    public User() {
        this.role = 0;
        this.bankID = 12202;
    }

    private int userID, bankID, role;

    private String Firstname, Lastname, login, password;

    private ArrayList<Deposit> userDeps = new ArrayList<>();

    public User(int userID, int role, String Firstname, String Lastname, String login, String password) {
        this.userID = userID;
        this.role = role;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.login = login;
        this.password = password;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRole() {
        return this.role;
    }

    public ArrayList<Deposit> getUserDeps() {
        return userDeps;
    }

    public void setUserDeps(ArrayList<Deposit> userDeps) {
        this.userDeps = userDeps;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFirstname() {
        return this.Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return this.Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean login(String login, String password) {
        return login.equals(this.login) && password.equals(this.password);
    }

    public Boolean register() {
        MyScanner.scaner.nextLine();
        System.out.println("Write your first name and last name");
        this.setFirstname(MyScanner.inp());
        this.setLastname(MyScanner.inp());
        System.out.println("Good. Now we will need you to provide a login and a password.");
        this.setLogin(MyScanner.inp());
        this.setPassword(MyScanner.inp());
        System.out.println("Great! Welcome to our system, " + this.Firstname);
        return true;
    }

    public void ShowDeals(String[] arr, ArrayList<Bank> banksBank) {
        // show deals {balance} {period_in_da}
        double balance = Double.parseDouble(arr[2]);
        int period = Integer.parseInt(arr[3]);
        for (Bank bank : banksBank) {
            for (DepCase depCase : bank.getDepShowList()) {
                System.out.println(depCase.getProfit(balance, period));
            }
        }
    }

    public void CreateDepCase(String[] arr, ArrayList<Bank> banksBank) {
        // create dep {BankID} {DepID} {balance} {period_in_days}
    }

    public void ChangeDepCase(String[] arr) {
        // change dep {hisDepId} {additional money income}
        System.out.println("User-Changing case");
    }

    public void DeleteDepCase(String[] arr) {
        // del dep {hisDepID}
        System.out.println("User-Deleting case");
    }

    public void ShowDepCases() {
        if (this.userDeps.size() > 0) {
            System.out.println("");
            for (Deposit each : this.userDeps) {
                System.out.println(each);
            }
            System.out.println("");
        } else {
            System.out.println("\nUse have 0 active deposits. MAybe it's time to create one?\n");
        }
    }
}
