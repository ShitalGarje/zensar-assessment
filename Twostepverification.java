package sel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.jboss.aerogear.security.otp.Totp;

public class Twostepverification {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver_win32\\chromedriver.exe");
	    // WebDriver driver = new ChromeDriver();

	//Add your key below, which you copied earlier and used in Google Authenticator
        Totp totp = new Totp("Your secret key goes here"); // 2FA secret key
        String twoFactorCode = totp.now(); //Generated 2FA code here
        System.out.println("Two factor code is:"+twoFactorCode);
        

	 //driver.findElement(By.id("totpPin")).sendKeys(twoFactorCode);

	}

}
