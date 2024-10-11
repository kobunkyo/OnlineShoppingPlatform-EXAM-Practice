package model;

public class Clothing extends Product implements Orderable {
	
	protected String size;
	protected int quantityInStock;

	public Clothing(String productId, String productName, double price, String size, int quantityInStock) {
		super(productId, productName, price);
		this.size = size;
		this.quantityInStock = quantityInStock;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	
	@Override
	public void displayProductInfo() {
		if(isAvailable() == true) {
			System.out.printf("%-4s | %-30s | $%-9.2f | %-7s | %-12s\n", productId, productName, price, size, "In Stock");
		}else if(isAvailable() == false) {
			System.out.printf("%-4s | %-30s | $%-9.2f | %-7s | %-12s\n", productId, productName, price, size, "Out of Stock");
		}
	}
	
	@Override
	public double getPrice() {
		return price;
	}
	
	@Override
	public boolean isAvailable() {
		if(quantityInStock > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public void addToOrder(Order order) {
		order.orderItems.add(this);
	}

	@Override
	public void displayOrderDetails() {
		System.out.printf("%-4s | %-30s | $%-9.2f\n", productId, productName, price);
	}
}
