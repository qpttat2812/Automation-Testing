package learn_java;

import java.util.Scanner;

public class topic03_exercise6 {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter a first number:");
		float a = myObj.nextFloat();
		
		if ((a >= 0) && (a < 5)) {
			System.out.println("Grade D");
		}
		else if ((a >= 5) && (a < 7.5)) {
			System.out.println("Grade C");
		}
		else if ((a >= 7.5) && (a < 8.5)) {
			System.out.println("Grade B");
		}
		else if ((a >= 8.5) && (a <= 10)){
			System.out.println("Grade A");
		}
		else {
			System.out.println("Your grade is invalid!");
		}
	}

}
