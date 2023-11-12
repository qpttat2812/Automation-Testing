package learn_java;

public class HandleCurrencyPrice {

	public static void main(String[] args) {
		String price = "Gift wrapping: Yes [+$10.00]";
		float new_price = Float.valueOf(price.substring(price.indexOf("$"), price.indexOf("]")).replace("$", "").replace(".", ""));
		
		System.out.println(new_price);
	}

}
