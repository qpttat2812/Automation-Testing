package learn_java;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_07_While_DoWhile {
	public static void DisplayEvenNumber() {
		Scanner obj = new Scanner(System.in);
		System.out.println("Input a number:");
		int num = obj.nextInt();
		
		while (num < 101) {
			if (num % 2 == 0) {
				System.out.print(num + "\t");
			}
			num++;
		}
	}
	
	public static void DividedByThreeAndFive() {
		Scanner obj = new Scanner(System.in);
		System.out.println("Input a first number:");
		int a = obj.nextInt();
		System.out.println("Input a second number:");
		int b = obj.nextInt();
		
		do {
			if ((a % 3 == 0) && (a % 5 == 0)) {
				System.out.print(a + "\t");
			}
			a++;
		} while (a <= b);
	}
	
	public static void SumOfOddNumber() {
		Scanner obj = new Scanner(System.in);
		System.out.println("Input a number:");
		int num = obj.nextInt();
		
		int sum = 0;
		for(int i = 0; i <= num; i++) {
			if(i % 2 != 0) {
				sum += i;
			}
		}
		System.out.println("Sum is " + sum);
	}
	
	public static void NumberDividedByThree() {
		Scanner obj = new Scanner(System.in);
		System.out.println("Input a first number:");
		int a = obj.nextInt();
		System.out.println("Input a second number:");
		int b = obj.nextInt();
		
		while (a <= b) {
			if (a % 3 == 0) {
				System.out.print(a + "\t");
			}
			a++;
		}
	}
	
	public static void Factorial() {
		Scanner obj = new Scanner(System.in);
		System.out.println("Input a number:");
		int num = obj.nextInt();
		int result = 1;
		for (int i = 1; i <= num; i++) {
			result *= i;
		}
		System.out.println("Factorial is " + result);
	}
	
	public static void SumOfEvenNumber() {
		int sum = 0;
		for (int i = 1; i < 11; i++) {
			sum += i;
		}
		System.out.println("Sum from 1 to 10 is " + sum);
	}
	
	public static void main(String[] args) {
//		DisplayEvenNumber();
//		DividedByThreeAndFive();
//		SumOfOddNumber();
//		NumberDividedByThree();
//		Factorial();
		SumOfEvenNumber();
	}
}
