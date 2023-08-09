package learn_java;

public class topic02_operator_1 {

	public static void main(String[] args) {
		int a = 5;
		int b = 9;
		
		//swap 2 numbers - no use a third variable
		
		a = a + b;
		b = a - b;
		a = a - b;
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);

	}

}
