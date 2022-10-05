package banking;

public class DepCase {
    int depID, type;
    float percentage;
    String name, description;

    public DepCase(int DepID, String name, String description, float percentage, int type) {
        this.depID = DepID;
        this.name = name;
        this.description = description;
        this.percentage = percentage;
        this.type = type;
    }

    public DepCase(int DepID, String name2, String description2, String string, String string2) {
        this.depID = DepID;
        this.name = name2;
        this.description = description2;
        this.percentage = Float.parseFloat(string);
        this.type = Integer.parseInt(string2);
    }

    public int getDepID() {
        return this.depID;
    }

    public void setDepID(int depID) {
        this.depID = depID;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getPercentage() {
        return this.percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + this.depID + "] Deposit: \"" + this.name + "\"\n\t" + this.description
                + "\n\tPercentages by year: " + this.percentage;
    }

    public double getProfit(double balance, int period) {
        double res = 0.0;
        // LOGIC

        switch (this.type) {
            case 0:
                res = (balance * (this.percentage / 100) * period) / 365;
                break;
            case 1:
                // Monthly capatalization / 100
                res = balance * Math.pow(((this.percentage / 100) / 12), Math.floor(period / 30));
                break;

            default:
                System.out.println("Wrong deposit type. Call support.");
                break;
        }

        return res * 0.805; // Taxes in Ukraine 19.5% (18% - For income, 1.5% - Army)
    }
}
