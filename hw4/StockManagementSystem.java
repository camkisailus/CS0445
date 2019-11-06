package cs445.hw4;

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 * A class that represents a stock management system
 * @author Jon Rutkauskas
 * @author Brian Nixon
 * @version 1.0
 */
 public class StockManagementSystem extends ArrayList{
	public ListInterface<Category> categories;

	// constructor.  instantiates categories with an ArrayList
	public StockManagementSystem() {
		// TODO: Complete this method
		this.categories = new ArrayList<Category>();
	}

	// creates a product and adds it to an existing category
	public void createAndAddProduct(String categoryName, String productName, int quantity, double price) {
		// TODO: Complete this method
		Product prod = new Product(productName, quantity, price);
		for(int i = 0;i<categories.getSize();i++){
			if(categories.get(i).getCategoryName().equals(categoryName)){
				categories.get(i).addProduct(prod);
			}
		}
	}

	// returns the number of items (Sum of all quantities) in a category
	public int getNumberOfStockedItemsInCategory(String categoryName) {
		int quant = 0;
		for(int i = 0;i<categories.getSize();i++){
			if(categories.get(i).getCategoryName().equals(categoryName)){
				quant += categories.get(i).getTotalQuantityOfStock();
			}
		}
		return quant;
	}

	// returns the number of stocked items (sum of all quantities) across multiple categories (given as a list)
	public int getNumberOfStockedItemsInCategories(ListInterface<String> categoryNames) {
		int quant = 0;
		for(int i = 0;i<categories.getSize();i++){
			for(int j = 0; j<categoryNames.getSize();j++){
				if(categories.get(i).getCategoryName().equals(categoryNames.get(j))){
					quant += categories.get(i).getTotalQuantityOfStock();
				}
			}	
		}
		return quant;
	}

	// returns the stocked quantity of a specific item
	// Search each category for the produtName
	// When found, get the quantity of proudctName
	public int getQuantityOfItemByName(String productName) {
		for(int i = 0; i<categories.getSize();i++){
			Category cat = categories.get(i);
			ArrayList<Product> prods = (ArrayList<Product>) cat.getAllProducts();
			for(int j = 0; j<prods.getSize();j++) {
				if(prods.get(j).getItemName().equals(productName)) {
					return prods.get(j).getQuantityInStock();
				}
			}
		}
		return 0;
	}

	// sets the stocked quantity of a specific item
	public void setQuantityOfItemByName(String productName, int newQuantity) {
		for(int i = 0; i<categories.getSize();i++){
			Category cat = categories.get(i);
			ArrayList<Product> prods = (ArrayList<Product>) cat.getAllProducts();
			for(int j = 0; j<prods.getSize();j++) {
				if(prods.get(j).getItemName().equals(productName)) {
					prods.get(j).setQuantityInStock(newQuantity);
				}
			}
		}
	}

	// removes a product from the system
	public Product removeProductByName(String productName) { 
		for(int i = 0; i<categories.getSize();i++){
			Category cat = categories.get(i);
			ArrayList<Product> prods = (ArrayList<Product>) cat.getAllProducts();
			for(int j = 0; j<prods.getSize();j++) {
				if(prods.get(j).getItemName().equals(productName)) {
					return cat.removeProductByName(productName);
				}
			}
		}
		return null;
	}

	// removes a category from the system
	public Category removeCategoryByName(String categoryName) {
		for(int i = 0;i<categories.getSize();i++){
			if(categories.get(i).getCategoryName().equals(categoryName)){
				Category cat = categories.get(i);
				categories.remove(i);
				return cat;
			}
		}
		return null;
	}

	// creates and adds new category
	public void createCategory(String categoryName) {
		Category new_cat = new Category(categoryName);
		categories.add(new_cat);
	}

	// calculates and returns the total value of all items in a given category
	public double totalValueOfItemsInCategory(String categoryName) {
		double val = 0;
		for(int i = 0;i<categories.getSize();i++){
			if(categories.get(i).getCategoryName().equals(categoryName)) {
				val = categories.get(i).getTotalValue();
			}
		}
		return val;
	}


	// a scanner to get input from the user
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		//interactivity

		StockManagementSystem s = new StockManagementSystem();
		preStock(s);
		int selection = -1;

		while (selection != 0) {
			System.out.println("\n=================================");
			System.out.println("Stock Management System Main Menu");
			System.out.println("1. Print stocked items");
			System.out.println("2. Create category");
			System.out.println("3. Create Product");
			System.out.println("4. Delete Product");
			System.out.println("5. Delete Category");
			System.out.println("6. Manage Quantity of Item");
			System.out.println("7. Get amount of items stocked in multiple categories");
			System.out.println("8. Get Total Value of all products in a category");
			System.out.println("0. Quit");
			System.out.print("Selection: ");

			try {
				selection = input.nextInt();
			} catch (NoSuchElementException e) {
				selection = -1;
			} catch (IllegalStateException e) {
				selection = -1;
			}
			input.nextLine();

			switch (selection) {
				case 1:
					print(s);
					break;
				case 2:
					createCat(s);
					break;
				case 3:
					createProd(s);
					break;
				case 4:
					deleteProd(s);
					break;
				case 5:
					deleteCat(s);
					break;
				case 6:
					setQty(s);
					break;
				case 7:
					getQtyAcrossCats(s);
					break;
				case 8:
					getPriceCategory(s);
					break;
				case 0:
					break;
				default:
					// Invalid, just ignore and let loop again
					break;
			}
		}
	}
// interactive functions implemented for students
	private static void createCat(StockManagementSystem s) {
		System.out.print("New Category Name: ");
        String name = input.nextLine();
		s.createCategory(name);

	}


	private static void createProd(StockManagementSystem s) {
		System.out.print("Category to add Product into: ");
        String cat = input.nextLine();
		System.out.print("New Product Name: ");
        String name = input.nextLine();
		System.out.print("Quantity of this item: ");
		int qty = input.nextInt();
		input.nextLine();
		System.out.print("Price of this item: ");
		double price = input.nextDouble();
		input.nextLine();
		s.createAndAddProduct(cat, name, qty, price);

	}

	private static void deleteProd(StockManagementSystem s){
		
		System.out.print("Name of Product to Delete: ");
        String name = input.nextLine();
		Product p = s.removeProductByName(name);
		System.out.println("Removed: " + p);
	}

	private static void deleteCat(StockManagementSystem s){
		System.out.print("Name of Category to Delete: ");
        String name = input.nextLine();
		
		Category c = s.removeCategoryByName(name);
		System.out.println("Removed " + name + " category with " + c.getTotalQuantityOfStock() + " items");
	}


	private static void setQty(StockManagementSystem s) {
		System.out.print("Product Name: ");
        String name = input.nextLine();
		int i = s.getQuantityOfItemByName(name);
		System.out.println("Current quantity of " + name + " is: " + i);
		System.out.print("Enter new quantity of this item (leave blank to leave unchanged): ");
		String str = input.nextLine();
		if(!str.equals("")){
			int qty = Integer.parseInt(str);
			s.setQuantityOfItemByName(name, qty);
		}
		
	}

	private static void getQtyAcrossCats(StockManagementSystem s) {
		ListInterface<String> list = new ArrayList<String>();
		String str = "initialString";
		System.out.println("Enter names of categories, pressing ENTER after each category; Type 'done' to finish: ");
		while(!str.equals("done")) {
			str = input.nextLine();
			if(!str.equals("done"))
				list.add(str);
		}
		int qty = s.getNumberOfStockedItemsInCategories(list);
		System.out.println("Total number of items stocked in those categories is: " + qty);
	}

	private static void print(StockManagementSystem s) {
		System.out.println("=========== All Items ===========");
		for(int i = 0; i < s.categories.getSize(); i++) {
			Category c = s.categories.get(i);
			System.out.println(c.getCategoryName() + " (" + c.getTotalQuantityOfStock() + " items):");
			ListInterface<Product> list = c.getAllProducts();
			for(int j = 0; j < list.getSize(); j++) {
				Product p = list.get(j);
				System.out.println("\t" + (j+1) + ". " + p.getItemName() + " (" + p.getQuantityInStock() + " @ $" + p.getPrice() + " each) ");
			}
		}
	}

	private static void getPriceCategory(StockManagementSystem s) {
		System.out.print("Name of Category: ");
        String name = input.nextLine();
		double d = s.totalValueOfItemsInCategory(name);
		System.out.println("Value of all items in " + name + ": $" + d);
	}

	private static void preStock(StockManagementSystem s) {
		System.out.print("Do you want to pre-stock the system with default values? (y/n): ");
		String entered = input.nextLine();
		if(entered.equals("y")) {
			Category food = new Category("food");
			Product chips = new Product("chips", 13, 1.49);
			Product apple = new Product("apple", 38, 0.79);
			Product soup = new Product("soup", 18, 1.99);
			Product bread = new Product("bread", 20, 1.87);
			food.addProduct(chips);
			food.addProduct(apple);
			food.addProduct(soup);
			food.addProduct(bread);
			s.categories.add(food);

			Category bev = new Category("beverage");
			Product beer = new Product("beer", 36, 5.99);
			Product wine = new Product("wine", 14, 9.99);
			Product vodka = new Product("vodka", 33, 16.50);
			Product oj = new Product("orange juice", 19, 2.49);
			Product coffee = new Product("coffee", 8, 3);

			bev.addProduct(beer);
			bev.addProduct(wine);
			bev.addProduct(vodka);
			bev.addProduct(oj);
			bev.addProduct(coffee);
			s.categories.add(bev);

			Category elec = new Category("electronics");
			elec.addProduct(new Product("mini-dp to hdmi", 2, 13.99));
			elec.addProduct(new Product("cd player", 8, 19.99));
			elec.addProduct(new Product("cell phone", 5, 99));
			elec.addProduct(new Product("tv remote", 6, 13));
			elec.addProduct(new Product("lamp", 19, 10));
			s.categories.add(elec);
		}
	}



}