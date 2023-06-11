import Display.DisplayResidentInfo;
import Order.OrderHistory;
import Order.OrderList;
import Ramdomizer.OrderGenerater;
import Ramdomizer.OrderRandomizer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int day;
        String residentFilePath = "residents.csv";
        String standFilePath = "stands.csv";

        day = 21;

        System.out.print("Select: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            boolean containName = false;
            System.out.print("Enter the resident's name: ");
            String name = scanner.nextLine();
            while (!containName) {
                System.out.println("===============================================================================");

                DisplayResidentInfo residentInfo = new DisplayResidentInfo();
                containName = residentInfo.residentProfile(residentFilePath, standFilePath, name);
                if (!containName) {
                    System.out.println("Invalid name");
                    System.out.print("Enter the resident's name: ");
                    name = scanner.nextLine();
                }
            }
            OrderRandomizer randomizer = new OrderRandomizer(residentFilePath, day);
            ArrayList<ArrayList<OrderList>> residentOrderLists = new OrderGenerater(randomizer, residentFilePath, day).generate();
            OrderHistory order = new OrderHistory(name, day);
            order.printOrderHistory(residentOrderLists);

        } else {
            System.out.println("Wrong input");
        }
    }
}
