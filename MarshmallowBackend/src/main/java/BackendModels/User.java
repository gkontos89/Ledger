package BackendModels;

import java.util.ArrayList;

import MainServer.TcpConnectionHandlerThread;
import Utilities.RandomUtilities;

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
	protected String org;
	protected String grade;
	protected String email;
	
	protected double money;
	protected ArrayList<Asset> assets;
	protected ArrayList<Transaction> transactions;
	protected double incomeRate;
	
	protected TcpConnectionHandlerThread myConnectionThread;
	
	public User()
	{
		userName = null;
		password = null;
		money = 0.0;
		assets = new ArrayList<Asset>();
		transactions = new ArrayList<Transaction>();
		incomeRate = 0.0;
		
		myConnectionThread = null;
	}
	
	public String getUserName() { return userName; } 
	public void setUserName(String n) { userName = n; }
	
	public String getPassword() { return password; }
	public void setPassword(String p) { password = p; }
	
	public String getOrg() { return org; }
	public void setOrg(String o) { org = o; }
	
	public String getGrade() { return grade; }
	public void setGrade(String g) { grade = g; }
	
	public String getEmail() { return email; }
	public void setEmail(String e) { email = e; }
	
	public double getMoney() { return money; }
	public void setMoney(double m) { money = m; } 
	
	public ArrayList<Asset> getAssets(){return assets;}
	public void addAsset(Asset a) { assets.add(a); }
	
	public ArrayList<Transaction> getTransactions(){ return transactions; }
	public void addTransaction(Transaction t) { transactions.add(t); }
	
	public double getIncomeRate() { return incomeRate; }
	public void setIncomeRate(double d) { incomeRate = d; }
	
	public TcpConnectionHandlerThread getMyConnectionThread() { return myConnectionThread; }
	public void setMyConnectionThread(TcpConnectionHandlerThread t) { myConnectionThread = t; }
	
	public void fillInWithRandomData()
	{
		this.setOrg(RandomUtilities.getRandomString(-1));
		this.setGrade(RandomUtilities.getRandomString(-1));
		this.setEmail(RandomUtilities.getRandomString(-1));
		this.setMoney(RandomUtilities.getRandomInt());
		this.setIncomeRate(RandomUtilities.getRandomInt());
		for(int i = 0; i < 3; i ++)
			this.addAsset(Asset.generateRandomAsset());
	}
}
