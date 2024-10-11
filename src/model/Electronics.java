package model;

public class Electronics extends Product implements Orderable {

	@Override
	public void addToOrder(Order order) {
		order.orderItems.add(this);
	}

	@Override
	public void displayOrderDetails() {
		System.out.printf("%-4s | %-30s | $%-9.2f\n", productId, productName, price);
	}

	@Override
	public void displayProductInfo() {
		if(isAvailable() == true) {
			System.out.printf("%-4s | %-30s | $%-9.2f | %-10s | %-12s\n", productId, productName, price, brand, "In Stock");
		}else if(isAvailable() == false) {
			System.out.printf("%-4s | %-30s | $%-9.2f | %-10s | %-12s\n", productId, productName, price, brand, "Out of Stock");
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

	protected String brand;
	protected int warrantyPeriod;
	protected int quantityInStock; // untuk isAvailable
	
	public Electronics(String productId, String productName, double price, String brand, int quantityInStock, int warrantyPeriod) {
		super(productId, productName, price);
		this.brand = brand;
		this.warrantyPeriod = warrantyPeriod;
		this.quantityInStock = quantityInStock;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(int warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	
	
}
