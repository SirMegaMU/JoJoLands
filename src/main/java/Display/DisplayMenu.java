package Display;

import Menu.Item;

public class DisplayMenu {
    public void displayMenu(Item[] menu, String Restaurant) {
        if (menu != null && menu.length > 0) {
            System.out.println("Menu for " + Restaurant + ":");

            for (Item item : menu) {
                System.out.printf("%s (%.2f)\n", item.name(), item.price());
            }
        } else {
            System.out.println("Menu not available for the specified restaurant.");
        }
    }
}
