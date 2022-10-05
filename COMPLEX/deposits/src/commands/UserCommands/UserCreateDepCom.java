package commands.UserCommands;

import userclass.User;

import java.util.ArrayList;

import banking.Bank;
import commands.Command;

public class UserCreateDepCom implements Command {
    private User b;
    ArrayList<Bank> banksBank;

    public UserCreateDepCom(User b, ArrayList<Bank> banksBank) {
        this.b = b;
        this.banksBank = banksBank;
    }

    @Override
    public void execute(String[] arr) {
        b.CreateDepCase(arr, banksBank);
    }

    @Override
    public String getInfo() {
        return " - create new deposit by given paramenters.";
    }
}
