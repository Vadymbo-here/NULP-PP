package commands.UserCommands;

import commands.Command;
import userclass.User;

public class UserShowDealsCom implements Command {
    private User b;

    public UserShowDealsCom(User b) {
        this.b = b;
    }

    @Override
    public String execute(String[] arr) {
        return b.ShowDeals(arr);
    }

    @Override
    public String getInfo() {
        return " - show all available deposit deals for you.\n\tExample: show deals {balance} {period in days}";
    }

}
