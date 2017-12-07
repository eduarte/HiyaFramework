package Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Base.BasePage;
import io.appium.java_client.AppiumDriver;

public class CallBlockingPage extends BasePage {
	
	@FindBy(how = How.ID, using = "block_list_textView")
	private WebElement blockListText;
	
	@FindBy(how = How.ID, using = "fab_expand_menu_button")
	private WebElement expandBtn;
	
	@FindBy(how = How.ID, using = "enter_number_button")
	private WebElement enterNumberBTN;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Enter a number']")
	private WebElement enterNumberText;
	
	@FindBy(how = How.ID, using = "fab_recent_button")
	private WebElement recentCallBTN;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Recent calls or texts']")
	private WebElement recentCallText;
	
	@FindBy(how = How.ID, using = "fab_contacts_button")
	private WebElement chooseContactBTN;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Choose from contacts']")
	private WebElement chooseContactText;
	
	@FindBy(how = How.ID, using = "fab_number_begins_button")
	private WebElement numbersBeginsWithBTN;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Numbers that begin with']")
	private WebElement numbersBeginsWithText;
	
	@FindBy(how = How.ID, using = "block_number_edit")
	private WebElement blockNumberEdit;
	
	@FindBy(how = How.ID, using = "button1")
	private WebElement blockBTN;
	
	@FindBy(how = How.ID, using = "button2")
	private WebElement cancelBTN;
	
	@FindBy(how = How.ID, using = "country_code")
	private WebElement countryCodeBTN;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='CALL ENDED']")
	private WebElement infoCall;
	
	//header_container
	
	public CallBlockingPage(AppiumDriver<WebElement> driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public CallBlockingPage clickExpandBTN() {
		expandBtn.click();
		return new CallBlockingPage(driver);
	}
	
	public CallBlockingPage clickEnterNumberBTN() {
		enterNumberBTN.click();
		return new CallBlockingPage(driver);
	}
	
	public CallBlockingPage setBlockedNumber(String phoneNumber) {
		blockNumberEdit.sendKeys(phoneNumber);
		return new CallBlockingPage(driver);
	}
	
	public CallBlockingPage blockNumber() {
		blockBTN.click();
		return new CallBlockingPage(driver);
	}
	
	public CallBlockingPage CancelBlockNumber() {
		cancelBTN.click();
		return new CallBlockingPage(driver);
	}
	
	public CallBlockingPage chooseContryCode() {
		countryCodeBTN.click();
		return new CallBlockingPage(driver);
	}
	
	public Boolean isblockListTextVisible() {
		return blockListText.isDisplayed();
	}
	
		 
	public Boolean isPhoneAdded(String phoneNumber) {
		
		List<WebElement> textViews = driver.findElementsByClassName("android.widget.TextView");
		
		for (WebElement textView : textViews) {
			if(textView.getText().contains(phoneNumber))
				return true;
		}
		
		return false;
		
	}

}
