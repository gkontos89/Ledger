package Utilities;

import java.util.logging.Logger;

/**
 * This class acts as a store house to hold all utilities for logging of the applications backend,
 * front end, transactions and anything else we feel like... because i can do that.
 * 
 * Initial implementation will only use the FileLogger native to java but feel free to change this up
 * @author Caleb Hanselman
 *
 */
public class LoggingUtilities 
{
	protected static final Logger backendLogger = Logger.getLogger("Marshmello.Logging.backend");
	protected static final Logger connectionLogger = Logger.getLogger("Marshmello.Logging.backend");
	protected static final Logger frontEndLogger = Logger.getLogger("Marshmello.Logging.backend");
	
	
	//TODO
	/*
	 * // This block configure the logger with handler and formatter  
        fh = new FileHandler("C:/temp/test/MyLogFile.log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  
	 */
	public static void logBackend(String log)
	{
		
	}
	
	public static void logConnection(String log)
	{
		
	}

	public static void logFrontEnd(String log)
	{
		
	}
}
