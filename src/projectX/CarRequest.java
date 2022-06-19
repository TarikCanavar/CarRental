package projectX;

import java.util.Random;

public class CarRequest {
	
	private static int Id = 1;
	private int requestID;
	private Date startdate;
	private Date enddate;
	private String brand;
	private String model;
	private String carClass;
	private int office_id;
	private int customer_id;
	
	public CarRequest(int office_id, int customer_id, String inputBrand, String inputModel, String inputClass, Date inputStartDate, Date inputEndDate) {
		
		requestID = Id;
		Id++;
		startdate = inputStartDate;
		enddate = inputEndDate;
		brand = inputBrand; 
		model = inputModel;
		carClass = inputClass;
		this.office_id = office_id;
		this.customer_id = customer_id;
		
	}
	
	public Date getStartDate() {
		return startdate;
	}
	
	public Date getEndDate() {
		return enddate;
	}
	public String getInputBrand() {
		return brand;
	}
	public String getInputModel() {
		return model;
	}
	public String getInputClass() {
		return carClass;
	}
	public int getRequestId() {
		return requestID;
	}
	public int getOffice_Id() {
		return office_id;
	}
	public int getCustomer_Id() {
		return customer_id;
	}
	public static int getId() {
		return Id;
	}
	public static void addCarRequestRandom(Office [] officeArray, String[] info, Random random, CarRequest[] carRequestArray, Contract[] contractArray, Employee[] employeeArray, Car[] carArray, Date startingDate) {
		int officeIndex = 0;
		boolean infoCheck = false;
		if (info[1].equals("*") || (info[1].equals("-1") && info[2].equals("*")) ) {
			officeIndex = random.nextInt(Office.getId() - 1);
			info[1] = Integer.toString(officeArray[officeIndex].getOfficeId());
		} 
		else if (info[1].equals("-1") && !info[2].equals("*")) {
			System.out.println("After -1 * must be entered.");
			infoCheck = true;
		} 
		else {
			// Mevcut �al��an var m� bak�lacak
			
			for (int i = 0; i < officeArray.length; i++) {
				if (officeArray[i] != null) {
					if (Integer.parseInt(info[1]) == (officeArray[i].getOfficeId())) {
						officeIndex = i;
						break;
					}
				}
			}
		}
		if(!infoCheck) {
			Car[] carControl = officeArray[officeIndex].getCars();
			int[] randomizer = new int[carControl.length];
			int rand_index = 0, testInt = 0;
			
			if (info[2].equals("*") || info[2].equals("-1")) {
				testInt = random.nextInt(3);
				if (testInt == 2) {
					for (int i = 0; i < carControl.length; i++) {
						if (carControl[i] != null && carControl[i].getcarClass().equals("luxury") && carControl[i].getStatus()) {
							randomizer[rand_index] = i;
							rand_index++;

						}
					}
				} 
				else if (testInt == 1) {
					for (int i = 0; i < carControl.length; i++) {
						if (carControl[i] != null && carControl[i].getcarClass().equals("sports") && carControl[i].getStatus()) {
							randomizer[rand_index] = i;
							rand_index++;
						}
					}
				} 
				else {
					for (int i = 0; i < carControl.length; i++) {
						if (carControl[i] != null && carControl[i].getcarClass().equals("economy") && carControl[i].getStatus()) {
							randomizer[rand_index] = i;
							rand_index++;
						}
					}
				}			
				// En sonda date var de�i�mesi gereken. Hemen alt sat�rda ucu g�r�nmeyen tarafta.
				
				
				// Contract eklenecek.

			} 
			else {
				for (int i = 0; i < carControl.length; i++) {
					if (carControl[i] != null && carControl[i].getcarClass().equals(info[2]) && carControl[i].getStatus()) {
						randomizer[rand_index] = i;
						rand_index++;
					}
				}
			}
			if(rand_index != 0) {
				rand_index = random.nextInt(rand_index);
				int randomDay = random.nextInt(4) + 1;
				Date endDate = new Date(1,1,2021);
				if(startingDate.getDay() + randomDay <= 31 && ((startingDate.getMonth() % 2 == 1 && startingDate.getMonth() != 2 && startingDate.getMonth() != 9 && startingDate.getMonth() != 11)  
						|| (startingDate.getMonth() == 8 || startingDate.getMonth() == 10 || startingDate.getMonth() == 12))) {
					 endDate = new Date((startingDate.getDay() + randomDay),startingDate.getMonth(),startingDate.getYear());
					
				}
				else if(startingDate.getDay() + randomDay > 31 && ((startingDate.getMonth() % 2 == 1 && startingDate.getMonth() != 2 && startingDate.getMonth() != 9 && startingDate.getMonth() != 11)  
						|| (startingDate.getMonth() == 8 || startingDate.getMonth() == 10 ))) {
					 endDate = new Date((startingDate.getDay() + randomDay) - 31,startingDate.getMonth() + 1,startingDate.getYear());
					
				}
				else if(startingDate.getDay() + randomDay > 31 && startingDate.getMonth() == 12) {
					 endDate = new Date((startingDate.getDay() + randomDay) - 31,1,startingDate.getYear() + 1);
				}
				else if(startingDate.getDay() + randomDay <= 30 && ((startingDate.getMonth() % 2 == 0 && startingDate.getMonth() != 2 && startingDate.getMonth() != 8 && 
						startingDate.getMonth() != 10 && startingDate.getMonth() != 12) || (startingDate.getMonth() == 11 || startingDate.getMonth() == 9))) {
					 endDate = new Date((startingDate.getDay() + randomDay),startingDate.getMonth(),startingDate.getYear());
				}
				else if(startingDate.getDay() + randomDay > 30 && ((startingDate.getMonth() % 2 == 0 && startingDate.getMonth() != 2 && startingDate.getMonth() != 8 && 
						startingDate.getMonth() != 10 && startingDate.getMonth() != 12) || (startingDate.getMonth() == 11 || startingDate.getMonth() == 9))) {
					 endDate = new Date((startingDate.getDay() + randomDay) - 30 ,startingDate.getMonth() + 1,startingDate.getYear());
				}
				else if((startingDate.getDay() + randomDay <= 28 && startingDate.getMonth() == 2 && startingDate.getYear() % 4 != 0) || (startingDate.getDay() + randomDay <= 29 && 
						startingDate.getMonth() == 2 && startingDate.getYear() % 4 == 0) ) {
					 endDate = new Date((startingDate.getDay() + randomDay),startingDate.getMonth(),startingDate.getYear());
				}
				else if((startingDate.getDay() + randomDay > 28 && startingDate.getMonth() == 2 && startingDate.getYear() % 4 != 0) || (startingDate.getDay() + randomDay > 29 && 
						startingDate.getMonth() == 2 && startingDate.getYear() % 4 == 0) ) {
					if(startingDate.getYear() % 4 == 0) {
						 endDate = new Date((startingDate.getDay() + randomDay) - 29,startingDate.getMonth() + 1,startingDate.getYear());
					}
					else {
						 endDate = new Date((startingDate.getDay() + randomDay) - 28,startingDate.getMonth() + 1,startingDate.getYear());
					}
				}
				
				
			
				carRequestArray[CarRequest.getId()] = new CarRequest(Integer.parseInt(info[1]),random.nextInt(Customer.getId()), carControl[randomizer[rand_index]].getBrand(),
				carControl[randomizer[rand_index]].getModel(), carControl[randomizer[rand_index]].getcarClass(),startingDate,endDate);
				int randomEmployee = -1;
				boolean randCheck = false;
				for(int i = 0; i < officeArray[Integer.parseInt(info[1]) - 1].getCounter(); i++) {
					 randomEmployee= random.nextInt(officeArray[Integer.parseInt(info[1]) - 1].getCounter());
					 if(employeeArray[randomEmployee].getStatus()){					 
						 randCheck = true;
						 break;
					 }		
				}
				if(randCheck) {
					contractArray[Contract.getId() - 1] = new Contract(employeeArray[randomEmployee].getEmployeeId(),startingDate,endDate,random.nextInt(Customer.getId() - 1) + 1,carControl[randomizer[rand_index]].getCarId());
					System.out.println("	Contract:Employee" + contractArray[Contract.getId() - 2].getEmployeeId() + ";Customer" + contractArray[Contract.getId() -2].getCustomerId() + ";Car" 
					+ contractArray[Contract.getId() -2].getCarId() + ";" + contractArray[Contract.getId() -2].getStartDate().getAllInfo() + ";" +  contractArray[Contract.getId() -2].getEndDate().getAllInfo());
					contractArray[Contract.getId() - 2].employeeBonus(employeeArray, carArray);
					employeeArray[randomEmployee].addRentCount(1);
	                carControl[randomizer[rand_index]].setStatus();
	                carControl[randomizer[rand_index]].increaseRentCount();
	                officeArray[officeIndex].setCars(carControl);
	                employeeArray[randomEmployee].setStatus();
	                officeArray[officeIndex].addContract(contractArray[Contract.getId() - 2]);
				}
				else {
					System.out.println("	Error! No employee available for contract.");
					officeArray[officeIndex].setInsufficientEmployee();
				}
				
			
							
			}				
			else {
				System.out.println("	There are no more cars to request from.");
				if(info[2].equals("economy")){
					 officeArray[officeIndex].setEconomyDemand();
				}
				else if(info[2].equals("sports")) {
					officeArray[officeIndex].setSportsDemand();
				}
				else if(info[2].equals("luxury")) {
					officeArray[officeIndex].setLuxuryDemand();
				}
			}										
		}					
	}
	public static void addCarRequest(String[] info,Office[] officeArray,Customer[] customerArray,Random random,Date startingDay,Employee[] employeeArray,Contract[] contractArray,
		Car[] carArray,CarRequest[] carRequestArray)  {
		String[] date = info[7].split("\\.");
		Date endDate = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
		String[] date2 = info[6].split("\\.");
		Date startDate = new Date(Integer.parseInt(date2[0]),Integer.parseInt(date2[1]),Integer.parseInt(date2[2]));
		int officeIndex = 0;
		int control = 0;
		for(int i = 0; i < officeArray.length; i++) {
			if(officeArray[i] != null) {				
				if(Integer.parseInt(info[1]) == (officeArray[i].getOfficeId())) {
					 officeIndex = i;					
					 control++;
					 break;
				}
			}
		}
		for(int i = 0; i < customerArray.length; i++) {
			if(customerArray[i] != null) {
				if(Integer.parseInt(info[2]) == (customerArray[i].getCustomerId())) {							
					control++;
					break;
				}
			}					
		}
							
	
		Car [] carControl = officeArray[officeIndex].getCars();
		int [] randomizer = new int[carControl.length];
		int rand_index = 0;
		boolean dateError = false;
		boolean employeeError = false;
		boolean carNotAvailable = false;
		for(int i = 0; i < carControl.length; i++) {					
			if(carControl[i] != null) {
				if(carControl[i].getBrand().equals(info[3]) && carControl[i].getModel().equals(info[4]) && carControl[i].getcarClass().equals(info[5])) {								
					control++;
					break;
				}
				else if(info[3].equals("*") && !info[4].equals("*")) {
					for(int j = 0; j < carControl.length; j++) {
						if(carControl[j] != null) {
							if(carControl[j].getModel().equals(info[4])) {
								randomizer[rand_index] = j;
								rand_index++;						
							}
						}
					}
					rand_index = random.nextInt(rand_index);
					info[3] = carControl[randomizer[rand_index]].getBrand();
					if(carControl[randomizer[rand_index]].getModel().equals(info[4]) && carControl[randomizer[rand_index]].getcarClass().equals(info[5])) {
						control++;
						break;
					}
											
				}
				else if(info[4].equals("*") && !info[3].equals("*")) {
					for(int j = 0; j < carControl.length; j++) {
						if(carControl[j] != null) {
							if(carControl[j].getBrand().equals(info[3])) {
								randomizer[rand_index] = j;
								rand_index++;
							}
						} 
					}
					
					rand_index = random.nextInt(rand_index);						
					info[4] = carControl[randomizer[rand_index]].getModel();							
					if(carControl[randomizer[rand_index]].getBrand().equals(info[3]) && carControl[randomizer[rand_index]].getcarClass().equals(info[5])) {
						control++;
						break;
					}						
					
				}
				else if(info[4].equals("*") && info[3].equals("*")) {
					for(int j = 0; j < carControl.length; j++) {
						if(carControl[j] != null) {
							if(carControl[j].getcarClass().equals(info[5])) {										
								randomizer[rand_index] = j;										
								rand_index++;
							}									
						}
					}
				
					rand_index = random.nextInt(rand_index);
					info[4] = carControl[randomizer[rand_index]].getModel();
					info[3] = carControl[randomizer[rand_index]].getBrand();											
					control++;
					break;							
				}
													
			}
			 
		}
		String[] dateHolder = info[6].split("\\.");
		if(startingDay.getDay() == Integer.parseInt(dateHolder[0]) && startingDay.getMonth() == Integer.parseInt(dateHolder[1]) && startingDay.getYear() == Integer.parseInt(dateHolder[2])) {
			control++;
			
		}
		if(control == 4) {
			if(carControl[randomizer[rand_index]].getStatus()) {
				int randomEmployee = -1;
				for(int i = 0; i < 1000; i++) {
					Employee[] empArr = officeArray[Integer.parseInt(info[1]) -1].getEmployee();
					randomEmployee = random.nextInt(officeArray[Integer.parseInt(info[1]) - 1].getCounter()) + (empArr[0].getEmployeeId() - 1);
					if(employeeArray[randomEmployee].getStatus())
						break;
				}
				if(Date.IsContractDateValid(startDate, endDate) && endDate.IsDateValid() && startDate.IsDateValid() && employeeArray[randomEmployee].getStatus()) {
					contractArray[Contract.getId() - 1] = new Contract(employeeArray[randomEmployee].getEmployeeId(),startDate,endDate,Integer.parseInt(info[2]),carControl[randomizer[rand_index]].getCarId());
					System.out.println("	Contract:Employee" + contractArray[Contract.getId() - 2].getEmployeeId() + ";Customer" + contractArray[Contract.getId() -2].getCustomerId() + ";Car" 
					+ contractArray[Contract.getId() -2].getCarId() + ";" + contractArray[Contract.getId() -2].getStartDate().getAllInfo() + ";" +  contractArray[Contract.getId() -2].getEndDate().getAllInfo());
					contractArray[Contract.getId() - 2].employeeBonus(employeeArray, carArray);
					for(int i = 0; i < Customer.getId() - 1; i++) {
						if(customerArray[i].getCustomerId() == Integer.parseInt(info[2])) {
							customerArray[i].addRentCount(1);
							break;
						}
					}
					employeeArray[randomEmployee].addRentCount(1);
					carControl[randomizer[rand_index]].setStatus();
					carControl[randomizer[rand_index]].increaseRentCount();
					officeArray[officeIndex].setCars(carControl);
					employeeArray[randomEmployee].setStatus();
					officeArray[officeIndex].addContract(contractArray[Contract.getId() - 2]);
				}
				else {
					if(!employeeArray[randomEmployee].getStatus() && (Date.IsContractDateValid(startDate, endDate) && endDate.IsDateValid() && startDate.IsDateValid())){
						employeeError = true;
					}
					else if(employeeArray[randomEmployee].getStatus() && !(Date.IsContractDateValid(startDate, endDate) && endDate.IsDateValid() && startDate.IsDateValid())) {
						dateError = true;
					}
					else {
						dateError = true;
						employeeError = true;
					}
				}
			}
			else {
				carNotAvailable = true;
			}			
		}
		else {
			if(carNotAvailable) {
				System.out.println("	Error! No available car.");
				if(carControl[randomizer[rand_index]].getcarClass() == "economy") {
					officeArray[officeIndex].setEconomyDemand();
				}
				else if(carControl[randomizer[rand_index]].getcarClass() == "sports") {
					officeArray[officeIndex].setSportsDemand();
				}
				else if(carControl[randomizer[rand_index]].getcarClass() == "luxury") {
					officeArray[officeIndex].setLuxuryDemand();
				}
			}
			if(dateError) {
				System.out.println("    Error! Wrong date.");
			}
			if(control < 4) {
				System.out.println("    Error! No car.");
				if(info[5].equals("economy")) {
					officeArray[officeIndex].setEconomyDemand();
				}
				else if(info[5].equals("sports")) {
					officeArray[officeIndex].setSportsDemand();
				}
				else if(info[5].equals("luxury")) {
					officeArray[officeIndex].setLuxuryDemand();
				}
				else {
					officeArray[officeIndex].setLuxuryDemand();
				}
			}
			if(employeeError) {
				System.out.println("    Error! No employee available for contract.");
				officeArray[officeIndex].setInsufficientEmployee();
			}
		}
		carRequestArray[CarRequest.getId()] = new CarRequest(Integer.parseInt(info[1]),Integer.parseInt(info[2]),info[3],info[4],info[5],startDate,endDate);
	}
	public static void listCarRequest(CarRequest[] carRequestArray) {
		for(int i = 0; i < carRequestArray.length; i++) {
			if(carRequestArray[i] != null) {
				System.out.println("\t"+ carRequestArray[i].getRequestId() +".Request;"+carRequestArray[i].getOffice_Id()+";"+ carRequestArray[i].getCustomer_Id()+";"+
			carRequestArray[i].getInputBrand()+";"+ carRequestArray[i].getInputModel() + ";" + carRequestArray[i].getInputClass() + ";" +
			carRequestArray[i].getStartDate().getAllInfo() + ";" + carRequestArray[i].getEndDate().getAllInfo());
			}		
		}
	}
	public static void addCarRequestNRandom(Random random, String[] info,Office[] officeArray,CarRequest[] carRequestArray, Contract[] contractArray, Employee[] employeeArray, Car[] carArray, Date startingDay) {
		if(Integer.parseInt(info[1]) > 0 && Integer.parseInt(info[2]) < 8) {
			int randInt = random.nextInt(Integer.parseInt(info[2])-Integer.parseInt(info[1])) + Integer.parseInt(info[1]);
			for(int i = 0; i < randInt; i++) {
				info[1] = "1";
				info[2] = "*";					
				CarRequest.addCarRequestRandom(officeArray,info,random,carRequestArray,contractArray,employeeArray,carArray,startingDay);
			}
		}	
	}
	
}
