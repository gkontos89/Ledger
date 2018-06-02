package BackendModels;

/**
 * A transaction class is the base class for any inter change of coods that deal 
 * with a user. Be it income, Assets, education or anything. In the future consider
 * branching this into different types of assets
 * 
 * @author Caleb Hanselman
 *
 */
public class Transaction 
{
	protected User originator;
	protected User destination;
	protected Asset item;
	
	protected long timeOfTransaction;
	protected long lastTimeSentToClient;
	
	public Transaction(User orig, User dest, Asset item)
	{
		originator = orig;
		destination = dest;
		this.item = item;
		timeOfTransaction = System.currentTimeMillis();
		lastTimeSentToClient = -1;
	}
	
	public User getOriginator() {return originator;}
	public void setOriginator(User org) {originator = org; }
	
	public User getDestination() {return destination;}
	public void setDestination(User dest) {destination = dest;}
	
	public Asset getItem() {return item;}
	public void setItem(Asset a) { item = a; }
	
	public long getTimeOfTransaction() {return timeOfTransaction;}
	
	public long getLastClientNotificationTime() { return lastTimeSentToClient; }
	public void setLastClientNotificationTime()
	{
		lastTimeSentToClient = System.currentTimeMillis();
	}
}
