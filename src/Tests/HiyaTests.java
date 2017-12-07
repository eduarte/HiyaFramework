package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;

public class HiyaTests extends BaseTest {

	String actual = "";
	float percentage = 0;
	String serialPhone = "emulator-5554";
	
	
	@DataProvider(name = "phoneNumbers")
    public Object[][] phoneNumber() {
        return new Object[][]{{"206-555-0181"}, {"206-555-0180"}, {"206-555-0186"}};
    }

	@Test(priority = 1)
	@Parameters({ "marketingMessageTxt" })
	public void MarketingMessage(String marketingMessageTxt) {
		actual = getStartedPage.getMarketingText();

		Assert.assertTrue(actual.contains(marketingMessageTxt));
	}

	@Test(priority = 2)
	@Parameters({ "permissionMessageTxt" })
	public void PermissionMessage(String permissionMessageTxt) {
		actual = getStartedPage.ClickGetStarted().getPrePermissionText();

		Assert.assertTrue(actual.contains(permissionMessageTxt));
	}

	@Test(priority = 3)
	@Parameters({ "allowContacAccessTxt" })
	public void AllowContacAccess(String allowContacAccessTxt) {
		actual = getStartedPage.ClickGetStarted().ClickOkBtn().getPermissionText();

		Assert.assertTrue(actual.contains(allowContacAccessTxt));
	}

	@Test(priority = 4)
	@Parameters({ "allowMakeCallsTxt" })
	public void AllowMakeCalls(String allowMakeCallsTxt) {
		actual = getStartedPage.ClickGetStarted().ClickOkBtn().ClickAllowBtn().getPermissionText();

		Assert.assertTrue(actual.contains(allowMakeCallsTxt));
	}

	@Test(priority = 5)
	@Parameters({ "signInTxt" })
	public void SignInText(String signInTxt) {
		getStartedPage.ClickGetStarted().ClickOkBtn().ClickAllowBtn().ClickAllowBtn();
		actual = signInPage.getSignInText();

		Assert.assertTrue(actual.contains(signInTxt));
	}

	@Test(priority = 6)
	@Parameters({ "enableDrawOverMessageTxt" })
	public void EnableDrawOverMessage(String enableDrawOverMessageTxt) {
		getStartedPage.ClickGetStarted().ClickOkBtn().ClickAllowBtn().ClickAllowBtn();
		signInPage.clickskipForNow();
		actual = homePage.getAlertMessage();

		Assert.assertTrue(actual.contains(enableDrawOverMessageTxt));
	}

	@Test(priority = 7)
	public void EnableDrawOverMessageAndAccept() {
		getStartedPage.ClickGetStarted().ClickOkBtn().ClickAllowBtn().ClickAllowBtn();
		signInPage.clickskipForNow();
		homePage.clickEnableAlert().clickPermitDrawingBTN().pressBackButton();

		Assert.assertTrue(homePage.isMenuItemDisplayed());
	}

	@Test(priority = 8)
	public void MenuIsDisplayed() {
		getStartedPage.ClickGetStarted().ClickOkBtn().ClickAllowBtn().ClickAllowBtn();
		signInPage.clickskipForNow();
		homePage.clickMenuBtn();

		Assert.assertTrue(homePage.isNavBarVisible());
	}

	@Test(priority = 9)
	@Parameters({ "UserNotSignedTxt" })
	public void UserNotSigned(String userNotSignedTxt) {
		getStartedPage.ClickGetStarted().ClickOkBtn().ClickAllowBtn().ClickAllowBtn();
		signInPage.clickskipForNow();
		actual = homePage.clickMenuBtn().getLoginTitleText();

		Assert.assertTrue(actual.contains(userNotSignedTxt));
	}

	@Test(priority = 10)
	@Parameters({ "signInTxt" })
	public void RedirectToSignInPage(String signInTxt) {
		getStartedPage.ClickGetStarted().ClickOkBtn().ClickAllowBtn().ClickAllowBtn();
		signInPage.clickskipForNow();
		homePage.clickMenuBtn().clickLoginBtn();
		actual = signInPage.getSignInText();

		Assert.assertTrue(actual.contains(signInTxt));
	}

	@Test(priority = 11)
	public void CallBlockingView() {
		getStartedPage.ClickGetStarted().ClickOkBtn().ClickAllowBtn().ClickAllowBtn();
		signInPage.clickskipForNow();
		homePage.clickMenuBtn().clickCallBlocking();

		Assert.assertTrue(callBlockingPage.isblockListTextVisible());
	}

	@Test(dataProvider = "phoneNumbers")
	public void FraudCallTest(String phoneNumber) throws IOException, InterruptedException {
		percentage = makeACallAndReturnSimilrity(serialPhone, phoneNumber, "FraudCall", "FraudCallScreenshot");

		Assert.assertTrue(percentage > 95);
	}

	@Test(dataProvider = "phoneNumbers")
	public void SpamCallTest(String phoneNumber) throws IOException, InterruptedException {
		percentage = makeACallAndReturnSimilrity(serialPhone, phoneNumber, "SpamCall", "SpamCallScreenshot");

		Assert.assertTrue(percentage > 95);
	}

	@Test(dataProvider = "phoneNumbers")
	public void ValidCallTest(String phoneNumber) throws IOException, InterruptedException {
		percentage = makeACallAndReturnSimilrity(serialPhone, phoneNumber, "ValidCall", "ValidCallScreenshot");

		Assert.assertTrue(percentage > 95);
	}

	public float makeACallAndReturnSimilrity(String serialPhone, String phoneNumber, String original, String screenshot)
			throws IOException, InterruptedException {
		getStartedPage.ClickGetStarted().ClickOkBtn().ClickAllowBtn().ClickAllowBtn();
		signInPage.clickskipForNow();
		homePage.clickMenuBtn().clickCallBlocking();
		callBlockingPage.startFakeCall(serialPhone, phoneNumber);
		callBlockingPage.takeScreenShot(screenshot);
		callBlockingPage.endFakeCall();
		return callBlockingPage.compareImage(original, screenshot);
	}

}
