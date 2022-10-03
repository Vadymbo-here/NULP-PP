package commands.UserCommands;

import userclass.User;
import commands.Command;

public class UserCreateDepCom implements Command {
    private User b;

    public UserCreateDepCom(User b) {
        this.b = b;
    }

    @Override
    public void execute(String[] arr) {
        b.CreateDepCase(arr);
    }

    @Override
    public String getInfo() {
        return " - create new deposit by given paramenters.";
    }
}
