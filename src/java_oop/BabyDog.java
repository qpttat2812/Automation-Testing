package java_oop;

public class BabyDog extends Dog{
	
	public BabyDog() {
		System.out.println("This is default constructor of BabyDog");
	}
	
	public void play() {
		System.out.println("I play more than 8 hours");
	}
	
	public void sleep() {
		super.sleep();
	}
	
	public static void main(String[] args) {
		BabyDog dog = new BabyDog();
		dog.play();
		dog.eat();
		dog.sleep();
		
		BabyDog dog1 = new BabyDog();
		
	}
}
