package testCases;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelUtils2;
import Utilities.GetDrivers;
import constants.LocatorConstants;
import pageObjects.CommonMethods;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TestCases {
	WebDriver driver;
	LoginPage objloginpage;
	GetDrivers objgetdriver;
	CommonMethods objcommonmethod;
	HomePage objhomepage;
	ExtentReports report;
	ExtentTest logger;

	@Parameters(value = { "browser" })
	@BeforeTest
	public void setup(String browser) throws Exception {
		Calendar.getInstance();
		report = new ExtentReports("src/test/resources/Reports/TestReport_" + browser + ".html");
		objgetdriver = new GetDrivers();
		driver = objgetdriver.getDriverForApplication(browser);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		objloginpage = new LoginPage(driver);
		objcommonmethod = new CommonMethods(driver);
		objhomepage = new HomePage(driver);

	}

	@Test(priority = 0)
	public void openURL() {

		logger = report.startTest("Verify URL launch");
		objcommonmethod.OpenURL();
		logger.log(LogStatus.INFO, "Linkedin URL is launched");
		String title = driver.getTitle();
		boolean verifyTile = title.equalsIgnoreCase("LinkedIn: Log In or Sign Up");
		Assert.assertTrue(verifyTile);
	}

	@Test(priority = 1)
	public void checklanguage() {

		logger = report.startTest("Check Language Menu on Login Page");
		objhomepage.clickLanguageOption();
		int NoOfLanguages = objhomepage.findNumberOfLanguages();
		logger.log(LogStatus.PASS, "Total Number of Languages are " + NoOfLanguages);
		Assert.assertTrue(objhomepage.verifyLanguagePresent("english"));
		logger.log(LogStatus.PASS, "English is Present in Language Menu");
	}

	@Test(dataProvider = "Authentication_invalid", priority = 2)
	public void testloginInvalid(String Username, String Password) throws InterruptedException {
		objcommonmethod.OpenURL();
		logger = report.startTest("Verify Invalid Login");
		objloginpage.login(Username, Password);
		Assert.assertTrue(objloginpage.verifyErrorOnLogin());
		logger.log(LogStatus.PASS, "Successfully Verified that Invalid User is not allowed to login");

	}

	@Test(dataProvider = "Authentication_valid", priority = 3)
	public void testloginValid(String Username, String Password) throws InterruptedException {
		boolean loginSuccess = false;
		objcommonmethod.OpenURL();
		logger = report.startTest("Verify Successfull Login With valid Credentials");
		objloginpage.login(Username, Password);
		loginSuccess = objhomepage.isElementPresennt(LocatorConstants.profileimgCssSelector);
		Assert.assertTrue(loginSuccess);
		logger.log(LogStatus.PASS, "Successfully Verified that valid user is allowed to login");
	}

	@Test(dataProvider = "Authentication_valid", priority = 4)
	public void advancedSearch(String Username, String Password) {
		logger = report.startTest("Verify Advanced Search Options");
		objhomepage.clickAdvancedSearch();
		objhomepage.ClickFilterPeopleMenuByText("Keywords");
		objhomepage.typeFirstName(CommonMethods.randomtext());
		objhomepage.clickSearch();
		Assert.assertTrue(objhomepage.VerifyNoResultsFound());
		logger.log(LogStatus.PASS,
				"Verified that NO results are displayed when searched with a random string in First Name field");
	}

	@Test(priority = 5)
	public void logOut() throws InterruptedException {
		logger = report.startTest("Verify Logout Action");
		objhomepage.Logout();
		Thread.sleep(3000);// due to issue on firefox
		String title = driver.getTitle();
		boolean verifyTitle = title.equalsIgnoreCase("LinkedIn: Log In or Sign Up");
		Assert.assertTrue(verifyTitle);
		logger.log(LogStatus.PASS, "Successfully Logged Out");

	}

	@DataProvider(name = "Authentication_invalid")

	public Object[][] Authentication_invalid() throws Exception {

		Object[][] testObjArray = ExcelUtils2.getTableArray("src/test/resources/TestData/TestData.xlsx", "Sheet1");

		return (testObjArray);

	}

	@DataProvider(name = "Authentication_valid")

	public Object[][] Authentication_valid() throws Exception {

		Object[][] testObjArray = ExcelUtils2.getTableArray2("src/test/resources/TestData/TestData.xlsx", "Sheet1");

		return (testObjArray);

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Skipped");
		}

	}

	@AfterTest
	public void endTest() {
		driver.quit();
		report.endTest(logger);
		report.flush();
	}

}
