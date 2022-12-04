package banking;

import java.util.ArrayList;

import commands.Command;

public class Bank {
    // ------------COMMAND SECTION----------------
    Command command;

    public void setCommand(Command com) {
        this.command = com;
    }

    public void executeCommand(String[] arr) {
        command.execute(arr);
    }

    // ------------COMMAND SECTION-----------------
    
    int BankID;
    String name;
    ArrayList<DepCase> depShowList = new ArrayList<>();

    public Bank(int BankID, String name) {
        this.BankID = BankID;
        this.name = name;
    }

    public Bank() {
    }

    public int getBankID() {
        return this.BankID;
    }

    public void setBankID(int BankID) {
        this.BankID = BankID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DepCase> getDepShowList() {
        return this.depShowList;
    }

    public void setDepShowList(ArrayList<DepCase> depShowList) {
        this.depShowList = depShowList;
    }

    public void CreateDepCase(String[] arr) {
        System.out.println("Admin-Creating case");
    }

    public void ChangeDepCase(String[] arr) {
        System.out.println("Admin-Changing case");
    }

    public void DeleteDepCase(String[] arr) {
        System.out.println("Admin-Deleting case");
    }

    public void ShowDepCases(String[] arr) {
        System.out.println("Admin-Showing case");
    }

}
