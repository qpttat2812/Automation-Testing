package learn_java;

import java.util.Scanner;

public class topic04_for_6 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter 2 numbers:");
		
		int a = scan.nextInt();
		int b = scan.nextInt();
		
		for(; a <= b; a++) {
			if (a % 3 == 0) {
				System.out.print(a + " ");
			}
		}
	}

}
