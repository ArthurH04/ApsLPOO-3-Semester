package apsLPOO_3_semestre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import models.Mall;
import models.Product;
import models.Store;
import util.CsvHelper;
import util.ProductCategory;
import util.Status;

public class Application {

	private static Scanner input = new Scanner(System.in);

	private static List<Mall> malls = new ArrayList<>();
	private static List<Store> stores = new ArrayList<>();
	private static List<Product> products = new ArrayList<>();
	private static final String storeType = "Store";
	private static final String productType = "Product";
	private static final String mallType = "Mall";
	private static final String MALL_PATH_MSG = "Enter the mall csv path: ";
	private static final String STORE_PATH_MSG = "Enter the store csv path: ";
	private static final String PRODUCT_PATH_MSG = "Enter the product csv path: ";

	public static void main(String[] args) throws IOException {
		menu();
	}

	private static void menu() throws IOException {

		System.out.println("Welcome");
		System.out.println("1 - Create malls");
		System.out.println("2 - Create stores");
		System.out.println("3 - Create products");
		System.out.println("4 - List");
		System.out.println("5 - List data ordered by more than one criteria");
		System.out.println("6 - List data filtered by some search");

		int options = validNumber();

		switch (options) {
		case 1:
			createMall();
			break;
		case 2:
			createStore();
			break;
		case 3:
			createProduct();
			break;
		case 4:
			list();
			break;
		case 5:
			listByCriteria();
			break;
		case 6:
			listBySearch();
			break;

		}
	}

	private static void createMall() throws IOException, ArrayIndexOutOfBoundsException {
		System.out.println("Enter the path you would like to save the malls: ");
		String path = input.nextLine();

		System.out.println("How many malls do you want to create?");
		int mall = validNumber();

		for (int i = 0; i < mall; i++) {
			System.out.println("Enter the " + (i + 1) + "° mall name: ");
			String name = input.nextLine();
			malls.add(new Mall(name));

			CsvHelper.createCSV(malls, mallType, path);
		}
		decision();
	}

	private static void createStore() throws IOException, ArrayIndexOutOfBoundsException {

		validPath(MALL_PATH_MSG, malls, mallType, false);

		System.out.println("Enter the path you would like to save the stores: ");
		String path = input.nextLine();

		System.out.println("How many stores do you want to create?");
		int store = validNumber();

		for (int i = 0; i < store; i++) {
			System.out.println("Enter the " + (i + 1) + "° store name: ");
			String name = input.next();
			input.nextLine();

			int mallChoice;
			boolean validChoice;

			do {
				System.out.println("Select the mall where this store is present: ");
				for (int j = 0; j < malls.size(); j++) {
					System.out.println((j + 1) + " Mall name: " + malls.get(j).getName() + "\n");
				}

				mallChoice = validNumber();
				validChoice = mallChoice > 0 && mallChoice <= malls.size();

				if (validChoice) {
					Mall chosenMall = malls.get(mallChoice - 1);
					List<Mall> mallList = new ArrayList<>();
					mallList.add(chosenMall);
					stores.add(new Store(name, mallList));

				} else {
					System.out.println("Please enter a valid number");
				}
				CsvHelper.createCSV(stores, storeType, path);
			} while (!validChoice);
		}
		decision();
	}

	private static void createProduct() throws IOException, ArrayIndexOutOfBoundsException {
		Product productClass = new Product();

		validPath(STORE_PATH_MSG, stores, storeType, false);

		System.out.println("Enter the path you would like to save the products: ");
		String path = input.next();
		input.nextLine();

		System.out.println("How many products do you want to create?");
		int product = validNumber();

		for (int i = 0; i < product; i++) {
			System.out.println("Enter the " + (i + 1) + "° product name: ");
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
				productClass.setCategory(ProductCategory.COLD_CUTS);
				break;
			case 2:
				productClass.setCategory(ProductCategory.VEGETABLES);
				break;
			case 3:
				productClass.setCategory(ProductCategory.LEGUMES);
				break;
			case 4:
				productClass.setCategory(ProductCategory.FRUITS);
				break;
			case 5:
				productClass.setCategory(ProductCategory.CLOTHES);
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
				productClass.setStatus(Status.AVAILABLE);
				break;
			case 2:
				productClass.setStatus(Status.UNAVAILABLE);
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
					products.add(new Product(name, storeList, productClass.getCategory(), productClass.getStatus()));

				} else {
					System.out.println("Please enter a valid number");
				}
			} while (!validChoice);

			CsvHelper.createCSV(products, productType, path);
		}
		decision();

	}

	private static void list() throws IOException, ArrayIndexOutOfBoundsException {
		System.out.println("What would you like to list?");
		System.out.println("1 - Malls");
		System.out.println("2 - Stores");
		System.out.println("3 - Products");

		int choice;
		boolean validChoice;

		do {
			choice = validNumber();
			validChoice = choice > 0 && choice <= 3;

			if (!validChoice) {
				System.out.println("Please enter a valid number");
			}
		} while (!validChoice);

		switch (choice) {
		case 1:
			validPath(MALL_PATH_MSG, malls, mallType, true);
			break;
		case 2:
			validPath(STORE_PATH_MSG, stores, storeType, true);
			break;
		case 3:
			validPath(PRODUCT_PATH_MSG, products, productType, true);
		}

		decision();
	}

	private static void listByCriteria() throws IOException {
		System.out.println("What would you like to list?");
		System.out.println("1 - Products");
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
			System.out.println("Enter the product csv path: ");
			String productPath = input.next();
			input.nextLine();
			System.out.println("Sorting product by name and status: ");
			CsvHelper.sortProductByNameAndStatus(products, productPath, productType, false);
			break;
		case 2:
			System.out.println("Enter the store csv path: ");
			String storePath = input.next();
			input.nextLine();
			System.out.println("Sorting store by name: ");
			CsvHelper.sortStoreByName(stores, storePath, storeType, false);
			break;
		}

		decision();
	}

	private static void listBySearch() throws IOException {
		System.out.println("What would you like to list by search?");
		System.out.println("1 - Products");
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
			System.out.println("Enter the product csv path: ");
			String productPath = input.next();
			input.nextLine();

			System.out.println("Enter the product name: ");
			String productName = input.next();
			input.nextLine();

			CsvHelper.searchByName(products, productName, productPath, productType, false);
			break;
		case 2:
			System.out.println("Enter the store csv path: ");
			String storePath = input.next();
			input.nextLine();

			System.out.println("Enter the store name: ");

			String storeName = input.next();
			input.nextLine();

			CsvHelper.searchByName(stores, storeName, storePath, storeType, false);
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

	private static <T extends Mall> void validPath(String message, List<T> list, String type, boolean print)
			throws IOException, ArrayIndexOutOfBoundsException {
		boolean validPath = false;

		while (!validPath) {
			try {
				System.out.println(message);
				String path = input.next();
				input.nextLine();
				CsvHelper.load(list, path, type, print);
				validPath = true;
			} catch (IOException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Wrong path. Please try again.");
			}
		}
	}

}
