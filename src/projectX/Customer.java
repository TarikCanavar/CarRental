package projectX;
public class Customer { // simple attributes for customer
	private static int Id = 1;
	private int customerId;
	private String name;
	private String surname;
	private int rentCount = 0; // for customer who rented most 
	
	public Customer() {			// constructor for customer incrementing the Id
		customerId=Id;
		Id++;
	}
		public int getCustomerId() {return customerId;} // getters for customer
		public String getName() {return name;}
		public String getSurname() {return surname;}
		public int getRentCount() {	return rentCount;}
		public void addRentCount(int rentCount) { // when customer rents a car adding to his/her count
			this.rentCount += rentCount;
		}
		public void setName(String name) {          // setters for name and surname
			this.name = name;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
		
		public static int getId() { // to get Id
			return Id;
		}
		public static void addCustomer(String[] info, Customer[] customerArray) { // when user adds a customer 
			customerArray[Customer.getId() - 1] = new Customer(); // creating a new customer
			customerArray[Customer.getId() - 2].setName(info[1]); // taking name and surname 
			customerArray[Customer.getId() - 2].setSurname(info[2]);
		}
		public static void listCustomer(Customer[] customerArray) { // to list customer taking array
			for(int i = 0; i < Customer.getId() - 1; i++) {    // going through customers and listing all
				System.out.println("\t" + customerArray[i].getCustomerId() + ".Customer;" + customerArray[i].getName() + ";" + customerArray[i].getSurname());
			}
		}
		public static void compareCustomer(Customer[] customerArray) { // for customer who rented most
			int compare = 0;  // assigning compare to 0 because we will be looking for max
			boolean check = false;   
			for(int i = 0; i < Customer.getId() -1; i++) { // going through customers
				if(customerArray[i].getRentCount() > compare) { // comparing rentCount
					compare = customerArray[i].getRentCount();// when it is bigger then compare is assigned to that 
				}
			}
			System.out.print("\nThe customer(s) who rented the most:");
			for(int i = 0; i < Customer.getId() - 1;i++) { // since we already now compare 
				if(customerArray[i].getRentCount() == compare && !check) { // if compare is equal to rentCount of customer			
					System.out.print(" " + customerArray[i].getName() + " " + customerArray[i].getSurname());//we found our customer
					check = true; // who rented most so printing name and surname assigned bool to true
				}
				else if(customerArray[i].getRentCount() == compare && check) { // when there are more then one customer with same 
					System.out.print(" - " + customerArray[i].getName() + " " + customerArray[i].getSurname()); // count then
				} // displaying the other customer(s) too
			}
			System.out.println();
		}

}
