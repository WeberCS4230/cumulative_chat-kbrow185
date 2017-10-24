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
		// Code left in commits on the submitted branch -2pts

		//System.out.println("Waiting for client connection. Port: " + theServer.getLocalPort());

		while (true) {
			try {
				Socket theSocket = theServer.accept();

				PrintWriter dataOut = new PrintWriter(theSocket.getOutputStream(), true);
				BufferedReader dataIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
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
							// The reason for your issue (and why the flush in the position you've put it solves your issue) is because you're running an infinite loop that takes all of the resources. As soon as you do a flush, the IO blocks you for long enough for other information to come across. This is still not a fix, but it looks like one because you've found a one-liner. In reality, you should be able to move that flush statement to AFTER read calls, and not have to have it before a check for messages (output and input should not have to be linked this way, otherwise you can assume some sort of issue).
							dataOut.flush();
							//LINE OF STRUCTURE ^
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

