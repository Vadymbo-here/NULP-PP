package userclass;

import java.io.IOException;
import java.util.ArrayList;

import banking.Bank;
import banking.DepCase;
import banking.Deposit;
import commands.Command;
import utils.JsonWorker;
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

    public User(int userID, int role, String firstName, String lastName, String login, String password) {
        this.userID = userID;
        this.role = role;
        this.Firstname = firstName;
        this.Lastname = lastName;
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

    public User login() {
        User user = new User();
        System.out.println("Enter your login and password.");
        String login = MyScanner.inp();
        String password = MyScanner.inp();
        user = JsonWorker.loginUser(login, password);
        return user;
    }

    public Boolean register() {
        System.out.println("Write your first name and last name");
        String fname = MyScanner.inp();
        String lname = MyScanner.inp();
        System.out.println("Good. Now we will need you to provide a login and a password.");
        String login = MyScanner.inp();
        String password = MyScanner.inp();
        try {
            JsonWorker.registerUser(fname, lname, login, password);
        } catch (IOException e) {
            System.out.println("Failed to register a user");
            e.printStackTrace();
            return false;
        }
        System.out.println("Great! Welcome to our system, " + this.Firstname);
        return true;
    }

    public String ShowDeals(String[] arr) {
        if (arr.length < 4) {
            return "Wrong Command or params. Try \"Help me\" to see available command with their description.";
        }
        // show deals {balance} {period_in_da}
        double balance = 1000;
        int period = 30;
        try {
            balance = Double.parseDouble(arr[2]);
            period = Integer.parseInt(arr[3]);
        } catch (Exception e) {
            return "There is no such element. Check you params.";
        }
        ArrayList<DepCase> depCases = JsonWorker.getDepCaseList();
        // Sorting
        int n = depCases.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (depCases.get(j).getProfit(1000, 30) < depCases.get(j + 1).getProfit(1000, 30)) {
                    DepCase tmp = depCases.get(j);
                    depCases.set(j, depCases.get(j + 1));
                    depCases.set(j + 1, tmp);
                }
            }
        }
        for (DepCase dCase : depCases) {
            ArrayList<Bank> banks = JsonWorker.getBankList();
            String bankName = "None";
            if (banks.size() == 0) {
                return "Error. Coudn't find any available banks.";
            }
            for (int i = 0; i < banks.size(); i++) {
                if (banks.get(i).getBankID() == dCase.getBankID()) {
                    bankName = banks.get(i).getName();
                }
            }

            System.out.printf(
                    "\t====Deposit %s[%d] by %s====\n\tBank ID: %d\n\tProfit: %.4f\n\n",
                    dCase.getName(),
                    dCase.getDepID(),
                    bankName,
                    dCase.getBankID(),
                    dCase.getProfit(balance, period));
        }
        return "Command executed succesfully";
    }

    public String[] ShowDealsToArr(String[] arr) {
        String[] out = new String[25];
        if (arr.length < 4) {
            out[0] = "Wrong Command or params. Try \"Help me\" to see available command with their description.";
            return out;
        }
        // show deals {balance} {period_in_da}
        double balance = 1000;
        int period = 30;
        try {
            balance = Double.parseDouble(arr[2]);
            period = Integer.parseInt(arr[3]);
        } catch (Exception e) {
            out[0] = "There is no such element. Check you params.";
            return out;
        }
        ArrayList<DepCase> depCases = JsonWorker.getDepCaseList();
        // Sorting
        int n = depCases.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (depCases.get(j).getProfit(1000, 30) < depCases.get(j + 1).getProfit(1000, 30)) {
                    DepCase tmp = depCases.get(j);
                    depCases.set(j, depCases.get(j + 1));
                    depCases.set(j + 1, tmp);
                }
            }
        }
        for (int j = 0; j < depCases.size(); j++) {
            ArrayList<Bank> banks = JsonWorker.getBankList();
            String bankName = "None";
            if (banks.size() == 0) {
                out[0] = "Error. Coudn't find any available banks.";
                return out;
            }
            for (int i = 0; i < banks.size(); i++) {
                if (banks.get(i).getBankID() == depCases.get(i).getBankID()) {
                    bankName = banks.get(i).getName();
                }
            }
            String item;
            item = String.format(
                    "\t====Deposit %s[%d] by %s====\n\tBank ID: %d\n\tProfit: %.4f\n\n",
                    depCases.get(j).getName(),
                    depCases.get(j).getDepID(),
                    bankName,
                    depCases.get(j).getBankID(),
                    depCases.get(j).getProfit(balance, period));
            out[j] = item;
        }
        return out;
    }

    public String CreateDepCase(String[] arr) {
        // create dep {BankID} {DepID} {balance} {period_in_days}
        if (arr.length < 5) {
            return "Wrong Command or params. Try \"Help me\" to see available command with their description.";
        }
        try {
            int bankID = Integer.parseInt(arr[2]);
            int depID = Integer.parseInt(arr[3]);
            int days = Integer.parseInt(arr[5]);
            double balance = Double.parseDouble(arr[4]);
            return JsonWorker.inputDeposit(this.userID, bankID, depID, balance, days);
        } catch (Exception e) {
            return "There is no such element. Check you params.";
        }
    }

    public String ChangeDepCase(String[] arr) {
        // change dep {bankid} {hisDepId} {additional money income}
        if (arr.length < 5) {
            return "Wrong Command or params. Try \"Help me\" to see available command with their description.";
        }
        try {
            int bankid = Integer.parseInt(arr[2]);
            int depID = Integer.parseInt(arr[3]);
            return JsonWorker.updateDeposit(this.userID, bankid, depID, arr[4]);
        } catch (Exception e) {
            return "There is no such element. Check you params.";
        }
    }

    public String DeleteDepCase(String[] arr) {
        if (arr.length < 4) {
            return "Wrong Command or params. Try \"Help me\" to see available command with their description.";
        }

        try {
            int bankid = Integer.parseInt(arr[2]);
            int depID = Integer.parseInt(arr[3]);
            return JsonWorker.delDeposit(this.userID, bankid, depID);
        } catch (Exception e) {
            return "There is no such element. Check you params.";
        }
    }

    public String ShowDepCases() {
        return JsonWorker.printAllDeposits(this.userID);
    }

}
