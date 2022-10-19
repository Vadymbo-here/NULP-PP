package commands.UserCommands;

import userclass.User;

import java.util.ArrayList;

import banking.Bank;
import commands.Command;

public class UserChangeDepCom implements Command {
    private User b;
    ArrayList<Bank> banksBank;

    public UserChangeDepCom(User b, ArrayList<Bank> banksBank) {
        this.b = b;
        this.banksBank = banksBank;
    }

    @Override
    public void execute(String[] arr) {
        b.ChangeDepCase(arr);
    }

    @Override
    public String getInfo() {
        return " - change deposit balance amount for bigger percantage income.\n\tExample: change dep {bankid} {hisDepId} {additional money income}";
    }
}
