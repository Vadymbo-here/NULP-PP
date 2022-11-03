package utils;

import org.json.*;

import banking.Bank;
import banking.DepCase;
import banking.Deposit;
import userclass.User;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class JsonWorker {
    public static void main(String[] args) {
        System.out.println(getFreeSlot("depcases", "depID"));
    }

    public static String getJsonStringF() {
        FileReader fr;
        String jsonString = "";
        try {
            fr = new FileReader("D:\\Code\\Proggrams\\PP_labki1\\NULP_PP\\COMPLEX\\deposits\\src\\database.json");
            int i;
            while ((i = fr.read()) != -1) {
                jsonString += ((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static void writeToDatabase(String where, JSONObject obj) throws IOException {
        JSONObject main = new JSONObject(getJsonStringF());
        JSONArray mm = main.getJSONArray(where);
        mm.put(obj);

        PrintWriter printRecord = new PrintWriter(new BufferedWriter(new FileWriter("src\\database.json", false)));
        printRecord.println(main.toString());

        printRecord.close();
    }

    public static void updateDatabase(JSONObject main) throws IOException {
        PrintWriter printRecord = new PrintWriter(new BufferedWriter(new FileWriter("src\\database.json", false)));
        printRecord.println(main.toString());

        printRecord.close();
    }

    public static void writeDepCase(int bankID, int depID, String name, String desc, int type, float perc)
            throws IOException {
        JSONObject temp = new JSONObject();
        temp.put("bankID", bankID);
        temp.put("depID", depID);
        temp.put("name", name);
        temp.put("description", desc);
        temp.put("type", type);
        temp.put("percentage", perc);
        writeToDatabase("depcases", temp);
    }

    public static void delDepCase(int BankID, int depID) throws IOException {
        JSONObject main = new JSONObject(getJsonStringF());
        JSONArray mm = main.getJSONArray("depcases");
        for (int i = 0; i < mm.length(); i++) {
            if (mm.getJSONObject(i).getInt("bankID") == BankID && mm.getJSONObject(i).getInt("depID") == depID) {
                mm.remove(i);
            }
        }
        updateDatabase(main);
    }

    public static int getLen(String where) {
        JSONObject main;
        JSONArray mm = new JSONArray();
        try {
            main = new JSONObject(getJsonStringF());
            mm = main.getJSONArray(where);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mm.length();
    }

    public static JSONArray getArrayList(String where) {
        try {
            JSONObject main = new JSONObject(getJsonStringF());
            JSONArray mm = main.getJSONArray(where);
            return mm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Bank> getBankList() {
        ArrayList<Bank> banksBank = new ArrayList<>();
        JSONArray myList = getArrayList("banks");
        for (int i = 0; i < myList.length(); i++) {
            banksBank
                    .add(new Bank(myList.getJSONObject(i).getInt("bankID"), myList.getJSONObject(i).getString("name")));
        }
        return banksBank;
    }

    public static ArrayList<Deposit> getDepList() {
        ArrayList<Deposit> deps = new ArrayList<>();
        JSONArray myList = getArrayList("deposits");
        for (int i = 0; i < myList.length(); i++) {
            JSONObject dep = myList.getJSONObject(i);
            deps.add(new Deposit(
                    dep.getInt("bankID"),
                    dep.getInt("userID"),
                    dep.getInt("period"),
                    dep.getInt("depID"),
                    dep.getDouble("balance"),
                    dep.getDouble("profit"),
                    dep.getString("start_date")));
        }
        return deps;
    }

    public static ArrayList<DepCase> getDepCaseList() {
        ArrayList<DepCase> depCases = new ArrayList<>();
        JSONArray depCasesList = getArrayList("depcases");
        for (int i = 0; i < depCasesList.length(); i++) {
            JSONObject depCase = depCasesList.getJSONObject(i);
            depCases.add(new DepCase(
                    depCase.getInt("depID"),
                    depCase.getString("name"),
                    depCase.getString("description"),
                    depCase.getFloat("percentage"),
                    depCase.getInt("type"),
                    depCase.getInt("bankID")));
        }

        return depCases;
    }

    public static int getFreeSlot(String where, String IdSlot) {
        JSONArray myList = getArrayList(where);
        boolean isUniq = false;
        int freeSlot = 1;
        if (myList.isEmpty()) {
            return 1;
        }
        while (!isUniq) {
            for (int i = 0; i < myList.length(); i++) {
                if (myList.getJSONObject(i).getInt(IdSlot) == freeSlot) {
                    isUniq = false;
                    freeSlot++;
                    break;
                }
                isUniq = true;
            }
        }

        return freeSlot;
    }

    public static String printAllDepCases(int bankID) {
        int i;
        JSONArray bankList = getArrayList("banks");
        JSONArray depCaseList = getArrayList("depcases");

        if (bankList.length() == 0) {
            return "Your bank doen't exits.";
        }

        if (depCaseList.length() == 0) {
            return "This bank has no deal for you.";
        }

        for (i = 0; i < bankList.length(); i++) {
            if (bankList.getJSONObject(i).getInt("bankID") == bankID) {
                break;
            }
        }

        for (int j = 0; j < depCaseList.length(); j++) {
            int bankID2 = depCaseList.getJSONObject(j).getInt("bankID");
            if (bankID == bankID2) {
                String type;
                switch (depCaseList.getJSONObject(j).getInt("type")) {
                    case 0:
                        type = "Without Capitalization";
                        break;

                    case 1:
                        type = "With Capitalization";
                        break;

                    default:
                        type = "";
                        break;
                }

                System.out.printf("------%s------\nOffer:\n%s[%d] - %s\nWhat is it: %s.\n\nYear Percentage: %.2f\n",
                        bankList.getJSONObject(i).getString("name"),
                        depCaseList.getJSONObject(i).getString("name"),
                        depCaseList.getJSONObject(j).getInt("depID"),
                        type,
                        depCaseList.getJSONObject(j).getString("description"),
                        depCaseList.getJSONObject(j).getFloat("percentage"));
            }
        }

        return "Command executed succesfully";
    }

    // ADMIN
    public static String changeDepCase(int bankID, int depID, String key, String newValue)
            throws JSONException, IOException {
        JSONArray depCaseList = getArrayList("depcases");
        if (depCaseList.isEmpty()) { // error
            return "There is no depCases to change.";
        }
        for (int i = 0; i < depCaseList.length(); i++) {
            JSONObject depCase = depCaseList.getJSONObject(i);
            if (bankID == depCase.getInt("bankID") && depID == depCase.getInt("depID")) {
                switch (key) {
                    case "name":
                        depCase.put("name", newValue);
                        break;

                    case "description":
                        depCase.put("description", newValue);
                        break;

                    case "percentage":
                        float tmp = Float.parseFloat(newValue);
                        depCase.put("percentage", tmp);
                        break;

                    case "type":
                        int type = Integer.parseInt(newValue);
                        depCase.put("type", type);
                        break;
                    default:
                        System.out.println("Wrong input check your spelling!");
                        break;
                }
                delDepCase(bankID, depID);
                writeToDatabase("depcases", depCase);
                return "Succesfully changed " + key + " in Depcase[" + depID + "]";
            }
        }
        return "Error occupied!";

    }

    public static void registerUser(String fName, String sName, String login, String password)
            throws IOException {
        JSONObject myUser = new JSONObject();
        myUser.put("BankID", 0);
        myUser.put("userID", getFreeSlot("users", "userID"));
        myUser.put("password", password);
        myUser.put("role", 0);
        myUser.put("firstName", fName);
        myUser.put("lastName", sName);
        myUser.put("login", login);
        writeToDatabase("users", myUser);
    }

    public static User loginUser(String login, String password) {
        JSONArray userList = getArrayList("users");
        User tmp = new User();
        tmp.setUserID(0);
        JSONObject params;
        for (int i = 0; i < userList.length(); i++) {
            params = userList.getJSONObject(i);
            if (params.getString("login").equals(login) && params.getString("password").equals(password)) {
                tmp.setFirstname(params.getString("firstName"));
                tmp.setLastname(params.getString("lastName"));
                tmp.setBankID(params.getInt("bankID"));
                tmp.setPassword(params.getString("password"));
                tmp.setRole(params.getInt("role"));
                tmp.setLogin(params.getString("login"));
                tmp.setUserID(params.getInt("userID"));
            }
        }
        return tmp;
    }

    public static String inputDeposit(int userID, int bankID, int depID, double balance, int days) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        JSONArray deps = getArrayList("deposits");
        JSONObject dep = new JSONObject();
        boolean isCreated = false;
        for (int i = 0; i < deps.length(); i++) {
            dep = deps.getJSONObject(i);
            if (dep.getInt("userID") == userID && dep.getInt("depID") == depID && dep.getInt("bankID") == bankID) {
                isCreated = true;
            }
        }
        if (isCreated) {
            return "Element is already created.";
        }
        dep.put("bankID", bankID);
        dep.put("period", days);
        dep.put("balance", balance);
        dep.put("depID", depID);
        dep.put("userID", userID);
        dep.put("profit", 0.0);
        dep.put("start_date", dtf.format(now));

        writeToDatabase("deposits", dep);
        return "Command executed succesfully.";
    }

    public static String delDeposit(int userID, int bankID, int depID) throws IOException {
        JSONObject main = new JSONObject(getJsonStringF());
        JSONArray mm = main.getJSONArray("deposits");
        if (mm.isEmpty()) {// error
            return "There is no deposit to delete.";
        }
        for (int i = 0; i < mm.length(); i++) {
            try {
                if (mm.getJSONObject(i).getInt("bankID") == bankID && mm.getJSONObject(i).getInt("depID") == depID
                        && mm.getJSONObject(i).getInt("userID") == userID) {
                    mm.remove(i);
                }
            } catch (Exception e) {
                return "There is no such element. Check you params.";
            }
        }
        updateDatabase(main);
        return "Command executed succesfully";
    }

    public static String printAllDeposits(int userID) {
        ArrayList<Deposit> deps = getDepList();
        ArrayList<Bank> banks = getBankList();
        if (deps.isEmpty()) {// error
            return "Looks like there is no deposits.";
        }
        System.out.println("Your active deposits:");
        boolean flag = false;
        for (Deposit each : deps) {
            if (each.getUserID() == userID) {
                flag = true;
                String bankName = "None";
                for (int i = 0; i < banks.size(); i++) {
                    if (banks.get(i).getBankID() == each.getBankID()) {
                        bankName = banks.get(i).getName();
                    }
                }

                System.out.printf(
                        "\t====Deposit[%d] by %s====\n\tFrom: %s\n\tYour balance: %f\n\tYour profit: %f\n\tPeriod in days: %d\n\tBank ID: %d\n\n",
                        each.getDepID(),
                        bankName,
                        each.getStart_date(),
                        each.getBalance(),
                        each.getProfit(),
                        each.getPeriod(),
                        each.getBankID());
            }
        }
        if (!flag) {
            return "Looks like you have no active deposits. Maybe it's time to make one?";
        }
        return "Command executed succesfully";
    }

    // USER
    public static String updateDeposit(int userID, int bankID, int depID, String newval) throws IOException {
        JSONArray deps = getArrayList("deposits");
        JSONObject dep = new JSONObject();
        if (deps.isEmpty()) {
            return "There is nothing to change.";
        }
        for (int i = 0; i < deps.length(); i++) {
            dep = deps.getJSONObject(i);
            try {
                if (dep.getInt("userID") == userID && dep.getInt("depID") == depID && dep.getInt("bankID") == bankID) {
                    double newbal = dep.getDouble("balance") + Double.parseDouble(newval);
                    dep.put("balance", newbal);
                    delDeposit(userID, bankID, depID);
                    writeToDatabase("deposits", dep);
                    return "Command executed succesfully.";
                }
            } catch (Exception e) {
                return "There is no such element. Check you params.";
            }
        }
        return "Command executed succesfully";
    }
}