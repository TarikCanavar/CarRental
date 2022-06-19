package projectX;
import java.util.Random;

public class Office{
	private static int Id = 1;
	private int count = 0;
	private int eCount = 0;
	private Car[] cars = new Car[100];
	private Employee[] employees = new Employee[3];
	private int OfficeId;
	private int income = 0;
	private int expense = 0;
	private int expenseClass = 0;
	private int expenseKm = 0;
	private int profit = 0;
	private int[] expenseEmpBonus = new int[3];
	private int expenseEmpSalary = 0;
	private int expenseDeleted = 0;
	
	
	private int noEmployee = 0;
	private int economyDemand = 0;
	private int sportsDemand = 0;
	private int luxuryDemand = 0;
	private int exceedEmployee = 0;
	private int insufficientEmployee = 0;
	
	private String phoneNumber;
	private String address;
	private String city;
	
	private Contract[] contracts = new Contract[100];
	private int cCount = 0;
	
	public Office() {
	OfficeId=Id;
	Id++;
	}
	public void addCar(Car car) {
		cars[count] = car;		
		count++;	
	}
	public void addEmployee(Employee employee) {		
			employees[eCount] = employee;
			eCount++;		 
	}
	public void addContract(Contract contract) {
        contracts[cCount] = contract;
        cCount++;
    }
	
	public void listCarStats(Contract[] contractArray) {
		int temp = 0;
		int temp2 = 0;
		boolean carBool = false;
		
		for (int i = 0; i < count; i++) {
			if (cars[i].getRentCount() > temp) {
				temp = cars[i].getRentCount();
			}
			if (cars[i].getCarProfit() > temp2) {
				temp2 = cars[i].getCarProfit();
			}
		}
		System.out.print("Most rented car(s):");
		for (int i = 0; i < count; i++) {
			if (cars[i].getRentCount() == temp && !carBool) {
				System.out.print(" Car" + cars[i].getCarId() + ";" + cars[i].getBrand() + ";" + cars[i].getModel());
				carBool = true;
			}
			else if (cars[i].getRentCount() == temp && carBool) {
				System.out.print(" - Car" + cars[i].getCarId() + ";" + cars[i].getBrand() + ";" + cars[i].getModel());
			}
		}
		carBool = false;
		System.out.print("\nMost rented car class(es):");
		for (int i = 0; i < count; i++) {
			if (cars[i].getRentCount() == temp && !carBool) {
				System.out.print(" " + cars[i].getcarClass());
				carBool = true;
			}
			else if (cars[i].getRentCount() == temp && carBool) {
				System.out.print(" - " + cars[i].getcarClass());
			}
		}
		carBool = false;
		System.out.print("\nCar(s) with the highest profit:");
		for (int i = 0; i < count; i++) {
			if (cars[i].getCarProfit() == temp2 && !carBool) {
				System.out.print(" Car" + cars[i].getCarId() + ";" + cars[i].getBrand() + ";" + cars[i].getModel());
				carBool = true;
			}
			else if (cars[i].getCarProfit() == temp2 && carBool) {
				System.out.print(" - Car" + cars[i].getCarId() + ";" + cars[i].getBrand() + ";" + cars[i].getModel());
			}
		}
		carBool = false;
		System.out.print("\nCar class(es) with the highest profit:");
		for (int i = 0; i < count; i++) {
			if (cars[i].getCarProfit() == temp2 && !carBool) {
				System.out.print(" " + cars[i].getcarClass());
				carBool = true;
			}
			else if (cars[i].getCarProfit() == temp2 && carBool) {
				System.out.print(" - " + cars[i].getcarClass());
			}
		}
		System.out.println();
		float total = 0;
		for (int i = 0; i < cCount; i++) {
			total += contracts[i].getEndDate().getDay() - contracts[i].getStartDate().getDay();
		}
		if(cCount == 0) {
			System.out.println("The average number of days the cars are rented: 0 days");
		}
		else {
			System.out.println("The average number of days the cars are rented: " + total / cCount + " days");
		}
	
	}
	
	public void listEmployeeProfit() {
		int temp = 0;
		
		System.out.print("The most profitable employee(s):");
		for(int i = 0; i < eCount; i++) {
			if(employees[i].getEmpProfit() > temp) {
				temp = employees[i].getEmpProfit();
			}
		}
		for(int i = 0; i < eCount; i++) {
			if(employees[i].getEmpProfit() == temp) {
				System.out.print(" Employee" +   employees[i].getEmployeeId() + ";" + employees[i].getName() + ";" + employees[i].getSurname() + " (" + 
			employees[i].getEmpIncome() + " - " + employees[i].getEmpExpense() + " = " + (employees[i].getEmpIncome() - employees[i].getEmpExpense()) + "cp)");
			}
		}
		System.out.println();
	}
	  public void deleteContract(int day) {
	    	contracts[day] = null;		
			for(int i = day; i < cCount; i++) {
				contracts[i] = contracts[i + 1];				
			}
			cCount--;
	    }
	  
	public void averageIncome(Employee[] employees) {
		float avgProfit = 0;
		boolean avgBoolean = false;
		for(int i = 0; i < eCount; i++) {
			avgProfit += employees[i].getEmpProfit();
		}
		avgProfit = avgProfit/eCount;
		System.out.print("Average income levels of the employees for the office: (");
		for(int i = 0; i < eCount; i++) {
			if(!avgBoolean) {
				System.out.print(employees[i].getEmpProfit());
				avgBoolean = true;
			}
			else {
				System.out.print(" + " +employees[i].getEmpProfit());
			}
		}
		System.out.print(")\\" + eCount + " = " + avgProfit);
		System.out.println();
	}
	
	public void deleteEmployee(int count, Employee[] employeeArray){
			int id = employees[count].getEmployeeId();
			employees[count] = null;
			employeeArray[id - 1] = null;
			for(int i = count; i < employees.length - 1; i++) {
				employees[i] = employees[i + 1];				
			}
			for(int i = id; i < Employee.getId(); i++) {
				employeeArray[i] = employeeArray[i + 1];
			}
			Employee.decreaseId();
			eCount--;
			expenseDeleted+=200;
	}
	
	public int getOfficeId() {return OfficeId;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getAddress() {return address;}
	public String getCity() {return city;}
	public int getcCount() {
		return cCount;
	}
	public int geteCount() {
		return eCount;
	}
	public Contract[] getContracts() {
		return contracts;
	}
	public Car[] getCars() {return cars;}
	public int getCounter() {return eCount;}
	public Employee[] getEmployee() {return employees;}
	public void decreaseOfficeId() {OfficeId--;}
	public static void decreaseId() {Id--;}

	public void setCars(Car[] cars) {
		this.cars = cars;
	}
	
    public void setAddress(String address) {
		this.address = address;
	}
    public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    public void setCity(String city) {
		this.city = city;
	}
	
	public static int getId() {
		return Id;
	}
	public static void addOffice(String[] info, Office[] officeArray) {
		officeArray[Office.getId() - 1] = new Office();
		officeArray[Office.getId() - 2].setPhoneNumber(info[1]);
		officeArray[Office.getId() - 2].setAddress(info[2]);
		officeArray[Office.getId() - 2].setCity(info[3]);	
	}
	public static void listOffice(Office[] officeArray) {
		for(int i = 0; i < Office.getId() - 1; i++) {		
			System.out.println("\t" + officeArray[i].getOfficeId() +".Office;" + officeArray[i].getPhoneNumber() + ";" +  officeArray[i].getAddress() + ";" + officeArray[i].getCity());
		}		
	}
    public void printProfit(Contract[] contract,Employee[] employee, int[] km) {
    	Contract[] testContract = new Contract[Contract.getId() - 1];
    	int index = 0;
    	for(int i = 0; i < Contract.getId() - 1; i++) {
    		int empId = 0;
    		for (int j = 0; j < eCount; j++) {
    			if(contract[i].getEmployeeId() == j) {
    				empId = j - 1;
    			}
    		}
    		for(int j = 0; j < count; j++) {
    			if(contract[i].getCarId() == cars[j].getCarId()) {
    				if(cars[j].getcarClass().equals("luxury")) {
    					if(km[j] == 100) {
    						expenseKm += 15;
    						cars[j].setCarProfit(15);
    						employee[i].setEmpExpense(15);   					
    					}
        				else if(km[j]==200) {
        					expenseKm += 30;
        					cars[j].setCarProfit(30);
        					employee[i].setEmpExpense(30);        					
        				}
        				else if(km[j]==300) {
        					expenseKm += 45;
        					cars[j].setCarProfit(45);
        					employee[i].setEmpExpense(45);       					
        				}
    					if(contract[i].getBonus()) {
    						expenseEmpBonus[index] = employee[empId].setBonus(15);
    						contract[i].setBonus();
    					}								
    					index++;
    					income += 300;
    					expenseClass += 120;
    					cars[j].setCarProfit(300, 120);
    					employee[i].setEmpIncome(300);    					
    					employee[i].setEmpExpense(120);   					
    				}
    				else if(cars[j].getcarClass().equals("sports")) {
    					if(km[j] == 100) {
    						expenseKm += 10;
    						cars[j].setCarProfit(10);
    						employee[i].setEmpExpense(10);  						
    					}
        				else if(km[j]==200) {
        					expenseKm += 20;
        					cars[j].setCarProfit(20);
        					employee[i].setEmpExpense(20);        				
        				}
        				else if(km[j]==300) {
        					expenseKm += 30;
        					cars[j].setCarProfit(30);
        					employee[i].setEmpExpense(30);        					
        				}
    					if(contract[i].getBonus()) {
    						expenseEmpBonus[index] = employee[empId].setBonus(10);  
    						contract[i].setBonus();
    					}
    					 				
    					index++;
    					income += 200;
    					expenseClass += 70;
    					cars[j].setCarProfit(200, 70);
    					employee[i].setEmpIncome(200);   					
    					employee[i].setEmpExpense(70);  					
    				}
    				else if(cars[j].getcarClass().equals("economy")) {
    					if(km[j] == 100) {
    						expenseKm += 5;
    						cars[j].setCarProfit(5);
    						employee[i].setEmpExpense(5);   						
    					}
        				else if(km[j]==200) {
        					expenseKm += 10;	
        					cars[j].setCarProfit(10);
        					employee[i].setEmpExpense(10);       					
        				}
        				else if(km[j]==300) {
        					expenseKm += 15;
        					cars[j].setCarProfit(15);
        					employee[i].setEmpExpense(15);       					
        				}
    					if(contract[i].getBonus()) {
    						expenseEmpBonus[index] = employee[empId].setBonus(5);  			
    						contract[i].setBonus();
    					}				
    					index++;
    					income += 100;
    					expenseClass += 20;
    					cars[j].setCarProfit(100, 20);
    					employee[i].setEmpIncome(100);    					
    					employee[i].setEmpExpense(20);
    				}
    				employee[i].setEmpProfit(employee[i].getEmpIncome(), employee[i].getEmpExpense());
    				testContract[i] = contract[i];
    			}
    		}
    	}
    	System.out.println("Office" + OfficeId+ " Incomes: " + income);
    	for(int i = 0; i < testContract.length; i++) {
    		for(int j = 0; j < count; j++) {
    			if(testContract[i] != null) {
    	 			if(testContract[i].getCarId() == cars[j].getCarId()) {
        				if(cars[j].getcarClass().equals("luxury")) {
                			System.out.println("Car" + testContract[i].getCarId()+": " + 300);
                		}
                		else if(cars[j].getcarClass().equals("sports")) {
                			System.out.println("Car" + testContract[i].getCarId()+": " + 200);
            			}
            			else if(cars[j].getcarClass().equals("economy")) {
            				System.out.println("Car" + testContract[i].getCarId()+": " + 100);
            			}
        				break;
            		}
    			}
   
        	
    		}
    	
    	}
    	for(int i = 0; i < eCount; i++) {
    		expenseEmpSalary += 30;
    	}
    	int totalBonus = 0;
    	for(int i = 0; i < expenseEmpBonus.length; i++) {
    		totalBonus+= expenseEmpBonus[i];
    	}
    	expense = expenseClass + expenseKm + totalBonus + 100 + expenseEmpSalary + expenseDeleted;
    	profit = income-expense;
    	
    	System.out.println("Office"+OfficeId+" expenses: "+ expense);
    	System.out.println("Office rent: 100");
    	if(expenseDeleted != 0)
    	System.out.println("Terminated employee contract compansation: " + expenseDeleted);
    	System.out.println("Employee salaries: " + expenseEmpSalary);
    	System.out.print("Employee Bonuses: ");
    	for(int i = 0; i < eCount; i++) {
    		if(expenseEmpBonus[i] != 0) {
    			if(i != eCount -1) System.out.print(expenseEmpBonus[i] + " + ");
    			
        		else System.out.println(expenseEmpBonus[i] + " = " + totalBonus);    		
    		}
    		
    	}
    	
    	for(int i = 0; i < testContract.length; i++) {
    		for(int j = 0; j < count; j++) {
    			if(testContract[i] != null) {
    				if(testContract[i].getCarId() == cars[j].getCarId()) {
        				if(cars[j].getcarClass().equals("luxury")) {
        					if(km[j] == 100) {
                				System.out.println("Car" + testContract[i].getCarId()+" maintenance : " + 120+" + "+15+" = 135 (100 km)");
                				}
                			else if(km[j] == 200) {
                				System.out.println("Car" + testContract[i].getCarId()+" maintenance : " + 120+" + "+30+" = 150 (200 km)");
                				}
                			else if(km[j] == 300) {
                				System.out.println("Car" + testContract[i].getCarId()+" maintenance : " + 120+" + "+45+" = 165 (300 km)");
                				}
        				}       			            		
    	        		else if(cars[j].getcarClass().equals("sports")) {
    	        			if(km[j] == 100) {
    	        				System.out.println("Car" + testContract[i].getCarId()+" maintenance : " + 70+" + "+10+" = 80 (100 km)");
    	        				}
    	        			else if(km[j] == 200) {
    	        				System.out.println("Car" + testContract[i].getCarId()+" maintenance : " + 70+" + "+20+" = 90 (200 km)");
    	        				}
    	        			else if(km[j] == 300) {
    	        				System.out.println("Car" + testContract[i].getCarId()+" maintenance : " + 70+" + "+30+" = 100 (300 km)");
    	        				}
    	        			
    	    			}
    	    			else if(cars[j].getcarClass().equals("economy")) {
    	    				if(km[j] == 100) {
    	        				System.out.println("Car" + testContract[i].getCarId()+" maintenance : " + 20+" + "+5+" = 25 (100 km)");
    	        				}
    	    				else if(km[j] == 200) {
    	        				System.out.println("Car" + testContract[i].getCarId()+" maintenance : " + 20+" + "+10+" = 30 (200 km)");
    	        				}
    	    				else if(km[j] == 300) {
    	        				System.out.println("Car" + testContract[i].getCarId()+" maintenance : " + 20+" + "+15+" = 35 (300 km)");
    	        				}
    	    			}
        			}
    			}
    
    		}		
    	}
    	System.out.println("Office"+ OfficeId +" profit : " + profit + "\n"); 	
    }
    
    
    public void resetAll() {
    	 income = 0;
    	 expense = 0;
    	 expenseClass = 0;
    	 expenseDeleted=0;
    	 for(int i = 0; i < expenseEmpBonus.length; i++) {
    		 expenseEmpBonus[i] = 0;
    	 }
    	 expenseKm = 0;
    	 profit = 0;
    	 expenseEmpSalary = 0;
    	 for(int i = 0; i < eCount; i++) {
    		 employees[i].setStandart();
    	 }
    }
    public static void deleteOffice(String[] info, Office[] officeArray) {
    	officeArray[Integer.parseInt(info[1]) - 1] = null;
		if(Office.getId() != 100) {
			for (int i = Integer.parseInt(info[1]) - 1; i < Office.getId(); i++) {
				officeArray[i] = officeArray[i + 1];	
				if (officeArray[i] != null) {
					officeArray[i].decreaseOfficeId();
				}
			}			
		}						
		Office.decreaseId();
    }
	public void empRent() {
		int compare = 0;
		
		for(int i = 0; i < eCount; i++) {		
			if(employees[i].getRentCount() > compare) {
				compare = employees[i].getRentCount();
			}
		}
		boolean empBool = false;
		System.out.print("The employee who rented the most: ");
		for(int i = 0; i < eCount; i++) {
			if(employees[i].getRentCount() == compare && !empBool) {
				System.out.print(employees[i].getName());
				empBool = true;
			}
			else if(employees[i].getRentCount() == compare && empBool) {
				System.out.print(" - " + employees[i].getName());
			}
		}
		System.out.println();
	}
	public void recommendations() {		
		int compare = 0;
		boolean notDismissed = true;
		if(Date.getDayCount() % 3 == 0) {
			System.out.println("--- System Recommendations ---");
			if(noEmployee == 30) {
				System.out.println("You may close this office.");
			}
			else{
				for(int i = 0; i < eCount; i++) {
					if(employees[i].getDismissCount() == 3 && notDismissed) {
						System.out.print("You may fire this employee(s): Employee" + employees[i].getEmployeeId() + " " + employees[i].getName() + " " + employees[i].getSurname());
						notDismissed = false;
					}
					else if(employees[i].getDismissCount() == 3 && !notDismissed) {
						System.out.print("-Employee" + employees[i].getEmployeeId() + " " + employees[i].getName() + " " + employees[i].getSurname());
					}
				}
				if(notDismissed) {
					int [] temp = new int[5];
					temp[0] = economyDemand;
					temp[1] = sportsDemand;
					temp[2] = luxuryDemand;
					temp[3] = exceedEmployee;
					temp[4] = insufficientEmployee;
					int[] randomizer = new int[5];
					int randIndex = 0;
 					
					for(int i = 0; i < temp.length; i++) {
						if(compare < temp[i]) {
							compare = temp[i];
						}
					}
					for(int i = 0; i < temp.length; i++) {
						if(compare == temp[i]) {
							randomizer[randIndex] = i;
							randIndex++;
						}
					}
					Random random = new Random();
					randIndex = random.nextInt(randIndex);
					if(randomizer[randIndex] == temp[0]) {
						System.out.println("You may buy a new car. (economy class)");
					}
					else if(randomizer[randIndex] == temp[1]) {
						System.out.println("You may buy a new car. (sports class)");
					}
					else if(randomizer[randIndex] == temp[2]) {
						System.out.println("You may buy a new car. (luxury class)");
					}
					else if(randomizer[randIndex] == temp[3]) {
						System.out.println("You may open a new office.");
					}
					else if(randomizer[randIndex] == temp[4]) {
						System.out.println("You may hire a new employee.he");
					}
				}				
			}
		
			
			
		}
	
	}
	public void setNoEmployee() {
		noEmployee += 10;
	}
	
	public void setEconomyDemand() {
		economyDemand += 1;
	}
	
	public void setSportsDemand() {
		sportsDemand += 2;
	}
	
	public void setLuxuryDemand() {
		luxuryDemand += 3;
	}

	public void setExceedEmployee() {
		exceedEmployee += 3;
	}
	public void setInsufficientEmployee() {
		insufficientEmployee += 2;
	}
}

