import myFirPack.House;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<House> hArr = new ArrayList<>();

    public static void main(String[] args) {

        getInput();

        for (House house : hArr) {
            System.out.println(house);
        }
        Scanner info = new Scanner(System.in);
        System.out.print("Enter how many rooms you want: ");
        showByRooms(info.nextInt());
        System.out.print("Enter how many rooms you want and on which floors (min, max): ");
        showByRoomsAndFloors(info.nextInt(), info.nextInt(), info.nextInt());
        System.out.print("Enter minimum square amount: ");
        showBySquare(info.nextDouble());
    }

    public static void getInput() {
        int tempId = 0;
        int tempFlatNumber = 0;
        double tempSquare = 0.0;
        int tempFloor = 0;
        int tempRooms = 0;
        String tempStreet = "";

        System.out.println("Welcome! Let's input house data.");

        while (true) {
            System.out.println("Input ID(0 - stop input): ");
            Scanner info = new Scanner(System.in);
            int temp = info.nextInt();
            if (temp <= 0)
                break;
            tempId = temp;
            System.out.println("Enter a Flat Number: ");
            tempFlatNumber = info.nextInt();
            System.out.println("Enter a Square(x,x): ");
            tempSquare = info.nextDouble();
            System.out.println("Enter a Floor: ");
            tempFloor = info.nextInt();
            System.out.println("Enter a number of Rooms: ");
            tempRooms = info.nextInt();
            System.out.println("Enter a Street adress: ");
            tempStreet = info.nextLine();

            hArr.add(new House(tempId, tempFlatNumber, tempSquare, tempFloor, tempRooms, tempStreet));
        }
    }

    public static void showByRooms(int limit) {
        boolean flag = false;
        for (var room : hArr) {
            if (room.getRooms() >= limit) {
                System.out.println(room);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("There is no match ;(");
        }
    }

    public static void showByRoomsAndFloors(int limit, int min, int max) {
        boolean flag = false;
        for (var room : hArr) {
            if (room.getRooms() >= limit && room.getFloor() > min && room.getFloor() < max) {
                System.out.println(room);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("There is no match ;(");
        }
    }

    public static void showBySquare(double limit) {
        boolean flag = false;
        for (var room : hArr) {
            if (room.getSquare() >= limit) {
                System.out.println(room);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("There is no match ;(");
        }
    }
}