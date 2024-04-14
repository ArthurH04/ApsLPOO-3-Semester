package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import models.Item;
import models.Store;

public class CsvHelper {

	private static Map<String, Store> storeMap = new HashMap<>();

	public static <T> void createCSV(List<T> list, String path) {

		try {
			OutputStream outputStream = new FileOutputStream(path);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);

			String headers = "";
			Function<T, String> mapper = null;

			if (!list.isEmpty()) {
				if (list.get(0) instanceof Store) {
					headers = "id,name";
					printWriter.println(headers);

					mapper = (T s) -> ((Store) s).getStoreId() + "," + ((Store) s).getName();
				} else if (list.get(0) instanceof Item) {
					
					headers = "id,name,shop,category,status";
					printWriter.println(headers);

					mapper = (T i) -> {

						Item item = (Item) i;
						List<String> storeNames = new ArrayList<>();
						for (Store store : item.getStores()) {
							storeNames.add(store.getName());
						};
						return item.getItemId() + "," + item.getName() + "," + String.join(",", storeNames) + ","
								+ item.getCategory() + "," + item.getStatus();
					};
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
	public static <T extends Mall> void loadItems(List<T> list, String path, String type) {

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

			String line = bufferedReader.readLine();
			line = bufferedReader.readLine();
			while (line != null) {
				String[] vect = line.split(",");
				String name = vect[1];

				switch (type) {
				case "Store":
					list.add((T) new Store(name));
					break;
				case "Item":
					String storeName = vect[2];
					ItemCategory itemCategory = ItemCategory.valueOf(vect[3]);
					Status status = Status.valueOf(vect[4]);
					Store store = storeMap.get(storeName);
					if (store == null) {
						store = new Store(storeName);
						storeMap.put(storeName, store);
					}

					list.add((T) new Item(name, Arrays.asList(store), itemCategory, status));
				}
				line = bufferedReader.readLine();
			}

			for (T element : list) {
				System.out.println(element);
			}

		} catch (Exception e) {
			System.out.println("Wrong path.");
		}
	}
}
