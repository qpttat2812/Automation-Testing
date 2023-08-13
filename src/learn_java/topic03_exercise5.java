package learn_java;

import java.util.Scanner;

public class topic03_exercise5 {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter a number:");
		int a = myObj.nextInt();
		
		if ((a >= 10) && (a <= 100)) {
			System.out.println(a + " is in the range [10,100]");
		}
		else {
			System.out.println(a + " is not in the range [10,100]");
		}

	}

}
