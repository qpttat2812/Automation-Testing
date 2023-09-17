package java_oop;

public class Animal {
	
	private Animal() {
		System.out.println("Parent constructor");
	}
	
	public Animal(int height) {
		System.out.println("Parent constructor " + height);
	}
	
	public Animal(String breed) {
		System.out.println("Parent constructor " + breed);
	}
}
