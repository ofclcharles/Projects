package CustomerPriorityQueue;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TestCustomer {
		public static void main(String[] args) {
			try {
				ArrayList<String> list = new ArrayList<String>();
				int num = asker();
				for(int i = 1; i < num; i++) { // runs input() "num" times
					System.out.println("--------------------------------------------------------------------------------------");
					list.add(input());
				}
				System.out.print("Customers on the list are: " + list);
			} catch(Exception e) {
				System.out.print("Wrong input. Try again");
				asker();
			}
			
		}	
		public static int asker() {
			//for(int i = 0; i < 10; i++) {
			//	System.out.print((int)(Math.random()*4.0));
			//}
			//System.out.print(" ");
			
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter number of customers to be accessed: ");
			
			int num = scan.nextInt() + 1; int i;
			
			return num;
			
		}

		public static String input() {
			BankComparator comparator = new BankComparator();
	        PriorityQueue<Customer> queue = new PriorityQueue<Customer>(10, comparator);
	        
			Scanner scanner = new Scanner(System.in);
			System.out.print("Customer First Name: "); String fName = scanner.next();
			System.out.print("Customer Last Name: "); String lName = scanner.next();
			System.out.print("Account Number(4 digits): "); int acc = scanner.nextInt();
			System.out.print("Is a customer (True/False): "); boolean isCustomer = scanner.nextBoolean();
			System.out.print("Acct Balance: "); double balance = scanner.nextDouble();
			System.out.print("Property Cost: "); double propertyCost = scanner.nextDouble();
			System.out.print("Work Length(In months): "); int workLength = scanner.nextInt();
			
			
			Customer c1 = new Customer(fName, lName, acc, workLength, propertyCost, balance, isCustomer);
					
			if((c1.getbalance() >= (0.05 * c1.getPropertyCost())) & (c1.isCustomer == true) & (c1.getWorkLength() >= 6)) {			
				queue.add(c1);
				//queue2.add(c1);
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println(fName + " " + lName + " has been added to the queue.");
				return fName + " " + lName;
				
			} else {
				
				if(c1.isCustomer == false) {
					System.out.println("--------------------------------------------------------------------------------------");
					System.out.println(fName + " " + lName  + " does not qualify for the queue because they are not a customer of the bank.");
				} else if((c1.getbalance() < (0.05 * c1.getPropertyCost()))) {
					System.out.println("--------------------------------------------------------------------------------------");
					System.out.println(fName + " " + lName + " does not qualify for the queue because balance is less than 5% of the propery cost.");
				} else if((c1.getWorkLength() < 6)){
					System.out.println("--------------------------------------------------------------------------------------");
					System.out.println(fName + " " + lName + " does not qualify for the queue because this customer has not worked up to 6 months.");
				}
			}
			
			return null;
		
			}
		}