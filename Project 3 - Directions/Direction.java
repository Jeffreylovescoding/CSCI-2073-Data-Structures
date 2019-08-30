/**
   Date: 03/30/19
  
   Course: CSCI 2073
   
   Description: A class that creates Direction objects to store the data of the number of miles, the direction, and the name
   of the street of the directions.txt file. It also includes a few additional methods to reduce repetitive code 
   in the Directions class.
      
   On my honor, I have neither given nor received unauthorized help while
   completing this assignment.
   
   Jeffrey Howe       
*/

import java.util.*;
import java.io.*;

/**
 * The Direction class to create the Direction objects that store miles, direction, and streetName of a single direction.
 * 
 * @author Jeffrey Howe
 */
public class Direction
{
   // Instance variables
   private double numMiles;
   private String direction;
   private String streetName;
   
   // Constructors
   /**
    * Creates a new Direction with argument input.
    * @param miles The number of miles per segment
    * @param dir The direction headed per segment
    * @param st The street name of the segment
    */
   public Direction(double miles, String dir, String st)
   {
      numMiles = miles;
      direction = dir;
      streetName = st;
   }
   
   // Methods
   
   /**
    * Setting the number of miles.
    * @param miles The number of miles per segment
    */
   public void setMiles(double miles)
   {
      numMiles = miles;
   }
   
   /**
    * Setting the direction.
    * @param dir The direction headed per segment
    */
   public void setDirection(String dir)
   {
      direction = dir;
   }
   
   /**
    * Setting the street.
    * @param st The street name per segment
    */
   public void setStreet(String st)
   {
      streetName = st;
   }
   
   /**
    * Getting the number of miles.
    * @return The number of miles per segment
    */
   public double getMiles()
   {
      return numMiles;
   }
   
   /**
    * Getting the direction.
    * @return The direction per segment
    */
   public String getDirection()
   {
      return direction;
   }
   
   /**
    * Getting the street.
    * @return The street per segment
    */
   public String getStreet()
   {
      return streetName;
   }
   
   /**
    * Changing the two character string into the proper word and returning it.
    * @param dir The direction of the segment
    * @return The directional string 
    */
   public static String translateDir(String dir)
   {
      String actualDir = "";
      if(dir.equals("NO"))
      {
         actualDir = "north";
      }
      else if(dir.equals("SO"))
      {
         actualDir = "south";
      }
      else if(dir.equals("EA"))
      {
         actualDir = "east";
      }
      else if(dir.equals("WE"))
      {
         actualDir = "west";
      }
      return actualDir;
   }
   
   /**
    * Determing whether there is a left turn from one segment to another
    * @param dir1 The first direction of the segments being compared
    * @param dir2 The second direction of the segments being compared
    * @return 0 if there was no left turn, 1 if there was
    */
   public static double calcLeftTurn(String dir1, String dir2)
   {
      double numLeftTurns = 0;
      if(dir1.equals("NO") && dir2.equals("WE"))
      {
         numLeftTurns = 1;
      }
      else if(dir1.equals("WE") && dir2.equals("SO"))
      {
         numLeftTurns = 1;
      }
      else if(dir1.equals("SO") && dir2.equals("EA"))
      {
         numLeftTurns = 1;
      }
      else if(dir1.equals("EA") && dir2.equals("NO"))
      {
         numLeftTurns = 1;
      }
      return numLeftTurns;
   }
   
   /**
    * Changing the two character string into the reverse proper word
    * and returning it
    * @param dir The direction of the segment
    * @return The reverse directional string
    */
   public static String reverseDir(String dir)
   {
      String actualDir = "";
      if(dir.equals("NO"))
      {
         actualDir = "south";
      }
      else if(dir.equals("SO"))
      {
         actualDir = "north";
      }
      else if(dir.equals("EA"))
      {
         actualDir = "west";
      }
      else if(dir.equals("WE"))
      {
         actualDir = "east";
      }
      return actualDir;      
   }
}