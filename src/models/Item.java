package models;

public class Item {

	private int id;
	private String name;
	private Shop shop;

	
	
	public Item(int id, String name, Shop shop) {
		super();
		this.id = id;
		this.name = name;
		this.shop = shop;
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

	@Override
	public String toString() {
		return getId() + getName();
				
	}

	
	
}
