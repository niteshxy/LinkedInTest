package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickLanguageOption() {

		driver.findElement(By.cssSelector(".lang-selector-state-label")).click();
	}

	public int findNumberOfLanguages() {

		List<WebElement> allLanguages = driver.findElements(By.cssSelector(".lang-selector>li"));
		int i = allLanguages.size();
		return i;
	}

	public boolean verifyLanguagePresent(String language) {
		boolean languagepresent = false;
		List<WebElement> allLanguages = driver.findElements(By.cssSelector(".lang-selector>li"));
		for (WebElement e : allLanguages) {
			if (e.getText().equalsIgnoreCase(language))
				languagepresent = true;

		}
		return languagepresent;

	}

	public void clickAdvancedSearch() {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".nav-search-button")));
			// driver.findElement(By.cssSelector(".nav-search-button")).click();
			element.click();
		} catch (NoSuchElementException e) {
			try {
				Thread.sleep(3000);

			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			driver.findElement(By.cssSelector(".nav-search-button"));
		}

		catch (Exception e1) {

			e1.printStackTrace();
		}

	}

	public void ClickFilterPeopleMenuByText(String FilterText) {
		List<WebElement> Filters = driver.findElements(By.cssSelector(".search-facet__legend"));
		for (WebElement e : Filters) {
			System.out.println(e.getText());
			if (e.getText().contains(FilterText)) {
				e.click();
				break;
			}
		}

	}

	public boolean isElementPresennt(String locator) throws InterruptedException {
		// Used thread sleep as sometimes it takes time to load the image
		boolean ispresent = false;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
		// WebElement e = driver.findElement(By.cssSelector(locator));
		ispresent = e.isDisplayed();

		if (!ispresent) {
			Thread.sleep(4000);
			ispresent = e.isDisplayed();
		}
		return ispresent;
	}

	public void typeFirstName(String FirstName) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.id("advanced-search-first-name")));
		e.sendKeys(FirstName);
	}

	public void clickSearch() {
		driver.findElement(By.cssSelector(".submit-button")).click();
	}

	public boolean VerifyNoResultsFound() {
		boolean value = false;
		String Text = driver.findElement(By.xpath(" //h1[contains(@class,'search-no-results')]")).getText();
		if (Text.contains("No results")) {
			value = true;

		}
		return value;
	}

	public void Logout() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement e = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='nav-settings__dropdown-trigger']")));
		e.click();

		WebElement e2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='nav-dropdown__action']/a")));

		e2.click();
	}
}
