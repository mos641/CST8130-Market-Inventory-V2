// Mostapha A
// Assignment 2
// Class for Vegetable object

import java.util.Formatter;
import java.util.Scanner;

/**
 * * 
 * This class contains the definition of the Vegetable object 
 * Student Name: Mostapha A
 * Course: CST8130 - Data Structures CET-CS-Level 3
 * 
 * @author/Mostapha A
 * 
 */
public class Vegetable extends FoodItem{
	/**
	 * The name of the farm that supplies the vegetable
	 */
	private String farmName;
	
	/**
	 * Constructor for a food item of type vegetable
	 */
	public Vegetable() {
		
	}
	
	/**
	 * Returns a string of the item and its attribute.
	 * @return Returns the string of the item.
	 */
	public String toString() {
		String item;
		// call super and add to the string
		item = super.toString() + " farm supplier: " + farmName + "\n";
		
		// return the string
		return item;
	}
	
	/**
	 * Add a food item from user input or file, call super method then add farm name
	 * 
	 * @param scanner The scanner to read input from user or from a file
	 * @param fromFile Whether we are adding from a file or user input
	 * @return Returns whether it was successful or not
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		Boolean returnVal;
		if (fromFile == false) {
			// call super method
			returnVal = super.addItem(scanner, false);

			// ask for farm name
			scanner.nextLine();
			System.out.print("Enter the name of the farm supplier: ");
			farmName = scanner.nextLine();

			return returnVal;
		} else {
			// call parent method
			if (super.addItem(scanner, true) == false) {
				return false;
			}

			// add farm name if there is a line
			if (scanner.hasNextLine()) {
				farmName = scanner.nextLine();
			} else {
				System.out.println("Incomplete item info.");
				return false;
			}

			return true;
		}
	}
	
	/**
	 * Print all the item details to a file stored in formatter
	 * @param writer The formatter for the specified file
	 */
	public void outputItem(Formatter writer) {
		// item type
		writer.format("v\n");
		// call super
		super.outputItem(writer);
		// rest of item info
		writer.format("%s\n", farmName);
	}
	
}
