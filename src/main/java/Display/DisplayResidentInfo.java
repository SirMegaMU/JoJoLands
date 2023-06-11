package Display;

import CsvReader.CsvReader;
import Mapping.Resident;
import Mapping.Stand;

import java.util.ArrayList;

public class DisplayResidentInfo {
    public boolean residentProfile(String residentFilePath, String standFilePath, String name) {

        CsvReader loadSystemFile = new CsvReader();
        ArrayList<Resident> resident = loadSystemFile.LoadResident(residentFilePath);
        ArrayList<Stand> stand = loadSystemFile.LoadStand(standFilePath);

        boolean containName = false;
        for (Resident value : resident) {
            if ((value.name).equals(name)) {
                System.out.println(name + "'s Profile");
                System.out.println("Name\t: " + value.name + "\n"
                        + "Age\t: " + value.name + "\n"
                        + "Gender\t: " + value.name + "\n"
                        + "Parents\t: " + value.parents);
                containName = true;
            }
        }

        for (Stand value : stand) {
            if ((value.standUser()).equals(name)) {
                System.out.println("Stand\t\t\t: " + value.stand() + "\n"
                        + "Destructive Power\t: " + value.destructivePower() + "\n"
                        + "Speed\t\t\t: " + value.speed() + "\n"
                        + "Range\t\t\t: " + value.range() + "\n"
                        + "Stamina\t\t\t: " + value.stamina() + "\n"
                        + "Precision\t\t: " + value.precision() + "\n"
                        + "Development Potential\t: " + value.developmentPotential());
            }
        }
        return containName;
    }
}
