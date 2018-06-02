package MainServer;

import java.io.IOException;
import java.util.Scanner;

import Messaging.MarshmallowMessage;
import ProtoJavaFiles.Heartbeat;

public class InitialTest {

	public static TcpServer testingServer;
	public static void main(String[] args) throws IOException {
		testingServer = new TcpServer(8321);
		testingServer.startServer();
		
		String input = "";
		while(!input.equals("exit"))
		{
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.println("Enter a number: ");
			input = reader.nextLine();
			
			Heartbeat.HeartBeat newTest = Heartbeat.HeartBeat.newBuilder().setId("HeartBeat").setBeat(input).build();
			MarshmallowMessage wrappedTest = new MarshmallowMessage(newTest);
			try {
				testingServer.sendMessage(wrappedTest, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		testingServer.closeServer();
	}

}
