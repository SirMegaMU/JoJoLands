package Order;

import Menu.Menu;
import Ramdomizer.OrderRandomizer;
import Ramdomizer.Pair;

import java.util.ArrayList;
import java.util.List;

public class OrderObtainer extends OrderRandomizer {
    public OrderObtainer() {
        super();
    }

    public Pair<Integer> jonathanOrder() {

        boolean loop = true;
        int indexRest = ran.nextInt(5);
        int indexOrder = ran.nextInt(getBound(indexRest));
        if (residentIndex != -1) {
            orderList = residentOrderLists.get(residentIndex);
            int maxcount = 0;
            int mincount = Integer.MAX_VALUE;
            List<Integer> maxIndices = new ArrayList<>();

            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.size() > 1) {
                    int count = 0;

                    for (OrderList list : orderList) {
                        if (orderList.get(i).food.equals(list.food)) {
                            count++;
                        }
                        if (count > maxcount) {
                            maxcount = count;
                            maxIndices.clear();
                            maxIndices.add(i);
                        } else if (count == maxcount) {
                            maxIndices.add(i);
                        }
                        if (count < mincount || mincount == 0) {
                            mincount = count;
                        }
                    }
                }
            }
            while (loop) {
                boolean foundMatch = false;
                if ((maxcount - mincount) >= 1) {
                    for (Integer maxIndex : maxIndices) {

                        int selectedMaxElement = orderList.get(maxIndex).indexOrder;
                        int selectedMaxRest = orderList.get(maxIndex).indexRestaurant;

                        if (selectedMaxElement == indexOrder && selectedMaxRest == indexRest) {
                            foundMatch = true;
                            break;
                        }
                    }
                }
                if (foundMatch) {
                    indexRest = ran.nextInt(5);
                    indexOrder = ran.nextInt(getBound(indexRest));
                } else {
                    loop = false;
                }
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }

    public Pair<Integer> josephOrder() {

        int indexRest = ran.nextInt(5);
        int indexOrder = ran.nextInt(getBound(indexRest));
        if (residentIndex != -1) {
            orderList = residentOrderLists.get(residentIndex);
            if (orderList.size() < 27) {
                boolean isDuplicate;
                do {
                    isDuplicate = false;
                    for (int j = 0; j < orderList.size(); j++) {
                        if (orderList.get(j).indexRestaurant == indexRest) {
                            for (int k = 0; k < orderList.size(); k++) {
                                if (orderList.get(j).indexOrder == indexOrder) {
                                    indexRest = ran.nextInt(5);
                                    indexOrder = ran.nextInt(getBound(indexRest));
                                    isDuplicate = true;
                                    break;
                                }
                            }
                        }
                    }
                } while (isDuplicate);
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }

    public Pair<Integer> jotaroOrder() {

        int indexRest = ran.nextInt(5);
        int indexOrder = ran.nextInt(getBound(indexRest));
        if (residentIndex != -1) {
            orderList = residentOrderLists.get(residentIndex);
            if (!orderList.isEmpty()) {
                int lastIndexRest = orderList.get(orderList.size() - 1).indexRestaurant;
                indexRest = lastIndexRest;
                indexOrder = ran.nextInt(getBound(indexRest));
                int count = 0;
                List<Integer> found = new ArrayList<>();
                for (int i = 0; i < orderList.size(); i++) {
                    if (orderList.get(i).indexRestaurant == indexRest) {
                        found.add(i);
                        count++;
                    }
                }
                if (count > getBound(indexRest)) {
                    count = count - getBound(indexRest);
                }
                if (count < getBound(indexRest)) {
                    boolean loop;
                    do {
                        loop = false;
                        for (Integer integer : found) {
                            if (orderList.get(integer).indexOrder == indexOrder) {
                                indexOrder = ran.nextInt(getBound(indexRest));
                                loop = true;
                                break;
                            }
                        }
                    } while (loop);
                } else {
                    indexRest = (lastIndexRest + 1) % 5;
                    indexOrder = ran.nextInt(getBound(indexRest));
                }
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }

    public Pair<Integer> josukeOrder() {

        double budget = 100;
        double moneySpent = 0;
        int indexRest = ran.nextInt(5);
        int indexOrder = ran.nextInt(getBound(indexRest));

        if (residentIndex != -1) {
            orderList = residentOrderLists.get(residentIndex);
            if (!orderList.isEmpty()) {
                int j = Math.min(orderList.size(), 7);
                for (int i = j; i < orderList.size(); i++) {
                    moneySpent += orderList.get(i).price;
                }

                Menu menu = new Menu(indexRest);
                moneySpent += menu.getPrice(indexOrder);

                while (moneySpent > budget) {
                    int[] restArr = {2, 3, 4};
                    indexRest = restArr[ran.nextInt(restArr.length)];
                    List<Integer> foodArr = new ArrayList<>();
                    switch (indexRest) {
                        case 2 -> {
                            foodArr.add(1);
                        }
                        case 3 -> {
                            foodArr.add(1);
                            foodArr.add(2);
                            foodArr.add(5);
                        }
                        case 4 -> {
                            foodArr.add(0);
                            foodArr.add(2);
                            foodArr.add(4);
                            foodArr.add(5);
                        }
                        default -> {
                        }
                    }
                    indexOrder = foodArr.get(ran.nextInt(foodArr.size()));
                    moneySpent = orderList.get(j).price;
                }
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }

    public Pair<Integer> giornoOrder() {

        int visited = 0;
        int indexVisited = -1;
        int weekVisits = 0;
        int count = 0;
        int indexRest = ran.nextInt(5);
        int indexOrder = ran.nextInt(getBound(indexRest));

        if (residentIndex != -1) {
            orderList = residentOrderLists.get(residentIndex);
            if (!orderList.isEmpty()) {
                for (int i = 0; i < orderList.size(); i++) {
                    count++;
                    if (orderList.get(i).indexRestaurant == 2) {
                        visited++;
                        indexVisited = i;
                        weekVisits++;
                    }
                    if (count == 8) {
                        count = 0;
                        weekVisits = 0;
                    }
                }
                if (indexRest == 2) {
                    if (visited >= 1) {
                        boolean loop;
                        do {
                            loop = false;
                            if (indexOrder == orderList.get(indexVisited).indexOrder) {
                                indexOrder = ran.nextInt(getBound(indexRest));
                                loop = true;
                            }
                        } while (loop);
                    }
                }

                count = 0;
                int weekSize = 7;
                int totalWeeks = (orderList.size() + weekSize - 1) / weekSize;

                for (int week = 0; week < totalWeeks; week++) {
                    int startIndex = week * weekSize;
                    int endIndex = Math.min(startIndex + weekSize, orderList.size());
                    boolean isFinalWeek = (week == totalWeeks - 1);

                    if (isFinalWeek) {

                        for (int i = startIndex; i < orderList.size(); i++) {
                            count++;
                            if (count == 5) {
                                if (weekVisits == 0) {
                                    indexRest = 2;
                                    indexOrder = ran.nextInt(getBound(indexRest));
                                }
                            }
                            if (count == 6) {
                                if (weekVisits == 1) {
                                    indexRest = 2;
                                    indexOrder = ran.nextInt(getBound(indexRest));
                                    boolean loop;
                                    do {
                                        loop = false;
                                        if (orderList.get(indexVisited).indexOrder == indexOrder) {
                                            indexOrder = ran.nextInt(getBound(indexRest));
                                            loop = true;
                                        }
                                    } while (loop);
                                }
                            }
                        }
                    }
                }
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }

    public Pair<Integer> jolyneOrder() {

        int indexRest = ran.nextInt(5);
        int indexOrder = ran.nextInt(getBound(indexRest));

        if (residentIndex != -1) {
            orderList = residentOrderLists.get(residentIndex);
            if (Days % 7 != 0) {
                boolean loop;
                do {
                    loop = false;
                    if (orderList.get(orderList.size() - 1).indexRestaurant == indexRest) {
                        indexRest = ran.nextInt(5);
                        indexOrder = ran.nextInt(getBound(indexRest));
                        loop = true;
                    }
                } while (loop);
            } else { //if the day is Saturday
                if (indexRestSat != Integer.MAX_VALUE) {
                    indexRest = indexRestSat;
                } else {
                    indexRestSat = indexRest;
                }
                indexOrder = ran.nextInt(getBound(indexRest));
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }
}
