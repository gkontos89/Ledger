package Messaging;

import java.io.IOException;

/**
 * The purpose of this interface is to provide a way for all of our factories to gaurentee
 * methods and actions without knowing the contents of the message or how it will behave
 * 
 * Futher, this will allow us to use AMQP, protos, JSON, Monglo, w/e we want
 * 
 * @author caleb hanselman
 */
public interface MarshmallowMessage
{
	/**
	 * This method gets the value of the ID field as represented by the data. IE it can not be filled
	 * in correctly
	 * 
	 * We are making it return a Comparable for more flexibility. At inception a string seems cool and 
	 * dandy but this will allow us to grow to use ints or even more complex classes that we create 
	 * ourselfs
	 */
	public Comparable<?> getMyIdData();
	
	/**
	 * Method to return the size of all the data that is currently in this message
	 * 
	 * @return the size of the messages data currently stored on the object
	 */
	public int getDataSize();
	
	/**
	 * This method gets the default value of the ID. If you do not have a default value you are 
	 * not allowed to exist on this server.
	 * @return
	 */
	public Comparable<?> getMyIdDefaultValue();
	
	/**
	 * This is essentialy the serialize method. I dont care how you do it but to be a message you 
	 * must be able to do a goddamn byte array transformation!
	 * @return
	 */
	public byte[] getAsByteArray() throws IOException;
	
	/**
	 * This method is the reverse from the top.
	 * @param input
	 */
	public void fillFromByteArray(byte[] input) throws IOException;
	
	/**
	 * A helper method that can be used to tell if a message can even handle the data. 
	 * @param input
	 * @return
	 */
	public boolean canParse(byte[] input);
	
	/**
	 * A public exposing method that allows for us to wrap a clone of the message if you want too
	 * 
	 * @return a new clone of the message with all data coppied as it should be
	 */
	public MarshmallowMessage getClone();
	
}
