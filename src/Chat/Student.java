package Chat;

import java.util.ArrayList;
import java.util.Arrays;

//Got comparable implementation from: https://www.javatpoint.com/java-treeset

public class Student implements Comparable<Student>
{
	String firstName;
	String lastName;
	float score;
	ArrayList<String> responses;
	
	
	
	public Student(String fName, String lName, float studentScore)
	{
		score = studentScore;
		firstName =fName;
		lastName = lName;
		//cannot convert string array directly to tree set. Have to convert to List, then treeset.
		//As (AsList) creates a read only array. I've created a new arraylist from an As List.
		//Got this idea from:https://stackoverflow.com/questions/11659173/create-mutable-list-from-array
		String[] theList = {"Hello","Yo","What's up?","Patience","What's that?","You're doomed","Ah!","Are you deaf?","I said no.","perhaps"};
		responses = new ArrayList<String>(Arrays.asList(theList));
		
		

	}
	//got alphabetize idea from: https://stackoverflow.com/questions/20445900/comparing-two-string-and-sorting-them-in-alphabetical-order
	//return 0 if equal, return 1 if this class is further than s, -1 if this is behind s
	public int compareTo(Student s)
	{
		if (firstName.compareTo(s.firstName) == 0) {
			return lastName.compareTo(s.lastName);
		}
		else {
			return firstName.compareTo(s.lastName);
		}
		// You may want to pull your return statement out, or use one less comparison on compareTo, but this does the same thing AND doesn't require you to redefine the actual return of compareTo from the String.
	}
	public String getResponse()
	{
		if(responses.size()<=0)
			return "I'm done";
			
		int rand = (int) ((Math.random()*10)%responses.size());
		
		String theReturn = responses.get(rand);
		responses.remove(rand);
		return theReturn;
	}
	
	
}
