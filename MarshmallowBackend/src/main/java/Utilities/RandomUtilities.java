package Utilities;

import java.util.Random;

import BackendModels.UserManager;

/**
 * Helper class to hold random methods for Generating random objects for testing and probing
 * @author Caleb Hanselman
 *
 */
public class RandomUtilities
{
	
	protected static Random rand = new Random();
	/**
	 * Generates a random string of a given lenght, length of -1 creates a random length between 5-15
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length)
	{
		if (length <= 0)
			length = (rand.nextInt()%11) + 5;
		byte[] characters = new byte[length];
		for( int i = 0; i<length; i++)
			characters[i] = (byte)((rand.nextInt()%58)+65);
		
		return new String(characters);
	}
	
	public static int getRandomInt()
	{
		return rand.nextInt();
	}
	
	public static boolean getRandomBool()
	{
		return rand.nextBoolean();
	}
}
