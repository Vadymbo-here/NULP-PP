package commands.UserCommands;

import userclass.User;

import commands.Command;

public class UserShowDepsCom implements Command {
    private User b;

    public UserShowDepsCom(User b) {
        this.b = b;
    }

    @Override
    public String execute(String[] arr) {
        return b.ShowDepCases();
    }

    @Override
    public String getInfo() {
        return " - show all your active deposits. Show all one by one in a nice table.\n\tExample: show dep";
    }
}
