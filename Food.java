package milagroMan;

import java.time.LocalDate;

public class Food {
    private String name;
    private Double price;
    private LocalDate priceDate;

    public Food(String name, Double price) {
        this.name = name;
        this.price = price;
        this.priceDate = LocalDate.now();
    }

    public void setPriceDateRange(int startDay, int endDay) {
        LocalDate startDate = LocalDate.ofYearDay(LocalDate.now().getYear(), startDay);
        LocalDate endDate = LocalDate.ofYearDay(LocalDate.now().getYear(), endDay);
        this.priceDate = startDate;
    }

    public double calculateTotalPrice(int startDay, int endDay) {
        double totalPrice = 0.0;
        for (Sale sale : SalesRecord.getInstance().getSales()) {
            LocalDate saleDate = sale.getDate();
            if (saleDate.getDayOfYear() >= startDay && saleDate.getDayOfYear() <= endDay
                    && sale.getFood().getName().equals(this.name)) {
                totalPrice += sale.getQuantity() * price;
            }
        }
        return totalPrice;
    }

    public int calculateQuantity(int startDay, int endDay) {
        int quantity = 0;
        for (Sale sale : SalesRecord.getInstance().getSales()) {
            LocalDate saleDate = sale.getDate();
            if (saleDate.getDayOfYear() >= startDay && saleDate.getDayOfYear() <= endDay
                    && sale.getFood().getName().equals(this.name)) {
                quantity += sale.getQuantity();
            }
        }
        return quantity;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(LocalDate priceDate) {
        this.priceDate = priceDate;
    }
}
