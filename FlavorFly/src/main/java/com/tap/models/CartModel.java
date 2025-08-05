package com.tap.models;

public class CartModel {
    private int menuId;
    private String itemName;
    private int quantity;
    private double price;

    public CartModel(int menuId, String itemName, int quantity, double price) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }
    public double getTotalPrice() {
        return this.quantity * this.price;
    }
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


}
