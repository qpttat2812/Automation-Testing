package java_oop.demoabstraction.test;

import java_oop.demoabstraction.model.Animal;
import java_oop.demoabstraction.model.IFlyable;

public class Bird extends Animal implements IFlyable{
	
	public Bird() {
		super();
	}
	
	
	public void eat() {
		super.eat();
	}
	
	@Override
	public void flyable() {
		System.out.println("I can fly!!!");
	}
	
	public static void main(String[] args) {
		Bird bird = new Bird();
		
		bird.eat();
		bird.flyable();
	}
}
