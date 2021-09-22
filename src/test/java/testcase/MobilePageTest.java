package testcase;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Pages.LoginPage;
import Pages.MobilePage;
import Utils.Utils;

public class MobilePageTest extends Utils {
	LoginPage LP;
	MobilePage MP;

	public MobilePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		click("mobile");
		LP = new LoginPage();
		MP = new MobilePage();

	}

	@Test(priority=0,dataProvider = "dp", dataProviderClass = Utils.class)
	public void CompareValuesTest(Hashtable<String, String> data) {

		boolean b = MP.CompareValues(data.get("brand in List"), data.get("brand in Details"));

		AssertJUnit.assertTrue(b);

	}

	@Test(priority=1)
	public void addToCartTest() {

		String expmsg = "Sony Xperia was added to your shopping cart.";

		AssertJUnit.assertEquals(MP.addToCart(), expmsg);
	}

	@Test(priority=2,dataProvider = "dp", dataProviderClass = Utils.class)
	public void updateCartTest(Hashtable<String, String> data) {

		AssertJUnit.assertEquals(MP.updateCart(data.get("qty")), "Cart updated successfully");
	}

	@Test(priority=3)
	public void emptyCartTest() {

		String expmsg = "SHOPPING CART IS EMPTY";
		MP.updateCart("20");
		AssertJUnit.assertEquals(MP.emptyCart(), expmsg);
	}

	@Test(priority=4,dataProvider = "dp", dataProviderClass = Utils.class)
	public void compareMobilesTest(Hashtable<String, String> data) {

		Assert.assertTrue(MP.compareMobiles(data.get("brand1"), data.get("brand2")));
		test.log(LogStatus.INFO,
				data.get("brand1") + " And " + data.get("brand2") + " products present on Compare window");
	}

//	
//	
	@Test(priority=5,dataProvider = "dp", dataProviderClass = Utils.class)
	public void sortByTest(Hashtable<String, String> data) {

		Assert.assertTrue(MP.sortBy(data.get("sortby")));
		test.log(LogStatus.INFO, "Sorted products by " + data.get("sortby"));
	}

	@Test(priority=6)
	public void shareWishlistTest() {

		LP.doLogin("mp@g.com", "12345678");
		click("mobile");
		boolean t = MP.shareWishlist("mp@g.com", "Test");

		Assert.assertTrue(t);
		test.log(LogStatus.INFO, "Wishlist shared to mail");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
