package com;

import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

public class NewTest {
	public String baseUrl = "https://practicetestautomation.com/practice-test-login/";
	String driverPath = "C:\\geckodriver.exe";
	public WebDriver driver;
	public String expected = null;
	public String actual = null;

	@BeforeTest
	public void launchBrowser() {
		System.out.println("launching chorme browser");
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}

	@BeforeMethod
	public void verifyHomepageTitle() {
		String expectedTitle = "Test Login | Practice Test Automation";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test(dataProvider = "testdata")
	public void testo(String username, String password) throws IOException {
		
		WebElement usernameE = driver.findElement(By.id("username"));
		usernameE.click();
		usernameE.sendKeys(username);
		
		WebElement passwordE = driver.findElement(By.id("password"));
		passwordE.click();
		passwordE.sendKeys(password);
		
		driver.findElement(By.id("submit")).click();
	}
	
	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}
	
	@DataProvider(name="testdata")
	public String[][] getData() throws Exception {
		File f = new File(System.getProperty("user.dir") + "\\src\\resources\\dummy.xlsx");
		FileInputStream fis = new FileInputStream(f);		
		XSSFWorkbook workbook_obj = new XSSFWorkbook(fis);
		XSSFSheet sheet_obj = workbook_obj.getSheet("login");
		
		int totalRow  = sheet_obj.getLastRowNum();
		System.out.println(totalRow);
		XSSFRow firestRow = sheet_obj.getRow(0);
		int totalCells = firestRow.getLastCellNum();
		System.out.println(totalCells);
		
		DataFormatter format = new DataFormatter();
		String testData[][] = new String[totalRow][totalCells];
		
		for(int i = 1; i <= totalRow ; i++) {
			for(int j = 0; j < totalCells; j++) {
				testData[i-1][j] = format.formatCellValue(sheet_obj.getRow(i).getCell(j));
			}
		}
	
		workbook_obj.close();
		
		return testData;
		

	}
}
