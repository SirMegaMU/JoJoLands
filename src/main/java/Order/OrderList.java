package Order;

public class OrderList {

    public String name, food, restaurant;
    public int dayNum, indexRestaurant, indexOrder, totalDays = 0;
    public double price;

    public OrderList(String name, int dayNum, String food, String restaurant, int indexRestaurant, int indexOrder, double price) {
        this.name = name;
        this.dayNum = dayNum;
        this.food = food;
        this.restaurant = restaurant;
        this.indexRestaurant = indexRestaurant;
        this.indexOrder = indexOrder;
        this.price = price;
    }
}
