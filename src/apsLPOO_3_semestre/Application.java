package apsLPOO_3_semestre;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import models.Item;
import models.Shop;
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
		shops.add(new Shop(1, "Shop 1"));
		shops.add(new Shop(2, "Shop 2"));

		CsvHelper.createItemCSV2(shops, s -> s.getId() + "," + s.getName(),
				"D:\\JavaProjects\\apsLPOO-3-semester\\arquivo.csv");
	}

	private static void createItem() {

		items.add(new Item(1, "Item 1", shops.get(0)));
		items.add(new Item(2, "Item 2", shops.get(1)));
		items.add(new Item(3, "Item 3", shops.get(0)));

		CsvHelper.createItemCSV2(items, i -> i.getId() + "," + i.getName() + "," + i.getShop(),
				"D:\\JavaProjects\\apsLPOO-3-semester\\items.csv");
	}

	private static void listAll() {
	}

	private static void listByCriteria() {
	}

	private static void listBySearch() {
	}

}
