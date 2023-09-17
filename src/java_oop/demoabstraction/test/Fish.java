package java_oop.demoabstraction.test;

import java_oop.demoabstraction.model.Animal;
import java_oop.demoabstraction.model.IFish;

public class Fish extends Animal implements IFish{
	
	public Fish() {
		super();
	}
	
	@Override
	public void sleep() {
		System.out.println("I'm not sleep!!!");
		
	}
	
	@Override
	public void swimmable() {
		System.out.println("I can swim!!!");
	}
	
	public static void main(String[] args) {
		Fish fish = new Fish();
		
		fish.sleep();
		fish.swimmable();
	}
}
