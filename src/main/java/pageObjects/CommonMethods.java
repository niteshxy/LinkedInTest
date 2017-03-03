package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;

public class CommonMethods {

	WebDriver driver;

	public CommonMethods(WebDriver driver) {
		this.driver = driver;
	}

	public void OpenURL() {
		driver.get(constants.PageConstants.baseurl);
	}

	public static String randomtext() {
		String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); // 9
		int n = alphabet.length(); // 10

		String result = new String();
		Random r = new Random(); // 11

		for (int i = 0; i < 20; i++) // 12
			result = result + alphabet.charAt(r.nextInt(n)); // 13

		return result;

	}

	public static String getDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("PST"));
		return df.format(new Date());
	}
}
