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

    public Resident(String name, int age, String gender, String residentialArea, String parents) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.residentialArea = residentialArea;
        this.parents = parents;
        this.stands = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Residential Area: " + residentialArea + "\n" +
                "Parents: " + parents + "\n";
    }
}
