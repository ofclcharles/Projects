package CustomerPriorityQueue;

public class Customer {
	
	private int acc; int workLength; double propertyCost; double balance; boolean isCustomer; String fName; String lName;
	//name, acc, workLength, propertyCost, balance, isCustomer
	public Customer(String fName, String lName, int acc, int workLength, double propertyCost, double balance, boolean isCustomer) {
		this.fName = fName;
		this.lName = lName;
		this.acc = acc;
		this.balance = balance;
		this.isCustomer = isCustomer;
		this.propertyCost = propertyCost;
		this.workLength = workLength;
	}		
	


	public String getlName() {
		return lName;
	}
	
	public String getfName() {
		return fName;
	}

	public int getAcc() {
		return acc;
	}

	public int getWorkLength() {
		return workLength;
	}

	public double getPropertyCost() {
		return propertyCost;
	}

	public double getbalance() {
		return balance;
	}

	public boolean isisCustomer() {
		return isCustomer;
	}
	
	public String toString() {
		String string =  "Account number: " + getAcc() + ", Is Customer: " + isisCustomer() 
		+ ", Has Worked: " + getWorkLength() + ", Property Cost: " + getPropertyCost()
		+ ", Account Balance: " + getbalance(); 
		
		return string;
	}
	
}
