package testng.demo.admin;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AdminFunction {
	
	@BeforeClass(alwaysRun = true)
	public void BeforeClass() {
		System.out.println("Before Class - Admin");
//		Assert.assertTrue(false);
	}
	
	@Test (groups = "change")
	public void TC_ChangeRole() {
		System.out.println("Change role - Admin");
	}
	
	@Test (enabled = false)
	public void TC_DeleteRole() {
		System.out.println("Delete role - Admin");
	}
	
	@Test (groups = "change")
	public void TC_EditRole() {
		System.out.println("Edit role - Admin");
	}
	
	@AfterClass(alwaysRun = true)
	public void AfterClass() {
		System.out.println("After Class - Admin");
	}
}
