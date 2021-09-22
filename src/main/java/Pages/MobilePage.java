package Pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import Utils.Utils;

public class MobilePage extends Utils {

	public String verifyProductValueInList(String brand) {
		String value;
		value = driver.findElement(By.xpath("//a[@title='" + brand + "']/following-sibling::div/div/span/span"))
				.getText();

		return value;
	}

	public String verifyProductValueInDetailsPage(String brand) {
		String value;
		driver.findElement(By.xpath("//h2/a[@title='" + brand + "']")).click();
		value = driver.findElement(By.xpath("//div/span/span[@class='price']")).getText();

		return value;
	}

	public boolean CompareValues(String brand, String brand2) {

		return verifyProductValueInList(brand).equals(verifyProductValueInDetailsPage(brand2));
	}

	public String addToCart() {
		click("addtocart");

		return getText(("addedtocartmsg"));
	}

	public String updateCart(String qty) {

		click(("addtocart"));
		driver.findElement(By.xpath(OR.getProperty("qty"))).clear();

		type(("qty"), qty);
		click(("update"));
		if (isElementPresent(By.xpath(OR.getProperty("errormsg"))))
			return getText("errormsg");

		return "Cart updated successfully";
	}

	public String emptyCart() {

		click("emptycart");

		return getText(("emptycartmsg"));

	}

	public boolean compareMobiles(String brand1, String brand2) {

		String Parent = driver.getWindowHandle();
		WebElement e1 = driver.findElement(By.xpath("//h2/a[@title='" + brand1
				+ "']//parent::h2/following-sibling::div[3]/ul/li/a[contains(text(),'Compare')]"));
		e1.click();
		test.log(LogStatus.INFO, "Added " + brand1 + " mobile for comparison");
		WebElement e2 = driver.findElement(By.xpath("//h2/a[@title='" + brand2
				+ "']//parent::h2/following-sibling::div[3]/ul/li/a[contains(text(),'Compare')]"));
		e2.click();
		test.log(LogStatus.INFO, "Added " + brand2 + " mobile for comparison");
		click("comparemobiles");
		Set<String> windows = driver.getWindowHandles();
		for (String w : windows) {
			if (!w.equalsIgnoreCase(Parent))
				driver.switchTo().window(w);
		}
		By Brand1 = By.xpath("//a[@title='" + brand1 + "']/img");
		By Brand2 = By.xpath("//a[@title='" + brand2 + "']/img");

		if (isElementPresent(Brand1) && isElementPresent(Brand2))
			return true;

		return false;

	}

	public boolean sortBy(String select) {

		if (select.equalsIgnoreCase("name")) {
			dropdown("sortby", "Name");
			if (getAttribute("first", "title").equalsIgnoreCase("iphone")
					&& getAttribute("second", "title").equalsIgnoreCase("samsung galaxy")
					&& getAttribute("third", "title").equalsIgnoreCase("xperia"))
				return true;
		}
		if (select.equalsIgnoreCase("position")) {
			dropdown("sortby", "Position");
			if (getAttribute("first", "title").equalsIgnoreCase("iphone")
					&& getAttribute("second", "title").equalsIgnoreCase("samsung galaxy")
					&& getAttribute("third", "title").equalsIgnoreCase("xperia"))
				return true;
		}
		if (select.equalsIgnoreCase("price")) {
			dropdown("sortby", "Price");
			if (getAttribute("first", "title").equalsIgnoreCase("xperia")
					&& getAttribute("second", "title").equalsIgnoreCase("samsung galaxy")
					&& getAttribute("third", "title").equalsIgnoreCase("iphone"))
				return true;
		}

		return false;
	}

	public boolean shareWishlist(String mail, String message) {

		click("addtowishlist");
		click("sharewishlist");
		type("textareaemail", mail);
		type("textareamsg", message);
		click("sharewishlist");

		return getText("wishlistsucces").equalsIgnoreCase("Your Wishlist has been shared.");
	}

}
