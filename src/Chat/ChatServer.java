package Chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

import javax.swing.JTextArea;

public class ChatServer extends Thread
{
	private ServerSocket theServer;
	JTextArea theChat;
	
	public ChatServer(int portID,JTextArea chatWindow) throws IOException {
		
	theServer = new ServerSocket(portID);
	theServer.setSoTimeout(10000);
	theChat = chatWindow;
	}
	
	
	public void run(){
	
		boolean noConnection = true;
		theChat.append("Waiting for client connection. Port: " + theServer.getLocalPort());
		while(true){
		try {
			Socket server = theServer.accept();
			if(noConnection) {
				noConnection = false;
				theChat.append("Just connected to " + server.getRemoteSocketAddress());
			}
			DataInputStream infoIn = new DataInputStream(server.getInputStream());
			theChat.append((String)infoIn.readUTF());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
