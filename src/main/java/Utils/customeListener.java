package Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class customeListener extends Utils implements ITestListener {


	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult arg0) {
		try {
			captureScreenshot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info(arg0.getName()+" is Failed");
		test.log(LogStatus.FAIL, arg0.getName() + " is Failed....!!");
		test.log(LogStatus.FAIL, test.addScreenCapture(path));
		rep.endTest(test);
		rep.flush();

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult arg0) {
		log.info("Test Started");
		test= rep.startTest(arg0.getName().toUpperCase());

	}

	public void onTestSuccess(ITestResult arg0) {
		log.info(arg0.getName()+" is Passed");

		test.log(LogStatus.PASS, arg0.getName() + " is Passed....!!");
		rep.endTest(test);
		rep.flush();
	}

}
