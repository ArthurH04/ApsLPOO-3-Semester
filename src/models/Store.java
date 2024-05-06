package models;

import java.util.ArrayList;
import java.util.List;

public class Store extends Mall {

	private int storeId;
	private static int nextId = 1;
	private List<Product> products = new ArrayList<>();
	private List<Mall> malls = new ArrayList<>();

	public Store() {
	}

	public Store(String name, List<Mall> malls) {
		super(name);
		this.storeId = nextId++;
		this.malls = malls;
	}

	public Store(int storeId, String name, List<Mall> malls) {
		super(name);
		this.storeId = storeId;
		this.malls = malls;

	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Mall> getMalls() {
		return malls;
	}

	public void setMalls(List<Mall> malls) {
		this.malls = malls;
	}

	@Override
	public String toString() {
		return getStoreId() + " " + getName();
	}

}
