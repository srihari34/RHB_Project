package utilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import base.Base_Class;

public class ScreenShotBase  extends Base_Class{
	@Test
	public void getScreenShot(String packageName, String className, String methodName) throws IOException
	{
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
	    String fileName = packageName + "_" + className + "_" + methodName + "_" + timestamp + ".txt";
		File screenshotfile = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotfile, new File(".//screenshot//"+ fileName));
	}

}
