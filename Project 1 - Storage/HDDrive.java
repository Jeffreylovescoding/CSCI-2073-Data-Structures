/**
   Date: 02/08/19
  
   Course: CSCI 2073
   
   Description: This class is the subclass for Hard Disk Drives of computers in the Storage hierarchy.
   
   On my honor, I have neither given nor received unauthorized help while
   completing this assignment.
   
   Jeffrey Howe    
*/

/**
   A subclass HDDrive, that extends the Storage superclass. It inherits superclass methods/constructors,
   and contains some of its own variables, constructors, and methods. It also overrides the computeSalePrice()
   and pricePerGB() methods.
*/
public class HDDrive extends Storage
{

    //*****Instance variables*****
    private int capacity;
    private int speed;
    private int cache;
    
    //*****Constructors*****
    
    /**
       The no-argument constructor for the HDDrive class. Assigns the variables to blank values
       when an object is created with this constructor.
    */
    public HDDrive()
    {
        super();
        capacity = 0;
        speed = 0;
        cache = 0;  
    }
    
    /**
       The argument constructor, used to assign values when creating the object variable.
       @param man the manufacturer of the drive
       @param model the model of the drive
       @param price the price of the drive
       @param cap the capacity of the drive
       @param speed the speed of the drive
       @param cache the cache size of the drive
    */
    public HDDrive(String man, String model, double price, int cap, int speed, int cache)
    {
        super(man, model, price);
        capacity = cap;
        this.speed = speed;
        this.cache = cache;
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
       Setter method for setting the speed of the drive
       @param speed the speed of the drive
    */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    
    /**
       Setter method for setting the cache size of the drive
       @param cache the speed of the cache
    */
    public void setCache(int cache)
    {
        this.cache = cache;
    }
    
    //*****Getters*****
    
    /**
       Getter method for getting the capacity of the drive.
       @return the capacity of the drive
    */ 
    public int getCapacity() 
	 {
		  return capacity;
	 }
    
    /**
       Getter method for getting the speed of the drive.
       @return the speed of the drive
    */
    public int getSpeed()
    {
        return speed;
    }
    
    /**
       Getter method for getting the cache size of the drive.
       @return the cache size of the drive
    */
    public int getCache()
    {
        return cache;
    }

    //*****Additional Methods*****
    
    /**
       Method that calculates the total sale price from the list price and discounted effects.
       Each drive always gets $5 off, and increases $5 for every factor of 50.
       @return the total sales price after calculations.
    */
    public double computeSalePrice()
    {
        double discount = Math.ceil(getPrice() / 50);
        double salePrice = getPrice() - (5 * discount);
        return salePrice;
    }
    
    /**
       pricePerGB method that calculates the price that each gigabyte costs.
       @return the cost of each gigabyte.
    */
    public double pricePerGB()
    {
        double price = getPrice() / (capacity * 1000);
        return price;
    }
    
    /**
       toString method for the HDDrive class. Calls the superclass's toString and adds on output for the
       instance variables of the subclass. String.format formats the string to look nicer.
       @return the instance variables' values formatted into output
    */
    public String toString()
    {
        return super.toString() + String.format("\nCapacity: %d TB\nSpeed: %d RPM\nCache size: %d MB\nPrice per GB: $%.2f", capacity, speed, cache, pricePerGB()); 
    }
    
}