package milagroMan;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create restaurants
        Restaurant jadeGarden = new Restaurant("Jade Garden");
        Restaurant cafeDeuxMagots = new Restaurant("Cafe Deux Magots");
        Restaurant moriohGrandHotel = new Restaurant("Morioh Grand Hotel");
        Restaurant sanGiorgioMaggiore = new Restaurant("San Giorgio Maggiore");
        Restaurant joestarMansion = new Restaurant("Joestar Mansion");
        Restaurant townHall = new Restaurant("Town Hall");

        // Create food items for Jade Garden
        Food braisedChicken = new Food("Braised Chicken in Black Bean Sauce", 15.00);
        Food gooseWeb = new Food("Braised Goose Web with Vermicelli", 20.00);
        Food tofuShrimps = new Food("Poached Tofu with Dried Shrimps", 10.00);
        Food scrambledEgg = new Food("Scrambled Egg White with Milk", 8.00);

        // Add food items to the menu of Jade Garden
        jadeGarden.addFood(braisedChicken);
        jadeGarden.addFood(gooseWeb);
        jadeGarden.addFood(tofuShrimps);
        jadeGarden.addFood(scrambledEgg);

        // Create sales records for Jade Garden
        SalesRecord jadeGardenSales = new SalesRecord();
        jadeGardenSales.addSale(LocalDate.of(2023, 6, 1), braisedChicken, 9);
        jadeGardenSales.addSale(LocalDate.of(2023, 6, 1), braisedChicken, 20);
        jadeGardenSales.addSale(LocalDate.of(2023, 6, 1), gooseWeb, 3);
        jadeGardenSales.addSale(LocalDate.of(2023, 6, 2), scrambledEgg, 12);
        jadeGardenSales.addSale(LocalDate.of(2023, 6, 2), tofuShrimps, 2);

        // Set the sales record for Jade Garden
        jadeGarden.setSalesRecord(jadeGardenSales);

        // Create Town Hall
        Restaurant townHall1 = new Restaurant("Town Hall");

        // Set the adjacent restaurants for each restaurant
        jadeGarden.setAdjacentRestaurants(cafeDeuxMagots, moriohGrandHotel, sanGiorgioMaggiore, townHall1, townHall1);
        cafeDeuxMagots.setAdjacentRestaurants(jadeGarden, moriohGrandHotel, sanGiorgioMaggiore, townHall1, townHall1);
        moriohGrandHotel.setAdjacentRestaurants(jadeGarden, cafeDeuxMagots, sanGiorgioMaggiore, townHall1, townHall1);
        sanGiorgioMaggiore.setAdjacentRestaurants(jadeGarden, cafeDeuxMagots, moriohGrandHotel, townHall1, townHall1);
        joestarMansion.setAdjacentRestaurants(jadeGarden, cafeDeuxMagots, moriohGrandHotel, sanGiorgioMaggiore, townHall1);

        // Set the current location
        Restaurant currentLocation = jadeGarden;

        Scanner scanner = new Scanner(System.in);

        // Start the program loop
        while (true) {
            System.out.println("Current Location: " + currentLocation.getName());
            System.out.println("[1] Move to:");
            System.out.println("    [A] " + cafeDeuxMagots.getName() + "      [B] " + joestarMansion.getName());
            System.out.println("    [C] " + moriohGrandHotel.getName() + "    [D] " + sanGiorgioMaggiore.getName());
            System.out.println("[E] " + townHall1.getName());
            System.out.println();
            System.out.println("[2] View Waiting List and Order Processing List");
            System.out.println("[3] View Menu");
            System.out.println("[4] View Sales Information");
            System.out.println("[5] Milagro Man");
            System.out.println("[6] Back (" + townHall1.getName() + ")");
            System.out.println("[7] Back to " + townHall1.getName());
            System.out.println();
            System.out.print("Select: ");

            // Read user input
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    currentLocation = cafeDeuxMagots;
                    break;
                case "B":
                    currentLocation = joestarMansion;
                    break;
                case "C":
                    currentLocation = moriohGrandHotel;
                    break;
                case "D":
                    currentLocation = sanGiorgioMaggiore;
                    break;
                case "E":
                    currentLocation = townHall1;
                    break;
                case "2":
                    // TODO: Implement View Waiting List and Order Processing List functionality
                    break;
                case "3":
                    currentLocation.printMenu();
                    break;
                case "4":
                	 System.out.print("Enter Start Day: ");
                     int startDay = scanner.nextInt();
                     System.out.print("Enter End Day: ");
                     int endDay = scanner.nextInt();
                     scanner.nextLine(); // Consume the newline character after reading the integer inputs
                     currentLocation.printSalesInformation(startDay, endDay);
                    break;
                case "5":
                    currentLocation.enterMilagroManMode();
                    break;
                case "6":
                case "7":
                    currentLocation = townHall1;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }

            System.out.println("======================================================================");
        }
    }
}
