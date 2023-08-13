package learn_java;

import java.util.Scanner;

public class topic03_exercise7 {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter month in number:");
		int a = myObj.nextInt();
		
		if (a == 1) {
			System.out.println("month " + a + " has 31 days");
		}
		else if (a == 2) {
			System.out.println("month " + a + " has 28 days");
		}
		else if (a == 3) {
			System.out.println("month " + a + " has 31 days");
		}
		else if (a == 4) {
			System.out.println("month " + a + " has 30 days");
		}
		else if (a == 5) {
			System.out.println("month " + a + " has 31 days");
		}
		else if (a == 6) {
			System.out.println("month " + a + " has 30 days");
		}
		else if (a == 7) {
			System.out.println("month " + a + " has 31 days");
		}
		else if (a == 8) {
			System.out.println("month " + a + " has 31 days");
		}
		else if (a == 9) {
			System.out.println("month " + a + " has 30 days");
		}
		else if (a == 10) {
			System.out.println("month " + a + " has 31 days");
		}
		else if (a == 11) {
			System.out.println("month " + a + " has 30 days");
		}
		else if (a == 12){
			System.out.println("month " + a + " has 31 days");
		}
		else {
			System.out.println("Invalid month!!!");
		}
	}

}
