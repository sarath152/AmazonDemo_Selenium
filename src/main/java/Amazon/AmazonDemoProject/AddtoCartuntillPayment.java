package Amazon.AmazonDemoProject;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddtoCartuntillPayment {

	// constants
	public static final String url = "https://www.amazon.in/";
	public static final String productName = "Oneplus";
	public static final String bankName = "HDFC Bank";
	private static final String username = "8946070894";
	private static final String password = "sarath+1234";

	// xpaths
	public static String x_searchBox = "//input[@id='twotabsearchtextbox']";
	public static String x_searchResults = "//span[text()='Customer Review']";
	public static String x_productLists = "//span[@class='a-size-medium a-color-base a-text-normal']";
	public static String x_sortby = "//span[contains(text(),'Sort by:')]";
	public static String x_sortPriceHightoLow = "//a[.='Price: High to Low']";
	public static String x_addToCart = "//div[@class='a-section a-spacing-none a-padding-none']//span[@id='submit.add-to-cart']";
	public static String x_proceedToCheckout = "//a[@id='nav-cart']";
	public static String x_proceedToCheckoutInPopup = "//input[@aria-labelledby='attach-sidesheet-checkout-button-announce']";
	public static String x_shipToThisAddressButton = "//span[@id='shipToThisAddressButton']";
	public static String x_proceedToBuy = "//input[@name='proceedToRetailCheckout']";
	public static String x_useThisAddress = "//input[@name='proceedToRetailCheckout']";
	public static String x_paymentMethod = "//select[@id='pp-7pfYuQ-106']']";
	public static String x_netBanking = "//a[.='%s']";
	public static String X_signout = "//span[normalize-space()='Sign Out']";
	public static String X_accountAndList = "//span[contains(.,'Account & Lists')]";
	public static String X_amazonLogo = "//div[@id='nav-logo']";
	public static String X_paymentAmazonLogo = "//i[@class='a-icon a-icon-logo clickable-heading']";
	public static String X_amazonLogoReturn = "//span[@class='a-button a-button-span12 a-button-primary a-float-right']";

	public static void main(String[] args) {
		try {
			WebDriver driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Hit URL
			driver.get(url);
			driver.manage().window().maximize();

			// Search product name & use wait option use for captcha to enter manually
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(x_searchBox)));
			searchBox.sendKeys(productName);
			searchBox.submit();

            // Sort the results by price (high to low)
			driver.findElement(By.xpath(x_sortby)).click();
			driver.findElement(By.xpath(x_sortPriceHightoLow)).click();
            // Wait for the product lists to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(x_productLists)));

			// Select the exact product (exclude !productName)
			List<WebElement> productLists = driver.findElements(By.xpath(x_productLists));
			for (WebElement selectProduct : productLists) {
				String linkText = selectProduct.getText();

				if (linkText.contains(productName)) {
					selectProduct.click();
					break; // exit the loop once the selected element is clicked

				} else
					continue;

			}
			// Switch to the new window (product page)
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> it = windows.iterator();
			it.next();
			String childId = it.next();
			driver.switchTo().window(childId);

			// Add product to cart
			WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(x_addToCart)));
			addToCart.click();
			Thread.sleep(2000);

            // Two different locators for proceed to checkout
			By proceedToCheckoutInPopup = By.xpath(x_proceedToCheckoutInPopup);
			By proceedToCheckout = By.xpath(x_proceedToCheckout);

			WebElement element = null;

			try {
				element = driver.findElement(proceedToCheckoutInPopup);
				element.click();
			} catch (org.openqa.selenium.NoSuchElementException e) {
				try {
					element = driver.findElement(proceedToCheckout);
					element.click();
					// proceedToBuy
					WebElement proceedToBuy = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(x_proceedToBuy)));
					proceedToBuy.click();

				} catch (org.openqa.selenium.NoSuchElementException e2) {
					System.out.println("Element not found");
				}
			}

			// Entering username & Password 

			wait.until(ExpectedConditions.elementToBeClickable(By.id("ap_email")));
			driver.findElement(By.id("ap_email")).sendKeys(username);
			driver.findElement(By.xpath("//input[@id='continue']")).click();
			driver.findElement(By.id("ap_password")).sendKeys(password);
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			driver.findElement(By.id("signInSubmit")).submit();
			
			// Just for reference
			System.out.println("SignIn..!");
			// Select default address
			WebElement shipToThisAddressButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(x_shipToThisAddressButton)));
			shipToThisAddressButton.click();
			// Select Paymentoptions

			WebElement selectBank = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//body/div[@id='a-page']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/span[1]/span[1]/span[1]/span[1]")));
			selectBank.click();

			// Choose NetBanking payment option
			WebElement bankDetails = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(x_netBanking, bankName))));
			bankDetails.click();

			// return to cart
			driver.findElement(By.xpath(X_paymentAmazonLogo)).click();
			WebElement amazonLogoReturn = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_amazonLogoReturn)));
			amazonLogoReturn.click();
			// Click Signout option from header
			WebElement accountAndList = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_accountAndList)));
			Actions actions = new Actions(driver);
			actions.moveToElement(accountAndList).perform();
			WebElement signout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(X_signout)));
			signout.click();
			System.out.println("Signout..!");
			// Hit back to Homepage
			driver.get(url);

			// wait page fully loaded
			Thread.sleep(5000);

			// Delete the cookies and quit
			driver.manage().deleteAllCookies();
			driver.quit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
