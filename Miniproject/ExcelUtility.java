package Miniproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExcelUtility {
	
	static String excelpath="D:\\worksoft\\resources\\shitalportfolio.xlsx";  //location of excelsheet;
	static String textfilepath = "D:\\worksoft\\resources\\log.txt";//location of textfile in which result is declared
	String sheetname = "Sheet1";
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;  
	WebDriverWait wait;
	

	// returning count if 'no of rows' from excel sheet
	public int getNoofRows(String sheetname) {
		try {
			FileInputStream fileinput = new FileInputStream(excelpath); 
			wb = new XSSFWorkbook(fileinput);
			sheet = wb.getSheet(sheetname);	
			//getNoofRows();
			//	getNoofColumns();
		} catch (IOException e) {        //exception handle by try catch if error occure
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int rows = 0;
		rows = sheet.getPhysicalNumberOfRows();    //total rows declare
		//System.out.println("Rows: " + rows);    //print index of row
		return rows;

	}

	// returning count if 'no of Columns' from excel sheet
	public int getNoofColumns(String sheetname) {
		try {
			FileInputStream fileinput = new FileInputStream(excelpath);
			wb = new XSSFWorkbook(fileinput);
			sheet = wb.getSheet(sheetname);	
			//getNoofRows();
		//	getNoofColumns();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int columns = 0;
		columns = sheet.getRow(0).getPhysicalNumberOfCells();
		//System.out.println("Columns: " + columns);   
		return columns;

	}
	
	// returning value from particular cell from excel sheet
	public  String getCellData(int row, int column) {
		try {
			FileInputStream fileinput = new FileInputStream(excelpath);
			wb = new XSSFWorkbook(fileinput);
			sheet = wb.getSheet("Sheet1");	
			//getNoofRows();
		//	getNoofColumns();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String celldata = null;
		celldata = sheet.getRow(row).getCell(column).getStringCellValue();

		return celldata;

	}
	
	//returning the portfolionames from excel sheet
	public ArrayList<String> getportfolio_Names() {
		ArrayList<String> portfolio_names = new ArrayList<String>();
		String sheetname = "Sheet1";
		int count_rows = getNoofRows(sheetname);
		try {
			for (int i = 1; i < count_rows; i++) {
				portfolio_names.add( getCellData(i, 0) );
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return portfolio_names;
	}
	
	// to check if portfolioname is exists on website (If single portfolioname to check)
	public boolean check_IfportfolioExists_one(WebDriver driver, String p_name) {
		  boolean flag = false;
		  try {
			  	wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='portfolioidName']")));
				String portfolioname_actual = driver.findElement(By.xpath("//div[@id='portfolioidName']")).getText();
				if (p_name.equals(portfolioname_actual)) {
					 System.out.println(p_name +": is already present");		
					flag = true;
				}

			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		  return flag;
	  }
		// to check if portfolioname is exists on website (If dropdown present)
	  public boolean check_IfportfolioExists_indropdown(WebDriver driver, String p_name) {
		  boolean flag = false;
		  try {
				List<WebElement> actualportfoliopresent = driver.findElements(By.xpath("//select[@id='portfolioid']//option"));
				List<String> actualportfoliopresent_names = new ArrayList<String>();
				for (int i = 0; i < actualportfoliopresent.size(); i++) {					
					actualportfoliopresent_names.add(actualportfoliopresent.get(i).getText());
				}
				if(actualportfoliopresent_names.contains(p_name)) {					
					flag= true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				flag = false;
			}
		  return flag;
	  }
	  
	  // write result in excel sheet
	  public void writeresult(String result, int rowno, int columnno) 
	  {
		  try {
		
	        Workbook wb = new XSSFWorkbook();	  
	        OutputStream os = new FileOutputStream(excelpath);	        
	        Sheet sheet = wb.getSheet("Sheet1");         
	        
	        Row row = sheet.getRow(rowno);
	        Cell cell = row.getCell(columnno);
	        // Specific cell number
	        cell.setCellValue("TRUE");	  
			wb.write(os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  
	     
	  }
	  
	  // write result in excel sheet
	  public void setresult(String result, int rowno, int columnno) throws IOException {
		  FileInputStream fsIP= new FileInputStream(new File(excelpath)); //Read the spreadsheet that needs to be updated
		  XSSFWorkbook wb = new XSSFWorkbook(fsIP); //Access the workbook
		  XSSFSheet worksheet = wb.getSheet("Sheet1"); //Access the worksheet, so that we can update / modify it.
		  Row row = worksheet.getRow(rowno);
		  Cell cell = row.createCell(columnno);
		  cell.setCellValue(result);  // Get current cell value value and overwrite the value		  
		  fsIP.close(); //Close the InputStream
		  FileOutputStream output_file =new FileOutputStream(new File(excelpath));  //Open FileOutputStream to write updates
		  wb.write(output_file); //write changes
		  output_file.close();  //close the stream   
	  }
	  
	  // write logfile
	  public void writetextfile(String modulename, String testresult, String comment) {
				
				try {
					File file = new File(textfilepath);
					 String text
			            =  modulename +" - "+ testresult +" - "+ comment +"\n";			      
			            FileWriter fWriter = new FileWriter(file,true); 
			            fWriter.append(text); 
			            fWriter.close();			 
			           
				}
				
				catch (IOException e) {
					
					System.out.print(e.getMessage());
				}
		
				
	  }
	  
	
}

