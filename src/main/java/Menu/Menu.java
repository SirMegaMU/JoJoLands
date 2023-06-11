package Menu;

public class Menu {
    public Item[] items;
    public String selectedRestaurant;
    private final int indexRest;

    public Menu(int indexRest) {
        this.indexRest = indexRest;
        setMenu();
    }

    public void setMenu() {
        switch (indexRest) {
            case 0 -> {
                selectedRestaurant = "Jade Gardens";
                items = new Item[]{
                        new Item("Braised Chicken in Black Bean Sauce", 15.00),
                        new Item("Braised Goose Web with Vermicelli", 21.00),
                        new Item("Deep-fried Hiroshima Oysters", 17.00),
                        new Item("Poached Tofu with Dried Shrimps", 12.00),
                        new Item("Scrambled Egg White with Milk", 10.00)
                };
            }
            case 1 -> {
                selectedRestaurant = "Cafe Daux Magots";
                items = new Item[]{
                        new Item("Sampling Matured Cheese Platter", 23.00),
                        new Item("Spring Lobster Salad", 35.00),
                        new Item("Spring Organic Omelette", 23.00),
                        new Item("Truffle-flavoured Poultry Supreme", 34.00),
                        new Item("White Asparagus", 26.00)
                };
            }
            case 2 -> {
                selectedRestaurant = "Trattoria Trussardi";
                items = new Item[]{
                        new Item("Caprese Salad", 10.00),
                        new Item("Creme caramel", 6.50),
                        new Item("Lamb Chops with Apple Sauce", 25.00),
                        new Item("Spaghetti alla Puttanesca", 15.00)
                };
            }
            case 3 -> {
                selectedRestaurant = "Libeccio";
                items = new Item[]{
                        new Item("Formaggio", 12.50),
                        new Item("Ghiaccio", 1.01),
                        new Item("Melone", 5.20),
                        new Item("Prosciutto and Pesc", 20.23),
                        new Item("Risotto", 13.14),
                        new Item("Zucchero and Sale", 0.60),
                };
            }
            case 4 -> {
                selectedRestaurant = "Savage Garden";
                items = new Item[]{
                        new Item("Abbacchio’s Tea", 1.00),
                        new Item("DIO’s Bread", 36.14),
                        new Item("Giorno’s Donuts", 6.66),
                        new Item("Joseph’s Tequila", 35.00),
                        new Item("Kakyoin’s Cherry", 3.50),
                        new Item("Kakyoin’s Porridge", 4.44),
                };
            }
            default -> {
            }
        }
    }

    public String getFood(int indexOrder) {
        String food;
        Item item = this.items[indexOrder];
        food = item.name();
        return food;
    }

    public double getPrice(int indexOrder) {
        double price;
        Item item = this.items[indexOrder];
        price = item.price();
        return price;
    }

}
