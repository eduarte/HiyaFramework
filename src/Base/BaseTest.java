package Base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Pages.CallBlockingPage;
import Pages.GetStartedPage;
import Pages.HomePage;
import Pages.SignInPage;
import io.appium.java_client.AppiumDriver;

public class BaseTest {
	protected static AppiumDriver<WebElement> driver;
	protected GetStartedPage getStartedPage;
	protected SignInPage signInPage;
	protected HomePage homePage;
	protected CallBlockingPage callBlockingPage;
	private int implicitTime = 15;

	@BeforeMethod
	@Parameters({ "deviceName", "platformVersion", "platformName", "appName", "AppiumServerURL" })
	public void setup(String deviceName, String platformVersion, String platformName,
			String appName, String AppiumServerURL) throws MalformedURLException {
		setupDriver(deviceName, platformVersion, platformName, appName, AppiumServerURL);
		getStartedPage = new GetStartedPage(driver);
		signInPage = new SignInPage(driver);
		homePage = new HomePage(driver);
		callBlockingPage = new CallBlockingPage(driver);

	}

	public AppiumDriver<WebElement> setupDriver(String deviceName, String platformVersion, String platformName,
			String appName, String AppiumServerURL) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("app", System.getProperty("user.dir") + appName);
		driver = new AppiumDriver<WebElement>(new URL(AppiumServerURL), capabilities);
		driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);

		return driver;
	}

	@AfterMethod
	public void cleanUp() {
		driver.closeApp();
	}

}
