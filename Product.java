package osu.cse2123;


/**
 * A simple class for holding product information.
 *
 * @author Charlie Britt
 * @version 10/23/2023
 */

public class Product {

    // Private member variables go here - you will need to add them yourself.
	private String name;
	private String type;
	private String code;
	private int ppu;
    /**
     * Product constructor - creates a default product with no name, no type, no inventory code, and no price
     * @postcond a product object with no name or type or inventory code, and no price
     */
    public Product() {
    	//constructor variables
    	this.name = "";
    	this.type = "";
    	this.code = "";
    	this.ppu = 0;
    }

    /**
     * sets the name of the product object
     *
     * @param name new name for the product
     * @postcond product name is now name
     */
    public void setName(String name) {
    	//sets name
    	this.name = name;
    }

    /**
     * returns the name of the product
     *
     * @return the name of the product
     */
    public String getName() {
    	//gets name
        return this.name;
    }

    /**
     * sets the type of the product
     *
     * @param type the type of the product
     * @postcond sets the type of the product to type
     */
    public void setType(String type) {
    	//sets type
    	this.type = type;
    }

    /**
     * returns the type of the product
     *
     * @return - the product type
     */
    public String getType() {
        //gets type
    	return this.type;
    }

    /**
     * sets the price of the product in cents. A $10 product will have a price
     * of 1000.
     *
     * @param price the price of the product
     * @postcond price of the product set to price
     */
    public void setPrice(int price) {
    	//sets price
    	this.ppu = price;
    }

    /**
     * returns the price of the product in cents.
     *
     * @return the price of the product in cents
     */
    public int getPrice() {
        //gets price
    	return this.ppu;
    }

  

    /**
     * sets the inventory code for this product
     *
     * @param code the new inventory code for the product
     * @postcond the inventory code for the product set to code
     */
    public void setInventoryCode(String code) {
		//sets inventory code
    	this.code = code;
    }

    /**
     * returns the inventory code for this product
     *
     * @return the inventory code of the product
     */
    public String getInventoryCode() {
        //gets inventory code
    	return this.code;
    }

    /**
     * returns the String representation of this object
     * (See project write-up for details)
     *
     * @return the String representation of this object
     */
    @Override
    public String toString() {
    	//prints formatted string based on product
    	return String.format("(%s, %s, %s, %d)", this.name, this.code, this.type, this.ppu);
    }
    
    /**
     * returns true if other equals this, false otherwise.
     * 
     * @return true if other has the same value as this, false otherwise
     */
    @Override
    public boolean equals(Object other) {
		boolean equal = false;
		//true if they are the same object
		if(other == this) {
			equal = true;
		//otherwise true if name, code, type, and price are the same
		} else if (other instanceof Product) {
			Product localOther = (Product) other;
			equal = this.name.equals(localOther.name) &&
					this.code.equals(localOther.code) &&
					this.type.equals(localOther.type) &&
					this.ppu == (localOther.ppu);
		}
    	return equal;
    }
    
    /**
     * returns an integer value for hashing
     * (See the project write-up for details)
     * 
     * @return an integer value suitable for use in a HashMap or HashSet
     */
    @Override
    public int hashCode() {
    	//creates hash code based on component pieces.
    	int code = this.name.hashCode() + this.code.hashCode() + this.type.hashCode() + this.ppu; 
    	return code;
    }
}