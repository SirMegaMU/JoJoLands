package Display;

import Comparator.ResidentComparator;
import Mapping.Resident;

import java.util.ArrayList;

public class DisplayResidents {
    public void displayResidentsInArea(String residentialArea, ArrayList<Resident> listOfResidents) {
        ArrayList<Resident> sorted = new ArrayList<>();
        ResidentComparator ResidentComparator = new ResidentComparator();
        boolean foundResidents = false;
        for (Resident residents : listOfResidents) {
            if (residents.residentialArea.equalsIgnoreCase(residentialArea)) {
                sorted.add(residents);

                foundResidents = true;
            }
        }
        if (!foundResidents) {
            System.out.println("No residents found in the given residential area.");
        } else {
            ResidentComparator.sort(sorted);
            for (Resident residents : sorted) {
                System.out.println(residents);
            }
        }

    }
}
