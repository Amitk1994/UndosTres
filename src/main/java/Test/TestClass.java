package Test;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class TestClass 
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver","/users/amitkumar/downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://sanbox.undostres.com.mx/");
		
		//now Selecting operator and fill Recharge Details
		
		driver.findElement(By.name("operator")).click();
		driver.findElement(By.xpath("//*[@id=\"col-sm-12\"]/form/div/div[1]/div[1]/div[2]/ul/li[1]/div/div/div/ul/li[1]/a/div/b")).click();
		driver.findElement(By.name("mobile")).sendKeys("8465433546");
		driver.findElement(By.name("amount")).click();
		driver.findElement(By.xpath("//*[@id=\"col-sm-12\"]/form/div/div[1]/div[1]/div[2]/ul/li[3]/div/div/div/ul[1]/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"col-sm-12\"]/form/div/div[1]/div[1]/div[3]/div/button")).click();
		
		//verify user is reached on payment Screen or not
		
		String str = driver.findElement(By.xpath("//*[@id=\"order-summary-container\"]/div[1]")).getText();
		 
		if(str.equalsIgnoreCase("Resumen de la compra")) { System.out.println("Successfully Login"); }
		else { System.out.println("Try Again"); }
		
		// Selecting Payment Method
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.findElement(By.id("new-card-toggle")).click();
		WebElement ele = driver.findElement(By.xpath("/html/body/div[32]/div[1]/div[3]/div/div[1]/div/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/table/tbody/tr[1]/td"));
		Actions act = new Actions(driver);
		act.moveToElement(ele).build().perform();
		act.click().build().perform();
		
		//filling payment details
		
		driver.findElement(By.id("cardnumberunique")).sendKeys("4111111111111111");
		driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[3]/div[1]/div/div[1]/input")).sendKeys("11");
		driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[3]/div[1]/div/div[3]/input")).sendKeys("25");
		driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[3]/div[2]/div/input")).sendKeys("111");
		driver.findElement(By.xpath("//*[@id=\"payment-form\"]/div[4]/div/div/input")).sendKeys("test@test.com");	
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"paylimit\"]/span")).click();
		
		//enter emailid to complete recharge
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.id("usrname")).sendKeys("automationexcersise@test.com");
		driver.findElement(By.id("psw")).sendKeys("123456");
		
		// Click on Recaptcha
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.switchTo().frame(0);
		WebElement elem = driver.findElement(By.cssSelector("#recaptcha-anchor"));
		Actions actn = new Actions(driver);
		actn.moveToElement(elem).build().perform();
		actn.click().perform();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		WebElement eleme = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[2]/table/tbody/tr/td/div/div[1]/form/button"));
		Actions acti = new Actions(driver);
		acti.moveToElement(eleme).build().perform();
		acti.click().perform();
		
		//Verify recharge is successfull or not
		
		String st = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div[1]")).getText();
		if(st.equalsIgnoreCase("Para pagar por favor Regístrate o Ingresa a tu cuenta"))
		{ System.out.println("Recharge not successfull due to user blocked"); }
		else { System.out.println("Recharge successfull"); }
	}
}
