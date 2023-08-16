package learn_java;

import java.util.Scanner;

public class topic04_for_4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter 2 numbers:");
		int a = scan.nextInt();
		int b = scan.nextInt();
		
		int sum = 0;
		
		for(; a <= b; a++) {
			sum += a;
		}
		
		System.out.println("Sum of numbers = " + sum);

	}

}
