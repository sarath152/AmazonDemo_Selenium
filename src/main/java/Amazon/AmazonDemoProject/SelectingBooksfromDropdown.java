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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectingBooksfromDropdown {
	public static final String url = "https://www.amazon.in/";
	// Enter exact dropdown text to choose other departments
	public static final String department = "Books";
	public static final String name = "The 5 AM Club";

	// xpaths
	public static String x_searchDropdownBox = "//div[@class='nav-search-scope nav-sprite']";
	public static String x_suggestionDepartment = "//option[contains(.,'%s')]";
	public static String x_suggestion = "//div[contains(@class,'suggestion')]/div[@aria-label='%s']";
	public static String x_searchBox = "//input[@id='twotabsearchtextbox']";
	public static String x_searchResults = "//span[text()='Customer Review']";
	public static String x_checkList = "//span[@class='a-size-medium a-color-base a-text-normal']";
	public static String x_addToCart = "//div[@class='a-section a-spacing-none a-padding-none']//span[@id='submit.add-to-cart']";

	public static void main(String[] args) {
		try {

			WebDriver driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			// HitURL
			driver.get(url);
			driver.manage().window().maximize();

			// Select DropdownInsearch
			WebElement searchDropdownBox = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(x_searchDropdownBox)));
			Actions actions = new Actions(driver);
			actions.click(searchDropdownBox).perform();
			// Select department
			WebElement suggestions = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(String.format(x_suggestionDepartment, department))));
			suggestions.click();

			// SearchBox
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(x_searchBox)));
			searchBox.sendKeys(name);
			searchBox.submit();

			// Select the exact product (exclude !sameName)
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(x_checkList)));

			List<WebElement> checkLists = driver.findElements(By.xpath(x_checkList));

			for (WebElement checkName : checkLists) {
				String linkText = checkName.getText();

				if (!linkText.contains(name)) {
					continue;

				} else
					checkName.click();
				break;// exit the loop once the selected element is clicked

			}
			// Switch to the new window (product page)
			Set<String> windows = driver.getWindowHandles();

			Iterator<String> it = windows.iterator();

			it.next();

			String childId = it.next();

			driver.switchTo().window(childId);

			// Get Total Quantities
			WebElement quantities = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quantity")));
			Select quantity = new Select(quantities);
			List<WebElement> options = quantity.getOptions();
			int optionCount = options.size();

			// Select the last available Quantity
			quantity.selectByIndex(optionCount - 1);

			// AddToCart
			WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(x_addToCart)));
			addToCart.click();

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
