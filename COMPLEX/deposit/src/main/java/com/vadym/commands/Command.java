package commands;

public interface Command {
    String execute(String[] arr);

    String getInfo();
}