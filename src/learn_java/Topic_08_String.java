package learn_java;

import java.util.Scanner;

public class Topic_08_String {
	
	public static String reverseString(String stringArg) {
		String result = "";
		char[] newCharArray = stringArg.toCharArray();
		
		for(int i = newCharArray.length - 1; i >= 0; i--) {
			result = result + newCharArray[i];
		}
		
		return result;
		
	}
	
	public static void handleString(String stringArg) {
		//count the number of a character
		int countOfCharacter = 0;
		for(int i = 0; i < stringArg.length(); i++) {
			if ((stringArg.charAt(i) == 'a') || (stringArg.charAt(i) == 'A')) {
				countOfCharacter++;
			}
		}
		System.out.println("The number of character a = " + countOfCharacter);
		
		//check if string has "Testing"
		if(stringArg.toLowerCase().contains("testing")) {
			System.out.println(true);
		}
		else {
			System.out.println(false);
		}
		
		//check if string starts with "Automation"
		if(stringArg.toLowerCase().startsWith("automation")) {
			System.out.println(true);
		}
		else {
			System.out.println(false);
		}
		
		//check if string ends with "Online"
		if(stringArg.toLowerCase().endsWith("online")) {
			System.out.println(true);
		}
		else {
			System.out.println(false);
		}
		
		//get index of "Tutorials"
		System.out.println("Index of word \"Tutorials\" = " + stringArg.indexOf("Tutorials"));
		
		//replace "Online" to "Offline"
		System.out.println(stringArg.replace("Online", "Offline"));
		
		//count number in string
		int countNumber = 0;
		for(int j = 0; j < stringArg.length(); j++) {
			if(stringArg.charAt(j) >= '0' && stringArg.charAt(j) <= '9') {
				countNumber++;
			}
		}
		System.out.println("Count number in the string = " + countNumber);
	}
	
	public static int countUppercaseCharacter(String stringArg) {
		int count = 0;
		
		for(int i = 0; i < stringArg.length(); i++) {
			if (stringArg.charAt(i) >= 'A' && stringArg.charAt(i) <= 'Z') {
				count++;
			}
		}
		return count;
	}
	
	public static void checkFormatTelephone() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a phone number: ");
		String phone = scan.next();
		if(phone.charAt(0) == '7' || phone.charAt(0) == '8' || phone.charAt(0) == '9') {
			System.out.println("You entered correct phone format!");
		}
		else {
			System.out.println("You entered wrong phone format!");
		}
	}
	
	public static void main(String[] args) {
		String a = "Automation Testing";
		System.out.println(reverseString(a));
		String b = "Automation Testing 345 Tutorials Online 789 900003";
		handleString(b);
		System.out.println("Number of uppercase characters = " + countUppercaseCharacter(b));
		checkFormatTelephone();
		
	}
}
