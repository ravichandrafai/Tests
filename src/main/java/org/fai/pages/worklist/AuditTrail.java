package org.fai.pages.worklist;

import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.base.BasePage;
import org.openqa.selenium.By;

public class AuditTrail extends BasePage{

	private By pageTitle = By.xpath("//div[contains(@class,'modal-header p-4')]//h5");
	
	private By closeAuditWindow = By.xpath("//div[contains(@class,'modal-header p-4')]/following-sibling::a");
	
	private By searchAuditTrail = By.xpath("//div[contains(@class,'modal-header p-4')]/following-sibling::div//input");
	
	
	public String getTitle() {
		String title =getText(pageTitle, ExplicitWaitExpextecConditions.VISIBILE);
		return title;
	}
	
	public AuditTrail searchAudit(String searchCriteria) {
		enterText(searchAuditTrail, searchCriteria, ExplicitWaitExpextecConditions.VISIBILE);
		return this;
	}
	public void closeAuditWindow() {
		clickUsinBy(closeAuditWindow, ExplicitWaitExpextecConditions.CLICKABLE);
				
	}
	public void waitforAuditWindow() {
		ExplicitWaitConditions.performExplicitWait(searchAuditTrail, ExplicitWaitExpextecConditions.VISIBILE);
		
	}
}
