// Mostapha A
// Assignment 2
// Class for Inventory array list

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 * * 
 * This class contains the Inventory array list 
 * Student Name: Mostapha A
 * Course: CST8130 - Data Structures CET-CS-Level 3
 * 
 * @author/Mostapha A
 * 
 */
public class Inventory {
	/**
	 * The array that holds all the different food items
	 */
	private static ArrayList<FoodItem> inventory;
	/**
	 * The amount of items we have added
	 */
	private static int numItems;
	
	/**
	 * Constructor for the inventory
	 */
	public Inventory() {
		inventory = new ArrayList<FoodItem>();
		numItems = 0;
	}
	
	/**
	 * Compiles a string of all the items in the array
	 * @return The formatted string of all items
	 */
	public String toString() {
		String allItems = "";
		for (int i = 0; i < numItems; i++) {
			allItems = allItems + inventory.get(i).toString();
		}
		return allItems;
	}
	
	/**
	 * Checks if an item code is in use or not using a binary search method
	 * @param itemCode The item to check the code of
	 * @return The index that the item is at or -1 if not found
	 */
	public int alreadyExists(int itemCode) {
		// set variables
		int firstIndex = 0;
		int lastIndex = numItems - 1;

		// loop until the range is just one number
		while (firstIndex <= lastIndex) {

			// calculate middle index
			int middleIndex = (firstIndex + lastIndex + 1) / 2;

			// check if the value is at the middle index
			if (inventory.get(middleIndex).isEqual(itemCode)) {

				// return the index
				return middleIndex;
			}

			// if the array value is smaller than search value, drop the lesser half of the
			// array
			if (inventory.get(middleIndex).itemCode < itemCode) {
				firstIndex = middleIndex + 1;
			} else {
				// else drop greater half
				lastIndex = middleIndex - 1;
			}
		}

		// return -1 because it was not found
		return -1;
	}

	/**
	 * Adds an item to the array through user input or from an external file, calling the appropriate method depending on item
	 * type and checking if item code is in use.
	 * 
	 * @param scanner The scanner to read input
	 * @param fromFile Whether we are adding from a file or user input
	 * @return Whether method was successful or not
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		String type = null;
		int exists = -1;
		boolean success = false;
		FoodItem item = new FoodItem();
		int index;

		if (fromFile == false) {

			do {
				// ask for item type
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
				type = scanner.next();
				type = type.toLowerCase();

				if (!type.equalsIgnoreCase("f") && !type.equalsIgnoreCase("v") && !type.equalsIgnoreCase("p")) {
					// print error
					System.out.println("Invalid entry");
					type = null;
				}

			} while (type == null);

			// add item depending on type
			if (type.equals("f")) {
				item = new Fruit();
			} else if (type.equals("v")) {
				item = new Vegetable();
			} else {
				item = new Preserve();
			}

			// ask for item code
			do {
				// input code if its valid
				success = item.inputCode(scanner);

				// if it returned true, check if the code exists
				if (success == true) {
					exists = alreadyExists(item.itemCode);

					if (exists != -1) {
						System.out.println("Item code already exists");
						success = false;
					}
				}

			} while (success == false);

			// add to array list, with sorted position
			item.addItem(scanner, false);

			index = item.compareTo(inventory);
			inventory.add(index, item);

			// increment num items
			numItems++;

			return true;
		} else {
			String fileName;
			File file;
			Scanner fileScanner;
			String temp;
			int itemCode;

			// Ask for file name
			System.out.print("Enter the filename to read from: ");
			scanner.nextLine();
			fileName = scanner.nextLine();

			// try catch block for file reading
			try {
				// set objects for reading
				file = new File(fileName);
				fileScanner = new Scanner(file);

				// while it is not empty
				while (fileScanner.hasNextLine()) {
					// if it has next line set it to type
					if (fileScanner.hasNextLine()) {
						type = fileScanner.nextLine();
					} else {
						return false;
					}
					// store the item code next
					if (fileScanner.hasNextLine()) {

						// add item code if it is a number
						temp = fileScanner.nextLine();
						try {
							itemCode = Integer.parseInt(temp);
						} catch (NumberFormatException e) {
							System.out.printf("Invalid item code %s.\n", temp);
							return false;
						}

					} else {
						return false;
					}

					// check if it exists
					if (alreadyExists(itemCode) != -1) {
						System.out.println("Item code already exists");
						return false;
					} else {
						type = type.toLowerCase();
						// otherwise create the item depending on type
						if (type.equals("f")) {
							item = new Fruit();
						} else if (type.equals("v")) {
							item = new Vegetable();
						} else {
							item = new Preserve();
						}

						// add the item attributes
						item.itemCode = itemCode;
						if (item.addItem(fileScanner, true) == false) {
							return false;
						}

						// add to array with sorted position
						index = item.compareTo(inventory);
						inventory.add(index, item);
						numItems++;
					}

				}
				fileScanner.close();
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found, ignoring...");
				return true;
			}

			return true;
		}
	}

	/**
	 * Updates the quantity of an item if buying or selling it
	 * 
	 * @param scanner   The scanner to read user input
	 * @param buyOrSell Whether we are buying items or selling
	 * @return Whether quantity was updated successfully or not
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		Boolean returnVal = true;
		int quantity = -1;
		int code;
		int exists;
		String message;
		FoodItem item = new FoodItem();

		// if there are no items return false
		if (numItems <= 0) {
			return false;
		}
		
		// ask for item code
		System.out.print("Enter the code for the item: ");

		// if input is an int, store
		if (scanner.hasNextInt()) {
			code = scanner.nextInt();
			item.itemCode = code;

			// check if it exists
			exists = alreadyExists(item.itemCode);
		} else {
			// clear input print error
			scanner.next();
			System.out.println("Code not found in inventory...");
			return false;
		}

		if (exists == -1) {
			System.out.println("Code not found in inventory...");
			return false;
		}

		// set message if buying or selling
		if (buyOrSell == true) {
			message = "Enter valid quantity to buy: ";
		} else {
			message = "Enter valid quantity to sell: ";
		}

		// ask for the quantity

		System.out.print(message);

		// if input is an int, store
		if (scanner.hasNextInt()) {
			quantity = scanner.nextInt();
			returnVal = true;
		} else {
			// clear input print error
			scanner.next();
			System.out.println("Invalid quantity...");
			return false;
		}

		// check range
		if (quantity < 0) {
			System.out.println("Invalid quantity...");
			return false;
		}
		
		// update the quantity for buy or sell
		if (buyOrSell == true) {
			returnVal = inventory.get(exists).updateItem(quantity);
		} else {
			returnVal = inventory.get(exists).updateItem(quantity * -1);
		}

		return returnVal;
	}
	
	/**
	 * Searches for an item based on the input item code, returns the information or tells us it can't be found
	 * @param scanner The scanner to read user input
	 */
	public void searchForItem(Scanner scanner) {
		boolean success = false;
		FoodItem item = new FoodItem();
		int exists = -1;

		// ask for item code
		do {
			// input code if its valid
			success = item.inputCode(scanner);

			// if it returned true, check if the code exists
			if (success == true) {
				exists = alreadyExists(item.itemCode);

				// if it exists print so, otherwise print it was not found
				if (exists != -1) {
					// print item
					System.out.print(inventory.get(exists).toString());
				} else {
					System.out.println("Code not found in inventory...");
				}
			}

		} while (success == false);
		
	}
	
	/**
	 * Saves all the inventory items to a specified file name.
	 * @param scanner The scanner to take user input
	 */
	public void saveToFile(Scanner scanner) {
		String fileName;
		Formatter fileOutput;
		
		// ask for file name
		System.out.print("Enter the filename to save to: ");
		scanner.nextLine();
		fileName = scanner.nextLine();

		// create formatter with file name
		try {
			fileOutput = new Formatter(fileName);

			// loop through array list and write each item to file
			for (int i = 0; i < numItems; i++) {
				inventory.get(i).outputItem(fileOutput);
			}
			
			fileOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
