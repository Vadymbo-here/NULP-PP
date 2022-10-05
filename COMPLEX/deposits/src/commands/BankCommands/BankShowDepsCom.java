package commands.BankCommands;

import banking.Bank;
import commands.Command;

public class BankShowDepsCom implements Command {
    private Bank b;

    public BankShowDepsCom(Bank b) {
        this.b = b;
    }

    @Override
    public void execute(String[] arr) {
        b.ShowDepCases();
    }

    @Override
    public String getInfo() {
        return " - show all active deposit deals in a bank.";
    }
}
