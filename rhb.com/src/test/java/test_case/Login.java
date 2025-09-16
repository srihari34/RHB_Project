package test_case;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Page_Objects.Login_page;
import base.Base_Class;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Login extends Base_Class {

	private static Logger log = LogManager.getLogger(Login.class);

	Login_page loginobj;
	

	@Test(dataProvider = "LoginCredentials", dataProviderClass = Login.class)

	public void Login_Data(String username, String password) {
		log.info("Login Test Cases Started");
		try {
			loginobj = new Login_page(obj);
			log.info("Login_Page Object Created");
		} catch (Exception e) {
			log.error("Login_Page Object Not Created");
		}
		log.info("Login Page Method call");
		
		boolean isLoginSucessful = loginobj.login_Data(username, password);
		try
		{
		Assert.assertTrue(isLoginSucessful, "Login Failed For User : " + username);
		log.info("Login Test Completed Sucessfully");
		}
		catch(AssertionError e)
		{
			log.error("Login Test Failed for User : "+ username,e);
			throw e;
		}

	}

	@DataProvider(name = "LoginCredentials")
	public Object[][] logindata() throws IOException {
		String userDir = System.getProperty("user.dir");
		File f = new File(userDir + "\\src\\test\\resources\\Utilites\\Logindata.xlsx");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount - 1][2];
		for (int i = 1; i < rowCount; i++) {
			Row r = sheet.getRow(i);
			if (r != null) {
				String username = r.getCell(0).toString();
				String password = r.getCell(1).toString();

				data[i - 1][0] = username;
				data[i - 1][1] = password;
			}
		}
		workbook.close();
		fis.close();
		return data;
	}
}
