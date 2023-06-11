package Comparator;

import Mapping.Resident;

import java.util.*;

public class ResidentComparator implements Comparator<Resident> {

    @Override
    public int compare(Resident resident1, Resident resident2) {

        int result = resident1.residentialArea.compareToIgnoreCase(resident2.residentialArea);
        if (result != 0) {
            return result;
        }
        result = resident1.name.compareToIgnoreCase(resident2.name);
        if (result != 0) {
            return result;
        }
        result = resident1.age.compareToIgnoreCase(resident2.age);
        if (result != 0) {
            return result;
        }
        result = resident1.gender.compareToIgnoreCase(resident2.gender);
        if (result != 0) {
            return result;
        }
        return resident1.parents.compareToIgnoreCase(resident2.parents);
    }

    public ArrayList<Resident> sort(ArrayList<Resident> residents) {
        for (int i = 0; i < residents.size() - 1; i++) {
            for (int j = i + 1; j < residents.size(); j++) {
                Resident resident1 = residents.get(i);
                Resident resident2 = residents.get(j);
                if (compare(resident1, resident2) > 0) {
                    Collections.swap(residents, i, j);
                }
            }
        }
        return residents;
    }
}

