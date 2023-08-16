package learn_java;

import java.util.Scanner;

public class topic04_for_7 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a number:");
		
		int a = scan.nextInt();
		
		int factorial = 1;
		
		for(int i = 1; i <= a; i++) {
			factorial *= i;
		}
		System.out.println("Factorial is " + factorial);
	}

}
