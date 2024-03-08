package classes;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean allowAdditions;

    public Menu(Scanner scanner, boolean allowAdditions) {
        this.allowAdditions = allowAdditions;
        this.scanner = scanner;
    }

    public int getChoice() {
        boolean validChoice = false;
        int choice = 0;
        while (!validChoice) {
            try {
                printMenu();
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice > 5 || choice < 1) {
                    System.out.println("Please enter a valid choice");
                } else {
                    validChoice = true;
                }
                
            } catch (Exception e) {
                System.out.println("Please enter a valid choice");
            }
        }
        return choice;
    }

    private void printMenu() {
        System.out.println("Choose an action from the menu:");
        System.out.println("1. Load a knowledge base from a file");
        if (!allowAdditions) {
            System.out.println("2. Update an existing statement to the knowledge base");
        } else {
            System.out.println("2. Add or update a statement to the knowledge base");
        }
        System.out.println("3. Search for an item in the knowledge base by term");
        System.out.println("4. Search for an item in the knowledge base by term and sentence");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }
}
