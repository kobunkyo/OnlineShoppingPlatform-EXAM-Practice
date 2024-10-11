package model;

public class User {
	protected String userID;
	protected String username;
	protected String email;
	
	protected int orderPlace;
	
	public User(String userID, String username, String email) {
		super();
		this.userID = userID;
		this.username = username;
		this.email = email;
	}
	
	public int getOrderPlace() {
		return orderPlace;
	}

	public void setOrderPlace(int orderPlace) {
		this.orderPlace = orderPlace;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void displayUserInfo() {
		System.out.printf("User ID: %s\nUsername: %s\nEmail: %s\n", userID, username, email);
		return;
	}
	public void updateEmail (String newEmail) {
		setEmail(newEmail);
		System.out.println("Email berhasil di update");
	}
	public void placeOrder(Order order) {
		
		return;
	}
}
