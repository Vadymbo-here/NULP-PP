package commands.BankCommands;

import banking.Bank;
import commands.Command;

public class BankChangeDepCom implements Command {
    private Bank b;

    public BankChangeDepCom(Bank b) {
        this.b = b;
    }

    @Override
    public String execute(String[] arr) {
        return b.ChangeDepCase(arr);
    }

    @Override
    public String getInfo() {
        return " - change some paraments into a specific deal.\n\tExample: change dep {depID} {key} {newVal}.";
    }
}
