package UserDatabase;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import BackendModels.User;
import Utilities.LoggingUtilities;

/**
 * This class is the most basic database i can think of on the fly while tired.
 * It will hold its data in a User by User basis and save/write to a single CSV file
 * on disk.
 * This is meant for debugging and quick testing purposes. This class can have future use
 * in automated testing as we can set up dummy databases fast with this.
 * @author Caleb Hanselman
 *
 */
public class CsvDatabase implements MarshmallowDatabase {
	
	protected ArrayList<User> myUsersDatabase;
	
	public CsvDatabase()
	{
		myUsersDatabase = new ArrayList<User>();
	}
	
	public void addUser(User newUser)
	{
		myUsersDatabase.add(newUser);
	}
	
	public boolean removeUser(String username)
	{
		for(User homie : myUsersDatabase)
		{
			if(homie.getUserName().equals(username))
			{
				myUsersDatabase.remove(homie);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean saveDatabase(File saveLocation) throws IOException 
	{
		if(saveLocation.isDirectory())
		{
			LoggingUtilities.logBackend("Was given a directory to save a csv database too...");
			return false;
		}
		
		PrintWriter writer = new PrintWriter(saveLocation.getAbsolutePath(), "UTF-8");
		for(User user : myUsersDatabase)
		{
			String userAsCsvLine = "";
			userAsCsvLine = userAsCsvLine + user.getUserName()+","+user.getPassword()+",";
			userAsCsvLine = userAsCsvLine + user.getMoney()+","+user.getIncomeRate()+",";
			
		}
		
		return false;
	}

	@Override
	public boolean loadDatabase(File loadLocation) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object answerQuery(Object query) {
		if(query instanceof User)
		{
			for(User user : myUsersDatabase)
			{
				if( ((User)query).getUserName().equals(user.getUserName()) )
					return user;
			}
		}
		
		LoggingUtilities.logBackend("Was Unable to answer a query because it is an unknown type for CSV Database");
		return null;
	}

	@Override
	public boolean storeData(Object data, Comparable<?> key) {
		// TODO Auto-generated method stub
		return false;
	}

}
