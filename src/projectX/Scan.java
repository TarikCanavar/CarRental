package projectX;

import java.io.File; // importing neccesary libraries
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class Scan {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void doOperations(Office[] officeArray,Customer[] customerArray,Employee[] employeeArray,Car[] carArray,CarRequest[] carRequestArray,Contract[] contractArray,Date startingDay,Random random, String line,String[] info, boolean mode) {
		info = line.split(";");			// this function is used for both input ways user enters by hand or load file 
		if (info[0].equals("addOffice") && info.length == 4) {	// we are splitting the line entered by user or file and 
			Office.addOffice(info, officeArray);            // controlling the first splitted String after that we are calling the
		 }                                               // function for that String
		else if (line.equals("listOffice")) {			        // for example info.length==4 control stands for controlling the 
			Office.listOffice(officeArray);                   // input line if there are missing info
		 }
		else if(info[0].equals("addCar") && info.length == 6) {        // same process for all
			Car.addCar(info, officeArray, carArray);
		}
		else if (info[0].equals("addCustomer") && info.length == 3) {
			Customer.addCustomer(info, customerArray);
		}
		else if (line.equals("listCustomer")) {
			Customer.listCustomer(customerArray);
		}
		else if(info[0].equals("listCar") && info.length == 2) {
			Car.listCar(info, officeArray);
		}
		else if(info[0].equals("addEmployee") && info.length == 6) {
			Employee.addEmployee(info, officeArray, employeeArray);
        }
		else if(info[0].equals("listEmployee") && info.length == 2) {
			Employee.listEmployee(info, officeArray);
        }
		else if (info[0].equals("deleteOffice") && info.length == 2) {
			Office.deleteOffice(info, officeArray);
		}
		else if (info[0].equals("deleteEmployee") && info.length == 3) {
			Employee.deleteEmployee(info, officeArray,employeeArray);
		}
		else if(info[0].equals("addCarRequest") && info.length == 8) {			
			CarRequest.addCarRequest(info, officeArray, customerArray, random, startingDay, employeeArray, contractArray, carArray, carRequestArray);
		}
		else if(line.equals("listCarRequest")) {
			CarRequest.listCarRequest(carRequestArray);
		}
		else if(line.equals("listContract")) {
			Contract.listContract(contractArray);
		}
		else if(info[0].equals("addCarRequestRandom") && info.length == 3) {
			CarRequest.addCarRequestRandom(officeArray,info,random, carRequestArray,contractArray,employeeArray,carArray,startingDay);				
		}

		else if(info[0].equals("addCarRequestNRandom") && info.length == 3) {
			CarRequest.addCarRequestNRandom(random, info, officeArray, carRequestArray, contractArray, employeeArray, carArray, startingDay);
		}
		else if(info[0].equals("nextday") && info.length == 1) {
			Date.nextDay(startingDay, officeArray, contractArray, employeeArray, carArray,customerArray);
		}
		else {
			if(mode) { // when input is entered by user this if statement works and displays our error message
				System.out.println("Missing or wrong parameters. This operation is cancelled.");
			}		
			else {       // when input is entered by file this else statement works and displays our error message
				System.out.println("Missing or wrong parameters. This operation is cancelled:");
				System.out.println("\t" + line);
			}			
		}			
	}
	public static void scan(Office[] officeArray,Customer[] customerArray,Employee[] employeeArray,Car[] carArray,CarRequest[] carRequestArray,Contract[] contractArray,Date startingDay,Random random) throws IOException {
		while (true) {       // when we called scane we are taking nextLine as line and splitting to info Strings
			System.out.print(">>>");    // after that we are sending our arrays and bool
			String line = scanner.nextLine();		
			String[] info = line.split(";");		
			doOperations(officeArray, customerArray, employeeArray, carArray, carRequestArray, contractArray, startingDay, random, line, info,true);
	    	if(info[0].equals("load") && info.length == 2) { // if user enters load not >>> then we are here and reading the file
	    		File file = new File("D:\\" + info[1]); //taking the name of the file from user
		    	Scanner sc = new Scanner(file, StandardCharsets.UTF_8);
		    	String line2;			
		    	while (sc.hasNextLine()) {    // if there are nextline it is keeping going  to take the input split it and send				
		    		line2 = sc.nextLine();   // to our doOperations method
		    		System.out.println(">" + line2);
		    		String[] info2 = line2.split(";");
		    		doOperations(officeArray, customerArray, employeeArray, carArray, carRequestArray, contractArray, startingDay, random, line2, info2,false);
		    	}
			}
				
		}	
	}
}
