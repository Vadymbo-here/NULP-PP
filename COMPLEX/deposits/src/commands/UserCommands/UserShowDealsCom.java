package commands.UserCommands;

import java.util.ArrayList;

import banking.Bank;
import commands.Command;
import userclass.User;

public class UserShowDealsCom implements Command {
    private User b;
    ArrayList<Bank> banksBank;

    public UserShowDealsCom(User b, ArrayList<Bank> banksBank) {
        this.b = b;
        this.banksBank = banksBank;
    }

    @Override
    public void execute(String[] arr) {
        b.ShowDeals(arr, banksBank);
    }

    @Override
    public String getInfo() {
        return " - show all available deposit deals for you.\n\tExample: show deals {balance} {period in days}";
    }

}
