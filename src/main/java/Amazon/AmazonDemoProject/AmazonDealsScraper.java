package Amazon.AmazonDemoProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonDealsScraper {
	// constants
	public static final String url = "https://www.amazon.in/";

	// Xpaths:
	public static String X_todaysDeals = "//a[.=\"Today's Deals\"]";
	public static String X_dealOfTheDay = "//span[contains(text(),'Deal of the Day')]";
	public static String X_itemsInDeals = "//div[@data-testid=\"deal-card\"]";
	public static String X_selectingProduct = "(//div[@class='a-section octopus-dlp-image-shield'])[1]";
	public static String X_productPrice = "//div[@id='corePriceDisplay_desktop_feature_div']";
	public static String x_addToCart = "//div[@class='a-section a-spacing-none a-padding-none']//span[@id='submit.add-to-cart']";

	// IDs:
	public static String ID_productTitle = "productTitle";

	public static void main(String[] args) {
		try {
			// launching in chrome
			WebDriver driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.manage().window().maximize();

			// HitURL
			driver.get(url);

			// Click on Today's Deal link & wait used for entering captcha manually
			WebElement todayDealLink = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_todaysDeals)));
			todayDealLink.click();

			// Select Deals of the Day
			WebElement dealOfTheDay = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_dealOfTheDay)));
			dealOfTheDay.click();

			// Select a product from Today's Deal
			WebElement firstDepartment = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_itemsInDeals)));
			firstDepartment.click();

			// Select the first product
			WebElement firstProduct = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_selectingProduct)));
			firstProduct.click();
			// Get the product title & price in console
			WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ID_productTitle)));
			WebElement productPrice = driver.findElement(By.xpath(X_productPrice));
			System.out.println(productTitle.getText());
			System.out.println(productPrice.getText());

			// Add the product to the cart
			driver.findElement(By.xpath(x_addToCart)).click();

			// Wait for the page to be fully loaded before quit
			Thread.sleep(5000);

			// Delete the cookies and quit
			driver.manage().deleteAllCookies();
			driver.quit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
