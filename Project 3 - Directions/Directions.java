/**
   Date: 03/30/19
  
   Course: CSCI 2073
   
   Description: 
   
   On my honor, I have neither given nor received unauthorized help while
   completing this assignment.
   
   Jeffrey Howe        
*/

import java.util.*;
import java.io.*;

/**
 * The Directions class to display directions from user prompts.
 * 
 * @author Jeffrey Howe
 */
public class Directions
{
   //ADT declarations
   StackInt<Direction> myStack;
   Queue<Direction> myQueue;
   
   // Constructors
   /**
    * Creates a Directions object and initializes the ADTs
    */
   public Directions()
   {
      myStack = new LinkedStack<>();
      myQueue = new LinkedList<>();
   }
   
   // Methods
   
   /**
    * Reading the file into the Data Structures
    * @param filename The name of the file to be read in
    * @return true if the file is read in, otherwise false
    */
   public boolean readFile(String filename)
   {
      try 
      {
         File file = new File(filename); // Exception thrown if file not found
         Scanner in = new Scanner(file);
         while(in.hasNextLine())
         {
            double miles = in.nextDouble();
            String direction = in.next();
            String street = in.nextLine();            
            if(myStack.empty() || !myStack.peek().getDirection().equals(direction)) // Filter out bogus directions
            {
               myStack.push(new Direction(miles, direction, street)); // Create objects directly into the stack
               myQueue.add(myStack.peek()); // Copy objects from stack into queue
            }
         }
         return true;
      }
      catch(FileNotFoundException e) // Exception handling if file not found
      {
         System.out.println("ERROR: The file was not found.");
         return false;
      }
   }
   
    /**
    * Creating the set of instructions from the source to destination using two queues
    * @return The number of miles traveled on the route
    */  
   public double getDirections()
   {  
      double milesTraveled = 0;
      Queue<Direction> temp = new LinkedList<>();
     
      while(myQueue.peek() != null)
      {  
          if(milesTraveled == 0) // Start of route; see Direction class for documentation on translateDir method
          { 
             System.out.println("Travel " + myQueue.peek().getMiles() + " mile(s) " + myQueue.peek().translateDir(myQueue.peek().getDirection()) + " on " + myQueue.peek().getStreet());
          }
          else // Rest of route; see Direction class for documentation on translateDir method
          {
             System.out.println("Turn " + Direction.translateDir(myQueue.peek().getDirection()) + " and travel " + myQueue.peek().getMiles() + " mile(s) on " + myQueue.peek().getStreet());
          }
          milesTraveled += myQueue.peek().getMiles();
          temp.add(myQueue.remove()); // Transfer data to other queue after iteration
      }
      while(temp.peek() != null) 
      {
         myQueue.add(temp.remove()); // Return the objects to the original Queue
      }
      return milesTraveled;
   }  
   
   /**
    * Creating the set of instructions from the destination to the source using two Stacks
    * @return The number of miles traveled on the route
    */
   public double returnTrip()
   {
      double milesTraveled = 0;
      StackInt<Direction> temp = new LinkedStack<>();
      
      while(!myStack.empty())
      {
         if(milesTraveled == 0) // Start of the route; see Direction class for documentation on reverseDir method
         {
            System.out.println("Travel " + myStack.peek().getMiles() + " mile(s) " + Direction.reverseDir(myStack.peek().getDirection()) + " on " + myStack.peek().getStreet());
         }
         else // Rest of the route; see Direction class for documentation on reverseDir method
         {
            System.out.println("Turn " + Direction.reverseDir(myStack.peek().getDirection()) + " and travel " + myStack.peek().getMiles() + " mile(s) on " + myStack.peek().getStreet());
         }
         milesTraveled += myStack.peek().getMiles();
         temp.push(myStack.pop()); // Transfer data to other stack after iteration
      } 
      while(!temp.empty())
      {
         myStack.push(temp.pop()); // Return the objects to the original Stack
      }
      return milesTraveled;
   }
   
   /**
    * Creating the set of instructions from the source to the deestination not exceeding the
    * max distance using two Stacks
    * @param maxDistance The maximum amount of miles to travel in a route
    * @return The number of miles traveled on the route
    */
   public double getDirections(double maxDistance)
   {
      double milesTraveled = 0;
      StackInt<Direction> temp = new LinkedStack<>();
      while(!myStack.empty())
      {
         temp.push(myStack.pop());
      }      
      while(!temp.empty())
      {  
         if(milesTraveled == 0 && milesTraveled < maxDistance)
         {
            milesTraveled += temp.peek().getMiles();
            System.out.println("Travel " + temp.peek().getMiles() + " mile(s) " + temp.peek().translateDir(temp.peek().getDirection()) + " on " + temp.peek().getStreet());
         }
         else if(milesTraveled + temp.peek().getMiles() < maxDistance)
         {
            milesTraveled += temp.peek().getMiles();
            System.out.println("Turn " + temp.peek().translateDir(temp.peek().getDirection()) + " and travel " + temp.peek().getMiles() + " mile(s) on " + temp.peek().getStreet());
         }
         else 
         {
            break; // breaks the loop if milesTraveled + the next segment exceeds the maxDistance
         }
         myStack.push(temp.pop());        
      }
      while(!temp.empty()) // loop runs if the previous one is broken to restore the data to the original Stack
      {
         myStack.push(temp.pop());
      }
      return milesTraveled;
   }
   
   /**
    * Creating the set of instructions from the destination to the source not exceeding the
    * max distance using two Stacks
    * @param maxDistance The maximum amount of miles to travel in a route
    * @return The number of miles traveled on the route
    */
   public double returnTrip(double maxDistance)
   {
      double milesTraveled = 0;
      String direction = "";
      StackInt<Direction> temp = new LinkedStack<>();
      while(!myStack.empty())
      {
         if(milesTraveled == 0 && milesTraveled < maxDistance)
         {
            milesTraveled += myStack.peek().getMiles();
            System.out.println("Travel " + myStack.peek().getMiles() + " mile(s) " + myStack.peek().translateDir(myStack.peek().getDirection()) + " on " + myStack.peek().getStreet());   
         }
         else if(milesTraveled + myStack.peek().getMiles() < maxDistance)
         {
            milesTraveled += myStack.peek().getMiles();
            System.out.println("Turn " + myStack.peek().translateDir(myStack.peek().getDirection()) + " and travel " + myStack.peek().getMiles() + " mile(s) on " + myStack.peek().getStreet());
         }
         else
         {
            break;
         }
         temp.push(myStack.pop());
      }
      while(!temp.empty())
      {
         myStack.push(temp.pop());
      }
      return milesTraveled;
   }
   
   /**
    * Estimates the travel time from source to destination based off of 
    * rates of miles and number of left turns
    * @return The estimated time calculated from the number of miles and left turns
    */
   public double travelTime()
   {
       StackInt<Direction> temp = new LinkedStack<>(); 
       double estimateTime = 0;
       double numMiles = 0;
       double leftTurns = 0;
       String direction1 = "";
       String direction2 = "";
       
       while(!myStack.empty())
       {
          temp.push(myStack.pop());
       }  
       while(!temp.empty())
       {
           numMiles = numMiles + temp.peek().getMiles();
           myStack.push(temp.pop());
           direction1 = myStack.peek().getDirection();
           if(!temp.empty())
           {
              direction2 = temp.peek().getDirection();
           }
           leftTurns = leftTurns + Direction.calcLeftTurn(direction1, direction2); // See documentation for Direction class for calcLeftTurn method
       }   
       estimateTime = (numMiles * 1.5) + (leftTurns * .5);
       return estimateTime;
   }
   
   /**
    * Estimates the travel time from destination to source based off of
    * rates of miles and number of left turns
    * @return The estimated time calculated from the number of miles and left turns
    */
   public double returnTripTime()
   {
      StackInt<Direction> temp = new LinkedStack<>();
      double estimateTime = 0;
      double numMiles = 0;
      double leftTurns = 0;
      String direction1 = "";
      String direction2 = "";
      
      while(!myStack.empty())
      {
         numMiles = numMiles + myStack.peek().getMiles();
         temp.push(myStack.pop());
         direction1 = temp.peek().getDirection();
         if(!myStack.empty())
         {
            direction2 = myStack.peek().getDirection();
         }
         leftTurns = leftTurns + Direction.calcLeftTurn(direction1, direction2); // See documentation for Direction class for calcLeftTurn method
      }
      while(!temp.empty())
      {
         myStack.push(temp.pop());
      }
      estimateTime = (numMiles * 1.5) + (leftTurns * .5); 
      return estimateTime;
   }
}