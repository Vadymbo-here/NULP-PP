package commands.BankCommands;

import banking.Bank;
import commands.Command;

public class BankDeleteDepCom implements Command {
    private Bank b;

    public BankDeleteDepCom(Bank b) {
        this.b = b;
    }

    @Override
    public String execute(String[] arr) {
        return b.DeleteDepCase(arr);
    }

    @Override
    public String getInfo() {
        return " - delete specific deposit deal from a list.\n\tExample: delete dep {depid}.";
    }
}
