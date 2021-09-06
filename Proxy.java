package sel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Proxy {

// https://stackoverflow.com/questions/45288842/how-to-set-proxy-for-chrome-browser-in-selenium-using-java-code	
	
     public static void main(String[] args) {
    	  
    	 System.setProperty("webdriver.chrome.driver","D:\\worksoft\\chromedriver_win32 (1)\\chromedriver.exe");   
		ChromeDriver Webdriver = new ChromeDriver();
		 String proxy = "127.0.0.1:5000";
         ChromeOptions options = new ChromeOptions().addArguments("--proxy-server=http://" + proxy);
         WebDriver driver = new ChromeDriver(options);
         driver.get("https://www.google.co.in/");

   }


}