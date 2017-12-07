package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Base.BasePage;
import io.appium.java_client.AppiumDriver;

public class GetStartedPage extends BasePage {

	// Elements

	@FindBy(how = How.ID, using = "marketing_imageView")
	private WebElement marketingImage;

	@FindBy(how = How.ID, using = "marketing_text_view")
	private WebElement marketingText;

	@FindBy(how = How.ID, using = "btn_get_started")
	private WebElement getStartedBtn;

	@FindBy(how = How.ID, using = "pre_permission_desc_tv")
	private WebElement pre_permissionText;

	@FindBy(how = How.ID, using = "ok_button")
	private WebElement okBtn;

	@FindBy(how = How.ID, using = "why_button")
	private WebElement whyBtn;

	@FindBy(how = How.CLASS_NAME, using = "android.widget.TextView")
	private WebElement permissionText;

	private List<WebElement> btns;

	@FindBy(how = How.ID, using = "current_page_text")
	private WebElement currentPageText;

	public GetStartedPage(AppiumDriver<WebElement> driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public GetStartedPage ClickGetStarted() {
		getStartedBtn.click();
		return new GetStartedPage(driver);
	}

	public GetStartedPage ClickOkBtn() {
		okBtn.click();
		return new GetStartedPage(driver);
	}

	public GetStartedPage ClickWhyBtn() {
		okBtn.click();
		return new GetStartedPage(driver);
	}

	public String getMarketingText() {
		return marketingText.getText().trim();
	}

	public String getPrePermissionText() {
		return pre_permissionText.getText().trim();
	}

	public String getPermissionText() {
		return permissionText.getText().trim();
	}

	public GetStartedPage ClickAllowBtn() {
		findElementsByTextAndClass("Allow").click();
		return new GetStartedPage(driver);
	}

	public GetStartedPage ClickDenyBtn() {
		findElementsByTextAndClass("Deny").click();
		return new GetStartedPage(driver);
	}

	public String getCurrentPageText() {
		return currentPageText.getText();
	}

	private WebElement findElementsByTextAndClass(String text) {
		btns = driver.findElements(By.className("android.widget.Button"));
		for (WebElement btn : btns) {
			if (btn.getText().equalsIgnoreCase(text))
				return btn;
		}
		return null;
	}

}
