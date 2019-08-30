/**
   Date: 03/01/19
  
   Course: CSCI 2073
   
   Description: This program is designed to create and store records of climbs of a club. It creates a list with a minimum height requirement, and users
   can add records or read them in from a file. They can also use some additional methods to get some common information from the records.
   
   On my honor, I have neither given nor received unauthorized help while
   completing this assignment.
   
   Jeffrey Howe       
*/

import java.util.*;
import java.io.*;

/**
   A class that creates and stores data of climbs at a particular climbing club, and includes additional
   information methods.
*/
public class ClimbingClub
{
   
   //*****Instance variables*****
   private int minHeight;
   
   //*****Additional variables*****
   List<ClimbRecord> club;
   int counter;
   int sum;
   double avg;
   
   //*****Constructor*****
   
   /**
      The constructor for initializing a list with a minimum height requirement.
      @param minHeight the minimum height required for a record to be added to the club records
   */
   public ClimbingClub(int minHeight)
   {
      club = new ArrayList<ClimbRecord>();
      this.minHeight = minHeight;
   }
   
   //****Methods*****
   
   /**
      Method to add a new record to the Club's record list. It checks if it meets the minimum height requirement,
      and creates a ClimbRecord object if it does, otherwise it returns false.
      @param peakName the name of the peak
      @param climbTime the time it took to reach the peak
      @param height the height of the peak
      @return true if climb object successfully added, false if it does not meet minimum height
   */
   public boolean addClimb(String peakName, int climbTime, int height)
   {
      if(height >= minHeight)
      {
         club.add(new ClimbRecord(peakName, climbTime, height));
         return true;
      }
      else
      {
         System.out.println("ERROR: The record (" + peakName + ", " + height + ", " + climbTime + 
                            ") does not meet the required minimum height.");
         return false;
      }
   }
   
   /**
      Method to add climbs read in from a file. Checks minimum height and adds the climb to the record if true.
      @param filename the name of the file to be read in.
      @return true if no exceptions thrown, false if an exception is caught
   */
   public boolean addClimbs(String filename)
   {  
      counter = 0; // Counter increments and keeps track of if an exception is thrown in this method.
      try
      {
         File file = new File(filename);
         Scanner in = new Scanner(file);
         while(in.hasNextLine())
         {   
            try
            {   
               String peak = in.next();
               int height = in.nextInt();
               int time = in.nextInt();
               
               if(height >= minHeight)
               {
                  club.add(new ClimbRecord(peak, time, height));          
               }
               else
               {
                  System.out.println("ERROR: The record (" + peak + ", " + height + ", " + time +
                                     ") does not meet the required minimum height.");
               }
            }
            catch(InputMismatchException e) // Exception handling in case the elements read in get out of order.
            {
               System.out.println("ERROR: One of the elements is in the wrong place!");
               counter++;
            }
            catch(NoSuchElementException e) // Prevent the program from crashing after reaching end of file.
            {
               continue;
            }  
         }   
      }   
      catch(FileNotFoundException e) // Prevent program from crashing if file requested is not in same folder.
      {
         System.out.println("ERROR: File not found");
         counter++;
      }
      if(counter == 0)
      {
         return true;
      }
      else
      {
         return false;
      }    
   }
   
   /**
      Method to determine how many peak names have been recorded. Adds name elements to a new list if it
      isn't present in new list. Whatever the size of that list is is the amount of distinct names.
      @return the size of the names list
   */
   public int distinctPeakNames()
   {  
      counter = 0;
      List<String> names = new ArrayList<String>();
      for(int i = 0; i < club.size(); i++)
      {
         if(!names.contains(club.get(i).getPeakName()))
         {
            names.add(club.get(i).getPeakName());
         }
      }
      return names.size();
   }
   
   /**
      Method to calculate the average climb time between records for the requested peak. Compares the names in the list to the given name. 
      Time is added to sum, counter is incremented if the if-statement is true.
      @param peakName the name of the peak
      @return the average climb time of the peak
   */
   public double avgClimbTime(String peakName)
   {
      counter = 0;
      sum = 0;
      for(int i = 0; i < club.size(); i++)
      {
         if(club.get(i).getPeakName().equals(peakName))
         {
            sum += club.get(i).getClimbTime();
            counter++; 
         }
      }
      try
      {
         avg = sum / counter; // If counter does not increment, divide by zero exception.
      }
      catch(ArithmeticException e) // Execution of exception means there are no records with requested peak name.
      {
         System.out.println("There are no records of a climb on the requested peak, or something went wrong."); 
      }
      return avg;
   }
   
   /**
      Method to determine the most climbed peak using two for loops to compare the elements against each other and using two variables to keep track of the most climbed peak.
      @return the name of the peak with the most climbs.
   */
   public String mostClimbedPeak()
   {
      int numClimbs = 0; // variable to hold the highest number of instances of a specific element
      String mostClimbed = "";
      for(int i = 0; i < club.size(); i++)
      {
         counter = 0; // reset counter every increment
         for(int j = 0; j < club.size(); j++)
         {
            if(club.get(i).getPeakName().equals(club.get(j).getPeakName()))
            {
               counter++;
            }
         }
         if(counter > numClimbs) // assign the ith index name to variable if true
         {
            numClimbs = counter;
            mostClimbed = club.get(i).getPeakName();
         }
      }
      return mostClimbed;    
   }
   
   //*****Setter*****

   /**
      Method to set the minimum height.
      @param minheight the minimum height required by the club
   */
   public void setMinHeight(int minheight)
   {
      this.minHeight = minheight;
   } 
   
   //*****Getter*****
   
   /**
      Method to return the minimum height.
      @return the minimum height of the club
   */
   public int getMinHeight()
   {
      return minHeight;
   }
}