package learn_java;

import java.util.Scanner;

public class topic04_for_1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("enter a number:");
		int a = scan.nextInt();
		
		for(int i = 1; i <= a; i++) {
			System.out.print(i + " ");
		}

	}

}
