package Base;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class BasePage {

	protected AppiumDriver<WebElement> driver;
	float percentage = 0;
	
	String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
			+ "/screenshots";

	public BasePage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}

	public void waitForLoad(WebElement element) {
		new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(element));
	}

	public Boolean VerifyDataEquals(String firstValue, String secondValue) {
		if (firstValue.equals(secondValue)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	

	public void pressBackButton() {
		driver.navigate().back();
	}
	
	public void startFakeCall(String serialPhone, String phoneNumber) throws IOException, InterruptedException {
		Thread.sleep(5000);
		String cmd = "adb -s " + serialPhone + " shell am start -a android.intent.action.CALL -d tel:" + phoneNumber;
		
		sendAdbCommand(cmd);
	
	}
	
	public void endFakeCall() throws IOException, InterruptedException {
		Thread.sleep(5000);
		String cmd = "adb shell input keyevent 6" ;
		sendAdbCommand(cmd);
		
	}
	
	private void sendAdbCommand(String command) throws IOException, InterruptedException {
		String line = "null";
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(command);

		pr.waitFor();

		BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		while ((line = buf.readLine()) != null) {
			System.out.println(line);
		}
	}
	
	public void takeScreenShot(String methodName) throws InterruptedException  {
		Thread.sleep(5000);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			
			File destFile = new File((String) reportDirectory + "/" + methodName + ".png");
			FileUtils.copyFile(scrFile, destFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public float compareImage(String original, String screenshot) {
		File fileA = new File((String) reportDirectory + "/" + original + ".png");
		File fileB = new File((String) reportDirectory + "/" + screenshot + ".png");
		
	   
	    try {
	        // take buffer data from both image files //
	        BufferedImage biA = ImageIO.read(fileA);
	        DataBuffer dbA = biA.getData().getDataBuffer();
	        int sizeA = dbA.getSize();
	        BufferedImage biB = ImageIO.read(fileB);
	        DataBuffer dbB = biB.getData().getDataBuffer();
	        int sizeB = dbB.getSize();
	        int count = 0;
	        // compare data-buffer objects //
	        if (sizeA == sizeB) {

	            for (int i = 0; i < sizeA; i++) {

	                if (dbA.getElem(i) == dbB.getElem(i)) {
	                    count = count + 1;
	                }

	            }
	            percentage = (count * 100) / sizeA;
	        } else {
	            System.out.println("Both the images are not of same size");
	        }

	    } catch (Exception e) {
	        System.out.println("Failed to compare image files ...");
	    }
	    return percentage;
	}
}
