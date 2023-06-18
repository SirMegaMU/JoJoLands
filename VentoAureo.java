package Extra_Features;

import Basic_Features.DATA;
import com.sun.source.tree.BreakTree;
import java.util.*;

public class VentoAureo {

    private static Map<String, Integer> locationMap = new HashMap<>();
    private static List<List<String>> districts = new ArrayList<>();
    private static List<String> uncategorized = new ArrayList<>();

    public static void displayVentoAureo() {
        initializeLocationMap();
        System.out.println("Entering Vento Aureo.\nEnter (Exit) to exit Vento Aureo");
        Scanner scanner = new Scanner(System.in);

        while (!uncategorized.isEmpty()) {

            System.out.print("Combine: ");
            String input = scanner.nextLine();
            System.out.println("======================================================================================");
            if (input.equalsIgnoreCase("Exit")) {
                break;
            }
            String[] locations = input.split(" & ");
            if (locations.length != 2) {
                System.out.println("Invalid input. Please enter two locations separated by ' & '.");
                continue;
            }
            String location1 = locations[0];
            String location2 = locations[1];

            if (!locationMap.containsKey(location1) || !locationMap.containsKey(location2)) {
                System.out.println("Invalid locations. Please enter valid locations.");
                continue;
            }
            if (!areConnected(location1, location2)) {
                System.out.println(location1 + " and " + location2 + " are not connected by a road!");
                continue;
            }
            combineDistricts(location1, location2);
            displayDistrictsAndUncategorized();

        }
        System.out.println("Exited from Vento Aureo.\n");
    }

    private static void initializeLocationMap() {
        for (int i = 0; i < DATA.LOCATION_NAMES.length; i++) {
            locationMap.put(DATA.LOCATION_NAMES[i], i);
            uncategorized.add(DATA.LOCATION_NAMES[i]);
        }
    }

    private static boolean areConnected(String location1, String location2) {
        int index1 = locationMap.get(location1);
        int index2 = locationMap.get(location2);
        boolean connected = false;

        // Check maps for the road connection
        connected = connected || isRoadPresent(DATA.DESTINATIONS01, index1, index2);

        return connected;
    }

    private static boolean isRoadPresent(int[][] destinations, int index1, int index2) {
        for (int i = 0; i < destinations[index1].length; i++) {
            if (destinations[index1][i] == index2) {
                return true;
            }
        }
        return false;
    }

    private static void combineDistricts(String location1, String location2) {
        int district1Index = -1;
        int district2Index = -1;

        for (int i = 0; i < districts.size(); i++) {
            List<String> district = districts.get(i);
            if (district.contains(location1)) {
                district1Index = i;
            }
            if (district.contains(location2)) {
                district2Index = i;
            }
        }

        if (district1Index != -1 && district2Index != -1) {
            // Both locations are already in separate districts, combine them
            districts.get(district1Index).addAll(districts.get(district2Index));
            districts.remove(district2Index);
        } else if (district1Index != -1) {
            // location1 is already in a district, add location2 to the district
            districts.get(district1Index).add(location2);
        } else if (district2Index != -1) {
            // location2 is already in a district, add location1 to the district
            districts.get(district2Index).add(location1);
        } else {
            // Neither location is in a district, create a new district
            List<String> district = new ArrayList<>();
            district.add(location1);
            district.add(location2);
            districts.add(district);
        }
        uncategorized.remove(location1);
        uncategorized.remove(location2);
    }

    private static void displayDistrictsAndUncategorized() {
        for (int i = 0; i < districts.size(); i++) {
            List<String> district = districts.get(i);
            System.out.println("District " + (i + 1) + ": " + district + " (" + district.size() + " locations)");
        }
        System.out.println("Uncategorized: " + uncategorized + " (" + uncategorized.size() + " locations)");
        System.out.println("======================================================================================");
    }
}
