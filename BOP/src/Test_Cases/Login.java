package Test_Cases;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	public static void main(String[] args) {
		//Firefox co = new ChromeOptions();
		FirefoxDriver obj = new FirefoxDriver();
		obj.get("http://google.com");
        obj.findElement(By.id("LoginId")).sendKeys("Approver");
        obj.findElement(By.name("Password")).sendKeys("User@123");
        obj.findElement(By.xpath("//button[text()='Login']")).click();
	}

}
