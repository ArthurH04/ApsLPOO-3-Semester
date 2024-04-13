package models;

import java.util.Locale.Category;

import util.ItemCategory;
import util.Status;

public class Item {

	private static int nextId = 1;
	private int id;
	private String name;
	private Shop shop;
	private ItemCategory category;
	private Status status;

	public Item() {
	}

	public Item(String name, Shop shop, ItemCategory category, Status status) {
		this.id = nextId++;
		this.name = name;
		this.shop = shop;
		this.category = category;
		this.status = status;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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

	@Override
	public String toString() {
		return getId() + getName();

	}

}
