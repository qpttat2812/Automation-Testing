package java_oop.overloading.model;

public class Product {
	private int productCode;
	private String productName;
	private float productCost;
	
	public Product(int productCode, String productName, float productCost) {
		this.productCode = productCode;
		this.productName = productName;
		this.productCost = productCost;
	}
	
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	
	public int getProductCode() {
		return this.productCode;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return this.productName;
	}
	
	public void setProductCost(float productCost) {
		this.productCost = productCost;
	}
	
	public float getProductCost() {
		return this.productCost;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "product code = " + this.productCode + "\n" + "product name = " + this.productName + "\n" + "product cost = " + this.productCost;
	}
	
	//parameter list
	public float costTotal(float ...costs) {
		float total = 0f;
		
		for (float cost : costs){
			total += cost;
		}
		return total;
	}
}
