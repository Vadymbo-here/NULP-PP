package myFirPack;

public class House {

    private int id;
    private int flatNumber;
    private double square;
    private int floor;
    private int rooms;
    private String street;

    public House() {
        this.id = this.flatNumber = this.floor = this.rooms = 0;
        this.square = 0.0;
        this.street = "";
    }

    public House(int id, int flatNumber, double square, int floor, int rooms, String street) {
        this.id = id;
        this.flatNumber = flatNumber;
        this.square = square;
        this.floor = floor;
        this.rooms = rooms;
        this.street = street;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlatNumber() {
        return this.flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public double getSquare() {
        return this.square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRooms() {
        return this.rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return " -->" +
                " Room[" + getId() + "]: " +
                " flatNumber='" + getFlatNumber() + "' ," +
                " square='" + getSquare() + "' ," +
                " floor='" + getFloor() + "' ," +
                " rooms='" + getRooms() + "' ," +
                " street='" + getStreet() + "'" +
                " <--";
    }
}
