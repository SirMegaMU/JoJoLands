package milagroMan;

class FoodQuantity {
    private Food food;
    private int quantity;

    public FoodQuantity(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }
}
