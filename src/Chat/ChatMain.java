package Chat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class ChatMain {

	public static void main(String[] args) 
	{
		HashSet<Student> studentList = new HashSet<Student>();
		studentList.add(new Student("Josh","Burnham",(float) 15.2));
		studentList.add(new Student("Mr.","Miyagi",(float) 12.9));
		studentList.add(new Student("Jackie","chan",(float) 30.8));
		studentList.add(new Student("Wooshoo","Mooshu",(float) 2.1));
		studentList.add(new Student("Macro","Micro",(float) 5.8));
		studentList.add(new Student("Ima","Bush",(float) 3.3));
		studentList.add(new Student("Ima","Trouble",(float) 3.3));
		studentList.add(new Student("WiiShii","Nintendo",(float) 3.3));
		
		ArrayList<TreeSet<Student>> groups = new ArrayList<TreeSet<Student>>();
		
		//Convert student hashset to array.
		//got idea from: https://stackoverflow.com/questions/33593371/how-to-convert-hash-set-into-array-using-toarray-if-the-method-toarray-is-not
		Student[] studentArray = studentList.toArray(new Student[studentList.size()]);
		for(int i = 0; i<studentList.size();i+=2)
		{
		TreeSet<Student> temp = new TreeSet<Student>();
		temp.add(studentArray[i]);
		temp.add(studentArray[i+1]);
		groups.add((temp));
		}
		
		//For each assigned group, have each student say 5 responses.
		for(TreeSet<Student> g : groups)
		{
			System.out.println("-----------New Convo----------");
			for(int i =0; i <5; i++)
			{
				for(Student s: g)
				{
					System.out.println(s.firstName + " " +s.lastName + ":  " +s.getResponse()+ "\n");
				}
			}
			
		}
	}

}
