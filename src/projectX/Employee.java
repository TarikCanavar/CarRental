package projectX;
public class Employee{
	private static int Id = 1;
	private int employeeId;  // we are creating our attributes here Id starts from 1 and it is static beacuse every
	private String name;   // time a employee created we are incrementing Id
	private String surname;
	private String gender;
	private String birthDate;
	private boolean isAvailable; // if employee created a contract that day if he/she did then not available for that day
	private int salary;          
	private int income = 0;
	private int rentCount;      // this attributes needed for office statistics we will come to that later
	private int empIncome;
	private int empExpense;
	private int empProfit;
	private int dismissCount;
	
	
	
	public Employee(String name,String surname,String gender,String birthDate,int officeId) {
		this.name=name;
		this.surname=surname;
		this.gender=gender; 
		this.birthDate=birthDate;
		salary = 30;
		isAvailable = true;
		employeeId=Id;
		Id++;
		rentCount = 0;      // initializing our attributes
		empIncome = 0;
		empExpense = 0;
		empProfit = 0;
		dismissCount = 0;
	}
	public int getEmpExpense() {     // these three getters stand for office statistics calculating and printing them
		return empExpense;
	}
	public int getEmpIncome() {
		return empIncome;
	}
	public int getEmpProfit() {
		return empProfit;
	}
	public void setEmpExpense(int empExpense) { // adds to expense of employee
		this.empExpense += empExpense;
	}
	public void setEmpIncome(int empIncome) {   // adds to income of employee
		this.empIncome += empIncome;
	}
	public void setEmpProfit(int empIncome, int empExpense) {     // income- expense becomes profit of employee
		empProfit = empIncome - empExpense;
	}
	public int getDismissCount() {     // these to stand for system recommendations we will come to that later in code
		return dismissCount;
	}
	public void setDismissCount() {
		dismissCount += 1;
	}
	
	public int getEmployeeId() {return employeeId;} // simple getters for employee 
	public String getName() {return name;}
	public String getSurname() {return surname;}
	public String getGender() {return gender;}
	public String getBirthDate() {return birthDate;}
	public static int getId() {return Id - 1;}
	public static void decreaseId() {Id--;} // when an employee is deleted calling this 
	public int getRentCount() {   // for statistics 
		return rentCount;
	}
	public void addRentCount(int rentCount) {
		this.rentCount += rentCount; // adding the parameter to rentCount
	}
	
	

	public void setName(String name) {
		this.name = name;
	}
	
	
	public boolean getStatus() { // controls for if employee is available to make a contract that day
		return isAvailable;
	}
	public void setStatus() {
		isAvailable = !isAvailable;
	}
    public int setBonus(int bonus) { // adds to salary of employee and returns it's value
    	salary+= bonus;
    	return bonus;
    }
    public void increaseIncome() { // adding salary to income of employee
       income += salary;
    }
    public void setStandart() { // salary should stay 30 everyday calling this to set it again
    	salary = 30;
    }
	public static void deleteEmployee(String[] info, Office[] officeArray,Employee[] employeeArray) { // when user enters delete 
		if(officeArray[Integer.parseInt(info[1]) - 1].getCounter() != 0) {       // employee w take office and employee array with
			officeArray[Integer.parseInt(info[1]) - 1].deleteEmployee(Integer.parseInt(info[2]) - 1, employeeArray); //info
			}    // when office id is not equal to 0 we are calling our deleteEmployee function for that office
			else { // if it is zero displaying error message
				System.out.println("You can't delete employees from this office because there are none.");
			}
	}         // for listEmployee since we are in employee class already we are taking office array and info
	public static void listEmployee(String[] info, Office[] officeArray) {
		Employee[] empTest = officeArray[Integer.parseInt(info[1]) - 1].getEmployee(); // creating an array employee class type 
        for(int i = 0; i < empTest.length;i++) { //for loop goes in bound of length of our array created up there 
        	if(empTest[i] != null) { // when it is not null listing employees with our getters
        		System.out.println("\t"+ empTest[i].getEmployeeId() +".Employee;"+empTest[i].getName()+";"+ empTest[i].getSurname()+";"+empTest[i].getGender()+";"+ 
        	empTest[i].getBirthDate() + ";" + (Integer.parseInt(info[1])));
        	}
        }
	} // when addEmployee is command from user we should take employee and office array because we should add it to both
	public static void addEmployee(String[] info, Office[] officeArray, Employee[] employeeArray) { // if there are already 
		if(officeArray[Integer.parseInt(info[5]) - 1].getCounter() < 3) { // 3 employee we can't add
			  employeeArray[Employee.getId()] = new Employee(info[1],info[2],info[3],info[4],Integer.parseInt(info[5])); 
            officeArray[Integer.parseInt(info[5]) - 1].addEmployee(employeeArray[Employee.getId() - 1]); // adding both to office           
		} // and employee array
		else {   // when it didn't enter the if statement up there this means there are already 3 employees so user can't enter more
			System.out.println("You can't add more employees to this office."); 
		} 
	}
}
