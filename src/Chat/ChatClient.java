package Chat;

import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

public class ChatClient extends Thread {
	Socket clientSocket;
	String serverLocation;
	int thePort;
	String newLine;
	Boolean serverStart;
	PrintWriter dataOut;
	BufferedReader dataIn;
	JTextArea theChatWindow;

	public ChatClient(String serverName, int port, JTextArea chatWindow) {
		thePort = port;
		serverLocation = serverName;
		newLine = "";
		theChatWindow = chatWindow;
		serverStart = false;
	}

	public void run() {
		try {
			clientSocket = new Socket(serverLocation, thePort);

			dataIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			dataOut = new PrintWriter(clientSocket.getOutputStream(), true);

			theChatWindow.append("Connecting to:  " + clientSocket.getRemoteSocketAddress() + "\n");
			serverStart = true;
			while (true) {

				theChatWindow.append(dataIn.readLine() + "\n");
				// dataOut = writeUTF()
			}

		} catch (UnknownHostException e) {
			//If theres an issue I can't do much with it here. There is a connection issue and will likely boot client.
			e.printStackTrace();
		} catch (IOException e) {
			//If theres an issue I can't do much with it here. There is a connection issue and will likely boot client.
			e.printStackTrace();
		}

	}

	public void sendNewLine(String theResponse) throws IOException {
		if (serverStart) {
			dataOut.println(theResponse);
			//dataOut.flush();
		
		}

	}

}
