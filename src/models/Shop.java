package models;

import java.util.ArrayList;
import java.util.List;

public class Shop {

	private static int nextId = 1;
	private int id;
	private String name;
	private List<Item> items = new ArrayList<>();

	public Shop() {

	}

	public Shop(String name) {
		this.id = nextId++;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return getName();
	}

}
