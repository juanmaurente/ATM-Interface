import java.util.ArrayList;

public class Account {

	private String name;
	private String uuid;
	private User holder;
	private ArrayList<Transaction> transactions;
	
	
	public Account(String name, User holder, Bank theBank) {
		this.name = name;
		this.holder = holder;
		
		this.uuid = theBank.getNewAccountUUID();
		
		this.transactions = new ArrayList<Transaction>();
		
	}
	// End of Constructor
	
	
	/**
	 * 
	 * @return
	 */
	public String getUUID() {
		return this.uuid;
	}
	
	// Get summary line for the account and return the string summary
	public String getSummaryLine() {
		
		// get the account balance
		double balance = this.getBalance();
		
		// format the summary line, depending on positive or balance
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
		}
	}
	
	
	public double getBalance() {
		
		double balance = 0;
		for (Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}

	
	
	/**
	 * Print transaction history for the Account 
	 */
	public void printTransHistory() {

		System.out.printf("\nTransaction history for account %s \n", this.uuid);
		for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
		
	}


	
	/**
	 * Add a new transaction in this account
	 * @param amount
	 * @param memo
	 */
	public void addTransaction(double amount, String memo) {
		
		// create new transaction object and add it to our list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
		
	}
	
	
}
