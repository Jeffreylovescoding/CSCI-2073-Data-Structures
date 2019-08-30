/**
   Date: 02/08/19
   
   Course: CSCI 2073
   
   Description: This class is the subclass for Solid State Drives of computers in the Storage hierarchy.
   
   On my honor, I have neither given nor received unauthorized help while
   completing this assignment.
   
   Jeffrey Howe      
*/

/**
   A subclass, SSDrive, that extends the Storage superclass. It inherits superclass methods/constructors,
   and contains some of its own variables, constructors, and methods. It also overrides the computeSalePrice()
   and pricePerGB() methods.
*/
public class SSDrive extends Storage
{

    //*****Instance variables*****
    private int capacity;
    private String type;
    
    //*****Constructors*****
    
    /**
       The no-argument constructor for the SSDrive class. Assigns the variables to blank values
       when an object is created with this constructor.
    */
    public SSDrive()
    {
       super();
       capacity = 0;
       type = "";
    }
 
    /**
       The argument constructor, used to assign values when creating the object variable.
       @param man the manufacturer of the drive
       @param model the model of the drive
       @param price the price of the drive
       @param cap the capacity of the drive
       @param type the type of drive 
    */    
    public SSDrive(String man, String model, double price, int cap, String type)
    {
       super(man, model, price);
       capacity = cap;
       this.type = type;
    }
   
    //*****Methods*****
    
    //*****Setters*****
    
    /**
       Setter method for setting the capacity of the drive
       @param cap the capacity of the drive
    */
	 public void setCapacity(int cap) 
	 {
	 	 capacity = cap;
	 }
    
    /**
       Setter method for setting the type of drive
       @param type the type of drive
    */
	 public void setType(String type) 
	 {
		 this.type = type;
	 }
    
    //*****Getters*****
    
    /**
       Getter method for getting the capacity of the drive
       @return the capacity of the drive
    */
	 public int getCapacity() 
	 {
		 return capacity;
	 }    
    
    /**
       Getter method for getting the type of drive
       @return the type of drive
    */
	 public String getType() 
	 {
		 return type;
	 }
    
    //*****Additional Methods*****
    
    /**
       Method that calculates the total sale price from the list price and discounted effects.
       External drives get 30% off, internal drives get 20% off.
       @return the total sales price after calculations.
    */
	 public double computeSalePrice()
	 {
		 double salesPrice = 0.0;
       double totalPrice = 0.0;
		 if(type.equals("external"))
		 {
			  salesPrice = getPrice() * 0.3;
           totalPrice = getPrice() - salesPrice;
		 }
		 else if(type.equals("internal"))
		 {
			  salesPrice = getPrice() * 0.2;
           totalPrice = getPrice() - salesPrice;
		 }
		 return totalPrice;
	 }
    
    /**
       pricePerGB method that calculates the price that each gigabyte costs.
       @return the cost of each gigabyte
    */
	 public double pricePerGB()
	 {
		 double price = getPrice() / capacity;
		 return price;
	 }
    
    /**
       toString method for the SSDrive class. Calls the superclass's toString and adds on output for the
       the instance variables of the subclass. String.format formats the string to look nicer.
       @return the instance variables' values formatted into output
    */
	 public String toString()
	 {
		 return super.toString() + String.format("\nCapacity: %d GB\nType of drive: %s\nPrice per GB: $%.2f", capacity, type, pricePerGB());
	 }
}