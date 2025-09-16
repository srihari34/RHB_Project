package Page_Objects;
import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_page {
	private static  Logger log = LogManager.getLogger(Login_page.class);
	
	WebDriver driv;
	
	public Login_page(WebDriver obj) throws IOException
	{
		this.driv=obj;
		PageFactory.initElements(driv, this);
		
	}
	
	@FindBy(id="LoginId")
	WebElement Username;
	
	@FindBy(name="Password")
	WebElement Pass;
	
	@FindBy(xpath="//button[text()='Login']")
	WebElement Submitbutton;
	
	@FindBy(xpath="//h1[text()='Home']")
	WebElement Home_Title;
	
	@FindBy(xpath="//h4[text()='Invalid user name or password or Deactivated.'] | //h4[text()='Invalid user name or password.'] | //h4[text()='Your account has been disabled due to maximum failed login attempts. Please contact System Administrator.']")
	WebElement Wrong_Username;
	
	@FindBy(xpath="//label[@id='dropdownMenuButton1']")
	WebElement ProfileDropdown;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	WebElement Sign_Out;
	
	@FindBy(xpath="//button[text()='Yes']")
	WebElement Yes_Button;
	
	@FindBy(xpath="//h4[text()='Your account has been disabled due to maximum failed login attempts. Please contact System Administrator.']")
	WebElement DisableUserTest;
	
	public boolean login_Data(String username, String password)
	{
		try
		{
		log.info("Login Method Called");
		Username.sendKeys(username);
		log.info("Entered UserName is :" + username);
		Pass.sendKeys(password);
		log.info("Password Entered");
		//log.info("This is xpath for submission  "+ Submitbutton);
		Submitbutton.click();
		log.info("Submit Button Clicked");
		if(Home_Title.isDisplayed())
		{
			log.info("Login SuccessFull");
			return true;
		}
		else
		{
			log.info("Login Failed");
			return false;
		}
		}
		catch(Exception e)
		{
			log.info("Login Failed");
			
		}
		return false;
		
	}
		public boolean login_Tests(String username, String password)
		{
			try
			{
			log.info("Login Method Called");
			Username.sendKeys(username);
			log.info("Entered UserName is :" + username);
			Pass.sendKeys(password);
			log.info("Password Entered");
			//log.info("This is xpath for submission  "+ Submitbutton);
			Submitbutton.click();
			log.info("Submit Button Clicked");
			if(Home_Title.isDisplayed())
			{
				log.info("Login SuccessFull");
				return true;
			}
			else
			{
				log.info("Login Failed");
				return false;
			}
			//log.info("Login SuccessFull");
			}
			catch(Exception e)
			{
				log.info("Login Failed");
			}
			return false;
			
	}
	
		public String failed_Test()
		{
			log.info("This is failed test text");
			String message = Wrong_Username.getText();
			log.info("This is failed test text 2");
			log.info(message);
			return message;
		}
		public boolean logout() {
			try
			{
			log.info("This is logout method");
			WebDriverWait wait = new WebDriverWait(driv, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(ProfileDropdown));
			log.info("Is ProfileDropdown displayed? " + ProfileDropdown.isDisplayed());
			log.info("Is ProfileDropdown enabled? " + ProfileDropdown.isEnabled());
		    log.info(ProfileDropdown.getText());
			//ProfileDropdown.click();
			((JavascriptExecutor) driv).executeScript("arguments[0].click();", ProfileDropdown);
			Sign_Out.click();
			Yes_Button.click();
			log.info("Logout Success");
			return true;
			
			}
			catch(Exception e)
			{
				log.info("Logout Fail");
				return false;
						
			}
			
			
			
		}

}
