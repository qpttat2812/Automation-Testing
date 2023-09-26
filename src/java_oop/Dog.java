package java_oop;

public class Dog extends Animal {
	private String color;
	private String country;

	public Dog() {
//		not use super then it calls constructor with no arguments
		super("vnesedog");
		System.out.println("Dog Constructor - Default");
	}
	
	public Dog(String color, String country) {
		this.color = color;
		this.country = country;
		System.out.println("Dog Constructor");
	}

	public void eat() {
		breed = "Golden";
		System.out.println("I eat!");
	}

	public static void main(String[] args) {
		Dog myDog = new Dog();
		myDog.eat();
		myDog.sleep();
	}
}
