package Menu.Ramdomizer;

import Order.OrderList;
import Order.OrderObtainer;

import java.util.ArrayList;

public class OrderGenerater {
    private final OrderRandomizer randomizer;
    private final OrderObtainer obtainer;

    public OrderGenerater(OrderRandomizer randomizer, String residentFilePath, int dayNum) {
        this.randomizer = randomizer;
        this.obtainer = new OrderObtainer(residentFilePath, dayNum);
    }

    public ArrayList<ArrayList<OrderList>> generate() {
        Pair<Integer> pair;

        for (int i = 0; i < this.randomizer.resident.size(); i++) {
            this.randomizer.residentOrderLists.add(new ArrayList<>());
        }
        while (this.randomizer.Days < this.randomizer.dayNum) {
            for (int i = 0; i < this.randomizer.resident.size(); i++) {
                this.randomizer.name = this.randomizer.resident.get(i).name;
                this.randomizer.residentIndex = i;
                switch (this.randomizer.name) {
                    case "Jonathan Joestar" -> {
                        pair = this.obtainer.jonathanOrder();
                        this.randomizer.orderList = this.randomizer.storeOrder(pair, this.randomizer.name);
                    }
                    case "Joseph Joestar" -> {
                        pair = this.obtainer.josephOrder();
                        this.randomizer.orderList = this.randomizer.storeOrder(pair, this.randomizer.name);
                    }
                    case "Jotaro Kujo" -> {
                        pair = this.obtainer.jotaroOrder();
                        if (this.randomizer.Days % 7 == 0)
                            this.randomizer.indexRestSat = pair.first();
                        this.randomizer.orderList = this.randomizer.storeOrder(pair, this.randomizer.name);
                    }
                    case "Josuke Higashikata" -> {
                        pair = this.obtainer.josukeOrder();
                        this.randomizer.orderList = this.randomizer.storeOrder(pair, this.randomizer.name);
                    }
                    case "Giorno Giovanna" -> {
                        pair = this.obtainer.giornoOrder();
                        this.randomizer.orderList = this.randomizer.storeOrder(pair, this.randomizer.name);
                    }
                    case "Jolyne Cujoh" -> {
                        pair = this.obtainer.jolyneOrder();
                        if (this.randomizer.Days % 7 == 0)
                            this.randomizer.indexRestSat = pair.first();
                        this.randomizer.orderList = this.randomizer.storeOrder(pair, this.randomizer.name);
                    }
                    default -> {
                        pair = this.randomizer.otherOrder();
                        this.randomizer.orderList = this.randomizer.storeOrder(pair, this.randomizer.name);
                    }
                }
            }
            this.randomizer.Days++;
        }
        return this.randomizer.residentOrderLists;
    }
}
