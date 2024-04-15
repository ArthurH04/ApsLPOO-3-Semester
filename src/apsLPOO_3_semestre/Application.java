package apsLPOO_3_semestre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import models.Item;
import models.Store;
import util.CsvHelper;
import util.ItemCategory;
import util.Status;

public class Application {

	private static Scanner input = new Scanner(System.in);

	private static List<Item> items = new ArrayList<Item>();
	private static List<Store> stores = new ArrayList<Store>();

	public static void main(String[] args) throws IOException {
		menu();
	}

	private static void menu() throws IOException {

		System.out.println("Welcome");
		System.out.println("1 - Create stores");
		System.out.println("2 - Create items");
		System.out.println("3 - List stores");
		System.out.println("4 - List items");
		System.out.println("5 - List data ordered by more than one criteria");
		System.out.println("6 - List data filtered by some search");

		int options = validNumber();

		switch (options) {
		case 1:
			createStore();
			break;
		case 2:
			createItem();
			break;
		case 3:
			listStore();
			break;
		case 4:
			listItem();
			break;
		case 5:
			listByCriteria();
			break;
		case 6:
			listBySearch();
			break;
		}
	}

	private static void createStore() throws IOException {

		System.out.println("Enter the path you would like to save the stores: ");
		String path = input.nextLine();

		System.out.println("How many stores do you want to create?");
		int store = validNumber();

		for (int i = 0; i < store; i++) {
			System.out.println("Enter the " + (i + 1) + "° store name: ");
			String name = input.nextLine();
			stores.add(new Store(name));

			CsvHelper.createCSV(stores, "Store", path);
		}

		decision();
	}

	private static void createItem() throws IOException {
		Item itemClass = new Item();

		boolean validPath = false;

		while (!validPath) {
			try {
				System.out.println("Enter the store csv path: ");
				String storePath = input.next();
				input.nextLine();
				CsvHelper.load(stores, storePath, "Store", false);
				validPath = true;
			} catch (IOException e) {
				System.out.println("Wrong path. Please try again.");
			}
		}

		System.out.println("Enter the path you would like to save the items: ");
		String path = input.next();
		input.nextLine();

		System.out.println("How many items do you want to create?");
		int item = validNumber();

		for (int i = 0; i < item; i++) {
			System.out.println("Enter the " + (i + 1) + "° item name: ");
			String name = input.next();
			input.nextLine();

			int category;
			boolean validCategory;
			do {
				System.out.println("Select " + name + " category: ");
				System.out.println("1 - Cold Cuts");
				System.out.println("2 - Vegetables");
				System.out.println("3 - Legumes");
				System.out.println("4 - Fruits");
				System.out.println("5 - Clothes");

				category = validNumber();
				validCategory = category > 0 && category <= 5;

				if (!validCategory) {
					System.out.println("Please enter a valid number");
				}
			} while (!validCategory);

			switch (category) {
			case 1:
				itemClass.setCategory(ItemCategory.COLD_CUTS);
				break;
			case 2:
				itemClass.setCategory(ItemCategory.VEGETABLES);
				break;
			case 3:
				itemClass.setCategory(ItemCategory.LEGUMES);
				break;
			case 4:
				itemClass.setCategory(ItemCategory.FRUITS);
				break;
			case 5:
				itemClass.setCategory(ItemCategory.CLOTHES);
				break;
			}

			int statusChoice;
			boolean validStatus;
			do {
				System.out.println("Select " + name + " status: ");
				System.out.println("1 - Available");
				System.out.println("2 - Unavailable");
				statusChoice = validNumber();
				validStatus = statusChoice > 0 && statusChoice <= 2;

				if (!validStatus) {
					System.out.println("Please enter a valid number");
				}

			} while (!validStatus);

			switch (statusChoice) {
			case 1:
				itemClass.setStatus(Status.AVAILABLE);
				break;
			case 2:
				itemClass.setStatus(Status.UNAVAILABLE);
				break;
			}

			int storeChoice;
			boolean validChoice;

			do {
				System.out.println("Select the store: ");
				for (int j = 0; j < stores.size(); j++) {
					System.out.println((j + 1) + " Store name: " + stores.get(j).getName() + "\n");
				}

				storeChoice = validNumber();
				validChoice = storeChoice > 0 && storeChoice <= stores.size();

				if (validChoice) {
					Store chosenStore = stores.get(storeChoice - 1);
					List<Store> storeList = new ArrayList<>();
					storeList.add(chosenStore);
					items.add(new Item(name, storeList, itemClass.getCategory(), itemClass.getStatus()));

				} else {
					System.out.println("Please enter a valid number");
				}
			} while (!validChoice);

			CsvHelper.createCSV(items, "Item", path);
		}
		decision();

	}

	private static void listStore() throws IOException {
		System.out.println("Enter the store csv path: ");
		String storePath = input.next();
		input.nextLine();
		CsvHelper.load(stores, storePath, "Store", true);
		decision();
	}

	private static void listItem() throws IOException {
		System.out.println("Enter the item csv path: ");
		String itemPath = input.next();
		input.nextLine();
		CsvHelper.load(items, itemPath, "Item", true);

		decision();
	}

	private static void listByCriteria() throws IOException {
		System.out.println("What would you like to list?");
		System.out.println("1 - Items");
		System.out.println("2 - Stores");

		int choice;
		boolean validChoice;

		do {
			choice = validNumber();
			validChoice = choice > 0 && choice <= 2;

			if (!validChoice) {
				System.out.println("Please enter a valid number");
			}
		} while (!validChoice);

		switch (choice) {
		case 1:
			System.out.println("Enter the item csv path: ");
			String itemPath = input.next();
			input.nextLine();
			System.out.println("Sorting item by name and status: ");
			CsvHelper.sortItemByNameAndStatus(items, itemPath, "Item", false);
			break;
		case 2:
			System.out.println("Enter the store csv path: ");
			String storePath = input.next();
			input.nextLine();
			System.out.println("Sorting store by name: ");
			CsvHelper.sortStoreByName(stores, storePath, "Store", false);
			break;
		}

		decision();

	}

	private static void listBySearch() throws IOException {
		System.out.println("What would you like to list by search?");
		System.out.println("1 - Items");
		System.out.println("2 - Stores");

		int choice;
		boolean validChoice;

		do {
			choice = validNumber();
			validChoice = choice > 0 && choice <= 2;

			if (!validChoice) {
				System.out.println("Please enter a valid number");
			}
		} while (!validChoice);

		switch (choice) {
		case 1:
			System.out.println("Enter the item csv path: ");
			String itemPath = input.next();
			input.nextLine();

			System.out.println("Enter the item name: ");
			String itemName = input.next();
			input.nextLine();

			CsvHelper.searchByName(items, itemName, itemPath, "Item", false);
			break;
		case 2:
			System.out.println("Enter the store csv path: ");
			String storePath = input.next();
			input.nextLine();

			System.out.println("Enter the store name: ");

			String storeName = input.next();
			input.nextLine();

			CsvHelper.searchByName(stores, storeName, storePath, "Store", false);
			break;
		}
		decision();
	}

	private static int validNumber() {
		boolean isValid = false;
		int num = 0;

		while (!isValid) {
			try {
				num = input.nextInt();
				input.nextLine();
				isValid = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				input.next();
			}
		}
		return num;
	}

	private static void decision() throws IOException {
		System.out.println("What would you like to do? ");
		System.out.println("1 - Menu ");
		System.out.println("2 - Quit ");

		int choice;
		boolean validChoice;

		do {
			choice = validNumber();
			validChoice = choice > 0 && choice <= 2;

			if (!validChoice) {
				System.out.println("Please enter a valid number");
			}
		} while (!validChoice);

		switch (choice) {
		case 1:
			menu();
			break;

		case 2:
			System.exit(0);
			break;
		}

	}

}
