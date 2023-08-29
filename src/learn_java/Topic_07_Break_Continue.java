package learn_java;

import java.util.Scanner;

public class Topic_07_Break_Continue {
	
	public static void CheckBrowserName() {
		Scanner obj = new Scanner(System.in);
		System.out.println("Input a name of browser:");
		String browserName = obj.next();
		browserName = browserName.toLowerCase();
		
		switch (browserName) {
			case "internet explorer":
			case "ie":
					System.out.println("Internet Explorer is not exist!!!");
					break;
			case "chrome":
			case "firefox":
			case "opera":
			case "safari":
				System.out.println("Browser name is " + browserName);
				break;
			default:
				System.out.println("This browser name is not exist in our system!");
		}
	}
	
	public static void DisplayNameOfMonth() {
		Scanner obj = new Scanner(System.in);
		System.out.println("Input a month (number):");
		int month = obj.nextInt();
		
		switch(month) {
			case 1:
				System.out.println("JANUARY");
				break;
			case 2:
				System.out.println("FEBRUARY");
				break;
			case 3:
				System.out.println("MARCH");
				break;
			case 4:
				System.out.println("APRIL");
				break;
			case 5:
				System.out.println("MAY");
				break;
			case 6:
				System.out.println("JUNE");
				break;
			case 7:
				System.out.println("JULY");
				break;
			case 8:
				System.out.println("AUGUST");
				break;
			case 9:
				System.out.println("SEPTEMBER");
				break;
			case 10:
				System.out.println("OCTOBER");
				break;
			case 11:
				System.out.println("NOVEMBER");
				break;
			case 12:
				System.out.println("DECEMBER");
				break;
			default:
				System.out.println("Invalid month!");
		}
			
	}
	
	
	public static void main(String[] args) {
//		CheckBrowserName();
		DisplayNameOfMonth();
	}
}
