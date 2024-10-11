package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
	protected String orderId;
	protected String orderDate;
	protected ArrayList<Product> orderItems;
	protected String status;
	
	public Order(String orderId) {
		super();
		this.orderId = orderId;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		orderDate = LocalDate.now().format(formatter);
		this.orderItems = new ArrayList<>();
		status = "Ordered";
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public ArrayList<Product> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<Product> orderItems) {
		this.orderItems = orderItems;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void displayOrderInfo() {
		System.out.println("=== Order Details ===");
		System.out.printf("Order ID: %s\nOrder Date: %s\nTotal Price: $%.2f\n", orderId, orderDate, calculateTotalPrice());
		
		System.out.println("Order Items:");
		System.out.printf("%-4s | %-30s | %-9s\n", "ID", "Product Name", "Price");
		System.out.println("-------------------------------------------------");
		for (Product p : orderItems) {
			if(p instanceof Clothing) {
				((Clothing) p).displayOrderDetails();
			}else if(p instanceof Electronics) {
				((Electronics) p).displayOrderDetails();
			}
		}
		System.out.println("-------------------------------------------------\n\n");
	}
	
	public double calculateTotalPrice() {
		double totalPrice = 0;
		for (Product p : orderItems) {
			totalPrice += p.getPrice();
		}
		return totalPrice;
	}
	
	public void processOrder(ShoppingSystem system) {
		for (Product p : orderItems) {
			for(Product sP : system.products) {
				if(p instanceof Clothing && p.getProductId().equals(sP.getProductId())) {
					int newStock = ((Clothing) p).getQuantityInStock() - 1;
					((Clothing) p).setQuantityInStock(newStock);
					((Clothing) sP).setQuantityInStock(newStock);
				}else if(p instanceof Electronics && p.getProductId().equals(sP.getProductId())) {
					int newStock = ((Electronics) p).getQuantityInStock() - 1;
					((Electronics) p).setQuantityInStock(newStock);
					((Electronics) sP).setQuantityInStock(newStock);
				}	
			}
		}
		setStatus("Shipped");
	}
}
