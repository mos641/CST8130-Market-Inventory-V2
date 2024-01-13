// Mostapha A
// Assignment 2
// Class for FoodItem object

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 * * 
 * This class contains the definition of FoodItem 
 * Student Name: Mostapha A
 * Course: CST8130 - Data Structures CET-CS-Level 3
 * 
 * @author/Mostapha A
 * 
 */
public class FoodItem implements Comparable<ArrayList<FoodItem>> {
	/**
	 * The code for the item
	 */
	protected int itemCode;
	/**
	 * The name of the item
	 */
	private String itemName;
	/**
	 * The price of the item
	 */
	private float itemPrice;
	/**
	 * The quantity available in stock
	 */
	private int itemQuantityInStock;
	/**
	 * The cost of the item
	 */
	private float itemCost;
	
	/**
	 * Constructor for FoodItem
	 */
	public FoodItem() {
		
	}
	
	/**
	 * Returns the item as a string
	 * @return The string of information
	 */
	public String toString() {
		// for formatting
		DecimalFormat format = new DecimalFormat("0.00");
		String item;
		
		// create a string with the values
		item = "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " price: $" + format.format(itemPrice) + " cost: $" + format.format(itemCost);
		
		return item;
	}

	/**
	 * Adds a food item from user input or from a file
	 * @param scanner The scanner to read input from user or from a file
	 * @param fromFile Whether we are adding from a file or user input
	 * @return Returns whether it was successful or not
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		float price = -1;
		int stock = -1;
		float cost = -1;
		boolean success = false;
		String temp;
		
		if (fromFile == false) {
			// ask for values

			// item name
			scanner.nextLine();
			System.out.print("Enter the name for the item: ");
			itemName = scanner.nextLine();

			// item quantity
			do {
				System.out.print("Enter the quantity for the item: ");

				// if input is an int, store
				if (scanner.hasNextInt()) {
					stock = scanner.nextInt();
					success = true;
				} else {
					// clear input
					scanner.next();
					success = false;
				}

				// check range
				if (stock < 0) {
					System.out.println("Invalid entry");
					success = false;
				}

			} while (success == false);
			itemQuantityInStock = stock;

			// cost of the item
			do {
				System.out.print("Enter the cost of the item: ");

				// if input is an float, store
				if (scanner.hasNextFloat()) {
					cost = scanner.nextFloat();
					success = true;
				} else {
					// clear input
					scanner.next();
					success = false;
				}

				// check range
				if (cost < 0) {
					System.out.println("Invalid entry");
					success = false;
				}

			} while (success == false);
			itemCost = cost;

			// price of the item
			do {
				System.out.print("Enter the sales price of the item: ");

				// if input is an float, store
				if (scanner.hasNextFloat()) {
					price = scanner.nextFloat();
					success = true;
				} else {
					// clear input
					scanner.next();
					success = false;
				}

				// check range
				if (price < 0) {
					System.out.println("Invalid entry");
					success = false;
				}

			} while (success == false);
			itemPrice = price;

			// if any fail return false
			if (itemName == null || itemQuantityInStock == -1 || itemPrice == -1 || itemCost == -1) {
				return false;
			}

			// return true
			return true;
		} else {
			// add orchard name if there is a line
			if (scanner.hasNextLine()) {
				itemName = scanner.nextLine();
			} else {
				System.out.println("Incomplete item info.");
				return false;
			}
			
			// add quantity - if it is an int, store
			if (scanner.hasNextLine()) {
				temp = scanner.nextLine();
			} else {
				System.out.println("Incomplete item info.");
				return false;
			}
			try {
				stock = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				System.out.println("Invalid stock amount.");
				return false;
			}
			// check range
			if (stock < 0) {
				System.out.println("Invalid stock amount.");
				return false;
			}
			itemQuantityInStock = stock;
			
			
			// add cost - if input is an float, store
			if (scanner.hasNextLine()) {
				temp = scanner.nextLine();
			} else {
				System.out.println("Incomplete item info.");
				return false;
			}
			try {
				cost = Float.parseFloat(temp);
			} catch (NumberFormatException e) {
				System.out.println("Invalid cost price.");
				return false;
			}
			// check range
			if (cost < 0) {
				System.out.println("Invalid cost price.");
				return false;
			}
			itemCost = cost;

			// add price - if input is an float, store
			if (scanner.hasNextLine()) {
				temp = scanner.nextLine();
			} else {
				System.out.println("Incomplete item info.");
				return false;
			}
			try {
				price = Float.parseFloat(temp);
			} catch (NumberFormatException e) {
				System.out.println("Invalid sale price.");
				return false;
			}
			// check range
			if (price < 0) {
				System.out.println("Invalid sale price.");
				return false;
			}
			itemPrice = price;

			return true;
		}
	}

	/**
	 * Update an items quantity depending on whether they are buying or selling
	 * @param amount The amount to change the item
	 * @return Boolean value of success
	 */
	public boolean updateItem(int amount) {
		// if buying or selling
		if (amount >= 0) {
			itemQuantityInStock += amount;
		} else {
			// if we are selling, check stock
			if (itemQuantityInStock < (amount * -1)) {
				System.out.println("Insufficient stock in inventory...");
				return false;
			}
			itemQuantityInStock += amount;
		}
		return true;
	}

	/**
	 * Checks an item code of a food item being acted upon against an item code that
	 * is passed as a parameter, if the two item codes are equal will return true,
	 * if not will return false.
	 * 
	 * @param itemCode The item code to compare to the food item's code
	 * @return Whether the item code is the same
	 */
	public boolean isEqual(int itemCode) {
		// check the code
		if (this.itemCode == itemCode) {
			return true;
		}
		return false;
	}
	
	/**
	 * Asks for the item code and validates
	 * @param scanner The scanner to read user input
	 * @return Whether the code was valid
	 */
	public boolean inputCode(Scanner scanner) {
		int code;
		
		// ask for code
		System.out.print("Enter the code for the item: ");

		// if input is an int, store
		if (scanner.hasNextInt()) {
			code = scanner.nextInt();
			itemCode = code;

			return true;
		} else {
			// clear input print error
			scanner.next();
			System.out.println("Invalid entry");
			return false;
		}
	}
	
	/**
	 * Print all the item details to a file stored in formatter
	 * @param writer The formatter for the specified file
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d\n%s\n%d\n%f\n%f\n", itemCode, itemName, itemQuantityInStock, itemCost, itemPrice);
	}
	
	/**
	 * Overrides compareTo method from comparable interface, compares an item to an
	 * array list of items to decide where to place it in a sorted position
	 * 
	 * @param inventory The list of items to find a sorted position
	 */
	@Override
	public int compareTo(ArrayList<FoodItem> inventory) {
		// set variables
		int firstIndex = 0;
		int lastIndex = inventory.size() - 1;

		// loop until the range is just one number
		while (firstIndex <= lastIndex) {

			// calculate middle index
			int middleIndex = (firstIndex + lastIndex + 1) / 2;

			// check if the middle value is greater or smaller
			if (inventory.get(middleIndex).itemCode == itemCode) {
				// if equal, return middle index
				return middleIndex;
			} else if (inventory.get(middleIndex).itemCode > itemCode) {
				// drop the greater half of list if mid index is larger
				lastIndex = middleIndex - 1;
			} else {
				// else drop lower half
				firstIndex = middleIndex + 1;
			}
		}
		
		// if range is one number, return number
		return firstIndex;
	}
}
