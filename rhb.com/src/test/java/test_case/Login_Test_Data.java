package test_case;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Page_Objects.Login_page;
import base.Base_Class;

public class Login_Test_Data extends Base_Class{

private static  Logger log = LogManager.getLogger(Login_Test_Data.class);
	
	Login_page loginobj ;
	
	@Test(priority=1)
	public void logoutMainUser() throws IOException
	{
		log.info("This is logout method for Main User");  	
		loginobj = new Login_page(obj); 
    	boolean mainuserlogout = loginobj.logout();
    	log.info("Logout Method Called");
    	if(mainuserlogout == true)
    	{
    		log.info("Main User Logged Out Success");
    	}
    	else
    	{
    		log.error("Main User Logged Out Failed");
    	}
	}
   
    @Test(priority=2, enabled=true, dataProvider = "LoginCredentials", dataProviderClass = Login_Test_Data.class)
    public void Login_Test(String username, String password, String errormessage) throws InterruptedException, IOException
	{
    	log.info("Login Test Started");
    	//loginobj = new Login_page(obj); 
    	
    	
    	try
    	{
            loginobj = new Login_page(obj); 
            log.info("Login_Page Object Created for User :" + username);
    	}
    	catch(Exception e)
    	{
    		log.error("Login_Page Object Not Created for User :" + username);
    	}
    	log.info("Attempting login for user: " + username);
    	 loginobj.login_Tests(username,password);
   //     boolean loginsucess =  loginobj.login_Tests(username,password);
        
        if(errormessage != null && !errormessage.isEmpty())
        {
        	String actualmessage =  loginobj.failed_Test();
        	try {
        	Assert.assertEquals(actualmessage, errormessage, "Error Message Does Not Matched");
        	log.info("Expected error message matched for user: " + username);
        	log.info(errormessage);
        	}catch(AssertionError e)
        	{
        	log.error("Expected error Not message matched for user: " + username);
        	throw e;
        	}
        }
        
        else 
        {
        	
        	Thread.sleep(3000);
        	WebElement Home = obj.findElement(By.xpath("//h1[text()='Home']"));
        	boolean Dashboard =  Home.isDisplayed();
        	Thread.sleep(3000);
        	Assert.assertTrue(Dashboard, "Login Failed For Valid condition");
        	Thread.sleep(3000);
        	log.info("Login Success for User :" + username);
        	Thread.sleep(3000);
        	 boolean testuser = loginobj.logout();
         	if(testuser == true)
         	{
         		log.info("Test User Logged Out Success");
         	}
         	else
         	{
         		log.error("Test User Logged Out Failed");
         	}
        	}
        
        
       
	}
    

	@DataProvider(name="LoginCredentials")
    public Object[][] logindata() throws IOException
    {
    	//log.info("Reading Excel for login test");
    	String userDir =   System.getProperty("user.dir");
    	File f = new File(userDir + "\\src\\test\\resources\\Utilites\\Logindata.xlsx");
    	FileInputStream fis = new FileInputStream(f);
    	XSSFWorkbook workbook = new XSSFWorkbook(fis);
    	Sheet sheet = workbook.getSheetAt(1); 
    	//log.info(sheet);
    	int rowCount = sheet.getPhysicalNumberOfRows();
    	//log.info(rowCount);
    	Object[][] data  = new Object[rowCount - 1][3];
    	for(int i=1; i < rowCount;i++)
    	{
    		Row r = sheet.getRow(i);
    		if(r != null)
    		{
    			String username = r.getCell(0).toString();
    			String password = r.getCell(1).toString();
    			String errormessage = getCellValuefortestdata(r, 2);
    			if(errormessage.isEmpty())
    			{
    				errormessage="";
    			}
    			
    			data[i-1][0] = username;
    			data[i-1][1] = password;
    			data[i-1][2] = errormessage;
    		//	log.info(username);
    			//log.info(errormessage);
    		}
    	}
    	workbook.close();
    	fis.close();
    	return data;
}
    private String getCellValuefortestdata(Row row, int cellIndex) {
        String value = "";
        Cell cell = row.getCell(cellIndex);
        if (cell != null) {
            value = cell.toString().trim(); // Trim to remove extra spaces
        }
        return value;
    }
    
    @Test(priority=3,enabled = true, dataProvider = "BlockLoginCredentials", dataProviderClass = Login_Test_Data.class)
    public void Block_User(String username, String password, String errormessage) throws InterruptedException
	{
    	//log.info("Login Test Cases Started");
    	log.info("BlockLoginCredentials");
    	try
    	{
            loginobj = new Login_page(obj); 
            log.info("Login_Page Object Created for User :" + username);
    	}
    	catch(Exception e)
    	{
    		log.error("Login_Page Object Not Created for User :" + username);
    	}
    	log.info("Attempting login for user: " + username);
        boolean loginsucess =  loginobj.login_Tests(username,password);
        if(loginsucess == true)
        {
        	log.info("Login Success for User :" + username);
        	boolean logoutmethod = loginobj.logout();
        	if(logoutmethod == true)
        	{
        		Assert.assertEquals(logoutmethod, true);
        		log.info("Logout Sucessfull");
        	}
        	else
        	{
        		log.error("Logout Failed");
        	}
        	
        }
        else {
        	log.info("This is else block for check user fail");
        	String actualmessage =	loginobj.failed_Test();
        	if(errormessage != null && !errormessage.isEmpty())
        	{
        		if(errormessage.equalsIgnoreCase("Your account has been disabled due to maximum failed login attempts. Please contact System Administrator."))
        		{
        			log.info("Account Disable Due to Maximum login Attemps for User:" + username);
        		}
        		else
        		{
            	try {
            	Assert.assertEquals(actualmessage, errormessage, "Error Message Does Not Matched");
            	log.info("Expected error message matched for user: " + username);
            	log.info(errormessage);
            	}catch(AssertionError e)
            	{
            	log.error("Expected error Not message matched for user: " + username);
            	throw e;
            	}
        		}
        	}
        	else
        	{
            log.error("Login Failed for User: " + username); // Log if login failed
        	}
        }
        
	}
    
    @DataProvider(name="BlockLoginCredentials")
    public Object[][] Blocklogindata() throws IOException
    {
    	
    	String userDir =   System.getProperty("user.dir");
    	File f = new File(userDir + "\\src\\test\\resources\\Utilites\\Logindata.xlsx");
    	FileInputStream fis = new FileInputStream(f);
    	XSSFWorkbook workbook = new XSSFWorkbook(fis);
    	Sheet sheet = workbook.getSheetAt(2); 
    	int rowCount = sheet.getPhysicalNumberOfRows();
    	Object[][] data  = new Object[rowCount - 1][3];
    	for(int i=1; i < rowCount;i++)
    	{
    		Row r = sheet.getRow(i);
    		if(r != null)
    		{
    			String username = r.getCell(0).toString();
    			String password = r.getCell(1).toString();
    			String errormessage = getCellValuefortestdatadisable(r, 2);
    			if(errormessage.isEmpty())
    			{
    				errormessage="";
    			}
    			data[i-1][0] = username;
    			data[i-1][1] = password;
    			data[i-1][2] = errormessage;
    		}
    	}
    	workbook.close();
    	fis.close();
    	return data;
}
    private String getCellValuefortestdatadisable(Row row, int cellIndex) {
        String value = "";
        Cell cell = row.getCell(cellIndex);
        if (cell != null) {
            value = cell.toString().trim(); // Trim to remove extra spaces
        }
        return value;
    }
    
    @Test(priority='4',dataProvider = "LoginCredentials1", dataProviderClass = Login_Test_Data.class)
    public void MainUserLogin(String username,String password)
    {
    	log.info("This is extra method for main user start");
    	Login loginobj1 = new Login();
    	loginobj1.Login_Data(username, password);
    	log.info("This is extra method for main user ended");
    }
    
    @DataProvider(name = "LoginCredentials1")
	public Object[][] MainUserLogin() throws IOException {
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

