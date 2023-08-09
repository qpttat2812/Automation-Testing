package learn_java;

import java.util.Scanner;

public class topic02_operator {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = myObj.nextLine();
		
		System.out.println("Enter age: ");
		int age = myObj.nextInt();
		
		System.out.println("Name is " + name);
		System.out.println("Age is " + age);
		
		System.out.println("After 15 years, age of " + name +  " will be " + (age + 15));
	}

}
