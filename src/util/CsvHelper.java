package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

import models.Item;
import models.Shop;

public class CsvHelper {
	public static <T> void createItemCSV2(List<T> list, String path) {

		try {
			OutputStream outputStream = new FileOutputStream(path);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);

			String headers = "";
			Function<T, String> mapper = null;

			if (!list.isEmpty()) {
				if (list.get(0) instanceof Shop) {
					headers = "id,name";
					printWriter.println(headers);

					mapper = (T s) -> ((Shop) s).getId() + "," + ((Shop) s).getName();
				} else if (list.get(0) instanceof Item) {
					headers = "id,name,shop,category,status";
					printWriter.println(headers);

					mapper = (T i) -> ((Item) i).getId() + "," + ((Item) i).getName() + "," + ((Item) i).getShop()
							+ "," + ((Item)i).getCategory() + "," + ((Item)i).getStatus();
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

	public static void loadShops(List<Shop> shops, String path) {

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
			
			String line = bufferedReader.readLine();
			line = bufferedReader.readLine();
			while(line != null) {
				String[] vect = line.split(",");
				String name = vect[1];
				
				shops.add(new Shop(name));
				
				line = bufferedReader.readLine();
			}
			
			for(Shop shop: shops) {
				System.out.println(shop);
			}
			
		} catch (Exception e) {
			// TODO: handle exception

		}
	}
}