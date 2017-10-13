package Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class GuiSection extends JFrame {

	/**
	 * To clear the warning of the class not declaring a static final
	 * serialVersionUID. Each serializable class requires a version number. Assists
	 * with InvalidClassExceptions.
	 */
	private static final long serialVersionUID = 1L;
	JButton addChatButton;
	JTextArea chatWindow;
	JTextArea userInputTextBox;
	JScrollPane theScroller;
	DefaultCaret caret;
	Box theBox;
	ChatClient theClient;
	ChatServer theServer;
	boolean nameAdd;
	String userName;
	String response;

	public GuiSection() {
		nameAdd = false;
		userName = "No Name";
		response = "";

		Box theBox = Box.createVerticalBox();
		chatWindow = new JTextArea(40, 10);
		chatWindow.setEditable(false);
		theScroller = new JScrollPane(chatWindow);
		theBox.add(theScroller);

		this.add(theBox);

		theBox.add(new JLabel("User Input Below"));

		userInputTextBox = new JTextArea(2, 10);
		theBox.add(userInputTextBox);

		addChatButton = new JButton();
		addChatButton.setText("Submit");
		addChatButton.setAlignmentX(CENTER_ALIGNMENT);
		addChatButton.setMnemonic(KeyEvent.VK_ENTER);
		addChatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connectAddToChat(userInputTextBox.getText());
				userInputTextBox.setText("");
			}
		});

		theBox.add(addChatButton);
		this.add(theBox);

		DefaultCaret caret = (DefaultCaret) chatWindow.getCaret();
		caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);

		this.setTitle("Chat Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 800);
		this.setVisible(true);

	}

	public void startConnection() {

		String host = JOptionPane.showInputDialog(new JFrame("Select Host"),"Please enter the server host ip address.\n localhost default.");
	try {
		if (!hostAvailable(host)) {
			theServer = new ChatServer(8090);
			new Thread(theServer).start();
		}
		theClient = new ChatClient(host, 8090, chatWindow);
		new Thread(theClient).start();
		
		}catch(IOException e) {
			//If theres an issue I can't do much with it here. There is a connection issue and will likely boot client.
		}
	}

	//Created this to check if the server is running. 
	public boolean hostAvailable(String host) {
		try {
			Socket s = new Socket(host, 8090);
			s.close();
			return true;
		} catch (IOException e) {
			//An exception task isnt really needed here. If the test fails it will just return false.
		}
		return false;
	}

	public void defaultAddToChat(String username, String sentence) {
		chatWindow.append(username + ":\t" + sentence + "\n");
	}

	public void connectAddToChat(String sentence) {

		try {
			if (!nameAdd) {
				userName = sentence;
				theClient.sendNewLine("UserName: " + sentence);
				nameAdd = true;

			} else {
				theClient.sendNewLine(userName + ":\t" + sentence);
			}
		} catch (IOException e) {
			//If theres an issue I can't do much with it here. There is a connection issue and will likely boot client.
		}

	}

}
