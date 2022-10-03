package commands.BankCommands;

import banking.Bank;
import commands.Command;

public class BankCreateDepCom implements Command {
    private Bank b;

    public BankCreateDepCom(Bank b) {
        this.b = b;
    }

    @Override
    public void execute(String[] arr) {
        b.CreateDepCase(arr);
    }

    @Override
    public String getInfo() {
        return " - create new deal for a bank deposit list.";
    }
}
