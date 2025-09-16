package Page_Objects;

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

public class Access_Permissions_page {
	
	private static  Logger log = LogManager.getLogger(Access_Permissions_page.class);
	
	WebDriver obj;

	public Access_Permissions_page(WebDriver driver) {

       this.obj = driver;
       PageFactory.initElements(obj, this);
	}
	
	@FindBy(xpath="//span[text()='Access & Permissions']")
	WebElement AccessPermissions;
	
	@FindBy(xpath="//span[text()='Groups']")
	WebElement Groups;
	
	@FindBy(xpath="//span[text()='Roles']")
	WebElement Roles;
	
	@FindBy(xpath="//span[text()='Users']")
	WebElement Users;
	
	@FindBy(xpath="//button[@title=\"Add New Group\"]")
	WebElement add_new_group;
	
	@FindBy(xpath="//button[text()=' New Role']")
	WebElement add_new_role;
	
	@FindBy(name="name")
	WebElement name;
	
	@FindBy(xpath="//input[@name='description']")
	WebElement description;
	
	@FindBy(id="mat-checkbox-1")
	WebElement Module;

	@FindBy(id="mat-checkbox-2")
	WebElement Accesspermisionmodule;
	
	@FindBy(id="mat-checkbox-3")
	WebElement Archiveviewermodule;
	
	@FindBy(id="mat-checkbox-4")
	WebElement cofigurationmodule;
	
	@FindBy(id="mat-checkbox-5")
	WebElement datasetupmodule;
	
	@FindBy(id="mat-checkbox-6")
	WebElement reportsmodule;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement submit;
	
	@FindBy(xpath="//mat-select[@name='group']")
	WebElement selectgroupdropdown;
	
	@FindBy(xpath="//span[text()=' Group ']")
	WebElement selectgroupname;
	
	@FindBy(xpath="//mat-panel-title[text()=' Access Permissions ']")
	WebElement Access_Permission_DropDown;
	
	@FindBy(xpath="//mat-panel-title[text()=' Archive Viewer ']")
	WebElement Archive_Viewer_DropDown;
	
	@FindBy(xpath="//mat-panel-title[text()=' Configuration ']")
	WebElement Configuration_DropDown;
	
	@FindBy(xpath="//mat-panel-title[text()=' Data Setup ']")
	WebElement Data_Setup_DropDown;
	
	@FindBy(xpath="//mat-panel-title[text()=' Reports ']")
	WebElement Reports_DropDown;
	
	@FindBy(xpath="//td[text()='Groups']/following-sibling::td[@class='ng-star-inserted'][1]")
	WebElement GroupAddPermission;
//	@FindBy(xpath="//td[text()='Groups']/following-sibling::td[@class='ng-star-inserted'][2]")
//	WebElement GroupEditPermission;



	public void addgroup() {
		
		AccessPermissions.click();
		Groups.click();
		add_new_group.click();
		name.sendKeys("Group");
		log.info(name);
		log.info(description.getAttribute("name"));
		description.sendKeys("Description for Group Creation");
		Accesspermisionmodule.click();
		Archiveviewermodule.click();
		
		cofigurationmodule.click();
		JavascriptExecutor js = (JavascriptExecutor) obj;
		js.executeScript("arguments[0].scrollIntoView(true);", submit);
		WebDriverWait wait = new WebDriverWait(obj, Duration.ofSeconds(10));
		WebElement checkboxdata = wait.until(ExpectedConditions.elementToBeClickable(datasetupmodule));
		checkboxdata.click();
		reportsmodule.click();
		//Module.click();
		submit.click();		
	}

	public void addrole() throws InterruptedException  {
		Roles.click();
		JavascriptExecutor js = (JavascriptExecutor) obj;
		js.executeScript("arguments[0].scrollIntoView(true);", add_new_role);
		log.info("This is message " +add_new_role.getText());
		Thread.sleep(2000);
		add_new_role.click();
		name.sendKeys("Role");
		selectgroupdropdown.click();
		selectgroupname.click();
		description.sendKeys("Description for Role Creation");
		Access_Permission_DropDown.click();
		js.executeScript("arguments[0].scrollIntoView(true);", GroupAddPermission);
		GroupAddPermission.click();
		//GroupEditPermission.click();
	}
	

}
