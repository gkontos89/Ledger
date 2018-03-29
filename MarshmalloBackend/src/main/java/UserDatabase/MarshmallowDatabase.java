package UserDatabase;

import java.io.File;
import java.io.IOException;

/**
 * The MarshmallowDatabase interface is a class that will allow for different types of 
 * data bases to be used interchangibly by implementing methods with categorized behavior
 * 
 * @author chbla
 *
 */
public interface MarshmallowDatabase 
{
	/**
	 * Method that will take any database and save it on disk to the file specified
	 * in some format that can later be loaded
	 * @param loadLocation the location on disk for the server to load its database from
	 * @return true if the load worked fine, false if an error occured
	 */
	public boolean saveDatabase(File saveLocation) throws IOException;
	
	/**
	 * Method that will take in a file location pointing to a specific file or a directory
	 * and then procede to try and load from this location. Returns true if sucessfull
	 * 
	 * @param loadLocation the location on disk for the server to load its database from
	 * @return true if the load worked fine, false if an error occured
	 */
	public boolean loadDatabase(File loadLocation) throws IOException;
	
	/**
	 * Method that will take in some object and return to you an object that matches
	 * @param query
	 * @return
	 */
	public Object answerQuery(Object query);
	
	/**
	 * Method that will store some data to a key in the database
	 * @param data
	 * @param key
	 * @return
	 */
	public boolean storeData(Object data, Comparable<?> key);
	
	
}
