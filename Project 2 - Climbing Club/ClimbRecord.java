/**
   Date: 03/01/19
  
   Course: CSCI 2073
   
   Description: This program is designed to create ClimbRecord objects to store the peak name, climb height, and climb time of a record.
   Intended for reuse.
      
   On my honor, I have neither given nor received unauthorized help while
   completing this assignment.
   
   Jeffrey Howe        
*/

/**
   A class that creates an object to hold climbing record information. 
*/
public class ClimbRecord
{

   //*****Instance variables*****
   private String peakName;
   private int climbHeight;
   private int climbTime;
   
   //*****Constructors*****
   
   /**
      The argument constructor to initialize the instance variables to the user's desire.
      @param peak the mountain peak's name
      @param time the time it takes to reach the peak
      @param height how high the peak is
   */
   public ClimbRecord(String peak, int time, int height)
   {
      peakName = peak;
      climbTime = time;
      climbHeight = height;
   }
   
   /**
      The no-argument constructor for if the object information is unknown.
   */
   public ClimbRecord()
   {
      peakName = "";
      climbHeight = 0;
      climbTime = 0;
   }
   
   //*****Methods*****
   
   //*****Setters*****
   
   /**
      Method to set the peak name
      @param peak the name of the peak
   */
   public void setPeakName(String peak)
   {
      peakName = peak;
   }
   
   /**
      Method to set the height climbed
      @param height the height of the climb
   */
   public void setClimbHeight(int height)
   {
      climbHeight = height;
   }
   
   /**
      Method to set the time taken to climb to the peak
      @param time the climb time
   */
   public void setClimbTime(int time)
   {
      climbTime = time;
   }
   
   //*****Getters*****
   
   /**
      Method to obtain the peak name
      @return the name of the peak
   */
   public String getPeakName()
   {
      return peakName;
   }
   
   /**
      Method to obtain the climb's height
      @return the height of the climb
   */
   public int getClimbHeight()
   {
      return climbHeight;
   }
   
   /**
      Method to obtain the climb's time
      @return the time of the climb
   */
   public int getClimbTime()
   {
      return climbTime;
   }
   
}