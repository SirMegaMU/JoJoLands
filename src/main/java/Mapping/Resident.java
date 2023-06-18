package Mapping;

import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.util.ArrayList;

public class Resident {

    public final String name, gender, residentialArea, parents;
    public final int age;
    public ArrayList<Stand> stands;
    public Table infoTable;
    public Table orderHistory;

    public Resident(String name, int age, String gender, String residentialArea, String parents) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.residentialArea = residentialArea;
        this.parents = parents;
        this.stands = new ArrayList<>();
        this.orderHistory = Table.create("Order History").addColumns(
                IntColumn.create("Day"),
                StringColumn.create("Food"),
                StringColumn.create("Restaurant")
        );
        Table_Gen();
    }

    public void Table_Gen() {
        int[] ages = {age,};
        this.infoTable = Table.create("Resident").addColumns(
                StringColumn.create("Name", name),
                IntColumn.create("Age", ages),
                StringColumn.create("Gender", gender),
                StringColumn.create("Residential Area", residentialArea),
                StringColumn.create("Parents", parents)
        );
    }

    public String detialInfo() {
        String ans = this.name + "'s Profile\n";
        ans += this + "\n";
        ans += orderHistory + "\n";
        return ans;
    }

    public void add_order(int day, String order, String location) {
        int[] day_l = {day,};
        Table temp = Table.create("Order History").addColumns(
                IntColumn.create("Day", day_l),
                StringColumn.create("Food", order),
                StringColumn.create("Restaurant", location)
        );
    }

    @Override
    public String toString() {
        return this.infoTable.toString();
    }
}
