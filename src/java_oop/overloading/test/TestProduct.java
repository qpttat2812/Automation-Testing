package java_oop.overloading.test;

import java_oop.overloading.model.Product;

public class TestProduct {

	public static void main(String[] args) {
		Product product1 = new Product(1, "Pepsi", 25f);
		Product product2 = new Product(2, "Coca", 35f);
		Product product3 = new Product(3, "7Up", 30f);
		
		System.out.println(product1 + "\n=======================");
		System.out.println(product2 + "\n=======================");
		System.out.println(product3 + "\n=======================");
		
		System.out.println(product1.costTotal(product1.getProductCost()));
		System.out.println(product2.costTotal(product1.getProductCost(), product2.getProductCost()));
		System.out.println(product3.costTotal(product1.getProductCost(), product2.getProductCost(), product3.getProductCost()));
	}

}
