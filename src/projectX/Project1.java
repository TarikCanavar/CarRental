package projectX;
import java.io.IOException; // import for file read when file is not found
import java.util.Random;    // for random number creating importing library

public class Project1 {
	public static void main(String[] args) {
	    final int arraySize = 100;		 // we created a final integer so when user wants to change size of arrays he/she can 
		Office[] officeArray = new Office[arraySize];	//change it from here easily
		Random random = new Random();
		Customer[] customerArray = new Customer[arraySize];
		Employee[] employeeArray = new Employee[arraySize];
		Car[] carArray = new Car[arraySize];
		CarRequest[] carRequestArray = new CarRequest[arraySize];
		Contract[] contractArray = new Contract[arraySize];		
		Date startingDay = new Date(1,1,2021); // date starts from 1.1.2021
		try {  // we have created a scan class and a scan function in that function we are taking input in both ways 
			Scan.scan(officeArray, customerArray, employeeArray, carArray, carRequestArray, contractArray, startingDay, random);
		} catch (IOException e) { // catching error and displaying error message when file is not found
			System.out.println("Cannot find the file.");			
		} 	
	}
}

