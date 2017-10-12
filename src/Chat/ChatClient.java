package Chat;

import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

public class ChatClient extends Thread
{
	Socket clientSocket;
	String serverLocation;
	int thePort;
	String newLine;
	Boolean serverStart;
	PrintWriter dataOut;
	BufferedReader dataIn;
	JTextArea theChatWindow;
	
	public ChatClient(String serverName,int port,JTextArea chatWindow ){
		thePort=port;
		serverLocation=serverName;
		newLine="";
		theChatWindow = chatWindow;
		serverStart=false;
	}
	
	public void run() {
		try {
			clientSocket = new Socket(serverLocation, thePort);
			
			
			 dataIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			 dataOut =  new PrintWriter(clientSocket.getOutputStream(), true);

			theChatWindow.append("Just connected to " + clientSocket.getRemoteSocketAddress());
			serverStart = true;
			while(true)
			{

				
				
				
				theChatWindow.append( dataIn.readLine());
				//dataOut = writeUTF()
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void checkForName()
	{
		
	}
	
	public void sendNewLine(String theResponse) throws IOException
	{
		if(serverStart){
		dataOut.println(theResponse);
		}
		
	}

}
