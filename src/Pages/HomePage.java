package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Base.BasePage;
import io.appium.java_client.AppiumDriver;

public class HomePage extends BasePage {

	@FindBy(how = How.ID, using = "alertTitle")
	private WebElement alertTitleText;

	@FindBy(how = How.ID, using = "message")
	private WebElement alertMessageText;

	@FindBy(how = How.ID, using = "button2")
	private WebElement cancelBtn;

	@FindBy(how = How.ID, using = "button1")
	private WebElement enableBtn;

	@FindBy(how = How.ID, using = "switchWidget")
	private WebElement permitDrawingBtn;

	@FindBy(how = How.ID, using = "nav_recycler_view")
	private WebElement navBarView;

	@FindBy(how = How.ID, using = "login_title_tv")
	private WebElement login_title;

	@FindBy(how = How.ID, using = "login_button")
	private WebElement loginBtn;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Call Blocking']")
	private WebElement callBlockingBtn;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Call Settings']")
	private WebElement callSettingsBtn;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='More']")
	private WebElement moreBtn;
	
	//

	

	public HomePage(AppiumDriver<WebElement> driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	private WebElement menuBtn() {
		return driver.findElementByAccessibilityId("Drawer open");
	}

	public HomePage clickMenuBtn() {
		menuBtn().click();
		return new HomePage(driver);
	}
	
	public Boolean isMenuItemDisplayed() {
		return menuBtn().isDisplayed();
	}

	public HomePage clickCancelAlert() {
		cancelBtn.click();
		return new HomePage(driver);
	}

	public HomePage clickEnableAlert() {
		enableBtn.click();
		return new HomePage(driver);
	}

	public HomePage clickPermitDrawingBTN() {
		permitDrawingBtn.click();
		return new HomePage(driver);
	}

	public String getAlertMessage() {
		return alertMessageText.getText().trim();
	}

	public String getAlertTitle() {
		return alertTitleText.getText().trim();
	}

	public Boolean isNavBarVisible() {
		return navBarView.isDisplayed();
	}

	public String getLoginTitleText() {
		return login_title.getText().trim();
	}

	public GetStartedPage clickLoginBtn() {
		loginBtn.click();
		return new GetStartedPage(driver);

	}
	
	public HomePage clickCallSettings() {
		callSettingsBtn.click();
		return new HomePage(driver);
	}
	
	public HomePage clickCallBlocking() {
		callBlockingBtn.click();
		return new HomePage(driver);
	}

}
