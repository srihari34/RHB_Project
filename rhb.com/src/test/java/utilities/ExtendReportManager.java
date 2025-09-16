package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReportManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance()
	{
		if(extent == null)
		{
		   String reportpath = System.getProperty("user.dir")+ "/reports/ExtentReport.html";
		   
		   ExtentSparkReporter  htmlreport = new ExtentSparkReporter(reportpath);
		   htmlreport.config().setDocumentTitle("Automation Test Report");
		   htmlreport.config().setReportName("Functional Extent Report");
		   htmlreport.config().setTheme(Theme.STANDARD);
		   
		   extent = new ExtentReports();
		   extent.attachReporter(htmlreport);
		}
		return extent;
		
	}

}
