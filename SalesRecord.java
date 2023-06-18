package milagroMan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesRecord {
    private static SalesRecord instance;
    private List<Sale> sales;

    SalesRecord() {
        sales = new ArrayList<>();
    }

    public static SalesRecord getInstance() {
        if (instance == null) {
            instance = new SalesRecord();
        }
        return instance;
    }

    public void addSale(LocalDate date, Food food, int quantity) {
        Sale sale = new Sale(date, food, quantity);
        sales.add(sale);
    }

    public List<Sale> getSales() {
        return sales;
    }

    public static void main(String[] args) {
        // Usage example
        SalesRecord salesRecord = SalesRecord.getInstance();

        // Adding sales
        LocalDate date1 = LocalDate.now();
        Food food1 = new Food("Braised Chicken in Black Bean Sauce", 15.00);
        int quantity1 = 2;
        salesRecord.addSale(date1, food1, quantity1);

        LocalDate date2 = LocalDate.now();
        Food food2 = new Food("Spring Lobster Salad", 35.00);
        int quantity2 = 1;
        salesRecord.addSale(date2, food2, quantity2);

        // Retrieving sales
        List<Sale> allSales = salesRecord.getSales();

        // Displaying sales
        for (Sale sale : allSales) {
            System.out.println("Date: " + sale.getDate());
            System.out.println("Food: " + sale.getFood().getName());
            System.out.println("Quantity: " + sale.getQuantity());
            System.out.println("--------------------------");
        }
    }
}

class Sale {
    private LocalDate date;
    private Food food;
    private int quantity;

    public Sale(LocalDate date, Food food, int quantity) {
        this.date = date;
        this.food = food;
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }
}
