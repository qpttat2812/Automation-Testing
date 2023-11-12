package learn_java;

public class HandleCountry {
	public static void main(String[] args) {
		/*
		 * String a = "South Georgia and the South Sandwich Islands"; String b = "South Georgia & South Sandwich Islands";
		 * 
		 * String[] aArray = a.split(" "); String[] bArray = b.split(" ");
		 * 
		 * if((aArray[0].equals(bArray[0])) && (aArray[aArray.length-1].equals(bArray[bArray.length-1]))) { System.out.println(true); } else { System.out.println(false); }
		 */
		
		String a = "Next Day Air ($0.00)";
		
		System.out.println(a.substring(0, a.indexOf("(")));
	}
}
