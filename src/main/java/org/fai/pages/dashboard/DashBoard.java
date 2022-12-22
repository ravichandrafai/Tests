package org.fai.pages.dashboard;

import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.pages.base.BasePage;
import org.fai.pages.worklist.WorkList;
import org.openqa.selenium.By;

public class DashBoard extends BasePage{

	private By totalFiles= By.xpath("//div[text()='Total Files']/parent::div/following-sibling::div/button");
	private By awaitingValidationFiles= By.xpath("//div[text()='Awaiting Validation']/parent::div/following-sibling::div/button");
	private By InProgressFiles= By.xpath("//div[text()='In Progress']/parent::div/following-sibling::div/button");
	private By validatedFiles= By.xpath("//div[text()='Validated']/parent::div/following-sibling::div/button");
	private By totalFilesCount= By.xpath("//div[text()='Total Files']/preceding-sibling::h4");
	private By awaitingValidationFilesCount= By.xpath("//div[text()='Awaiting Validation']/preceding-sibling::h4");
	private By InProgressFilesCount= By.xpath("//div[text()='In Progress']/preceding-sibling::h4");
	private By validatedFilesCount= By.xpath("//div[text()='Validated']/preceding-sibling::h4");

	public String getTotalFilesCount() {
		String totalFiles=getText(totalFilesCount,ExplicitWaitExpextecConditions.PRESENSCE);
		return totalFiles;
	}
	public String getawaitingValidationCount() {

		String awaitingValidation=getText(awaitingValidationFilesCount,ExplicitWaitExpextecConditions.PRESENSCE);
		return awaitingValidation;
	}
	public String validatedCount() {

		String validated=getText(validatedFilesCount,ExplicitWaitExpextecConditions.PRESENSCE);
		return validated;
	}
	public String InProgressCount() {
		String inprogress=getText(InProgressFilesCount,ExplicitWaitExpextecConditions.PRESENSCE);
		return inprogress;
	}
	
	public WorkList viewTotalFilesDetails() {
		clickUsinBy(totalFiles, ExplicitWaitExpextecConditions.CLICKABLE);
		waitForPageload();
		new WorkList().getSearchCriteria();
		return new WorkList();
	}
	public WorkList viewAwaitingValidationDetails() {
		clickUsinBy(awaitingValidationFiles, ExplicitWaitExpextecConditions.CLICKABLE);
		waitForPageload();
		return new WorkList();
	}
	public WorkList viewInprogressDetails() {
		clickUsinBy(InProgressFiles, ExplicitWaitExpextecConditions.CLICKABLE);
		waitForPageload();
		return new WorkList();
	}
	public WorkList viewValidateDetails() {
		clickUsinBy(validatedFiles, ExplicitWaitExpextecConditions.CLICKABLE);
		waitForPageload();
		return new WorkList();
	}

}
