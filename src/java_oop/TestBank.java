package java_oop;

public class TestBank {
	public static void main(String[] args) {
		Bank bank = new Bank();
		System.out.println(bank.rateOfInterest());
		
		//runtime polymorphism - identify after initializing obj
		bank = new TCB();
		System.out.println(bank.rateOfInterest());
		
		bank = new SHB();
		System.out.println(bank.rateOfInterest());
	}
}
