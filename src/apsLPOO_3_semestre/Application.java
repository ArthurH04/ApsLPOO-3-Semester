package apsLPOO_3_semestre;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		menu();
	}

	private static void menu() {

		System.out.println("Welcome");
		System.out.println("1 - Create");
		System.out.println("2 - List all data");
		System.out.println("3 - List data ordered by more than one criteria");
		System.out.println("4 - List data filtered by some search");

		int options = 0;
		boolean validInput = false;

		while (!validInput) {
			try {
				options = input.nextInt();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Please, enter a number.");
				input.next();
			}
		}
		input.close();

		switch (options) {
		case 1:
			create();
			break;
		case 2:
			listAll();
			break;
		case 3:
			listByCriteria();
			break;
		case 4:
			listBySearch();
			break;
		}
	}

	private static void listBySearch() {
	}

	private static void listByCriteria() {
	}

	private static void listAll() {
	}

	private static void create() {
	}

}
