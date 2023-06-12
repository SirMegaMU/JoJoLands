package Mapping;

import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

public record Stand(String name, String standUser, String destructivePower, String speed, String range, String stamina,
                    String precision, String developmentPotential) {
    public Table getTable() {
        return Table.create("Stand").addColumns(
                StringColumn.create("Stand", name),
                StringColumn.create("Stand User", standUser),
                StringColumn.create("Destructive Power", destructivePower),
                StringColumn.create("Speed", speed),
                StringColumn.create("Range", range),
                StringColumn.create("Stamina", stamina),
                StringColumn.create("Precision", precision),
                StringColumn.create("Development Potential", developmentPotential)
        );
    }

    @Override
    public String toString() {
        return "Stand: " + name + "\n" +
                "Stand User: " + standUser + "\n" +
                "Destructive Power: " + destructivePower + "\n" +
                "Speed: " + speed + "\n" +
                "Range: " + range + "\n" +
                "Stamina: " + stamina + "\n" +
                "Precision: " + precision + "\n" +
                "Development Potential: " + developmentPotential + "\n";
    }
}
