package projectX;

import java.util.Random;

public class Date { // attributes for date 
	private int day;
	private int month;
	private int year;
	private static int dayCount = 0; // for how much days past control
	
	public Date(int day,int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public int getDay() {          // standart getters and setters
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public static int getDayCount() {
		return dayCount;
	}
	
    public void addDay() {
    	if(day < 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 )) {
    		day++; // For months that are 31 days long if day is not 31 then incrementing the day
    	}
    	else if(day == 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 ))  { 	
    		month++; // if day is 31 then day is 1 and incrementing month
    		day = 1;
    	}  	
    	else if(day < 31 && month == 12) {  
    		day++; //for december we needed another statement because we will increment year too if day is 31 
    	}   	
    	else if(day == 31 && month == 12) {
    		year++; day = 1; month = 1;  // this means last day of year incrementing year and month and day assigned to 1
    	}		
    	
    	else if(day < 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
    		day++; // for months that are 30 days long if it not last day of month increment the day
    	}
    	else if(day == 30 && (month == 4 || month == 6 || month == 9 || month == 11)) { 
    		day = 1; // end of the month go to next month assing day to 1
    		month++; 
    	}
    		
    	else if(day < 28 && month == 2) { 
    		day++; // for februrary we needed another statement when day is not 28 increment day
    		}   
    	else if (day == 28 && month == 2) { 	// when it is end of februrary increment month and day assigned to 1
    		day = 1; 
    		month++; 
    	}       	   	    	
    }
    public int[] addKm(Car[] cars) { // this function is created to add km to cars randomly
     	Random random = new Random(); // creating random object
    	int[] kmAdded = new int[Car.getId() - 1];  // creating and array and assigning it to car we took from parameter
    	for(int i = 0; i < Car.getId() - 1; i++) {
    		int rand = random.nextInt(3); // creating a random number for random kilometers
    		if(cars[i] != null) {			
    			if(!cars[i].getStatus()) {  // controlling the availability of car first
        			if(rand == 0) {   // when it is 0 km is 100 when 1 km is 200
        				cars[i].setKm(100);
        				kmAdded[i] = 100;
        			}    				
            		else if(rand == 1){
            			cars[i].setKm(200);
            			kmAdded[i] = 200;
            		}
            		else {
            			cars[i].setKm(300);
            			kmAdded[i] = 300;
            		}
        		}
    		}
    	
    	}    	
    	return kmAdded; // returning the km added to car 
    }
 // Method used to check if the date is correct.
 	public boolean IsDateValid() {
 		boolean IsValid = false;
 		
 		if ((month % 2 == 1 && month != 2) || month == 8 || month == 10 || month == 12) {
 			if (day <= 31 && day >= 0) {// when the mode of month by 2 is 1 and it is not february or when it is august october or december
 				IsValid = true; // if day is not negative or bigger than 31 then it is true
 			}
 		}
 		else if ((month % 2 == 0 && month != 2) || month == 9 || month == 11) {
 			if (day <= 30 && day >= 0) {//when the mode of month by 2 is 0 and it is not february or when it is september or november
 				IsValid = true; // controlling day for these days  if it is true assigning bool to true
 			}
 		}
 		else if (month == 2) { // for february
 			if (year % 4 == 0) { // if it is leap year 
 				if (day <= 29 && day >= 0) { // in leap year day can be 29 so another check
 					IsValid = true;
 				}
 			}
 			else { // when not leap year
 				if (day <= 28 && day >= 0) {
 					IsValid = true;
 				}
 			}
 		}
 		return IsValid; // if it didn't enter any of these statements then bool stays false if entered then it is true
 	}
 	
 	// Method used to check if the contract date format is correct.
 	public static boolean IsContractDateValid(Date date1 ,Date date2) {
 		boolean IsValid = false;
 		
 		// If year of the start date is lower than then end date, contract date is valid.
 		if (date1.getYear() <date1.getYear()) {
 			IsValid = true;
 		}  // when years are same and startdate's month is smaller than end date
 		else if(date1.getYear() == date2.getYear()) {
 			if(date1.getMonth() < date2.getMonth()) {
 				IsValid = true;
 			}         // same month controlling the day difference between days if lower than 4 then bool is true
 			else if(date1.getMonth() == date2.getMonth()) {
 				if(date1.getDay() < date2.getDay() && date2.getDay() - date1.getDay() <= 4) {
 					IsValid = true;
 				}
 			}
 		}
 		return IsValid; //if it didn't enter any of these statements then bool stays false if entered then it is true
 	}
 	public String getAllInfo() { // for displaying date easily
        String info = day + "." + month + "." + year;
        return info;
    }
 	public static void nextDay(Date startingDay, Office[] officeArray, Contract[] contractArray,Employee [] employeeArray, Car[] carArray, Customer[] customerArray) {
 		startingDay.addDay(); // calling addDay function
 		dayCount++; // incrementing our day count
 		Contract.checkEndDate(startingDay, contractArray, employeeArray, carArray, officeArray); // calling our contract function
			// we will come to that later in code 
		for(int i = 0; i < Office.getId() - 1; i++) { // going throgh offices
			int [] getKm = startingDay.addKm(officeArray[i].getCars()); // taking kilometers added randomly
			officeArray[i].printProfit(contractArray,employeeArray,getKm); // calculating and printing profit for office
			officeArray[i].resetAll();		// setting them to zero 
		}
		System.out.println("--- Office Statistics of the Last 10 Days ---");
		for(int i = 0; i < Office.getId() - 1; i++) {        // going through offices again
			System.out.println("--- Office" + officeArray[i].getOfficeId() + " ---"); // displaying office Id
			officeArray[i].empRent(); // employee who rented most
			officeArray[i].listCarStats(contractArray); //most rented car / car class /highest profit / and average days
			officeArray[i].listEmployeeProfit(); // profit of employee
			officeArray[i].averageIncome(employeeArray); // average profit of employee
			for(int j = 0; j < officeArray[i].geteCount(); j++) { // for recommendations
				if(officeArray[i].getEmployee()[j].getStatus()) {
					officeArray[i].getEmployee()[j].setDismissCount();	
				}
			}
				
		}
	
		Customer.compareCustomer(customerArray); // customer who rented most
 	}  
}
