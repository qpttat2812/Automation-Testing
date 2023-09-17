package java_oop.demoabstraction.test;

import java_oop.demoabstraction.model.Animal;
import java_oop.demoabstraction.model.IRunable;

public class Dog extends Animal implements IRunable{
	
	public Dog() {
		super();
	}
	
	public Dog(String breed, float weight, boolean isTall) {
		super(breed, weight);
		
		if(isTall == true) {
			System.out.println("The dog is tall!");
		}
		else {
			System.out.println("The dog is short!");
		}
	}
	
	@Override
	public void runable() {
		System.out.println("I can run!!!");
		
	}

	public static void main(String[] args) {
		Dog myDog = new Dog("golden", 15f, false);
		
		myDog.eat();
		myDog.runable();
	}
}
