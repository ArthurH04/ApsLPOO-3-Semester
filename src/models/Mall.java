package models;

import java.util.ArrayList;
import java.util.List;

public class Mall {
	
    private int id;
	private String name;
	private List<Store> stores = new ArrayList<>();

	public Mall() {
	}

	public Mall(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
