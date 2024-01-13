// Mostapha A
// Assignment 2
// Class for main method and menu

import java.util.Scanner;

/**
 * * 
 * This class contains the main method and menu 
 * Student Name: Mostapha A
 * Course: CST8130 - Data Structures CET-CS-Level 3
 * 
 * @author/Mostapha A
 * 
 */
public class Assign2 {

	/**
	 * Main method for printing menu and processing.
	 * 
	 * @param args Default needed for main method
	 */
	public static void main(String[] args) {
		// var for menu input
		int menu = -1;
		Scanner scanner = new Scanner(System.in);
		// object
		Inventory inventory = new Inventory();
		boolean returnVal;

		// loop through menu
		do {
			// set menu to -1
			menu = -1;

			// print menu
			printMenu();
			
			// if input is an int, store
			if (scanner.hasNextInt()) {
				menu = scanner.nextInt();
			} else {
				// clear input
				scanner.next();
			}

			// if it is out of range print error and loop again
			if (menu < 1 || menu > 8) {
				// print error and loop through again
				System.out.println("Incorrect value entered");
			} else {
				// case structure for menu options
				switch (menu) {
				case 1:
					// add an item
					inventory.addItem(scanner, false);
					break;
				case 2:
					// print items
					System.out.println("Inventory:\n" + inventory.toString());
					break;
				case 3:
					// buy an item
					returnVal = inventory.updateQuantity(scanner, true);
					if (returnVal == false) {
						System.out.println("Error...could not buy item");
					}
					break;
				case 4:
					// sell an item
					returnVal = inventory.updateQuantity(scanner, false);
					if (returnVal == false) {
						System.out.println("Error...could not sell item");
					}
					break;
				case 5:
					// search for item
					inventory.searchForItem(scanner);
					break;
				case 6:
					// save to file
					inventory.saveToFile(scanner);
					break;
				case 7:
					// read from file
					if (inventory.addItem(scanner, true) == false) {
						System.out.println("Error Encountered while reading the file, aborting...");
					}
					break;
				case 8:
					// exit
					System.out.println("Exiting...");
					break;
				}
			}

		} while (menu != 8);
		
		scanner.close();
	}

	/**
	 * Prints the menu of available options.
	 */
	public static void printMenu() {
		System.out.print("Please select one of the following:\r\n" + 
				"1: Add Item to Inventory\r\n" + 
				"2: Display Current Inventory\r\n" + 
				"3: Buy Item(s)\r\n" + 
				"4: Sell Item(s)\r\n" + 
				"5: Search for Item\r\n" + 
				"6: Save Inventory to File\r\n" + 
				"7: Read Inventory from File\r\n" + 
				"8: To Exit\r\n" + 
				"> ");
	}
		
}
