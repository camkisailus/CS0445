package cs445.hw4;

/**
 * A class that represents a category of products in a stock management system
 * @author Jon Rutkauskas
 * @author Brian Nixon
 * @version 1.0
 */
public class Category extends ArrayList{
	private ListInterface<Product> products;
	private String categoryName;


	public Category(String categoryName) {
		this.products = new ArrayList<>();
		this.categoryName = categoryName;
	}

	// returns the name of this category
	public String getCategoryName() {
		// TODO: Complete this method
		return this.categoryName;
	}

	// adds a single product to this category
	public void addProduct(Product prod) {
		// TODO: Complete this method
		products.add(prod);
	}

	// returns a product entry given a string of the product's name
	public Product findProductByName(String productName) {
		// TODO: Complete this method
		for(int i = 0; i < products.getSize(); i++){
			if(products.get(i).getItemName() == productName){
				return products.get(i);
			}
		}
		return null; 
	}

	// removes a product entry from this category and returns it
	public Product removeProductByName(String productName) {
		// TODO: Complete this method
		for(int i = 0; i < products.getSize(); i++){
			if(products.get(i).getItemName().equals(productName)){
				Product temp = products.get(i);
				products.remove(i);
				return temp;
			}
		}
		
		return null; // or throw exception?
	}

	// returns the number of products in this category
	public int getSize() {
		return products.getSize();
	}

	// returns the total number of items stocked in this category (sum of all quantities)
	public int getTotalQuantityOfStock() {
		int quant = 0;
		for(int i = 0; i < products.getSize(); i++){
			quant += products.get(i).getQuantityInStock();
		}
		return quant;
	}

	// returns the value of all products in the system: Sum(Price * Quantity) for each Product in this category
	public double getTotalValue() {
		double val = 0;
		for(int i = 0; i < products.getSize(); i++){
			val += (products.get(i).getQuantityInStock() * products.get(i).getPrice());
		}
		return val;
	}

	// returns a new List containing all products in this category.  Do not directly return the private backing List
	public ListInterface<Product> getAllProducts() {
		ListInterface<Product> all_prods = new ArrayList<>();
		for(int i = 0; i <products.getSize();i++){
			all_prods.add(products.get(i));
		}
		return all_prods;
	}

	
}