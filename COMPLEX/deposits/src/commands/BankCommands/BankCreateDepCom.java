package commands.BankCommands;

import java.io.IOException;

import banking.Bank;
import commands.Command;

public class BankCreateDepCom implements Command {
    private Bank b;

    public BankCreateDepCom(Bank b) {
        this.b = b;
    }

    @Override
    public void execute(String[] arr) {
        try {
            b.CreateDepCase(arr);
        } catch (IOException e) {
            System.out.println("I am here!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }
    }

    @Override
    public String getInfo() {
        return " - create new deal for a bank deposit list.\n\tExample: create dep {name} {{description}} {type} {percentage}";
    }
}
