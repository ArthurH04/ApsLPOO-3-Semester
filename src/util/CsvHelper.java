package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import models.Mall;
import models.Product;
import models.Store;

public class CsvHelper {

	private static Map<String, Mall> mallMap = new HashMap<>();
	private static Map<String, Mall> mallMapCreate = new HashMap<>();

	public static <T> void createCSV(List<T> list, String type, String path) {
		try {
			OutputStream outputStream = new FileOutputStream(path);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);

			String headers = "";
			Function<T, String> mapper = null;

			if (!list.isEmpty()) {
				switch (type) {

				case "Mall":
					headers = "id,name";
					printWriter.println(headers);
					mapper = (T m) -> ((Mall) m).getMallId() + "," + ((Mall) m).getName();
					break;
				case "Store":
					headers = "id,name,mall";
					printWriter.println(headers);
					mapper = (T s) -> {
						Store store = (Store) s;

						List<String> mallNames = new ArrayList<>();
						for (Mall mall : store.getMalls()) {
							mallNames.add(mall.getName());
						}
						return store.getStoreId() + "," + store.getName() + "," + String.join(",", mallNames);
					};
					break;
				case "Product":
					headers = "id,name,store,category,status";
					printWriter.println(headers);

					mapper = (T i) -> {

						Product product = (Product) i;
						List<String> storeNames = new ArrayList<>();
						for (Store store : product.getStores()) {
							storeNames.add(store.getName());
						}
						;
						return product.getProductId() + "," + product.getName() + "," + String.join(",", storeNames)
								+ "," + product.getCategory() + "," + product.getStatus();
					};
					break;
				}
			}
			for (T element : list) {
				printWriter.println(mapper.apply(element));
			}

			printWriter.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Mall> void load(List<T> list, String path, String type, boolean print)
			throws IOException, ArrayIndexOutOfBoundsException {

		list.clear();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

			String line = bufferedReader.readLine();
			line = bufferedReader.readLine();
			while (line != null) {
				String[] vect = line.split(",");
				int id = Integer.parseInt(vect[0]);
				String name = vect[1];
				List<Mall> malls = (List<Mall>) mallMap.get(name);

				switch (type) {
				case "Mall":
					list.add((T) new Mall(id, name));
					break;
				case "Store":
					list.add((T) new Store(id, name, malls));
					break;
				case "Product":
					String storeName = vect[2];
					ProductCategory productCategory = ProductCategory.valueOf(vect[3]);
					Status status = Status.valueOf(vect[4]);
					Store store = null;
					if (store == null) {
						store = new Store(id, storeName, malls);
					}
					list.add((T) new Product(id, name, Arrays.asList(store), productCategory, status));
					break;
				}
				line = bufferedReader.readLine();
			}

			if (print) {
				for (T element : list) {
					System.out.println(element);
				}
				list.clear();
			}
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			throw e;
		}
	}

	public static void sortProductByNameAndStatus(List<Product> products, String path, String type, boolean print)
			throws IOException {
		load(products, path, type, print);

		products.sort(Comparator.comparing(Product::getName).thenComparing(Product::getStatus));
		for (Product product : products) {
			System.out.println(product);
		}
		products.clear();
	}

	public static void sortStoreByName(List<Store> stores, String path, String type, boolean print) throws IOException {

		load(stores, path, type, print);

		stores.sort(Comparator.comparing(Store::getName));
		for (Store store : stores) {
			System.out.println(store);
		}
	}

	public static <T extends Mall> void searchByName(List<T> list, String searchValue, String path, String type,
			boolean print) throws IOException {
		load(list, path, type, print);

		for (T element : list) {
			if (element.getName().trim().equalsIgnoreCase(searchValue.trim())) {
				System.out.println(element);
			}
		}

	}
}
