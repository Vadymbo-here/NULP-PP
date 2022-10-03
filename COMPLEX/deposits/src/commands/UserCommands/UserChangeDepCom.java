package commands.UserCommands;

import userclass.User;
import commands.Command;

public class UserChangeDepCom implements Command {
    private User b;

    public UserChangeDepCom(User b) {
        this.b = b;
    }

    @Override
    public void execute(String[] arr) {
        b.ChangeDepCase(arr);
    }
    
    @Override
    public String getInfo() {
        return " - change deposit balance amount for bigger percantage income.";
    }
}
