/**
   Date: 04/30/19
  
   Course: CSCI 2073
   
   Description: A class that processes through a CSV file of hotels and a txt file that contains
   a command and information to use based on that command.
      
   On my honor, I have neither given nor received unauthorized help while
   completing this assignment.
   
   Jeffrey Howe     
*/

import java.util.*;
import java.io.*;

/**
 * The Hotels class to create a hotel listing and process commands relative to them.
 *
 * @author Jeffrey Howe
 */
public class Hotels
{
   // Declaring abstract data structures to use throughout the program
   HashMap<String, String> hotels;        // map to store the hotels with their code
   HashMap<String, Set<Member>> hotelAct; // map to store respective hotel with a set of Member objects
   Set<Member> members;                   // set to hold the arbitrary set of objects of the hotel in the hotelAct ADT to manipulate the objects in said set
   Set<String> codes;                     // set to hold the codes that were used for the hotels
   
   /**
    * Constructor for the Hotels class; initializes the ADTs and reads in the CSV file containing the hotels and their codes
    * @param csvFile the CSV file to be processed
    */
   public Hotels(String csvFile) throws FileNotFoundException
   {
      hotels = new HashMap<String, String>();
      hotelAct = new HashMap<String, Set<Member>>();
      members = new HashSet<Member>();
      codes = new HashSet<String>();
      File file = new File(csvFile);
      Scanner in = new Scanner(file);
      while(in.hasNextLine())
      {
         String lineData = in.nextLine();
         String hotelCode = lineData.substring(0, 1);
         String hotelName = lineData.substring(2);
         hotels.put(hotelCode, hotelName);
         hotelAct.put(hotelName, new HashSet<Member>());
         codes.add(hotelCode);
      }
   }
   /**
    * Method that reads in and processes through a data file of commands and information
    * @param dataFile the file of commands and information to be processed
    * @return the result of all of the commands after being processed
    */   
   public String process(String dataFile) throws FileNotFoundException
   {
      String output = "";
      File file = new File(dataFile);
      Scanner in = new Scanner(file);
      try
      {   
         while(in.hasNextLine())
         {
            String command = in.next(); 
            
            //Initiates if the first word of the line in the file is ADD
            if(command.equals("ADD"))
            {
               String hotelCode = in.next();
               String lastName = in.next();           
               String firstName = in.next();            
               String memCategory = in.next();           
               String memID = in.next();            
               Member tempMember = new Member();
               
               tempMember = searchMember(memID); //assigns the returned member object to a temporary member object
               
               //Checks to see if member is already registered at given hotel  
               if(tempMember.getCode().equals(hotelCode) && tempMember.getID().equals(memID)) 
               {
                  output += "ERROR: " + memID + " already registered at the " + hotels.get(hotelCode) + "\n";
                  continue;
               }
               
               //Checks to see if member is assigned to a different hotel than the given one
               if(!tempMember.getCode().equals(hotelCode) && tempMember.getID().equals(memID))
               {
                  deleteMember(memID); //uses deleteMember method to delete member from the other hotel
               }
               addMember(hotelCode, lastName, firstName, memCategory, memID); //uses addMember method to add member to the hotel set
               output += memID + " added to the " + hotels.get(hotelCode) + "\n";
            }
            //Initiates if the first word of the line in the file is DELETE
            else if(command.equals("DELETE"))
            {
               String memID = in.next();
               Member tempMember = new Member();
               tempMember = searchMember(memID); //assigns the returned member object to a temporary member object
               if(tempMember.getID().equals(memID)) // Checks to see if member is registered to -any- hotel
               {
                  deleteMember(memID); //uses deleteMember method to delete member from the hotel given
                  output += memID + " deleted from the " + hotels.get(tempMember.getCode()) + "\n";
               }   
               else
               {
                  output += "ERROR: " + memID + " not registered\n";
               }
            }
            //Initiates if the first word of the line in the file is SUMMARY
            else if(command.equals("SUMMARY"))
            {
               String hotelCode = in.next();
               String hotelName = hotels.get(hotelCode);
               members = hotelAct.get(hotelName);
               //members.size() is the number of objects in the set (number of guests); hotelFees method to calculate fees based on the user's category.
               output += String.format("%s: %d guest(s) with a total of $%.2f\n", hotelName, members.size(), hotelFees(hotelCode));
            }
            //Initiates if the first word of the line in the file is SEARCH
            else if(command.equals("SEARCH"))
            {
               String memID = in.next();
               Member tempMember = new Member();
               tempMember = searchMember(memID); //assigns the returned member object to a temporary member object
               if(tempMember.getID().equals(memID))
               {
                  output += memID + ": " + tempMember.getName() + " at the " + hotels.get(tempMember.getCode()) + "\n";
               }
               else
               {
                  output += "ERROR: " + memID + " not registered\n";
               }            
            }  
         } 
      }
      catch(NoSuchElementException e) //executes in case of a white space at the end of the file
      {
         System.out.print("");
      }
      return output;
   } 
   
   /**
    * Method that adds a member object to the set of the given hotel
    * @param hotel the hotel's code
    * @param last the last name of the person
    * @param first the first name of the person
    * @param cate the category of the person
    * @param id the id of the person
    */   
   public void addMember(String hotel, String last, String first, String cate, String id)
   {
      String hotelName = hotels.get(hotel);
      members = hotelAct.get(hotelName);
      members.add(new Member(hotel, last, first, cate, id));
      hotelAct.put(hotelName, members);
   }
   
   /**
    * Method that searches for a member throughout the hotels' sets
    * @param id the person's id 
    * @return the member object that holds the id
    */ 
   public Member searchMember(String id)
   {
      Member member = new Member();
      boolean flag = false;
      for(String code : codes) //uses all the hotel codes to search through every hotel
      {
         String hotelName = hotels.get(code);
         members = hotelAct.get(hotelName);
         for(Member tempMember : members) //searches through all the objects in the set
         {
            if(id.equals(tempMember.getID()))
            {
               member = tempMember;
               flag = true;
               break; //breaks inner loop if found
            }
         }
         if(flag)
         {
            break; //breaks outer loop if found
         }
      }
      return member;
   }
   
   /**
    * Method that deletes a member's registry from a hotel
    * @param id the person's id
    */ 
   public void deleteMember(String id)
   {
      Member tempMember = new Member();
      tempMember = searchMember(id);
      String hotelName = hotels.get(tempMember.getCode());
      members = hotelAct.get(hotelName);
      members.remove(tempMember);
      hotelAct.put(hotelName, members);
   }
   
   /**
    * Method that calculates the total hotel fees of a singular hotel
    * @param hotelCode the code of the hotel
    * @return the total fees that members have at the given hotel
    */ 
   public double hotelFees(String hotelCode)
   {
      double totalFees = 0;
      for(Member tempMember : members)
      {
         if(tempMember.getCategory().equals("O"))
         {
            totalFees += 100;
         }
         else if(tempMember.getCategory().equals("F"))
         {
            totalFees += 150;
         }
         else if(tempMember.getCategory().equals("S"))
         {
            totalFees += 50;
         }
      }
      return totalFees;
   }
}