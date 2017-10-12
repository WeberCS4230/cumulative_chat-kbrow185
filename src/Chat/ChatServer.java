package Chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;



public class ChatServer extends Thread
{
	private ServerSocket theServer;
	ArrayList<String> users;
	
	public ChatServer(int portID) throws IOException {
		
	theServer = new ServerSocket(portID);
	//theServer.setSoTimeout(20000);
	users = new ArrayList<String>();

	}
	
	
	public void run(){
	
		System.out.println("Waiting for client connection. Port: " + theServer.getLocalPort());
		while(true){
		try {
			Socket theSocket = theServer.accept();

			PrintWriter dataOut =  new PrintWriter(theSocket.getOutputStream(), true);
			BufferedReader dataIn = new BufferedReader( new InputStreamReader(theSocket.getInputStream()));
			System.out.println("Client connection successful, Port: " + theServer.getLocalPort());
			
			while(theSocket.isConnected()) 
			{
				String input = dataIn.readLine();
				if(input.contains("<('.')> Name"))
				{
					String tempUser = input.replace("<('.')> Name", "");
					if(users.contains(tempUser)){
						dataOut.println("USER NAME HAS BEEN TAKEN. Renaming!");
						System.out.println("Username taken");
					}
					else {
						users.add(tempUser);
						dataOut.println("Welcome "+tempUser);
						System.out.println(tempUser +" Connected");
					}
				}
				else {
				dataOut.println(input);
				}
			}
			//theSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
