package learn_java;

import java.util.Scanner;

public class topic04_for_5 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a numbers:");
		int a = scan.nextInt();
		
		int sum = 0;
		
		for (int i = 1; i <= a; i++) {
			if (i % 2 != 0) {
				sum += i;
			}
		}
		System.out.println("Sum of odd numbers: " + sum);

	}

}
