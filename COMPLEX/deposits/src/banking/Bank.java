package banking;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import commands.Command;
import utils.JsonWorker;

public class Bank {
    // ------------COMMAND SECTION----------------
    Command command;

    public void setCommand(Command com) {
        this.command = com;
    }

    public void executeCommand(String[] arr) {
        command.execute(arr);
    }

    // ------------COMMAND SECTION-----------------

    int BankID;
    String name;
    ArrayList<DepCase> depShowList = new ArrayList<>();

    public Bank(int BankID, String name) {
        this.BankID = BankID;
        this.name = name;
    }

    public Bank() {
    }

    public int getBankID() {
        return this.BankID;
    }

    public void setBankID(int BankID) {
        this.BankID = BankID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DepCase> getDepShowList() {
        return this.depShowList;
    }

    public void setDepShowList(ArrayList<DepCase> depShowList) {
        this.depShowList = depShowList;
    }

    public String CreateDepCase(String[] arr) {
        if (arr.length < 6) {
            return "Wrong Command or params. Try \"Help me\" to see available command with their description.";
        }
        arr[3] = arr[3].replace('{', ' ').replace('}', ' ').trim();
        // DepCase temp = new DepCase(depShowList.size() + 1, arr[2], arr[3], arr[4],
        // arr[5]);
        // new DepCase()
        // depShowList.add(temp);
        int type = Integer.parseInt(arr[4]);
        float perc = Float.parseFloat(arr[5]);
        int depid = JsonWorker.getFreeSlot("depcases", "depID");

        try {
            JsonWorker.writeDepCase(this.BankID, depid, arr[2], arr[3], type, perc);
            return "Command succesfully executed!";
        } catch (IOException e) {
            return "Error occupied!";
        }

    }

    public String ChangeDepCase(String[] arr) {
        if (arr.length < 5) {
            return "Wrong Command or params. Try \"Help me\" to see available command with their description.";
        }
        int point = Integer.parseInt(arr[2]);
        arr[4] = arr[4].replace('{', ' ').replace('}', ' ').trim();

        try {
            return JsonWorker.changeDepCase(this.BankID, point, arr[3], arr[4]);
        } catch (JSONException | IOException e) {
            return "Error occupied.";
        }
    }

    // name, description, percantage, type
    // change dep depID PARAM new value

    public String DeleteDepCase(String[] arr) {
        if (arr.length < 3) {
            return "Wrong Command or params. Try \"Help me\" to see available command with their description.";
        }
        // int point = Integer.parseInt(arr[2]);
        // depShowList.remove(point - 1);

        try {
            JsonWorker.delDepCase(this.BankID, Integer.parseInt(arr[2]));
            return "Command executed succesfully!";
        } catch (NumberFormatException | IOException e) {
            return "Error occupied.";
        }
    }

    public String ShowDepCases() {
        return JsonWorker.printAllDepCases(this.BankID);
    }

}
