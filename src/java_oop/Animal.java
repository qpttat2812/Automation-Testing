package java_oop;

public class Animal {
	String breed; 
	
	public Animal() {
		System.out.println("Parent constructor");
	}
	
	private Animal(int height) {
		System.out.println("Parent constructor " + height);
	}
	
	public Animal(String breed) {
		System.out.println("Parent constructor " + breed);
	}
	
	public void sleep() {
		System.out.println("I sleep!");
	}
}
