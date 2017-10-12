package Chat;

import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

public class ChatClient extends Thread 
{
	Socket clientSocket;
	String theServerName;
	int thePort;
	String newLine;
	Boolean serverStart;
	DataOutputStream dataOut;
	DataInputStream dataIn;
	JTextArea theChatWindow;
	
	public ChatClient(String serverName,int port,JTextArea chatWindow ){
		thePort=port;
		theServerName=serverName;
		newLine="";
		serverStart=false;
		theChatWindow = chatWindow;
		
		try {
			clientSocket = new Socket(theServerName, thePort);
			dataIn = new DataInputStream(clientSocket.getInputStream());
			dataOut = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader buff = new BufferedReader(new InputStreamReader(dataIn));
			serverStart=true;
			
			while(true)
			{
				
				
				
				newLine = buff.readLine();
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
	

	public void run(){
		
	}
	
	public void sendNewLine(String theResponse) throws IOException
	{
		if(serverStart){
		newLine = theResponse;
		dataOut.writeUTF(newLine);
		}
		
	}

}
