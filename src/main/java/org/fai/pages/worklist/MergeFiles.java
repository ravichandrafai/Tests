package org.fai.pages.worklist;

import java.util.List;

import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MergeFiles extends BasePage{
	
	private By fileNamesInMergeScreen = By.xpath("//div[@class='col']/span");
	private By mergedFileName = By.xpath("//label[contains(text(),'The Merged File name')]/following-sibling::div//input");
	private By goback= By.xpath("//div[@class='col']/button[text()='Go Back']");
	private By proceed= By.xpath("//div[@class='row'][last()]//div[@class='col']//button[2]");
	private By closeMergeWindow = By.xpath("//div[@class='modal-header']//h5[contains(text(),'Batch Validate')]/following-sibling::button");
	
	public String getFileNames() {
		waitForMergeWindow();
		List<WebElement> list = findElements(fileNamesInMergeScreen);
		String fileNames = "";
		for(WebElement element:list) {
			fileNames=element.getText()+","+fileNames;
		}
		return fileNames;
	}
	public MergeFiles waitForMergeWindow() {
		
		ExplicitWaitConditions.performExplicitWait(mergedFileName, ExplicitWaitExpextecConditions.VISIBILE);
		return this;
	}
	public String getMergedFileName() {
		
		String mergedFile=getAttribute(mergedFileName, ExplicitWaitExpextecConditions.VISIBILE, "value");
		return mergedFile;
	}
	public WorkList goBacktoWorkList() {
		clickUsinBy(goback, ExplicitWaitExpextecConditions.VISIBILE);
		return new WorkList();
		
	}
	public WorkList proceedtoMerge() {
		waitForPageload();
		clickUsingJS(proceed, ExplicitWaitExpextecConditions.VISIBILE,true);
		return new WorkList();
		
	}
	public WorkList closeMergeWindow() {
		clickUsinBy(closeMergeWindow, ExplicitWaitExpextecConditions.VISIBILE);
		return new WorkList();
		
	}
	
}
