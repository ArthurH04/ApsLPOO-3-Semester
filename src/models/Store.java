package models;

import java.util.ArrayList;
import java.util.List;

public class Store extends Mall {

	private int storeId;
	private static int nextId = 1;
	private List<Item> items = new ArrayList<>();
	private List<Mall> malls = new ArrayList<>();

	public Store() {
	}

	public Store(String name) {
		super(name);
		this.storeId = nextId++;
	}
	
	public Store(int storeId, String name) {
		super(name);
		this.storeId = storeId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return getStoreId() + " " + getName();
	}

}
