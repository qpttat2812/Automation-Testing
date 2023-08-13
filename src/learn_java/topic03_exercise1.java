package learn_java;

import java.util.Scanner;

public class topic03_exercise1 {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter a number: ");
		
		int number = myObj.nextInt();
		
		if (number % 2 == 0) {
			System.out.println(number + " is the even!");
		}
		else {
			System.out.println(number + " is the odd!");
		}
		

	}

}
