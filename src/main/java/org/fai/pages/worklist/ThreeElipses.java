package org.fai.pages.worklist;

import org.fai.pages.base.BasePage;
import org.fai.pages.download.Download;
import org.fai.reports.ExtentLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThreeElipses extends BasePage{

	final WebDriver driver;
	public ThreeElipses(WebDriver driver) {
		this.driver=driver;
	}

	@FindBy(xpath = "//button[@class='btn btn-secondary']")
	private WebElement threeElipses_btn;

	@FindBy(xpath = "//button[text()='Download']")
	private WebElement download_Btn;

	@FindBy(xpath = "//button[text()='Archive']")
	private WebElement archive_Btn;

	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement delete_Btn;

	@FindBy(xpath = "//button[text()='Rename']")
	private WebElement rename_Btn;

	@FindBy(xpath = "//button[text()='Audit Trail']")
	private WebElement auditTrail_Btn;

	public ThreeElipses clickOnThreeElipses() {
		waitForPageload();
		clickUsingWebElement(threeElipses_btn);
		return this;
	}
	public Download gotoDownloadDoc(){
		clickUsingWebElement(download_Btn);
		return new Download();

	}
	public ThreeElipses gotoArchiveDoc() {

		clickUsingWebElement(archive_Btn);
		return this;
	}
	public ThreeElipses gotoDeleteDoc() {
		
		clickUsingWebElement(delete_Btn);
		return this;
	}
	public ThreeElipses gotAuditTrail() {
		waitForPageload();
		clickUsingWebElement(auditTrail_Btn);
		new AuditTrail().waitforAuditWindow();
		ExtentLogger.pass("End of Audit Trail",true);
		return this;
	}
	public ThreeElipses gotoRenameDoc() {

		
		clickUsingWebElement(rename_Btn);
		return this;
	}
}
