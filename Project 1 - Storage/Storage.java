/**
   Date: 02/08/19
   
   Course: CSCI 2073
   
   Description: This class is the superclass, Storage, in the Storage hierarchy of a computer.
   
   On my honor, I have neither given nor received unauthorized help while
   completing this assignment.
   
   Jeffrey Howe 
*/ 

/**
   An abstract class, Storage, intended as a superclass for the Storage hierarchy. 
   It contains the basis of storage module facts for a computer (manufacturer, model,
   price) for subclasses to utilize. Contains instance variables, methods for manipulating 
   the variables, and constructors. It also includes two abstract methods, computeSalePrice()
   to compute the price with the discount, and pricePerGB() to determine the price per gigabyte.
*/
 public abstract class Storage
 {
    
     //*****Instance variables*****
     private String manufacturer;
     private String model;
     private double listPrice;
     
     //*****Constructors*****
     
     /**
        The no-argument constructor for the Storage class. Assigns the variables to blank values
        when an object is created with this constructor.
     */
     public Storage()
     {
         manufacturer = "";
         this.model = "";
         listPrice = 0.0;
     }
     
     /**
        The argument constructor, used to assign values when creating the object variable.
        @param man the manufacturer of the drive
        @param model the model of the drive
        @param price the price of the drive
     */
     public Storage(String man, String model, double price)
     {
         manufacturer = man;
         this.model = model;
         listPrice = price;
     }
     
     //*****Methods*****
     
     //*****Setters*****
     
     /**
        Setter method for setting the manufacturer of the drive.
        @param man the manufacturer of the drive
     */
     public void setManufacturer(String man)
     {
         manufacturer = man;
     }
     
     /**
        Setter method for setting the model of the drive.
        @param model the model of the drive
     */
     public void setModel(String model)
     {
         this.model = model;
     }
     
     /**
        Setter method for setting the price of the drive.
        @param price the price of the drive
     */
     public void setPrice(double price)
     {
         listPrice = price;
     }
     
     //*****Getters*****
     
     /**
        Getter method for getting the manufacturer name.
        @return the manufacturer of the drive
     */
     public String getManufacturer()
     {
         return manufacturer;
     }
     
     /**
        Getter method for getting the model of the drive.
        @return the model of the drive
     */
     public String getModel()
     {
         return this.model;
     }
     
     /**
        Getter method for getting the price of the drive.
        @return the price of the drive
     */
     public double getPrice()
     {
         return listPrice;
     }
     
     //*****Additional Methods*****
    
     /**
        toString method for printing the instance variables.
        @return the manufacturer, model, and price stored in result
     */
     public String toString()
     {
         String result = "\nManufacturer: " + manufacturer + 
         				"\nModel: " + this.model + 
         				"\nPrice: $" + listPrice;
         return result;
     }
     
     /**
        Abstact methods that must be overriden in subclasses that use the Storage class.
     */
     public abstract double computeSalePrice(); //computes sale price, depending on subclass specifics, in the subclass
     public abstract double pricePerGB(); //computes price per gigabyte in the subclass
}
