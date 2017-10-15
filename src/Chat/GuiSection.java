package Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.DefaultCaret;


public class GuiSection extends JFrame 
{
	
	/**
	 * To clear the warning of the class not declaring a static final serialVersionUID.
	 * Each serializable class requires a version number. Assists with InvalidClassExceptions.
	 */
	private static final long serialVersionUID = 1L;
	JTextArea chatWindow;
	
	public GuiSection(String username) 
	{
		String userName = username;

        Box theBox = Box.createVerticalBox();
		chatWindow= new JTextArea(40,10);
		chatWindow.setEditable(false);
		JScrollPane theScroller = new JScrollPane(chatWindow);
		theBox.add(theScroller);
		
		this.add(theBox);
		
		theBox.add(new JLabel("User Input Below"));
		
		JTextArea userInputTextBox = new JTextArea(2,10);
		theBox.add(userInputTextBox);
         
		JButton addChatButton = new JButton();
		addChatButton.setText("Submit");
		addChatButton.setAlignmentX(CENTER_ALIGNMENT);
		addChatButton.setMnemonic(KeyEvent.VK_ENTER);
		addChatButton.addActionListener(new ActionListener() {@Override
				public void actionPerformed(ActionEvent e) {
					addToChat(userName + ": " + userInputTextBox.getText());
					userInputTextBox.setText("");
			}});
		
		theBox.add(addChatButton);
		this.add(theBox);
		
		
	    DefaultCaret caret = (DefaultCaret)chatWindow.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		
        this.setTitle("Chat Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 800);
		this.setVisible(true);
		
	}
	public void addToChat(String sentence){
		chatWindow.append(sentence + "\n");

	}

}


