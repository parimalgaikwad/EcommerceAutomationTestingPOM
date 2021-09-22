package testcase;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Pages.LoginPage;
import Pages.RegistrationPage;
import Utils.Utils;

public class RegistrationPageTest extends Utils {

	LoginPage LP;
	RegistrationPage RP;
	
	public RegistrationPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();

		LP = new LoginPage();
		RP = LP.goToCreateAnAccount();
		
	}
	
	@Test(dataProvider="dp",dataProviderClass=Utils.class)
	public void createAccountTest(Hashtable<String,String>data)
	{
	String msg=	RP.createAccount(data.get("firstname"),
						 data.get("lastname"),
						 data.get("email"),
						 data.get("password"),
						 data.get("confirm password"));
		Assert.assertEquals(msg, "Thank you for registering with Main Website Store.");
		test.log(LogStatus.INFO, msg);
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
}
