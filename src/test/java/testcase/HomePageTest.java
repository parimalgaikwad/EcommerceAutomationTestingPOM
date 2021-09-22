package testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Utils.Utils;

public class HomePageTest extends Utils {
	LoginPage LP;
	HomePage HP;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		LP = new LoginPage();

	}

	@Test
	public void verifyWelcomeMsgTest() {
		HP = LP.doLogin("shraddhaforparimal@gmail.com", "swarajforupsc");
		Assert.assertTrue(HP.verifyWelcomeMsg().contains("shraddha"));

	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
