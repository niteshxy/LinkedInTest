package Utilities;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GetDrivers {

	WebDriver driver;
	public static final DesiredCapabilities capabilities = new DesiredCapabilities();

	public WebDriver getDriverForApplication(String browser) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("iexplore")) {
			System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			// capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			// true);
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			// capabilities = WebCaps.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
			driver = new ChromeDriver(capabilities);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			;
		}
		return driver;

	}

}
