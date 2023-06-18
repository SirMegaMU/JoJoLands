package milagroMan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Restaurant {
    private String name;
    private ArrayList<String> waitingList;
    private ArrayList<String> orderProcessingList;
    private ArrayList<SalesRecord> salesRecords;
    private ArrayList<Restaurant> adjacentRestaurants;
    private SalesRecord salesRecord;
    private HashMap<String, Food> menu;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new HashMap<>();
        this.waitingList = new ArrayList<>();
        this.orderProcessingList = new ArrayList<>();
        this.salesRecords = new ArrayList<>();
        this.adjacentRestaurants = new ArrayList<>();
    }

    public void enterMilagroManMode() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equals("3")) {
            System.out.println("======================================================================");
            System.out.println("Restaurant: " + name + " (Milagro Man Mode)");
            System.out.println("[1] Modify Food Prices");
            System.out.println("[2] View Sales Information");
            System.out.println("[3] Exit Milagro Man");
            System.out.print("Select: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    modifyFoodPrices();
                    break;
                case "2":
                    viewSalesInformation();
                    break;
                case "3":
                    System.out.println("Exiting Milagro Man...");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void modifyFoodPrices() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter food name: ");
        String foodName = scanner.nextLine();

        if (!menu.containsKey(foodName)) {
            System.out.println("Food not found in the menu.");
            return;
        }

        System.out.print("Enter new price: $");
        double newPrice = scanner.nextDouble();
        System.out.print("Enter Start Day: ");
        int startDay = scanner.nextInt();
        System.out.print("Enter End Day: ");
        int endDay = scanner.nextInt();

        Food food = menu.get(foodName);
        food.setPrice(newPrice);
        food.setPriceDateRange(startDay, endDay);

        System.out.println("Food prices modified successfully.");
    }

    private void viewSalesInformation() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equals("3")) {
            System.out.println("======================================================================");
            System.out.println("Restaurant: " + name + " (Milagro Man Mode)");
            System.out.println("Sales Information");
            System.out.println("[1] View Sales");
            System.out.println("[2] View Aggregated Information");
            System.out.println("    [A] Minimum Sales");
            System.out.println("    [B] Maximum Sales");
            System.out.println("    [C] Top k Highest Sales");
            System.out.println("    [D] Total and Average Sales");
            System.out.println("[3] Exit");
            System.out.print("Select: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // TODO: Implement the logic to view sales
                    break;
                case "2A":
                    // TODO: Implement the logic to view minimum sales
                    break;
                case "2B":
                    // TODO: Implement the logic to view maximum sales
                    break;
                case "2C":
                    // TODO: Implement the logic to view top k highest sales
                    break;
                case "2D":
                    System.out.print("Enter Start Day: ");
                    int startDay = scanner.nextInt();
                    System.out.print("Enter End Day: ");
                    int endDay = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer inputs

                    // Call printSalesInformation() with startDay and endDay
                    printSalesInformation(startDay, endDay);

                    break;

                case "3":
                    System.out.println("Exiting Sales Information...");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }


    public String getName() {
        return name;
    }

    public void addToMenu(String foodName, double price) {
        menu.put(foodName, new Food(foodName, price));
    }

    public void addToWaitingList(String customerName) {
        waitingList.add(customerName);
    }

    public void addToOrderProcessingList(String customerName) {
        orderProcessingList.add(customerName);
    }

    public void removeFromWaitingList(String customerName) {
        waitingList.remove(customerName);
    }

    public void removeFromOrderProcessingList(String customerName) {
        orderProcessingList.remove(customerName);
    }

    public boolean hasFood(String foodName) {
        return menu.containsKey(foodName);
    }

    public double getPrice(String foodName) {
        Food food = menu.get(foodName);
        if (food != null) {
            return food.getPrice();
        }
        return 0.0;
    }

    public void addSalesRecord(SalesRecord salesRecord) {
        salesRecords.add(salesRecord);
    }

    public List<SalesRecord> getSalesRecords() {
        return salesRecords;
    }

    public void setSalesRecord(SalesRecord salesRecord) {
        this.salesRecord = salesRecord;
    }

    public List<String> getWaitingList() {
        return waitingList;
    }

    public List<String> getOrderProcessingList() {
        return orderProcessingList;
    }

    public void addFood(Food food) {
        menu.put(food.getName(), food);
    }

    public void setAdjacentRestaurants(Restaurant north, Restaurant south, Restaurant east, Restaurant west, Restaurant additional) {
        adjacentRestaurants.clear();
        adjacentRestaurants.add(north);
        adjacentRestaurants.add(south);
        adjacentRestaurants.add(east);
        adjacentRestaurants.add(west);
        adjacentRestaurants.add(additional);
    }

    public void printSalesInformation(int startDay, int endDay) {
        System.out.println("======================================================================");
        System.out.println("Restaurant: " + name + " (Milagro Man Mode)");
        System.out.println("Total and Average Sales (Day " + startDay + " - " + endDay + ")");
        System.out.println("+-------------------------------------------+------------+---------------+");
        System.out.println("| Food                                      | Quantity   | Total Price   |");
        System.out.println("+-------------------------------------------+------------+---------------+");

        double totalSales = 0;
        int totalQuantity = 0;
        int count = 0;

        for (Food food : menu.values()) {
            int itemQuantity = food.calculateQuantity(startDay, endDay);
            double itemTotalPrice = food.calculateTotalPrice(startDay, endDay);

            if (itemQuantity > 0) {
                System.out.printf("| %-41s | %10d | $%11.2f |\n", food.getName(), itemQuantity, itemTotalPrice);
                totalSales += itemTotalPrice;
                totalQuantity += itemQuantity;
                count++;
            }
        }

        System.out.println("+-------------------------------------------+------------+---------------+");
        System.out.printf("| Total Sales                                               | $%11.2f |\n", totalSales);
        System.out.printf("| Average Sales                                             | $%11.2f |\n", totalSales / count);
        System.out.println("+-------------------------------------+-------------------+------------+");
        System.out.println();
    }



    public void printMenu() {
        System.out.println("Menu for " + name + ":");
        for (Food food : menu.values()) {
            System.out.println(food.getName() + " - $" + food.getPrice());
        }
        System.out.println();
    }
}
