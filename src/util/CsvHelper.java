package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

import models.Item;
import models.Shop;

public class CsvHelper {
	public static <T> void createItemCSV2(List<T> list, Function<T, String> mapper, String path) {

		try {
			OutputStream outputStream = new FileOutputStream(path);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);

			printWriter.println("id,nome,shop");

			for (T element : list) {
				printWriter.println(mapper.apply(element));
			}

			printWriter.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}