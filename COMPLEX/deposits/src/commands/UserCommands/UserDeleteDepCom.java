package commands.UserCommands;

import userclass.User;
import commands.Command;

public class UserDeleteDepCom implements Command {
    private User b;

    public UserDeleteDepCom(User b) {
        this.b = b;
    }

    @Override
    public void execute(String[] arr) {
        b.DeleteDepCase(arr);
    }

    @Override
    public String getInfo() {
        return " - close your active deposit but without planned profit. Your profit will be RECALCULATED according to  \"Do pitannya\" plan.";
    }
}
