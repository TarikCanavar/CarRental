package projectX;

public class Car {            // simple attributes for car
	private static int Id = 1;
	private int carId;
	private String brand;
	private String model;
	private String carClass;
	private int km = 0;
	private boolean isAvailable;
	private int rentCount; // for office statistics 
	private int carProfit;
	 

	public Car() { // constructor for car 
		isAvailable = true;
		carId=Id;
		Id++;
		rentCount = 0; // initialized the attributes which are for statistics
		carProfit = 0;
	}
	
	public void setBrand(String brand) { // simple setters
		this.brand = brand;  
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}
	public int getCarId() {return carId;}      //simple getters
	public String getBrand() {return brand;}
	public String getModel() {return model;}
	public String getcarClass() {return carClass;}
	public int getKm() {return km;}
	public int getRentCount() {return rentCount;}
	public int getCarProfit() {return carProfit;}
	
	public static int getId() {
		return Id;
	}
	public void setStatus() {      // setting the availability of car
		isAvailable = !isAvailable;
	}
	public boolean getStatus() { // if car is already under a contract or not returning bool
		return isAvailable;
	}
	public void setKm(int km) {
		this.km += km;
	}
	public void setCarProfit(int a, int b) {carProfit = carProfit + (a-b);} // added the difference between parameters to profit which are income end expense of car
	public void setCarProfit(int a) {carProfit -= a;} // overload function
	public void increaseRentCount() {rentCount++;}     // when a car is rented incrementing count for statistics
	public static void listCar(String[] info, Office[] officeArray) { // to list car taking office array and info
		Car[] carTest = officeArray[Integer.parseInt(info[1]) - 1].getCars();	// creating an array for car class
		for(int i = 0; i < carTest.length; i++) { //goes through the cars of that office
			if(carTest[i] != null) { //  if not null
				System.out.println("\t" + carTest[i].getCarId() +".Car;" + carTest[i].getBrand() + ";" +  carTest[i].getModel() + ";" + carTest[i].getcarClass() + ";" + 
			carTest[i].getKm() + ";" + (Integer.parseInt(info[1]))); // displaying all info about car 
			}				
		}
	}
	public static void addCar(String[] info, Office[] officeArray, Car[] carArray) { // when user wants to add a car 
		carArray[Car.getId() - 1] = new Car(); // creating a new car 
		carArray[Car.getId() - 2].setBrand(info[1]); // taking attributes with getters
		carArray[Car.getId() - 2].setModel(info[2]);
		carArray[Car.getId() - 2].setCarClass(info[3]);
		carArray[Car.getId() - 2].setKm(Integer.parseInt(info[4]));
		officeArray[Integer.parseInt(info[5]) - 1].addCar(carArray[Car.getId() - 2]); // adding car to office 	
	}
}
