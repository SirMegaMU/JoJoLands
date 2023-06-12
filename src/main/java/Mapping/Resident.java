package Mapping;

import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.util.ArrayList;
import java.util.Objects;

public class Resident {

    public final String name, age, gender, residentialArea;
    public final String parents;
    public ArrayList<Stand> stands;
    public Table infoTable;

    public Resident(String name, String age, String gender, String residentialArea, String parents) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.residentialArea = residentialArea;
        this.parents = parents;
        this.stands = new ArrayList<>();
        Table_Gen();
    }

    public void Table_Gen() {
        this.infoTable = Table.create("Resident").addColumns(
                StringColumn.create("Name", name),
                StringColumn.create("Age", age),
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
