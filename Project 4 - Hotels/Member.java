/**
   Date: 04/30/19
  
   Course: CSCI 2073
   
   Description: A class that was created to work along-side the Hotels class;
   stores data of members in objects for easy access and efficient storage.
      
   On my honor, I have neither given nor received unauthorized help while
   completing this assignment.
   
   Jeffrey Howe        
*/

/**
 * The Member class to be used with the Hotels class; creates an object that holds
 * the data of a member when registered to a hotel.
 *
 * @author Jeffrey Howe
 */
public class Member
{
   // Instance variables
   private String memCode;
   private String memName;
   private String memCategory;
   private String memID;

   /**
    * Constructor that initializes the instance variables
    * @param code the hotel code
    * @param lastName the person's last name
    * @param firstName the person's first name
    * @param cat the person's category
    * @param id the person's id
    */   
   public Member(String code, String lastName, String firstName, String cat, String id)
   {
      memCode = code;
      memName = firstName + " " + lastName;
      memCategory = cat;
      memID = id;
   }

   /**
    * No-argument constructor that initializes a blank object
    */   
   public Member()
   {
      memCode = "";
      memName = "";
      memCategory = "";
      memID = "";
   }
   
   //*****Setters*****
   
   /**
    * Method that sets the hotel's code
    * @param code the hotel's code
    */ 
   public void setCode(String code)
   {
      memCode = code;
   }
   
   /**
    * Method that sets the person's name
    * @param name the person's name
    */ 
   public void setName(String name)
   {
      memName = name;
   }
   
   /**
    * Method that sets the person's category
    * @param the person's category
    */ 
   public void setCategory(String cat)
   {
      memCategory = cat;
   }
   
   /**
    * Method that sets the person's id
    * @param the person's id
    */ 
   public void setID(String id)
   {
      memID = id;
   }
   
   //*****Getters*****
   
   /**
    * Method that returns the hotel's code
    * @return the hotel's code
    */ 
   public String getCode()
   {
      return memCode;
   }
   
   /**
    * Method that returns the person's name
    * @return the person's name
    */ 
   public String getName()
   {
      return memName;
   }
   
   /**
    * Method that returns the person's category
    * @return the person's category
    */ 
   public String getCategory()
   {
      return memCategory;
   } 
   
   /**
    * Method that returns the person's ID
    * @return the person's ID
    */ 
   public String getID()
   {
      return memID;
   }
   
   /**
    * toString method that returns the output of the instance variables
    * @return the output of the instance variables on the same line
    */ 
   public String toString()
   {
      return memCode + " " + memName + " " + memCategory + " " + memID;
   }
}