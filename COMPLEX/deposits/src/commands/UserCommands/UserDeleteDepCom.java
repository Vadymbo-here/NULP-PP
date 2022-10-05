package commands.UserCommands;

import userclass.User;

import java.util.ArrayList;

import banking.Bank;
import commands.Command;

public class UserDeleteDepCom implements Command {
    private User b;
    ArrayList<Bank> banksBank;
    
    public UserDeleteDepCom(User b, ArrayList<Bank> banksBank) {
        this.b = b;
        this.banksBank = banksBank;
    }

    @Override
    public void execute(String[] arr) {
        b.DeleteDepCase(arr);
    }

    @Override
    public String getInfo() {
        return " - close your active deposit but without planned profit. Your profit will be RECALCULATED according to  \"Do pitannya\" plan.";
    }
}
