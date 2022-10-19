package commands;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] arr) {
        System.exit(0);
    }

    @Override
    public String getInfo() {
        return " - end proces. Full exiting of a proggram.\n\tExample: exit here";
    }

}
