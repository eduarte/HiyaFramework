package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Base.BasePage;
import io.appium.java_client.AppiumDriver;

public class SignInPage extends BasePage {

	@FindBy(how = How.ID, using = "signin_text_view")
	private WebElement signinInText;

	@FindBy(how = How.ID, using = "sign_in_image_view")
	private WebElement signInImage;

	@FindBy(how = How.ID, using = "signin_with_text_view")
	private WebElement signInWithText;

	@FindBy(how = How.ID, using = "facebook_login_btn")
	private WebElement facebookBtn;

	@FindBy(how = How.ID, using = "google_login_btn")
	private WebElement googleBtn;

	@FindBy(how = How.ID, using = "skip_for_now_btn")
	private WebElement skipBtn;

	public SignInPage(AppiumDriver<WebElement> driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickFacebook() {
		facebookBtn.click();
	}

	public void clickGoogle() {
		googleBtn.click();
	}

	public void clickskipForNow() {
		skipBtn.click();
	}

	public String getSignInText() {
		return signinInText.getText().trim();
	}

	public String getSignInWithText() {
		return signinInText.getText().trim();
	}

}
