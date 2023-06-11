package CsvReader;

import Mapping.Resident;
import Mapping.Stand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
    ArrayList<Resident> listOfResidents;
    ArrayList<Stand> listOfStands;

    public CsvReader() {
        this.listOfResidents = new ArrayList<>();
        this.listOfStands = new ArrayList<>();
    }

    public ArrayList<Resident> LoadResident(String filePath) {
        ArrayList<Resident> residentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0].trim();
                String age = data[1].trim();
                String gender = data[2].trim();
                String residentialArea = data[3].trim();

                String parents;
                if ((data[0]).equals("George Joestar II")
                        || (data[0]).equals("Giorno Giovanna")
                        || (data[0]).equals("Holy Kujo")
                        || (data[0]).equals("Jonathan Joestar")
                        || (data[0]).equals("Joseph Joestar")
                        || (data[0]).equals("Josuke Higashikata")
                        || (data[0]).equals("Jotaro Kujo")) {
                    parents = data[4].trim() + ", " + data[5].trim();
                } else {
                    parents = data[4].trim();
                }
                residentList.add(new Resident(name, age, gender, residentialArea, parents));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listOfResidents = residentList;
        return listOfResidents;
    }

    public ArrayList<Stand> LoadStand(String filePath) {
        ArrayList<Stand> standList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                String stand = data[0].trim();
                String standUser = data[1].trim();
                String destructivePower = data[2].trim();
                String speed = data[3].trim();
                String range = data[4].trim();
                String stamina = data[5].trim();
                String precision = data[6].trim();
                String developmentPotential = data[7].trim();
                Stand currentStand = new Stand(stand, standUser, destructivePower, speed, range, stamina, precision, developmentPotential);
                standList.add(currentStand);

                for (Resident resident : listOfResidents) {
                    if (standUser.equals(resident.name)) {
                        resident.stand = currentStand;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listOfStands = standList;
        return listOfStands;
    }
}
