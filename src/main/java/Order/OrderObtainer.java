package Order;

import Menu.Menu;
import Ramdomizer.OrderRamdomizer;
import Ramdomizer.Pair;

import java.util.ArrayList;
import java.util.List;

public class OrderObtainer extends OrderRamdomizer {
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
                        int selectedMaxRest = orderList.get(maxIndex).indexRest;

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
        /*won’t eat the same food twice until he’s tried
          everything currently available in JOJOLand’s*/

        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        if (residentIndex != -1) { //orderList for Joseph not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if (orderList.size() < 27) { //check if total days is less than total menu
                boolean isDuplicate;
                do {
                    isDuplicate = false;
                    for (int j = 0; j < orderList.size(); j++) {
                        if (orderList.get(j).indexRest == indexRest) {
                            for (int k = 0; k < orderList.size(); k++) {
                                if (orderList.get(j).indexOrder == indexOrder) { //check if order is similar
                                    indexRest = ran.nextInt(5); //get new index for restaurant
                                    indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
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
        /*try every dish at one restaurant
          before moving on to the next.*/

        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        if (residentIndex != -1) { //orderList for Jotaro not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if (!orderList.isEmpty()) {
                int lastIndexRest = orderList.get(orderList.size() - 1).indexRest; //get last indexRest from orderList
                indexRest = lastIndexRest;
                indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                int count = 0;
                List<Integer> found = new ArrayList<>();
                for (int i = 0; i < orderList.size(); i++) {
                    if (orderList.get(i).indexRest == indexRest) {
                        found.add(i); //add index for indexRest
                        count++;
                    }
                }
                if (count > getBound(indexRest)) { //if second time to the restaurant
                    count = count - getBound(indexRest); //get balance
                }
                if (count < getBound(indexRest)) { //haven't tried every menu at the restaurant yet
                    boolean loop;
                    do {
                        loop = false;
                        for (Integer integer : found) {
                            if (orderList.get(integer).indexOrder == indexOrder) { //check if food already tried
                                indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                                loop = true;
                                break;
                            }
                        }
                    } while (loop);
                } else {
                    indexRest = (lastIndexRest + 1) % 5; // Move to the next restaurant (5 restaurants)
                    indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                }
            }

        }
        return new Pair<>(indexRest, indexOrder);
    }

    public Pair<Integer> josukeOrder() {
        //tight weekly budget of $100, can borrow least amount of money if overspent

        double budget = 100;
        double moneySpent = 0;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order

        if (residentIndex != -1) { //orderList for Josuke not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if (!orderList.isEmpty()) {
                int j = Math.min(orderList.size(), 7);
                for (int i = j; i < orderList.size(); i++) {
                    moneySpent += orderList.get(i).price;
                }

                Menu menu = new Menu(indexRest);
                moneySpent += menu.getPrice(indexOrder); //get current index's price

                while (moneySpent > budget) {
                    int[] restArr = {2, 3, 4}; //get the restaurant with cheapest menu
                    indexRest = restArr[ran.nextInt(restArr.length)]; //get index for restaurant
                    List<Integer> foodArr = new ArrayList<>();
                    switch (indexRest) { //get cheepest food from the restaurant
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
        /*visits Trattoria Trussardi twice a week. orders
          different dish than last visit except when only
          1 option available*/

        int visited = 0;
        int indexVisited = -1;
        int weekVisits = 0;
        int count = 0;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order

        if (residentIndex != -1) { //orderList for Giorno not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if (!orderList.isEmpty()) {
                for (int i = 0; i < orderList.size(); i++) {
                    count++;
                    if (orderList.get(i).indexRest == 2) { //if he visited Trattoria Trussardi
                        visited++;
                        indexVisited = i; //get last index where indexRest = 2
                        weekVisits++;
                    }
                    if (count == 8) {
                        count = 0;
                        weekVisits = 0;
                    }
                }

                //if it's not his first visit, indexOrder should be different than last visit
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
                int weekSize = 7; //number of days in each week
                int totalWeeks = (orderList.size() + weekSize - 1) / weekSize; //calculate the total number of weeks

                for (int week = 0; week < totalWeeks; week++) {
                    int startIndex = week * weekSize; //starting index of the week
                    int endIndex = Math.min(startIndex + weekSize, orderList.size()); //ending index of the week (considering the remaining days)

                    boolean isFinalWeek = (week == totalWeeks - 1); //check to get most recent week

                    if (isFinalWeek) {
                        //loop from the first day of the final week until the last day in orderList
                        for (int i = startIndex; i < orderList.size(); i++) {
                            count++;
                            if (count == 5) { //fifth day but haven't visited Trattoria Trussardi yet
                                if (weekVisits == 0) {
                                    indexRest = 2; //last 2 days has to be a visit to Trattoria Trussardi
                                    indexOrder = ran.nextInt(getBound(indexRest));
                                }
                            }
                            if (count == 6) {
                                if (weekVisits == 1) {
                                    indexRest = 2; //last day has to be a visit to Trattoria Trussardi
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
        /*avoid dining at the same restaurant twice in a row
          She and her father, Jotaro Kujo, always dine together
          at same restaurant every Saturday.*/

        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order

        if (residentIndex != -1) { //orderList for Jolyne not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if (Days % 7 != 0) { //if the day is not Saturday
                //if the previous restaurant is same as the current generated one
                boolean loop;
                do {
                    loop = false;
                    if (orderList.get(orderList.size() - 1).indexRest == indexRest) {
                        indexRest = ran.nextInt(5);
                        indexOrder = ran.nextInt(getBound(indexRest));
                        loop = true;
                    }
                } while (loop);
            } else { //if the day is Saturday
                if (indexRestSat != Integer.MAX_VALUE) {
                    indexRest = indexRestSat;    //if there's a restaurant for Jotaro on Saturday, make it same for Jolyne
                } else {
                    indexRestSat = indexRest;    //if restaurant for Jotaro hasn't been assigned, assign Jolyne's one to Jotaro so that both of them are same
                }
                indexOrder = ran.nextInt(getBound(indexRest));
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }
}
