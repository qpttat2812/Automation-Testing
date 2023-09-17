package java_oop;

public class Dog extends Animal{
	private String breed;
	
	public Dog() {
//		not use super then it calls constructor with no arguments
		super("vnesedog");
		System.out.println("Child Constructor");
	}
	
	public static void main(String[] args) {
		Dog myDog = new Dog();
	}
}
