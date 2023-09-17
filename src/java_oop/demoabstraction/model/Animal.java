package java_oop.demoabstraction.model;

public abstract class Animal{
	public String breed;
	public float weight;
	public String color;
	
	public Animal() {
		System.out.println("This is Animal constructor!");
	}
	
	public Animal(String color) {
		this.color = color;
	}
	
	public Animal(String breed, float weight) {
		this.breed = breed;
		this.weight = weight;
	}
	
	public void eat() {
		System.out.println("Eat!!!");
	}
	
	public void sleep() {
		System.out.println("Sleep!!!");
	}
}
