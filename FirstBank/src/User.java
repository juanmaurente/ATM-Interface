import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	private String firstName;
	private String lastName;
	private String uuid;
	private byte pinHash[];
	private ArrayList<Account> accounts;
	
	//Constructor
	public User(String firstName, String lastName, String pin, Bank theBank) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		
		//Store the pin's MD5 hash, rather than the original value for security purposes
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		// get a new, unique universal id for the user
		this.uuid = theBank.getNewUserUUID();
		
		// create an empty list of accounts
		this.accounts = new ArrayList<Account>();
		
		//print log message
		System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
	}
	//End of Constructor
	
	//Add an account for the user
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	public String getUUID() {
		return this.uuid;
	}

	public boolean validatePin(String pin) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
	}

	
	public Object getFirstName() {
		
		return this.firstName;
	}
	
	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for (int a=0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a+1, this.accounts.get(a).getSummaryLine()  );
		}
		System.out.println();
	}

	public int numAccounts() {	
		return this.accounts.size();
	}

	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
		
	}

	public double getAcctBalance(int acctIdx) {
		
		return this.accounts.get(acctIdx).getBalance();
	}

	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
		
	}

	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
	
}
