package Miniproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MoneyRediff {

	 static WebDriver driver;
	 static WebDriverWait wait;
	 
	 public static void main(String[] args) throws IOException {
	 // TODO Auto-generated method stub
		TextFileInput textfile = new TextFileInput();
		ArrayList<String> textfiledata = textfile.readtextinputfile();
		String url = textfiledata.get(0);
		String textfile_useremail = textfiledata.get(1);
		String textfile_password = textfiledata.get(2);
		String expected_username = textfiledata.get(3);
		 
		// system property set for chromdriver
		 System.setProperty("webdriver.chrome.driver","D:\\worksoft\\chromedriver_win32 (1)\\chromedriver.exe");  
			      
		 driver=new ChromeDriver(); 	 //creating object of chromedriver class
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //implicite wait
		 driver.get(url); 				//fetcing url in chrome browser
		 driver.manage().window().maximize();  //maximize the window of chrome browser
		 wait = new WebDriverWait(driver, 10);  //explicite wait

		 driver.findElement(By.xpath("//a[@href='https://portfolio.rediff.com/portfolio-login']")).click();  //click on lgin button

	     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='useremail']")));  //wait for the element to be present
						
						//
		 WebElement username = driver.findElement(By.xpath("//input[@id='useremail']")); //get username webelement
		 WebElement password = driver.findElement(By.xpath("//input[@id='userpass']"));
		 WebElement submit = driver.findElement(By.xpath("//input[@id='loginsubmit']"));

			ExcelUtility u = new ExcelUtility();  //ExcelUtility class is created for reading and writing excel sheets.
			u.writetextfile("Module name : ", "Test result", "Comments \n");   //writing into excel sheet result
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginsubmit']"))); //wait for the element to be clickable
			username.sendKeys(textfile_useremail);  //
			password.sendKeys(textfile_password);
			submit.click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='http://mypage.rediff.com/profile/myprofile']")));
			String actualusername = driver.findElement(By.xpath("//a[@href='http://mypage.rediff.com/profile/myprofile']")).getText();
			if (actualusername.equals(expected_username)) {
					u.writetextfile("Login", "Pass", "Logged in successfully");
				}
		   else {
				u.writetextfile("Login", "Fail", "Logged in not successful");
				}
						
						
			u.sheetname = "Sheet1";   //store sheetname object as As "sheet1"
			u.getNoofColumns(u.sheetname);  //call getNoofColumns method in ExcelUtility
			int count_portfolio = u.getNoofRows(u.sheetname);
			ArrayList<String> portfolioname = u.getportfolio_Names();
			
			for (int i = 0; i < count_portfolio - 1; i++) {
				String p_name = portfolioname.get(i);

				int rowno = i + 1;
				int columnno = 2;
				
				try {
					// checking if portfolio is present on website
					if (u.check_IfportfolioExists_one(driver, p_name) == true
							|| u.check_IfportfolioExists_indropdown(driver, p_name) == true) {
						System.out.println(p_name + ": is already present");

						u.setresult("TRUE", rowno, columnno);  // setting result in excel sheet
						u.writetextfile("PORFOLIO : " + p_name, "Pass", "Already Exist \n"); // storing logs 

					} else {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='createPortfolio']")));
						driver.findElement(By.xpath("//a[@id='createPortfolio']")).click();
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='create']")));
						WebElement portfolio_nameenter = driver.findElement(By.xpath("//input[@id='create']"));
						portfolio_nameenter.clear();
						portfolio_nameenter.sendKeys(portfolioname.get(i));
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//input[@id='createPortfolioButton']")));
						driver.findElement(By.xpath("//input[@id='createPortfolioButton']")).click();
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='createPortfolio']")));
						System.out.println("PORFOLIO :" + p_name + " is newly created");
						u.setresult("FALSE", rowno, columnno);      	// setting result in excel sheet
						u.writetextfile("PORFOLIO :" + p_name, "Pass", "New Created \n");  // storing logs
					}
				}	
						
			 catch (Exception e) {
			 // TODO: handle exception
			 u.writetextfile("Error : " + p_name, "Fail ",  e.getMessage()+ " \n");
			 }
	      }

		   //driver.close();
	     }

     }
			

				
				





