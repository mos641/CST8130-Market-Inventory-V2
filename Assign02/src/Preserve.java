// Mostapha A
// Assignment 2
// Class for Preserve object

import java.util.Formatter;
import java.util.Scanner;

/**
 * * 
 * This class contains the definition of the Preserve object 
 * Student Name: Mostapha A
 * Course: CST8130 - Data Structures CET-CS-Level 3
 * 
 * @author/Mostapha A
 * 
 */
public class Preserve extends FoodItem {
	/**
	 * The size of the jar for the preserve
	 */
	private int jarSize;
	
	/**
	 * Constructor for a food item of type preserve
	 */
	public Preserve(){
		
	}
	
	/**
	 * Returns a string of the item and its attribute.
	 * @return Returns the string of the item.
	 */
	public String toString() {
		String item;
		// call super and add to the string
		item = super.toString() + " size: " + jarSize + "mL\n";
		
		// return the string
		return item;
	}
	
	/**
	 * Add a food item from user input or file, call super method then add jar size
	 * @param scanner The scanner to read input from user or file
	 * @param fromFile Whether we are adding from a file or user input
	 * @return Returns whether it was successful or not
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		Boolean returnVal;
		String temp;

		if (fromFile == false) {
			// call super method
			returnVal = super.addItem(scanner, false);

			// ask for the jar size
			do {
				System.out.print("Enter the size of the jar in millilitres: ");

				// if input is an int, store
				if (scanner.hasNextInt()) {
					jarSize = scanner.nextInt();
					returnVal = true;
				} else {
					// clear input
					scanner.next();
					jarSize = -1;
				}

				// check range
				if (jarSize < 1) {
					System.out.println("Invalid entry");
					jarSize = -1;
					returnVal = false;
				}

			} while (jarSize == -1);

			return returnVal;
		} else {
			// call parent method
			if (super.addItem(scanner, true) == false) {
				return false;
			}

			// add jar size if there is a line
			if (scanner.hasNextInt()) {
				temp = scanner.nextLine();
			} else {
				System.out.println("Incomplete item info.");
				return false;
			}
			// check if it is an int
			try {
				jarSize = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				System.out.println("Invalid jar size.");
				return false;
			}
			// check range
			if (jarSize < 1) {
				System.out.println("Invalid Jar Size");
				jarSize = -1;
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Print all the item details to a file stored in formatter
	 * @param writer The formatter for the specified file
	 */
	public void outputItem(Formatter writer) {
		// item type
		writer.format("p\n");
		// call super
		super.outputItem(writer);
		// rest of item info
		writer.format("%d\n", jarSize);
	}

}
