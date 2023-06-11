package Order;


import CsvReader.CsvReader;
import Mapping.Resident;

import java.util.ArrayList;

public class OrderHistory {
    String name;
    int dayNum;
    String residentFilePath = "residents.csv";
    CsvReader loadSystemFile = new CsvReader();
    ArrayList<Resident> resident = loadSystemFile.LoadResident(residentFilePath);

    public OrderHistory(String name, int dayNum) {
        this.name = name;
        this.dayNum = dayNum;
    }

    public void printOrderHistory(ArrayList<ArrayList<OrderList>> residentOrderLists) {
        System.out.println("");
        System.out.println("Order History");
        System.out.println("+------+------------------------------------------+----------------------+");
        System.out.println("| Day  | Food                                     | Restaurant           |");
        System.out.println("+------+------------------------------------------+----------------------+");
        int residentIndex = -1;
        for (int i = 0; i < resident.size(); i++) {
            if (resident.get(i).name.equals(name)) {
                residentIndex = i;
                break;
            }
        }
        ArrayList<OrderList> orderList = residentOrderLists.get(residentIndex);
        for (int i = 0; i < orderList.size(); i++) {
            System.out.printf("| %-2d   | %-40s | %-20s |%n", i + 1, orderList.get(i).food, orderList.get(i).restaurant);
        }
        System.out.println("+------+------------------------------------------+----------------------+");
        System.out.println("===============================================================================");
    }
}
