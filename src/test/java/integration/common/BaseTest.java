package integration.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	public WebDriver driver;
	private Properties props = new Properties();
	private static final String filePath = "C:\\Users\\rmend\\git\\framework\\variables.properties";

	@BeforeSuite
	public void initDriver() {
		try {
			FileInputStream file = new FileInputStream(filePath);
			props.load(file);

			if (props.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
				driver = new ChromeDriver();
			} else {
				System.getProperty("gecko.chrome.driver", "C://geckodriver.exe");
				driver = new FirefoxDriver();
			}
			driver.manage().window().maximize();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@AfterSuite
	public void cleanUp() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
