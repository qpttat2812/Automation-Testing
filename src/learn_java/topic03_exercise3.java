package learn_java;

import java.util.Scanner;

public class topic03_exercise3 {
	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter a name: ");
		String name1 = myObj.nextLine();
		System.out.println("Enter another the name:");
		String name2 = myObj.nextLine();
		
		if (name1.contentEquals(name2)) {
			System.out.println("It's the same name!");
		} else {
			System.out.println("It's the 2 different names");
		}
	}

}
