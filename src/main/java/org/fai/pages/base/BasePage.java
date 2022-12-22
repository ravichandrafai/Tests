package org.fai.pages.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.fai.driver.DriverManager;
import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.reports.ExtentLogger;
import org.fai.reports.FrameworkLogger;
import org.fai.utils.JSExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class BasePage {


	private By workListPageLoading = By.xpath("//div[@class='failoader_laod_roller fai_sample_fadeout']//div");
	private By saveDocLoading = By.xpath("//div[@class='failoader_laod_wrapper']/h4");
	private By afterPageloader = By.xpath("//div[contains(@class,'grUEVs')]");
	private By pageloader = By.xpath("//div[contains(@class,'jrAGJF')]");
	
	protected void waitforReload() {
			List<WebElement> loader = findElements(pageloader);
			if(loader.size()>0) {
				ExplicitWaitConditions.invisbileOfElement(pageloader, null);
			}
			ExplicitWaitConditions.performExplicitWait(afterPageloader, ExplicitWaitExpextecConditions.PRESENSCE);
					
	}
	protected void waitForPageload() {
		ExplicitWaitConditions.waitforInvisiblity(workListPageLoading);
		//ExtentLogger.pass("Page load successful",true);
	}
	protected void waitForSaveChanges() {
		ExplicitWaitConditions.performExplicitWait(saveDocLoading, ExplicitWaitExpextecConditions.VISIBILE);
		ExplicitWaitConditions.waitforInvisiblity(saveDocLoading);
		//ExtentLogger.pass("Page load successful",true);
	}
	
	protected void clickUsinBy(By by, ExplicitWaitExpextecConditions expectedCondition)  {
		WebElement element= ExplicitWaitConditions.performExplicitWait(by, expectedCondition);
		element.click();
		ExtentLogger.pass("Clicked on "+by+" Succesfully",true);
		FrameworkLogger.logInfo("Clicked on "+by+" Succesfully");
	}
	protected void clickUsingWebElement(WebElement element) {
		ExplicitWaitConditions.waitWithWebElemt(element);
		element.click();
		//ExtentLogger.pass("Clicked on "+element+" Succesfully",true);
	}

	protected void clickUsingJS(By by, ExplicitWaitExpextecConditions expectedCondition, boolean isJSClick)  {
		WebElement element= ExplicitWaitConditions.performExplicitWait(by, expectedCondition);

		if(isJSClick) {
			JSExecutor.click(element);
			ExtentLogger.pass("JS Clicked on "+by+"Succesfully",true);
		}else {
			element.click();
			ExtentLogger.pass("Clicked on "+by+"Succesfully",true);
		}

		FrameworkLogger.logInfo("Clicked on "+by+"Succesfully");


	}

	protected void enterText(By by, String strText , ExplicitWaitExpextecConditions expectedCondition) {

		WebElement element= ExplicitWaitConditions.performExplicitWait(by, expectedCondition);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		element.clear();
		element.sendKeys(strText);
		//ExtentLogger.pass("Entered text "+strText+" in the Locator "+by,true);
		FrameworkLogger.logInfo("Entered text "+strText+" in the Locator "+by);

	}

	protected String getPageTitle() {

		return DriverManager.getDriver().getTitle();

	}
	protected String getText(By by, ExplicitWaitExpextecConditions expectedCondition) {
		FrameworkLogger.logInfo("Start of getText method for the locaor "+ by);
		ExplicitWaitConditions.performExplicitWait(by, ExplicitWaitExpextecConditions.PRESENSCE);
		String elementText = DriverManager.getDriver().findElement(by).getText();
		FrameworkLogger.logInfo("Value of the text is "+elementText);
		//ExtentLogger.pass("Get text from "+by,true);
		return elementText;
	}
	protected String getTextJS(By by, ExplicitWaitExpextecConditions expectedCondition) {
		WebElement element= ExplicitWaitConditions.performExplicitWait(by, expectedCondition);
		String text= JSExecutor.getTextJS(element);
		//ExtentLogger.pass("Get text using Java script executor"+by,true);
		return text;
	}
	protected String getAttribute(By by, ExplicitWaitExpextecConditions expectedCondition, String attribute) {
		FrameworkLogger.logInfo("Start of getText method for the locaor "+ by);
		ExplicitWaitConditions.performExplicitWait(by, expectedCondition);
		String attributeValue = DriverManager.getDriver().findElement(by).getAttribute(attribute);
		FrameworkLogger.logInfo("Value of the text is "+attributeValue);
		//ExtentLogger.pass("Get Attribute value of"+by,true);
		return attributeValue;

	}
	protected String getAttributeJS(By by, ExplicitWaitExpextecConditions expectedCondition, String attribute) {
		FrameworkLogger.logInfo("Start of getText method for the locaor "+ by);
		WebElement element= ExplicitWaitConditions.performExplicitWait(by, expectedCondition);
		String attributeValue = JSExecutor.getAttribiteJS(element, attribute);
		FrameworkLogger.logInfo("Value of the text is "+attributeValue);
		//ExtentLogger.pass("Get attribute value using Java script executor of"+by,true);
		return attributeValue;

	}
	protected List<WebElement> findElements(By by){
		List<WebElement> list = DriverManager.getDriver().findElements(by);
		return list;
	}

	protected String getURL() {

		return DriverManager.getDriver().getCurrentUrl();
	}
	protected void enterFileName(String uploadFileName) throws AWTException {
		Robot rb= new Robot();
		StringSelection uploadFilePath =new StringSelection(uploadFileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadFilePath, null);

		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.delay(1000);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.delay(1000);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.delay(1000);
		rb.keyRelease(KeyEvent.VK_ENTER);
		ExtentLogger.pass("Select file to upload",true);

	}
	protected boolean compareDimensions(Dimension beforeSize, Dimension afterSize) {

		int beforeHeight = beforeSize.getHeight();
		int beforeWidth = beforeSize.getWidth();
		int afterHeight = afterSize.getHeight();
		int afterWidth = afterSize.getWidth();

		if((afterHeight>beforeHeight)&&(afterWidth>beforeWidth))
			return true;
		else
			return false;

	}

}
