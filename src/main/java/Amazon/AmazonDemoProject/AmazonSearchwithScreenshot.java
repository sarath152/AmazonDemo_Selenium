package Amazon.AmazonDemoProject;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonSearchwithScreenshot {

	// constants
	public static final String url = "https://www.amazon.in/";
	//The product name should be copy from AmazonProduct link. 
	public static final String productName = "iPhone 13";

	// xpaths
	public static String x_searchBox = "//input[@id='twotabsearchtextbox']";
	public static String x_searchResults = "//span[text()='Customer Review']";
	public static String x_checkList = "//span[@class='a-size-medium a-color-base a-text-normal']";
	public static String x_productDisplayed = "//div[@id='centerCol']";

	public static void main(String[] args) {
		try {
			
			WebDriver driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			// HitURL
			driver.get(url);
			driver.manage().window().maximize();

			// Search for the product and wait for the search results to load
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(x_searchBox)));
			searchBox.sendKeys(productName);
			searchBox.submit();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(x_searchResults)));

			// Filter the price range
			WebElement minvalue = driver.findElement(By.id("low-price"));
			minvalue.sendKeys(Keys.DOWN);
			minvalue.sendKeys("55000");
			WebElement maxvalue = driver.findElement(By.id("high-price"));
			maxvalue.sendKeys("70000");
			driver.findElement(By.className("a-button-input")).click();
			
			// Select the exact product (exclude !sameName)
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(x_checkList)));
			List<WebElement> checkNames = driver.findElements(By.xpath(x_checkList));

			for (WebElement checkName : checkNames) {
				String linkText = checkName.getText();

				if (!linkText.contains(productName)) {
					continue;

				} else {
					checkName.click();
				break;// exit the loop once the desired element is clicked
				}
			}
			
			// Switch to the new window (product page)
			Set<String> windows = driver.getWindowHandles();

			Iterator<String> it = windows.iterator();

			it.next();// switch to the parent window

			String childId = it.next();// switch to the child window

			driver.switchTo().window(childId);

			// Wait for the product to be displayed
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(x_productDisplayed)));
			
			// Take a screenshot
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./AmazonProducts/Product1.png"));
			
			// Wait for the page to be fully loaded before quitting
			Thread.sleep(5000);

			// Delete the cookies and quit
			driver.manage().deleteAllCookies();
			driver.quit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
