package model;

import java.util.ArrayList;

public class Customer extends User {
	protected String address;
	public ArrayList<Order> orders;
	public ArrayList<Product> cart;
	
	public Customer(String userID, String username, String email, String address) {
		super(userID, username, email);
		this.address = address;
		this.orders = new ArrayList<>();
		this.cart = new ArrayList<>();	
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(Order order) {
		orders.add(order);
	}

	public ArrayList<Product> getCart() {
		return cart;
	}

	public void setCart(Product product) {
		cart.add(product);
	}
	
	public void displayCustomerInfo() {
		super.displayUserInfo();
		System.out.printf("Address: %s\n", address);
	}
	
	public void addToCart(Product product) {
		cart.add(product);
	}
	
	public void viewCart() {
		double totalPrice = 0;
		System.out.printf("%-4s | %-30s | %-9s\n", "ID", "Product Name", "Price");
		System.out.println("-------------------------------------------------");
		if(cart.isEmpty()) {
			System.out.println("Cart is empty");
		}else {
			for (Product p : cart) {
				System.out.printf("%-4s | %-30s | $%-9.2f\n", p.getProductId(), p.getProductName(), p.getPrice());
				totalPrice += p.getPrice();
			}
		}
		System.out.println("-------------------------------------------------\n\n");
		
		System.out.printf("Total Price: $%.2f\n", totalPrice);
	}
}
