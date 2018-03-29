package BackendModels;

/**
 * An Asset is an object that players can purchase, they will have a name and a value,
 * as well as other meta data like purchase date and in the future depreciate etc.
 * @author Caleb Hanselman
 *
 */
public class Asset 
{
	protected String name;
	protected User owner;
	protected double cost;
	protected double value;
	// time is in ms since epoch
	protected long timeOfPurchase;
	
	public Asset()
	{
		name = null;
		owner = null;
		cost = 0; 
		value = 0;
		timeOfPurchase = -1;
	}
	
	public String getAsCsvSaveString()
	{
		String answer = "";
		//answer = answer + name+","+owner.getName()+","+cost+","
		return answer;
	}
	
	public String getName() {return name;}
	public void setName(String s) { name = s; }
	
	public User getOwner() { return owner; } 
	public void setOwner(User o) { owner = o; }
	
	public double getCost() { return cost; }
	public void setCost(double c) { cost = c; }
	
	public double getValue() { return value; }
	public void setValue(double v) { value = v; }
}
