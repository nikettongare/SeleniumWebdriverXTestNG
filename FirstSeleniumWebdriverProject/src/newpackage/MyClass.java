package newpackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyClass {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://www.google.com/ncr");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("calculator", Keys.RETURN);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
			wait.until(ExpectedConditions.titleIs("calculator - Google Search"));

			/*
			 * const keyMap = { 0: 'bkEvMb', 1: 'N10B9', 2: 'lVjWed', 3: 'KN1kY', 4:
			 * 'xAP7E', 5: 'Ax5wH', 6: 'abcgof', 7: 'rk7bOd', 8: 'T7PMFe', 9: 'XoxYJ',
			 * equals: 'Pt8tGc', plus: 'XSr6wc', minus: 'pPHzQc', multiply: 'YovRWb',
			 * divide: 'WxTTNd', };
			 */

			WebElement fourthKeyElement = driver.findElement(By.xpath("//*[@jsname='xAP7E']"));
			fourthKeyElement.click();

			WebElement plusElement = driver.findElement(By.xpath("//*[@jsname='XSr6wc']"));
			plusElement.click();

			WebElement eighthKeyElement = driver.findElement(By.xpath("//*[@jsname='T7PMFe']"));
			eighthKeyElement.click();

			WebElement equalsElement = driver.findElement(By.xpath("//*[@jsname='Pt8tGc']"));
			equalsElement.click();

			WebElement resultElement = driver.findElement(By.xpath("//*[@jsname='VssY5c']"));
			String text = resultElement.getText();

			System.out.println("The result is: " + text);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}
}
