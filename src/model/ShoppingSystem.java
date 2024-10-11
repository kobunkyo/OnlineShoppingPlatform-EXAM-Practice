package model;

import java.util.ArrayList;

public class ShoppingSystem {
	public ArrayList<Product> products;
	public ArrayList<User> users;
	
	public ShoppingSystem(ArrayList<Product> products, ArrayList<User> users) {
		super();
		this.products = products;
		this.users = users;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}


	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}


	public void displayMainMenu() {
		System.out.println("=== Online Shopping Platform ===\n" + 
						"1. Shop for Products\n" +
						"2. View Shopping Cart\n" +
						"3. View Orders\n" + 
						"4. Customer Info" +
						"5. Exit" +
						"Your Choice: ");
	}
	
	public void displayProductsMenu() {
		System.out.println("=== Shop for Products ===\r\n" + 
				"1. View All Products\r\n" + 
				"2. Add Clothing to Cart\r\n" + 
				"3. Add Electronics to Cart\r\n" + 
				"4. Back to Main Menu\r\n" + 
				"Your Choice: ");
	}
	
	public void displayCartMenu() {
		System.out.print("\n=== View Shopping Cart ===\n"
				+ "1. View Cart Contents\n"
				+ "2. Checkout\n"
				+ "3. Back to Main Menu\n"
				+ "Your Choice: ");
	}
	
	public void displayOrdersMenu() {
		System.out.print("\n=== View Orders ===\n"
				+ "1. View Order History\n"
				+ "2. Track Order\n"
				+ "3. Back to Main Menu\n"
				+ "Your Choice: ");
	}
	
	public void displayCustomerInfo(String UserID) {
		System.out.println("=== Customer Info ===");
		for (User u : users) {
			if(u.getUserID().equals(UserID)) {
				((Customer) u).displayCustomerInfo();
				break;
			}
		}
	}
	
	public void exit() {
		System.exit(0);
	}
}
