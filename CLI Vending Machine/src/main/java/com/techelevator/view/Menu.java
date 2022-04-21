package com.techelevator.view;

import com.techelevator.Products;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.Stream;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {

		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}
	//Get Slot selection from user using vendingMachine
//
//	public String getSlotChoiceFromUserInput() {
//		String slotChoice = null;
//		System.out.println("Please input slot location for desired item >>>");
//		slotChoice = in.nextLine();
//
//		if (slotChoice == null || slotChoice == " ") {
//			out.println(System.lineSeparator() + "*** " + slotChoice + " is not a valid option ***" + System.lineSeparator());
//		}
//		return slotChoice;
//	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
	//Using vendingMachine method displayItems in CLI
	//TODO Need to add slot key option in this class?

//	public void displayMenuOptionsForItems(String[] options) {
//		out.println();
////		String str = String.format("%1$-7s %2$-13s %3$12s %4$10s", "Slot", "Product Name", "Price", "Quantity");
//		//out.println(str);
//		for(int i = 0; i < options.length; i++) {
//			out.println(options[i]);
//		}
//		out.flush();
//	}

}
