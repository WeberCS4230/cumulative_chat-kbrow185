package Chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatServer extends Thread {
	private ServerSocket theServer;
	ArrayList<String> users;
	ChatHistory.ChatCommands chatCommands;

	public ChatServer(int portID) throws IOException {

		theServer = new ServerSocket(portID);
		// theServer.setSoTimeout(20000);
		users = new ArrayList<String>();
		chatCommands = new ChatHistory.ChatCommands();

	}

	public void run() {

		//System.out.println("Waiting for client connection. Port: " + theServer.getLocalPort());

		while (true) {
			try {
				Socket theSocket = theServer.accept();

				PrintWriter dataOut = new PrintWriter(theSocket.getOutputStream(), true);
				BufferedReader dataIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
				System.out.println("Client connection successful, Port: " + theServer.getLocalPort());
				dataOut.println("ACK \n Successfully Connected to: " +theServer.getLocalPort());
				

				new Thread(new Runnable() {
					public void run() {
						
						while (true) {
						String input;
						try {
							input = dataIn.readLine();
						
						if (input.contains("UserName: ")) {
							chatCommands.checkUserName(input);
							} 
						else {
							chatCommands.addMessage(input);
						}}catch (IOException e) {
							//If theres an issue I can't do much with it here. There is a connection issue and will likely boot client.
						}
						}}}).start();
				
				//Creating another thread because the server has to be actively listening for each input.
				//The readers and sockets hold up the program until an input has been received.
				//Placed after previous thread as a third thread layer would prematurely crash. 
				//(Need to find out why.)
				//Printed out by indexing so items would not print multiple times.
				//Each client will have a different area they will drop into the chat from.
				new Thread(new Runnable() {
					public void run() {
						int clientChatLocation = chatCommands.getChatCurrentLocation();
						while(true) {
							//**************************
							//These two lines. Make or break the connection. NEED TO FIND OUT WHY!! V
							System.out.println(chatCommands.getChatCurrentLocation());
							System.out.println(clientChatLocation + "Client");
							//LINES OF STRUCTURE ^
							if(clientChatLocation < chatCommands.getChatCurrentLocation()) {
								
								dataOut.println(chatCommands.getMessage(clientChatLocation));
								clientChatLocation++;
							}
						}
					}}).start();
			

			} catch (IOException e) {
				//If theres an issue I can't do much with it here. There is a connection issue and will likely boot client.
				e.printStackTrace();
			}
		}
	}
}

