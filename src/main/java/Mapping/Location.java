package Mapping;

import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;

import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    public final String name;
    public HashMap<String, Integer> connections;
    public ArrayList<Resident> residents;

    public Location(String name) {
        this.name = name;
        connections = new HashMap<>();
        residents = new ArrayList<>();
    }

    public void add_connection(String LocName, Integer distance) {
        this.connections.put(LocName, distance);
    }

    public Table residentInfo() {
        Table ans = Table.create("Resident").addColumns(
                StringColumn.create("Name"),
                IntColumn.create("Age"),
                StringColumn.create("Gender"),
                StringColumn.create("Residential Area"),
                StringColumn.create("Parents")
        );
        for (Resident resident : residents) {
            ans.append(resident.infoTable);
        }
        return ans;
    }

    public Table getResidentInfo() {

        IntColumn no = IntColumn.create("No");
        StringColumn name = StringColumn.create("Name");
        IntColumn age = IntColumn.create("Age");
        StringColumn gender = StringColumn.create("Gender");
        StringColumn stand = StringColumn.create("Stand");
        StringColumn despower = StringColumn.create("Destructive Power");
        StringColumn speed = StringColumn.create("Speed");
        StringColumn range = StringColumn.create("Range");
        StringColumn stamina = StringColumn.create("Stamina");
        StringColumn precision = StringColumn.create("Precision");
        StringColumn devpot = StringColumn.create("Development Potential");
        int i = 1;
        for (Resident resident_f : residents) {
            for (Stand stand_f : resident_f.stands) {
                no.append(i);
                name.append(stand_f.standUser());
                age.append(resident_f.age);
                gender.append(resident_f.gender);
                stand.append(stand_f.name());
                despower.append(stand_f.destructivePower());
                speed.append(stand_f.speed());
                range.append(stand_f.range());
                stamina.append(stand_f.stamina());
                precision.append(stand_f.precision());
                devpot.append(stand_f.developmentPotential());
                i++;
            }
        }
        return Table.create("Resident Infomation").addColumns(
                no, name, age, gender, stand, despower, speed, range, stamina, precision, devpot
        );
    }
}
