package learn_java;

import java.util.Scanner;

public class topic03_exercise2 {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter number a: ");
		int a = myObj.nextInt();
		System.out.println("Enter number b: ");
		int b = myObj.nextInt();
		
		System.out.println(a > b ? "a is greater than b" : "a is less than b");
	}

}
