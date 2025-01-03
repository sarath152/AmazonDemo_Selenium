package Amazon.AmazonDemoProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonPrimeSubscription {
	
	// constants
	public static final String url = "https://www.amazon.in/";
	public static final String department = "Book";
	private static final String username = "8946070894";
	private static final String password = "sarath+1234";
	
	// xpaths
	public static String X_prime = "//a[.=\"Prime\"]";
	public static String X_joinPrime = "//span[contains(.,'Login to join Prime')]";
	public static String X_primeDetails = "//div[@id='see-more-only-text']";
	public static String X_monthlyPlan = "//div[@id='plan-card-bg-2']";
	public static String X_signout = "//span[normalize-space()='Sign Out']";
	public static String X_accountAndList = "//span[contains(.,'Account & Lists')]";

	public static void main(String[] args) {
		try {

			WebDriver driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Hit URL
			driver.get(url);
			driver.manage().window().maximize();
			
			// Navigate to Prime and Join Prime
			WebElement prime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_prime)));
			prime.click();
			driver.findElement(By.xpath(X_joinPrime)).click();

			// User login &submit
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ap_email")));
			driver.findElement(By.id("ap_email")).sendKeys(username);
			driver.findElement(By.xpath("//input[@id='continue']")).click();
			driver.findElement(By.id("ap_password")).sendKeys(password);
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			driver.findElement(By.id("signInSubmit")).submit();
		
			 // Select more plans and choose Monthly Plan
			WebElement primeDetails = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_primeDetails)));
			primeDetails.click();
			WebElement selectMonthlyPlan = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_monthlyPlan)));
			selectMonthlyPlan.click();

			// Click Signout from header
			WebElement accountAndList = driver.findElement(By.xpath(X_accountAndList));
			Actions actions = new Actions(driver);
			actions.moveToElement(accountAndList).perform();
			WebElement signout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_signout)));
			signout.click();
			
            // Sign out completed, go to Landing page
			driver.get(url);

            // Delete cookies and quit the browser
			driver.manage().deleteAllCookies();
			driver.quit();

			;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
