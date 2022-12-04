package commands.UserCommands;

import userclass.User;

import java.util.ArrayList;

import banking.Bank;
import commands.Command;

public class UserCreateDepCom implements Command {
    private User b;

    public UserCreateDepCom(User b, ArrayList<Bank> banksBank) {
        this.b = b;
    }

    @Override
    public String execute(String[] arr) {
        return b.CreateDepCase(arr);
    }

    @Override
    public String getInfo() {
        return " - create new deposit by given paramenters.\n\tExample: create dep {BankID} {DepID} {balance} {period_in_days}";
    }
}
