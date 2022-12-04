package banking;

public class Deposit {

    int userID, period, depID, bankID;
    double balance, profit;
    String start_date;

    public Deposit(int bankID, int userID, int period, int depID, double balance, double profit, String start_date) {
        this.bankID = bankID;
        this.userID = userID;
        this.period = period;
        this.depID = depID;
        this.balance = balance;
        this.profit = profit;
        this.start_date = start_date;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPeriod() {
        return this.period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getDepID() {
        return this.depID;
    }

    public void setDepID(int depID) {
        this.depID = depID;
    }

    public int getBankID() {
        return this.bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getProfit() {
        return this.profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getStart_date() {
        return this.start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

}
