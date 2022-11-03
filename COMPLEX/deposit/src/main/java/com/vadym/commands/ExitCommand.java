package commands;

public class ExitCommand implements Command {
    @Override
    public String execute(String[] arr) {
        System.exit(0);
        return "Exiting proggram. Good bye";
    }

    @Override
    public String getInfo() {
        return " - end proces. Full exiting of a proggram.\n\tExample: exit here";
    }

}
