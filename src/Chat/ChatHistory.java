package Chat;

import java.util.ArrayList;

//Chat history created as a static class so the server can access the user array
//and messages array. This class allows multiple methods for a better programming structure.
//This class can perform methods reguarding the messages and user array seperate from the server.
public class ChatHistory {
	
	private static ArrayList<String> users  = new ArrayList<String>();
	private static ArrayList<String> messages = new ArrayList<String>();
	
	
	public static class ChatCommands{

		
		public String getMessage(int location) {
			System.out.println(messages.get(location));
			return messages.get(location);
		}
		
		
		public void addMessage(String message) {
			
			messages.add(message);
		}
		
		//I created a checkusername method to verify if a username has been taken.
		//I didn't create an exception recovery for it, so a user will be notified but
		//will still appear as the duplicate name. Did this due to time constraints.
		//This doubles as a welcome message for everyone.
		public void checkUserName(String userName) {
			String tempUser = userName.replace("UserName: ", "");
			if (users.contains(tempUser)) {
				String temp = "USER NAME HAS BEEN TAKEN. Renaming!";
				messages.add(temp);

			} else {
				
				String theMessage = ("Welcome " + tempUser);
				users.add(tempUser);
				messages.add(theMessage);
				
		}
		
	}
		public int getChatCurrentLocation() {
				
			return messages.size();
		}


}
}

