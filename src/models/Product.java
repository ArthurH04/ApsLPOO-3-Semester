package models;

import java.util.ArrayList;
import java.util.List;

import util.ProductCategory;
import util.Status;

public class Product extends Mall {

	private int productId;
	private static int nextId = 1;
	private List<Store> stores = new ArrayList<>();
	private ProductCategory category;
	private Status status;

	public Product() {
	}

	public Product(String name, List<Store> stores, ProductCategory category, Status status) {
		super(name);
		this.stores = stores;
		this.category = category;
		this.status = status;
		this.productId = nextId++;
	}

	public Product(int itemId, String name, List<Store> stores, ProductCategory category, Status status) {
		super(name);
		this.stores = stores;
		this.category = category;
		this.status = status;
		this.productId = itemId;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return getProductId() + " " + getName() + " " + getCategory() + " " + getStatus();

	}

}
