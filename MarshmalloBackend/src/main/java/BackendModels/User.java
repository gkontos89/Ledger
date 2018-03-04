package BackendModels;

import java.util.ArrayList;

/**
 * A User is the master class that represents any one person that is using the application
 * This object will hold many pointers to sub type classes and probably grow... FUCKING NEAT
 * @author Caleb Hanselman
 *
 */
public class User
{
	//TODO fix this... this is retarded and should never be stored like this but 
	// this is just a rough draft atm
	protected String userName;
	protected String password;
	
	protected double money;
	protected ArrayList<Asset> assets;
	protected ArrayList<Transaction> transactions;
	protected double incomeRate;
	
	
	public User()
	{
		userName = null;
		password = null;
		money = 0.0;
		assets = new ArrayList<Asset>();
		transactions = new ArrayList<Transaction>();
		incomeRate = 0.0;
	}
	
	public String getUserName() { return userName; } 
	public void setUserName(String n) { userName = n; }
	
	public String getPassword() { return password; }
	public void setPassword(String p) { password = p; }
	
	public double getMoney() { return money; }
	public void setMoney(double m) { money = m; } 
	
	public ArrayList<Asset> getAssets(){return assets;}
	public void addAsset(Asset a) { assets.add(a); }
	
	public ArrayList<Transaction> getTransactions(){ return transactions; }
	public void addTransaction(Transaction t) { transactions.add(t); }
	
	public double getIncomeRate() { return incomeRate; }
	public void setIncomeRate(double d) { incomeRate = d; }
}
