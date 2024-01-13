// Mostapha A
// Assignment 2
// Class for Fruit object

import java.util.Formatter;
import java.util.Scanner;

/**
 * * 
 * This class contains the definition of Fruit object 
 * Student Name: Mostapha A
 * Course: CST8130 - Data Structures CET-CS-Level 3
 * 
 * @author/Mostapha A
 * 
 */
public class Fruit extends FoodItem{
	/**
	 * The name of the orchard that provides the fruit
	 */
	private String orchardName;
	
	/**
	 * Constructor for a food item of type fruit
	 */
	public Fruit() {
		
	}
	
	/**
	 * Returns a string of the item and its attribute.
	 * @return Returns the string of the item.
	 */
	public String toString() {
		String item;
		// call super and add to the string
		item = super.toString() + " orchard supplier: " + orchardName + "\n";
		
		// return the string
		return item;
	}
	
	/**
	 * Add a food item through user input or file, call super method then add farm name
	 * @param scanner The scanner to read input from user or file
	 * @param fromFile Whether we are adding from a file or user input
	 * @return Returns whether it was successful or not
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		Boolean returnVal;
		
		if (fromFile == false) {
			// call super method
			returnVal = super.addItem(scanner, false);

			// ask for orchard name
			scanner.nextLine();
			System.out.print("Enter the name of the orchard supplier: ");
			orchardName = scanner.nextLine();

			return returnVal;
		} else {	
			// call parent method
			if (super.addItem(scanner, true) == false) {
				return false;
			}
			
			// add orchard name if there is a line
			if (scanner.hasNextLine()) {
				orchardName = scanner.nextLine();
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
		writer.format("f\n");
		// call super
		super.outputItem(writer);
		// rest of item info
		writer.format("%s\n", orchardName);
	}

}
