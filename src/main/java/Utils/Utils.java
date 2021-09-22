package Utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.LogStatus;

import Pages.TestBase;

public class Utils extends TestBase {

	public static String path;

	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
		}
		catch(Exception e) {
			
			return false;
		}
		return true;
	}

	public static void click(String locator) {

		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		log.info("Clicked on "+locator);
		test.log(LogStatus.INFO, "Clicked on : " + locator);
	}

	public static void type(String locator, String value) {

		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		log.info("Typed " + value + " in : " + locator);
		test.log(LogStatus.INFO, "Typed " + value + " in : " + locator);

	}

	public static String getText(String locator) {

		String s = driver.findElement(By.xpath(OR.getProperty(locator))).getText();
		return s;
	}
	
	public static void dropdown(String locator , String orderby) {
		 
		Select select = new Select(driver.findElement(By.xpath(OR.getProperty(locator))));
		select.selectByVisibleText(orderby);
		log.info( "Clicked on dropdown "+locator+" and selected "+ orderby);
		test.log(LogStatus.INFO, "Clicked on dropdown "+locator+" and selected "+ orderby);


	}
	
	public static String getAttribute(String locator , String attri) {
		 
	return	driver.findElement(By.xpath(OR.getProperty(locator))).getAttribute(attri);
		
	}

	public static String captureScreenshot() throws Exception {

		File Src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String name = d.toString().replace(" ", "_").replace(":", "_") + ".jpg";
		path = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + name;

		File dest = new File(path);

		FileUtils.copyFile(Src, dest);
		log.info("Screenshot Taken and stored at path :"+ path);
		test.log(LogStatus.INFO, "Screenshot Taken and stored at path :"+ path);
		return path;
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String sheet = m.getName();
		int rows = excel.getRowCount(sheet);
		int cols = excel.getColumnCount(sheet);
		Object[][] data = new Object[rows - 1][1];

		for (int rownum = 2; rownum <= rows; rownum++) {
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int colnum = 0; colnum < cols; colnum++) {
				table.put(excel.getCellData(sheet, colnum, 1), excel.getCellData(sheet, colnum, rownum));
				data[rownum - 2][0] = table;

			}

		}

		return data;
	}
}
