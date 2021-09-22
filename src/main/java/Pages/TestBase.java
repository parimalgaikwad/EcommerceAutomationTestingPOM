package Pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Utils.ExcelReader;
import Utils.ExtentManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties config;
	public static Properties OR;
	public static WebDriverWait wait;
	public static ExcelReader excel;
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static Logger log;

	public TestBase() {

		config = new Properties();
		OR = new Properties();
		excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\Ecomtestdata.xlsx");
		try {
			config.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			OR.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\OR.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		if (config.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();

		}
	driver.get(config.getProperty("url"));
		log.info("Launched Browser.....!!!");


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}

	@BeforeSuite
	public void startLogsExtentReports() {
		Date d = new Date();
		System.setProperty("current.date", d.toString().replace(" ","_").replace(":","_"));
		PropertyConfigurator.configure(System.getProperty("user.dir") + "//src//test//resources//log4j.properties");
		
		log = Logger.getLogger(TestBase.class);
		test = rep.startTest("");

	}
	
}
