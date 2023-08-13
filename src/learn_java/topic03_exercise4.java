package learn_java;

import java.util.Scanner;

public class topic03_exercise4 {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter a first number:");
		int a = myObj.nextInt();
		System.out.println("Enter a second the name:");
		int b = myObj.nextInt();
		System.out.println("Enter a third the name:");
		int c = myObj.nextInt();
		
		if ((a > b) && (a > c)) {
			System.out.println("The largest number is " + a);
		}
		else if (b > c) {
			System.out.println("The largest number is " + b);
		}
		else {
			System.out.println("The largest number is " + c);
		}


	}

}
