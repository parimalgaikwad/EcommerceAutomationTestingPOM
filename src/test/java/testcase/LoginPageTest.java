package testcase;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Utils.Utils;

public class LoginPageTest extends Utils {

	LoginPage LP;
	HomePage HP;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		LP = new LoginPage();

	}

	@Test
	public void verifyTitleTest() {
		try {
			Assert.assertEquals(LP.verifyTitle(), "LOGIN OR CREATE AN ACCOUNT", "Title did not matched");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dataProvider = "dp", dataProviderClass = Utils.class)
	public void doLoginTest(Hashtable<String, String> data) {

		HP = LP.doLogin(data.get("email"), data.get("password"));
		try {
			Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("welcomemsg"))), "Invalid Credentials ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("done");
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

}
