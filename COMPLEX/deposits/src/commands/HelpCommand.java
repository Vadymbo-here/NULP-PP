package commands;

import java.util.Map;

public class HelpCommand implements Command {
    private Map<String, Command> b;

    public HelpCommand(Map<String, Command> b) {
        this.b = b;
    }

    @Override
    public void execute(String[] arr) {
        System.out.println("<---------------------------->");
        b.forEach((key, value) -> {
            if (!value.getInfo().isEmpty()) {
                System.out.println("==> " + key + value.getInfo() + "\n");
            }
        });
    }

    @Override
    public String getInfo() {
        return "";
    }
}
