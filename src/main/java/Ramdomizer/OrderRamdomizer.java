package Ramdomizer;

import CsvReader.CsvReader;
import Mapping.Resident;
import Menu.Menu;
import Order.OrderList;

import java.util.ArrayList;
import java.util.Random;

public class OrderRamdomizer {
    int dayNum;
    double price;
    String name, restaurant, food, residentFilePath;
    public int Days = 0, residentIndex = -1, indexRestSat = Integer.MAX_VALUE;
    public Random ran = new Random();
    public ArrayList<OrderList> orderList = new ArrayList<>();
    CsvReader loadSystemFile = new CsvReader();
    ArrayList<Resident> resident = loadSystemFile.LoadResident(residentFilePath);
    public ArrayList<ArrayList<OrderList>> residentOrderLists = new ArrayList<>();

    public OrderRamdomizer() {
        this.residentFilePath = residentFilePath;
    }

    public int getBound(int indexRest) {
        int bound = 0;
        switch (indexRest) {
            case 0, 1 -> bound = 5;
            case 2 -> bound = 4;
            case 3, 4 -> bound = 6;
            default -> {
            }
        }
        return bound;
    }

    public Pair<Integer> otherOrder() {
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        return new Pair<>(indexRest, indexOrder);
    }

    public ArrayList<OrderList> storeOrder(Pair<Integer> pair, String name) { //gets items from Menu and store to orderList
        Menu menu = new Menu(pair.first());
        restaurant = menu.selectedRestaurant;
        food = menu.getFood(pair.second());
        price = menu.getPrice(pair.second());

        for (int i = 0; i < resident.size(); i++) {
            if (resident.get(i).name.equals(name)) {
                residentIndex = i;
                break;
            }
        }
        if (residentIndex != -1) {
            orderList = residentOrderLists.get(residentIndex);
            orderList.add(new OrderList(name, dayNum, food, restaurant, pair.first(), pair.second(), price));
        }
        return orderList;
    }
}
