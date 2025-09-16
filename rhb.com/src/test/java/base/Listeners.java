package base;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExtendReportManager;

import utilities.ScreenShotBase;

public class Listeners extends ScreenShotBase implements ITestListener {
	// Added for extent report
	 private static ExtentReports extent = ExtendReportManager.getInstance(); //ee
	 private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); //ee
	
	public void onTestStart(ITestResult result)
	{
		Reporter.log("Test Case Started : "+ result.getName());
		System.out.println("Test Case Starting");
		// Added for extent report
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
	}
	
	public void onTestSuccess(ITestResult result)
	{
		System.out.println("Test Case Success");
		String packageName = result.getTestClass().getRealClass().getPackage().getName();
        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();
		try {
			getScreenShot(packageName, className, methodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Test Case Success : "+ result.getStatus());
		// Added for extent report
		  extentTest.get().log(Status.PASS, "Test Passed");
	}
	public void onTestFailure(ITestResult result)
	{
		System.out.println("Test Case Failure");
		String packageName = result.getTestClass().getRealClass().getPackage().getName();
        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();
		try {
			getScreenShot(packageName, className, methodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Test Case Failure : "+ result.getStatus());
		// Added for extent report
		extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());
	}
	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();  // This writes the report file
	}

}
