package main;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		ArrayList<Product> products = new ArrayList<>();
		ArrayList<User> users = new ArrayList<>();
		
		products.add(new Clothing("P001", "T-Shirt - Blue", 19.99, "M", 10));
		products.add(new Clothing("P002", "Sneakers - Sports", 39.99, "9", 2));
		products.add(new Electronics("P003", "Smartphone - Model X", 499.99, "TechCo", 6, 12));
		products.add(new Electronics("P004", "Laptop - UltraBook", 899.99, "MegaElect", 3, 24));
		products.add(new Clothing("P005", "Jeans - Slim Fit", 59.99, "32/34", 5));
		products.add(new Clothing("P006", "Shirt - Red Flannel", 29.99, "L", 20));
		products.add(new Electronics("P007", "Smartwatch - Fitness", 129.99, "FitTech ", 2, 12));
		products.add(new Electronics("P008", "Smartphone - Model Z(Flip)", 699.99, "TechCo", 11, 24));
		products.add(new Electronics("P009", "Laptop - Flipbook", 999.99, "MegaElect", 7, 12));
		products.add(new Clothing("P010", "Stiletto - Formal", 79.99, "8/9", 1));
			
		users.add(new Customer("C001", "aglae_beatty", "aglae.beatty@email.com", "1956 Ryan Road, Viborg"));
		users.add(new Customer("C002", "dax_moen", "dax.moen@email.com", "550 Martha Street, Kingman"));
		users.add(new Customer("C003", "mike_jones", "mike.jones@email.com", "789 Pine Road, Villagetown"));
		users.add(new Customer("C004", "nigel_hahnI", "nigel.hahnI@email.com", "1088 Finwood Road, Washington"));
		users.add(new Customer("C005", "savion_aufderhar", "savion.aufderhar@email.com", "156 Fairmont Avenue, Moberly"));
		
		ShoppingSystem system = new ShoppingSystem(products, users);
		
		User activeUser = null;
		
		while(true) {
			while(activeUser == null) {
				system.displayMainMenu();
				String customerId = input.nextLine();
				
				if(customerId.equalsIgnoreCase("exit")) {
					input.close();
					system.exit();
				}
				
				int isFound = 0;
				for (User u : users) {
					if(u.getUserID().equalsIgnoreCase(customerId)) {
						activeUser = u;
						isFound = 1;
						break;
					}
				}
				if(isFound == 0) {
					System.out.println("Customer ID is not found / Wrong input");
				}
			}
			
			Order orderHold = new Order(String.format("O%03d", activeUser.getOrderPlace()));
			
			int choice = 0;

			while(activeUser != null) {
				if(choice == 0) {
					system.displayMainMenu();
					choice = input.nextInt(); input.nextLine();
					
					if(choice < 1 || choice > 5) {
						System.out.println("Menu not found");
						choice = 0;
					}
				}
				
				if(choice == 1) {
					while(true) {
						system.displayProductsMenu();
						int pChoice = input.nextInt(); input.nextLine();
						
						if(pChoice == 1) {
							System.out.println("=== View All Products ===");
							System.out.printf("%-4s | %-30s | %-9s | %-12s\n", "ID", "Product Name", "Price", "Availability");
							System.out.println("----------------------------------------------------------------");
							for(Product p : system.products) {
								p.displayProductInfo();;
							}
							System.out.println("----------------------------------------------------------------");
							System.out.print("Press Enter to go back to Shop for Products Menu...");
							input.nextLine();
						}
						
						if(pChoice == 2) {
							System.out.println("=== Add Clothing to Cart ===");
							System.out.printf("%-4s | %-30s | %-9s | %-7s | %-12s\n", "ID", "Product Name", "Price", "Size", "Availability");
							System.out.println("--------------------------------------------------------------------------");
							for(Product p : system.products) {
								if(p instanceof Clothing) {
									p.displayProductInfo();
								}
							}
							System.out.println("--------------------------------------------------------------------------");
							
							while(true) {
								System.out.print("Enter the ID to add to your cart (or type 'back' to go back): ");
								String pID = input.nextLine();
								
								if(pID.equalsIgnoreCase("back")) {
									break;
								}
								
								int isFound = 0;
								for(Product p : system.products) {
									if(p instanceof Clothing && p.getProductId().equalsIgnoreCase(pID)) {
										isFound = 1;
										if(p.isAvailable() == false) {
											System.out.println("Product is out of stock");
											break;
										}
										((Customer) activeUser).addToCart(p);
										System.out.println("Product successfully added to cart");
										break;
									}
								}
								if(isFound == 0) {
									System.out.println("ID not found");
								}
							}
						}
						
						if(pChoice == 3) {
							System.out.println("=== Add Electronics to Cart ===");
							System.out.printf("%-4s | %-30s | %-9s | %-10s | %-12s\n", "ID", "Product Name", "Price", "Brand", "Availability");
							System.out.println("-----------------------------------------------------------------------------");
							for(Product p : system.products) {
								if(p instanceof Electronics) {
									p.displayProductInfo();
								}
							}
							System.out.println("-----------------------------------------------------------------------------");

							while(true) {
								System.out.print("Enter the ID to add to your cart (or type 'back' to go back): ");
								String pID = input.nextLine();
								
								if(pID.equalsIgnoreCase("back")) {
									break;
								}
								
								int isFound = 0;
								for(Product p : system.products) {
									if(p instanceof Electronics && p.getProductId().equalsIgnoreCase(pID)) {
										isFound = 1;
										if(p.isAvailable() == false) {
											System.out.println("Product is out of stock");
											break;
										}
										((Customer) activeUser).addToCart(p);
										
										System.out.println("Product successfully added to cart");
										break;
									}
								}
								
								if(isFound == 0) {
									System.out.println("ID not found");
								}
							}
						}
						
						if(pChoice == 4) {
							choice = 0;
							break;
						}
					}
				}
				
				if(choice == 2) {
					while(true) {
						system.displayCartMenu();
						
						int sChoice = input.nextInt(); input.nextLine();
						
						if(sChoice == 1) {
							System.out.println("=== View Cart Contents ===");
							((Customer) activeUser).viewCart();
							System.out.print("Press Enter to go back to View Shopping Cart Menu...");
							input.nextLine();
						}
						
						if(sChoice == 2) {
							System.out.println("=== Checkout ===");
							((Customer) activeUser).viewCart();
							
							while(true) {
								if(((Customer) activeUser).cart.isEmpty()) {
									System.out.print("Press Enter to go back to View Shopping Cart Menu...");
									input.nextLine();
									break;
								}
								
								System.out.print("Do you want to proceed with the checkout? (Y/N): ");
								String yn = input.nextLine();
								
								if(yn.equalsIgnoreCase("Y")) {
									orderHold.processOrder(system);
									
									for(Product p : ((Customer) activeUser).cart) {
										if(p instanceof Clothing) {
											((Clothing) p).addToOrder(orderHold);
										}else if(p instanceof Electronics) {
											((Electronics) p).addToOrder(orderHold);
										}
									}
								
									((Customer) activeUser).placeOrder(orderHold);
									((Customer) activeUser).cart.clear();
									System.out.println("Cart successfully checkout with an Order ID: " + orderHold.getOrderId());
									activeUser.setOrderPlace(activeUser.getOrderPlace() + 1);
									orderHold = new Order(String.format("O%03d", activeUser.getOrderPlace()));
									break;
								}else if(yn.equalsIgnoreCase("N")) {
									break;
								}else {
									System.out.println("The input is wrong");
								}
							}
						}
						if(sChoice == 3) {
							choice = 0;
							break;
						}
					}
					
				}
				
				if(choice == 3) {
					system.displayOrdersMenu();
					
					int oChoice = input.nextInt(); input.nextLine();
					
					if(oChoice == 1) {
						((Customer) activeUser).getOrderPlace();
						
						while(true) {
							if(((Customer) activeUser).orders.isEmpty()) {
								System.out.print("Press Enter to go back to Order Menu...");
								break;
							}
							
							System.out.print("Enter the Order ID to view details (or type 'back' to go back): ");
							String oID = input.nextLine();
							
							if(oID.equalsIgnoreCase("back")) {
								oChoice = 0;
								break;
							}
							
							int isFound = 0;
							for(Order o: ((Customer) activeUser).orders) {
								if(o.getOrderId().equalsIgnoreCase(oID)) {
									o.displayOrderInfo();;
									isFound++;
									System.out.print("Press Enter to go back to View Order History...");
									input.nextLine();
									break;
								}
							}
							if(isFound == 0) {
								System.out.println("Order ID Not Found Please Try Again");
							}else if(isFound == 1) {
								break;
							}
						}
					}

					if(oChoice == 2) {
						System.out.println("=== Track Order ===");
						while(true) {
							if(((Customer) activeUser).orders.isEmpty()) {
								System.out.print("Press Enter to go back to Order Menu...");
								break;
							}
							
							System.out.print("Enter the Order ID to track (or type 'back' to go back): ");
							String oID = input.nextLine();
							
							if(oID.equalsIgnoreCase("back")) {
								oChoice = 0;
								break;
							}
							
							int isFound = 0;
							for(Order o: ((Customer) activeUser).orders) {
								if(o.getOrderId().equalsIgnoreCase(oID)) {
									o.displayOrderInfo();;
									isFound++;
									System.out.print("Press Enter to go back to Track Order Menu...");
									input.nextLine();
									break;
								}
							}
							if(isFound == 0) {
								System.out.println("Order ID Not Found Please Try Again");
							}else if(isFound == 1) {
								break;
							}
						}
					}

					if(oChoice == 3) {
						choice = 0;
					}
				}

				if(choice == 4) {
					int cChoice = 0;
					
					while(cChoice < 1 || cChoice > 3) {
						System.out.print("\n=== Customer Menu ===\n"
								+ "1. Customer Info\n"
								+ "2. Change Customer Email\n"
								+ "3. Back to Main Menu\n"
								+ "Your Choice: ");
						cChoice = input.nextInt(); input.nextLine();
						if(cChoice < 1 || cChoice > 3) {
							System.out.println("Wrong Input");
						}
					}
					
					if(cChoice == 1) {
						system.displayCustomerInfo(activeUser.getUserID());
						System.out.print("Press Enter to back to Customer Menu...");
						input.nextLine();
					}
					
					if(cChoice == 2) {
						System.out.println("=== Update Email ===");
						System.out.println("Current Email	: " + activeUser.getEmail());
						
						while(true) {
							System.out.print("Enter the new email to update email (or type 'back' to go back): ");
							String newEmail = input.nextLine();
							
							if(newEmail.equalsIgnoreCase("back")) {
								break;
							}
							
							if(newEmail.endsWith("@email.com")) {
								activeUser.updateEmail(newEmail);
								break;
							}else {
								System.out.println("Wrong email format (ends with '@email.com')");
							}
						}
					}
					
					if(cChoice == 3) {
						choice = 0;
					}
				}

				if(choice == 5) {
					activeUser = null;
					System.out.println("");
				}
			}
		}
	}
}
