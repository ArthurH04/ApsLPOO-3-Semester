package models;

import java.util.ArrayList;
import java.util.List;

import util.ItemCategory;
import util.Status;

public class Item extends Mall {

	private int itemId;
	private static int nextId = 1;
	private List<Store> stores = new ArrayList<>();
	private ItemCategory category;
	private Status status;

	public Item() {
	}

	public Item(String name, List<Store> stores, ItemCategory category, Status status) {
		super(name);
		this.stores = stores;
		this.category = category;
		this.status = status;
		this.itemId = nextId++;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(Store store : stores) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(store.getName());
		}
		
		return getItemId() + " " + getName() + " " + sb.toString() + " " + getCategory() + " " + getStatus();

	}

}
