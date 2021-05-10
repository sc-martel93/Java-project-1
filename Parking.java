/*
 * Name: Scott Martel
 * Course: CSI233 (Spring 2021)
 * Date: March 31, 2021
 * 
 * Description: A program used to calculate parking cost in various areas in the city of Boston MA.         
 *     Attempted Bonus to display labeled cost using single statement conditional operator  [line 170]         
 */

import java.util.*; // for scanner

class Parking {
   /** Constant representing metered parking */
   static final String METER = "m";

   /** Constant representing garage parking */
   static final String GARAGE = "g";

   /** Constant for Back Bay neighborhood */
   static final String BACK_BAY = "back bay";

   /** Constant for Bulfinch neighborhood */
   static final String BULFINCH = "bulfinch";

   /** Constant for Fenway/Kenmore neighborhood */
   static final String FENWAY_KENMORE = "fenway/kenmore";

   /** Constant for Waterfront neighborhood */
   static final String WATERFRONT = "waterfront";

   /** Number of hours user was parked */
   static double hoursParked;

   /** Day of week user parked */
   static String dayOfWeek;

   /** Type of parking ((m)eter or (g)arage) */
   static String parkingType;

   /** neighborhood user parked in if applicable */
   static String neighborhood;

   /** calculated cost of parking */
   static double parkingCost;

   /** amount of times user will have to move their vehicle if applicable */
   static double vehicleMoveCount = 0;

   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);

      // Allow user to input hours parked
      System.out.print("Number of hours (ex: 3.2): ");
      hoursParked = console.nextDouble();
      console.nextLine(); // Eat new line

      // Allow user to input day of week
      System.out.print("Day of week (ex: Monday): ");
      dayOfWeek = console.nextLine();
      // convert to lowercase for processing
      dayOfWeek = dayOfWeek.toLowerCase();

      // Allow user to input parking type (meter or garage)
      System.out.print("Park at m)eter or g)arage: ");
      parkingType = console.nextLine();
      // Remove whitespace and convert to lowercase for processing
      parkingType = parkingType.replaceAll(" ", "");
      parkingType = parkingType.toLowerCase();

      // constants to hold weekdays, this is a stylistic choice, I think it makes the
      // switch cleaner and follows the same standard used by the other switch
      // statements in this program
      final String MONDAY = "monday", TUESDAY = "tuesday", WEDNESDAY = "wednesday", THURSDAY = "thursday",
            FRIDAY = "friday", SATURDAY = "saturday", SUNDAY = "sunday";

      // time limit for parking in hours (initialized to zero then changed if
      // applicable)
      int parkingTimeLimit = 0;

      // calculate parking cost based on parking type (meter or garage)
      switch (parkingType) {
      // calculate for garage parking
      case GARAGE:
         // Switch used to calculate cost depending on day of the week
         switch (dayOfWeek) {
         // calculate for weekday garage parking
         case MONDAY:
         case TUESDAY:
         case WEDNESDAY:
         case THURSDAY:
         case FRIDAY:
            if (hoursParked <= 2)
               parkingCost = 18.00;
            else if (hoursParked <= 3)
               parkingCost = 24.00;
            else if (hoursParked <= 10)
               parkingCost = 28.00;
            else
               parkingCost = 32.00;
            break;
         // calculate for weekend garage parking
         case SATURDAY:
         case SUNDAY:
            if (hoursParked <= 1)
               parkingCost = 10.00;
            else if (hoursParked <= 3)
               parkingCost = 14.00;
            else
               parkingCost = 18.00;
            break;
         }
         break;
      // calculate for meter parking
      case METER:
         // meter parking for free on sundays with no time limit
         if (dayOfWeek.equals("sunday")) {
            parkingCost = 0.00;
            break;
         }

         // holds standard rate prompt message for conformity using variables with printf
         String standardRateMessage = "Or any other for standard rate";

         // Prompt neighborhood options indented two spaces
         System.out.printf("Parking meter at?\n%10s\n%10s\n%16s\n%12s\n%32s\nNeighborhood: ", BACK_BAY, BULFINCH,
               FENWAY_KENMORE, WATERFRONT, standardRateMessage);
         // allow user to input neighborhood
         neighborhood = console.nextLine();
         // Convert user input to lowercase for processing
         neighborhood = neighborhood.toLowerCase();

         // calculate parking cost based on neighborhood
         switch (neighborhood) {
         // calculate for metered parking in back bay, must move vehicle every 2 hours
         case BACK_BAY:
            parkingCost = hoursParked * 3.75;
            parkingTimeLimit = 2;
            break;
         // calculate for metered parking in bulfinch, must move vehicle every 3 hours
         case BULFINCH:
            parkingCost = hoursParked * 2.5;
            parkingTimeLimit = 3;
            break;
         // calculate for metered parking in fenway/kenmore, must move vehicle every 3
         // hours
         case FENWAY_KENMORE:
            parkingCost = hoursParked * 2.5;
            parkingTimeLimit = 3;
            break;
         // calculate for metered parking in waterfront, must move vehicle every 2
         // hours
         case WATERFRONT:
            parkingCost = hoursParked * 3.75;
            parkingTimeLimit = 2;
            break;
         // default rate for other neighborhoods, must move vehicle every 3 hours
         default:
            parkingCost = 2.00 * hoursParked;
            parkingTimeLimit = 3;
         }
         break;
      // exit program and output error message if parking type is not meter or garage
      default:
         System.out.printf("Unknown parking location: %s", parkingType);
         System.exit(0);
      }
      
      // Output parking cost
      // Bonus: attempt at using single statement conditional operator
      System.out.printf(parkingCost <= 0 ? "Parking cost: FREE" : "Parking cost: $%.2f", parkingCost);
      
      // calculate amount of times user must move vehicle if applicable
      if (parkingTimeLimit > 0 && hoursParked > parkingTimeLimit) {
         vehicleMoveCount = hoursParked / parkingTimeLimit;
         
         // adjust move count if user leaves at time limit otherwise they will have an
         // unnecessary move added when they are leaving
         if (hoursParked % parkingTimeLimit == 0)
            vehicleMoveCount--;
            
         // round movement count to a whole number
         vehicleMoveCount = Math.floor(vehicleMoveCount);
         // output number of times user must move vehicle if applicable
         if (vehicleMoveCount > 0)
            System.out.printf("\nYou must move your vehicle %.0f time(s)", vehicleMoveCount);
      } 
   }
}