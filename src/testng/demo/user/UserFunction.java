package testng.demo.user;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserFunction {
	
	@BeforeClass
	public void BeforeClass() {
		System.out.println("Before Class - User");
	}
	
	@Test (priority = 2, groups = "view")
	public void TC_ViewUser() {
		System.out.println("View User - User");
	}
	
	@Test (priority = 4, groups = "change")
	public void TC_DeleteUser() {
		System.out.println("Delete User - User");
	}
	
	@Test (priority = 1)
	public void TC_InsertUser() {
		System.out.println("Insert User - User");
	}
	
	@Test (priority = 3, groups = "change")
	public void TC_UpdateUser() {
		System.out.println("Update User - User");
	}
	
	@AfterClass
	public void AfterClass() {
		System.out.println("After Class - User");
	}
}
