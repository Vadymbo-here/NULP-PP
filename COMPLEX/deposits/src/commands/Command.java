package commands;

public interface Command {
    void execute(String[] arr);

    String getInfo();
}