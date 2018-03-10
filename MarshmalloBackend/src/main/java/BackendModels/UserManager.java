package BackendModels;

import java.util.Map;
import java.util.TreeMap;

import Messaging.MarshmallowMessage;
import ProtoJavaFiles.Heartbeat.CreateAccountMessage;
import Utilities.LoggingUtilities;

/**
 * This class will be the manager for all Users. It will hold a list of all allowed users, the users that are connected
 * and Facilitate the actions that involve changing their state with the server as a whole
 * 
 * @author Caleb Hanselman
 */
public class UserManager {
	
	protected static UserManager instance;
	protected Map<String, User> possibleUserTable;
	protected Map<String, User> activeUsers;
	
	
	public static UserManager Instance()
	{
		if(instance == null)
			instance = new UserManager();
		return instance;
	}
	
	protected UserManager()
	{
		possibleUserTable = new TreeMap<String, User>();
		activeUsers = new TreeMap<String, User>();
	}
	
	public User getUser(String username)
	{
		return activeUsers.get(username);
	}
	
	public boolean isActive(String username)
	{
		return activeUsers.containsKey(username);
	}
	
	public void logoutUser(String username)
	{
		activeUsers.remove(username);
	}
	
	public boolean loginUser(String username, String password)
	{
		if(!possibleUserTable.containsKey(username))
		{
			LoggingUtilities.logBackend("Tried to login a user with the name :"+username+" but this user has not been registered/");
			return false;
		}
		
		// TODO make this not so shitty
		if( !possibleUserTable.get(username).getPassword().equals(password) )
			return false;
		activeUsers.put(username, possibleUserTable.get(username));
		LoggingUtilities.logBackend("User:"+username+" has Logged in.");
		
		return true;
	}
	
	public boolean isUserRegistered(String username)
	{
		return possibleUserTable.containsKey(username);
	}
	
	public boolean registerUser(MarshmallowMessage registerMessage)
	{
		CreateAccountMessage registerMsg = (CreateAccountMessage)registerMessage.getProtoMessage();
		String username = registerMsg.getUsername();
		if(isUserRegistered(username))
		{
			LoggingUtilities.logBackend("Tried to register:"+username+" but this user has already been registered");
			return false;
		}
		User newUser = new User();
		newUser.setUserName(username);
		newUser.setPassword(registerMsg.getPassword());
		newUser.setOrg(registerMsg.getOrg());
		newUser.setGrade(registerMsg.getGrade());
		newUser.setEmail(registerMsg.getEmail());
		possibleUserTable.put(username, newUser);
		return true;
	}
}
