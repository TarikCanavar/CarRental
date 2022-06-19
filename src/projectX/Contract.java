package projectX;

public class Contract {

    private static int Id = 1;
    private int contractID;
    private Date startdate;
    private Date enddate;
    private int customerID;
    private int carID;
    private int empID;
    private boolean bonus;
    public Contract(int employeeId, Date inputStartDate, Date inputEndDate, int inputCustomerID, int inputCarID) {

        contractID = Id;
        Id++;
        startdate = inputStartDate; 
        enddate = inputEndDate;
        customerID = inputCustomerID;
        carID = inputCarID;
        empID = employeeId;
        bonus = true;
    }

    public Date getStartDate() {
        return startdate;
    }

    public Date getEndDate() {
        return enddate;
    }
    public int getContractId() {
    	return contractID;
    }
    public int getCustomerId() {
    	return customerID;
    }
    public int getCarId() {
    	return carID;
    }
    public int getEmployeeId() {
    	return empID;
    }
    public static int getId() {
		return Id;
	}
    public boolean getBonus() {
    	return bonus;
    }
    public void setBonus() {
    	bonus = !bonus;
    }
    public void employeeBonus(Employee[] employee, Car[] car) {
    	String carClass = "";
    	for(int i = 0; i < Car.getId() - 1; i++) {
    		if(car[i].getCarId() == carID) {
        		carClass = car[i].getcarClass();
        	}
    	}
    	for(int i = 0; i < Employee.getId() - 1; i++) {
    		if(employee[i].getEmployeeId() == empID) {
    			if(carClass.equals("economy")) {
    				employee[i].setBonus(5);
    			}
    			else if(carClass.equals("sports")) {
    				employee[i].setBonus(10);
    			}
    			else if(carClass.equals("luxury")) {
    				employee[i].setBonus(15);
    			}
        		
        	}
    	}
    	   	
    }
    public static void listContract(Contract[] contractArray) {
    	for(int i = 0; i < contractArray.length; i++) {
			if(contractArray[i] != null) {
				System.out.println("\t"+ contractArray[i].getContractId() +".Contract:Employee"+contractArray[i].getEmployeeId()+";"+ contractArray[i].getCustomerId()+";Car"
			+contractArray[i].getCarId()+";" +contractArray[i].getStartDate().getAllInfo() + ";" + contractArray[i].getEndDate().getAllInfo());
			}			
		}
    }
    public static void checkEndDate(Date startingDay, Contract[] contractArray, Employee[] employeeArray, Car[] carArray, Office[] officeArray) {
    	for(int i = 0; i < Contract.getId() - 1; i++) {
    		boolean month1 = (((contractArray[i].getEndDate().getMonth() % 2 == 1 && contractArray[i].getEndDate().getMonth() != 2) || contractArray[i].getEndDate().getMonth() == 8 || 
            contractArray[i].getEndDate().getMonth() == 10 || contractArray[i].getEndDate().getMonth()  == 12) 
            && contractArray[i].getEndDate().getDay() == 31) && (startingDay.getMonth() == contractArray[i].getEndDate().getMonth() + 1 || startingDay.getYear() == contractArray[i].getEndDate().getYear() + 1);
    		
    		boolean month2 = (contractArray[i].getEndDate().getDay() == 30 && (contractArray[i].getEndDate().getMonth() == 4 || contractArray[i].getEndDate().getMonth() == 6 || contractArray[i].getEndDate().getMonth() == 9 
    		|| contractArray[i].getEndDate().getMonth() == 11)) && startingDay.getMonth() == contractArray[i].getEndDate().getMonth() + 1;
    		
    		boolean month3 = contractArray[i].getEndDate().getMonth() == 2 && contractArray[i].getEndDate().getDay() == 28 && startingDay.getMonth() == contractArray[i].getEndDate().getMonth() + 1;
    		
    		boolean month4 = contractArray[i].getEndDate().getDay() + 1 == startingDay.getDay() && startingDay.getMonth() == contractArray[i].getEndDate().getMonth();
    		if(month1 || month2 || month3 || month4) {
    		
    			for(int j = 0; j < Employee.getId() - 1; j++) {
    				if(contractArray[i].getEmployeeId() == employeeArray[j].getEmployeeId()){
    					employeeArray[j].setStatus();
    					break;
    				}
    			}
    			for(int j = 0; j < Car.getId() - 1; j++) {
    				if(contractArray[i].getCarId() == carArray[j].getCarId()) {
    					carArray[j].setStatus();
    					break;
    				}
    			}
    			
    			boolean breaker = false;
    			for(int j = 0; j < Office.getId() - 1; j++) {
    				for(int k = 0; k < officeArray[j].getcCount() ; k++) {
        				if(contractArray[i].getContractId() == officeArray[j].getContracts()[k].getContractId()) {
        					officeArray[j].deleteContract(k);
        					breaker = true;
        					break;
        				}
        			}
    				if(breaker) break;
    			}
    			
    			Contract.deleteContract(contractArray, i);
    			i--;
    		}
    	}
    }
    public static void deleteContract(Contract [] contractArray, int index) {
    	contractArray[index] = null;		
		for(int i = index; i < Contract.getId() - 1; i++) {
			contractArray[i] = contractArray[i + 1];				
		}
		Id--;
    }
}
