package models;

import java.util.ArrayList;
import java.util.List;

public class Mall {

	private int mallId;
	private static int nextId = 1;
	private String name;
	private List<Store> stores = new ArrayList<>();

	public Mall() {
	}

	
	public Mall(String name) {
		this.name = name;
		this.mallId = nextId++;
	}
	public Mall(int mallId, String name) {
		this.name = name;
		this.mallId = mallId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMallId() {
		return mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	@Override
	public String toString() {
		return getMallId() + " " + getName();
	}
	
	

}
