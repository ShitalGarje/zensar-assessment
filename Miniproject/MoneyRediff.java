package Miniproject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;


public class MoneyRediff {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		

	

		
		 System.setProperty("webdriver.chrome.driver","D:\\worksoft\\chromedriver_win32\\chromedriver.exe");  
			 // Instantiate a ChromeDriver class.       
			    WebDriver driver=new ChromeDriver(); 
		       driver.get("https://money.rediff.com/index.html");
		       driver.manage().window().maximize();
		       String title= driver.getTitle();
		       System.out.println(title);
		       
		       
		       driver.findElement(By.xpath("(//a[text()='My Portfolio'])[1]")).click();
		       WebElement loginph= driver.findElement(By.id("useremail"));
		       loginph.sendKeys("shitalgarje1530@gmail.com");
		       WebElement pass = driver.findElement(By.id("userpass"));
		       pass.sendKeys("SG@123");
		       driver.findElement(By.id("loginsubmit")).click();
		     
		       driver.findElement(By.xpath("//img[@title='Create']")).click();
		     
		       
		       //driver.findElement(By.xpath("(//a[text()='My Portfolio'])[1]")).click();
		       //driver.findElement(By.xpath("(//a[text()='My Portfolio'])[1]")).click();
		       //driver.switchTo().alert().sendKeys("ShitalPortfolio");
		       Thread.sleep(2000);
		      
		     //  Actions act=new Actions(driver);   
		      // WebElement wb2= driver.findElement(By.xpath("html[1]/body[1]/div[2]/div[5]/div[3]/form[1]/div[1]/div[3]/input"));
		      // act.doubleClick(wb2).perform();      
		       
		     //img[@title='Create']
		     //input[@name='create']
		     //input[@value='Create Portfolio']
		       
		       
		     }  

		}


