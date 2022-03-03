import java.util.ArrayList;
import java.util.Random;

public class Bank {

	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	
	//Create a new bank
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	
	//Generate a UUID for a User
	public String getNewUserUUID() {
		
		String uuid;
		Random rng = new Random(); //Random number generator.
		int len = 6;
		
		// flag for non-uniqueness
		boolean nonUnique;
		
		// to do something once, then continue looping while flag is no true
		do {
			// generate the number
			uuid = "";
			
			for (int c=0; c< len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString(); //first rng.nextInt generates a new integer between 0 and 9. then it cast it to the class integer so we can use methods like to String
			}
			
			// check the uuid generated is unique. For each user (u) in all of the list of users
			nonUnique = false;
			for(User u: this.users) {
				if (uuid.compareTo(u.getUUID()) == 0) {
					nonUnique =true;
					break;
				}
			}
		} while (nonUnique);
		
		return uuid;
	}
	
	//Generate a UUID for a Account
	public String getNewAccountUUID() {
		String uuid;
		Random rng = new Random(); //Random number generator.
		int len = 10;
		
		// flag for non-uniqueness
		boolean nonUnique;
		
		// to do something once, then continue looping while flag is no true
		do {
			// generate the number
			uuid = "";
			
			for (int c=0; c< len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString(); //first rng.nextInt generates a new integer between 0 and 9. then it cast it to the class integer so we can use methods like to String
			}
			
			// check the uuid generated is unique. For each user (u) in all of the list of users
			nonUnique = false;
			for(Account a: this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					nonUnique =true;
					break;
				}
			}
		} while (nonUnique);
		
		return uuid;
	}
	
	//Add an account for the bank
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	//Add a new user
	public User addUser(String firstName, String lastName, String pin) {
		
		// create a new User object and add it to our list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		// create a savings account
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
		
	//login method. get users object associated with userid and pin, and validate credentials
	public User userLogin(String userID, String pin) {
		
		// search through list of users to find the user's id
		for(User u: this.users) {
			
			// check user id is correct
			if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
				
			}
		}
		// if we haven't found the user or have an incorrect pin
		return null;
	}
	
	// Get the name of the 

	public String getName() {
		
		return this.name;
	}
	
}
