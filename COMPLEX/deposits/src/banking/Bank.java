package banking;

import java.util.ArrayList;

import commands.Command;

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

    public void CreateDepCase(String[] arr) {
        arr[3] = arr[3].replace('{', ' ').replace('}', ' ').trim();
        DepCase temp = new DepCase(depShowList.size() + 1, arr[2], arr[3], arr[4], arr[5]);
        depShowList.add(temp);
    }


    public void ChangeDepCase(String[] arr) {
        int point = Integer.parseInt(arr[2]);
        DepCase temp = depShowList.get(point - 1);
        switch (arr[3]) {
            case "name":
                temp.setName(arr[4]);
                break;

            case "description":
                temp.setDescription(arr[4]);
                break;

            case "percentage":
                float tempF = Float.parseFloat(arr[4]);
                temp.setPercentage(tempF);
                break;

            case "type":
                int tempI = Integer.parseInt(arr[4]);
                temp.setType(tempI);
                break;
            default:
                System.out.println("\nWrong parametr name. No data saved. Try again or use \"help me\".\n");
                break;
        }
    }

    // name, description, percantage, type
    // change dep depID PARAM new value

    public void DeleteDepCase(String[] arr) {
        int point = Integer.parseInt(arr[2]);
        depShowList.remove(point - 1);
    }

    public void ShowDepCases() {
        if (this.depShowList.size() > 0) {
            System.out.println("");
            for (DepCase each : this.depShowList) {
                System.out.println(each);
            }
            System.out.println("");
        } else {
            System.out.println("\nThis bank has no deals at the moment.\n");
        }
    }

}
