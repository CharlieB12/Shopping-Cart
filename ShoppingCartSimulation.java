package osu.cse2123;
/**
 * A simple program that implements an interactive shopping cart.
 *
 * @author Charlie Britt
 * @version 10/26/2023
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShoppingCartSimulation {
	
	/**
	 * Loads a List of Product objects from a file (format given in the
	 * project write-up).  You should assume that the file is correctly
	 * formatted.
	 * 
	 * @param fname name of properly formatted product file to load data from
	 * @precond fname is a properly formatted file as given in the assignment
	 * @return List of Product objects as read from the file
	 */
	public static List<Product> loadProductsFromFile(String fname) throws FileNotFoundException {
		File textFile = new File(fname);
		Scanner scan = new Scanner(textFile);
		ArrayList<Product> product_list = new ArrayList<>();
		
		//loops through each line
		while(scan.hasNext()) {
			String line = scan.nextLine();
			//splits line based on comma
			String[] prod_arr = line.split(",");
			//new product to assign line to
			Product prod = new Product();
			
			//creates product based on array
			prod.setName(prod_arr[0]);
			prod.setInventoryCode(prod_arr[1]);
			prod.setPrice(Integer.parseInt(prod_arr[2]));
			prod.setType(prod_arr[3]);
			
			product_list.add(prod);
			
		}
		
		return product_list;
	}
	
	/**
	 * Prints the choices for the user to choose from to the screen
	 * (See the project write-up for details)
	 * 
	 * @param prods List of Product objects to display for purchase
	 */
	public static void displayChoices(List<Product> prods) {
		//count variable to store item index
		int count = 1;
		//loops through each product
		for (Product prod : prods) {
			//formats string based on product
			double price = prod.getPrice();
			String name = prod.getName();
			System.out.println(String.format("%-7d %-36s %.2f",count, name, price/100));
			count+=1;
		}
		System.out.println();
	}
	
	/**
	 * Prints the current contents of the shopping cart
	 * (See the project write-up for details)
	 * 
	 * @param cart a Map of (Product,Integer) where the key is the product
	 *         in the cart and the value is the quantity of that product
	 *         in the cart.
	 */
	public static void displayCart(Map<Product, Integer> cart) {
		//loops through map
		for(Map.Entry<Product, Integer> entry: cart.entrySet()) {
			//assigns product properties
			String name = entry.getKey().getName();
			int count = entry.getValue();
			double ppu = entry.getKey().getPrice();
			double price = ppu/100;
			double total = price * count;
			//prints formatted cart
			System.out.println(String.format("%-40s %d x %.2f %10.2f", name, count, price, total));
		}
	}
	
	
	/**
	 * gets the total price of products in cart.
	 * 
	 * @param prods
	 * @return the total price of products in cart
	 */
	public static double getTotal(Map<Product, Integer> cart){
		double total = 0;
		for(Map.Entry<Product, Integer> entry: cart.entrySet()) {
			total += entry.getKey().getPrice() * entry.getValue();
		}
		double total_dollar = total/100;
		return total_dollar;
	}
	
	
	
	/**
	 * gets user input and adds product to map with total price and num of products.
	 * 
	 * @param cart Map of Cart
	 * @param products List of Products
	 * @throws InterruptedException 
	 */
	public static void promptUser(Map<Product, Integer> cart, List<Product> products) {
		Scanner scan = new Scanner(System.in);
		displayChoices(products);
		
		boolean bool = true;
		
		//loops while 0 is not entered by user
		while(bool) {
			System.out.print("Enter something to add to the cart (0 to quit): ");
			int item_num = scan.nextInt();
			if(item_num > 0) {
				System.out.print("Enter a quantity: ");
				int quant = scan.nextInt();
				System.out.println();
				//adds prodcut and amount to cart map
				cart.put(products.get(item_num-1), quant);
				//displays cart
				displayCart(cart);
				//formats the total inside cart
				System.out.println(String.format("Total cost: %10.2f", getTotal(cart)));
				System.out.println();
				
				//enter to continue. used two nextLines because one gets caught by
				//the enter after quant above. Don't know why
				System.out.println("Hit ENTER to continue");
				scan.nextLine();
				scan.nextLine();
				//displays product list
				displayChoices(products);
				
			//ran if 0 is entered
			} else {
				System.out.println();
				displayCart(cart);
				System.out.println(String.format("Total cost: %10.2f", getTotal(cart)));
				bool = false;
			}
		}
		scan.close();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter an input file: ");
		String file = scan.nextLine();
		//cart map and product list
		Map<Product, Integer> cart = new HashMap<>();
		List<Product> products = loadProductsFromFile(file);
		
		//runs the cart logic
		promptUser(cart, products);
		
		scan.close();
		
		
		
	}

}
