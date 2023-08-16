package learn_java;

import java.util.Scanner;

public class topic04_switchcase_2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter 2 numbers and operator(add/substract/divide/multiplication):");
		
		int a = scan.nextInt();
		int b = scan.nextInt();
		String operator = scan.next();
		
		switch(operator) {
		case "+":
				System.out.println("a + b = " + (a+b));
				break;
				
		case "-":
			System.out.println("a - b = " + (a-b));
			break;
		case "*":
			System.out.println("a x b = " + (a*b));
			break;
		case "/":
			System.out.println("a / b = " + (a/b));
			break;
		}
	}

}
