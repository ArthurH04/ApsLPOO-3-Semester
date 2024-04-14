package apsLPOO_3_semestre;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import models.Item;
import models.Shop;
import util.ItemCategory;
import util.Status;
import util.CsvHelper;

public class Application {

	private static Scanner input = new Scanner(System.in);

	private static List<Item> items = new ArrayList<Item>();
	private static List<Shop> shops = new ArrayList<Shop>();

	public static void main(String[] args) {
		menu();
	}

	private static void menu() {

		System.out.println("Welcome");
		System.out.println("1 - Create shop");
		System.out.println("2 - Create items");
		System.out.println("3 - List all data");
		System.out.println("4 - List data ordered by more than one criteria");
		System.out.println("5 - List data filtered by some search");

		int options = validNumber();

		switch (options) {
		case 1:
			createShop();
			break;
		case 2:
			createItem();
			break;
		case 3:
			listAll();
			break;
		case 4:
			listByCriteria();
			break;
		case 5:
			listBySearch();
			break;
		}
	}

	private static void createShop() {

		System.out.println("Enter the path: ");
		String path = input.nextLine();

		System.out.println("How many shops do you want to create?");
		int shop = validNumber();

		for (int i = 0; i < shop; i++) {
			System.out.println("Enter the " + (i + 1) + "° shop name: ");
			String name = input.nextLine();
			shops.add(new Shop(name));
		}

		CsvHelper.createCSV(shops, path);
	}

	private static void createItem() {
		Item itemClass = new Item();
		
		System.out.println("Enter the shop csv path: " );
		String shopPath = input.next();
		input.nextLine();
		CsvHelper.loadShops(shops, shopPath);

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

				category = validNumber();
				validCategory = category > 0 && category <= 3;

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

			int shopChoice;
			boolean validChoice;

			do {
				System.out.println("Select the shop: ");
				for (int j = 0; j < shops.size(); j++) {
					System.out.println((j + 1) + "Shop name: " + shops.get(j).getName() + "\n");
				}

				shopChoice = validNumber();
				validChoice = shopChoice > 0 && shopChoice <= shops.size();

				if (validChoice) {
					Shop chosenShop = shops.get(shopChoice - 1);
					items.add(new Item(name, chosenShop, itemClass.getCategory(), itemClass.getStatus()));

				} else {
					System.out.println("Please enter a valid number");
				}
			} while (!validChoice);

		}

		CsvHelper.createCSV(items, path);
	}

	private static void listAll() {
		CsvHelper.loadShops(shops, "D:\\JavaProjects\\apsLPOO-3-semester\shop.csv");
	}

	private static void listByCriteria() {
	}

	private static void listBySearch() {
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

}
