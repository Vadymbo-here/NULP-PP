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

    public void CreateDepCase(String[] arr) throws IOException {
        if (arr.length < 6) {
            System.out.println(
                    "Wrong Command or params. Try \"Help me\" to see available command with their description.");
            return;
        }
        arr[3] = arr[3].replace('{', ' ').replace('}', ' ').trim();
        // DepCase temp = new DepCase(depShowList.size() + 1, arr[2], arr[3], arr[4],
        // arr[5]);
        // new DepCase()
        // depShowList.add(temp);
        int type = Integer.parseInt(arr[4]);
        float perc = Float.parseFloat(arr[5]);
        int depid = JsonWorker.getFreeSlot("depcases", "depID");

        JsonWorker.writeDepCase(this.BankID, depid, arr[2], arr[3], type, perc);
    }

    public void ChangeDepCase(String[] arr) {
        if (arr.length < 5) {
            System.out.println(
                    "Wrong Command or params. Try \"Help me\" to see available command with their description.");
            return;
        }
        int point = Integer.parseInt(arr[2]);
        arr[4] = arr[4].replace('{', ' ').replace('}', ' ').trim();
        // DepCase temp = depShowList.get(point - 1);
        // switch (arr[3]) {
        // case "name":
        // temp.setName(arr[4]);
        // break;

        // case "description":
        // temp.setDescription(arr[4]);
        // break;

        // case "percentage":
        // float tempF = Float.parseFloat(arr[4]);
        // temp.setPercentage(tempF);
        // break;

        // case "type":
        // int tempI = Integer.parseInt(arr[4]);
        // temp.setType(tempI);
        // break;
        // default:
        // System.out.println("\nWrong parametr name. No data saved. Try again or use
        // \"help me\".\n");
        // break;
        // }

        try {
            JsonWorker.changeDepCase(this.BankID, point, arr[3], arr[4]);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    // name, description, percantage, type
    // change dep depID PARAM new value

    public void DeleteDepCase(String[] arr) {
        if (arr.length < 3) {
            System.out.println(
                    "Wrong Command or params. Try \"Help me\" to see available command with their description.");
            return;
        }
        // int point = Integer.parseInt(arr[2]);
        // depShowList.remove(point - 1);

        try {
            JsonWorker.delDepCase(this.BankID, Integer.parseInt(arr[2]));
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void ShowDepCases() {
        JsonWorker.printAllDepCases();
    }

}
