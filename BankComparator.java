package CustomerPriorityQueue;

import java.util.Comparator;

public class BankComparator implements Comparator<Customer>{
	public int compare(Customer c1, Customer c2) {
		
		if(c1.getbalance() > (0.05 * c1.getPropertyCost()))
		{return 1;}
		
		else 
		
		if(c1.getbalance() < (0.05 * c1.getPropertyCost())) 
		{return -1;}
		
		return 0;
	}
		
}
