package commands.BankCommands;

import banking.Bank;
import commands.Command;

public class BankCreateDepCom implements Command {
    private Bank b;

    public BankCreateDepCom(Bank b) {
        this.b = b;
    }

    @Override
    public String execute(String[] arr) {
        return b.CreateDepCase(arr);
    }

    @Override
    public String getInfo() {
        return " - create new deal for a bank deposit list.\n\tExample: create dep {name} {{description}} {type} {percentage}";
    }
}
