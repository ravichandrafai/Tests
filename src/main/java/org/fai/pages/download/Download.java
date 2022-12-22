package org.fai.pages.download;

import java.util.List;

import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class Download extends BasePage{

	private By pageheading = By.xpath("//h5/span[contains(text(),'DOWNLOAD')]");
	private By closeDownalodWindow = By.xpath("//h5[contains(text(),'DOCUMENTS')]/following-sibling::div/button");
	private By selectAll = By.xpath("//input[@id='downloaddocsallcheck']");
	private By download= By.xpath("//div[contains(@class,'modal-footer')]//button[text()='Download']");
	private By allChkBoxes = By.xpath("//input[contains(@id,'_downloaddoccheck')]");
	private By validatedCatsByCategory = By.xpath("//div[text()='Document Category']/parent::div/following-sibling::div/div[2]//div[@class='d-inblock mr-2']");
	private By validatedCatsByName = By.xpath("//div[text()='Document Name']/parent::div/following-sibling::div/div[3]//div[@class='d-inblock mr-2']");
	private By disabledCategories = By.xpath("//div[contains(@class,'disabled_checkBox')]");
	private By documentName = By.xpath("//div[@class='td' and text()='Document Name']/parent::div/following-sibling::div//div[3]");	
	private By docCatandDocName; // = By.xpath("//div[@class='td' and text()='Document Name']/parent::div/following-sibling::div[@class='tr']");
	
	
	
	public String getPageHeading() {
		String heading= getText(pageheading, ExplicitWaitExpextecConditions.VISIBILE);
		return heading;
		
	}
	public Download closeDownloadWindow() {
		List<WebElement> closeBtn = findElements(closeDownalodWindow);
		if(closeBtn.size()>0) {
			try {
		clickUsinBy(closeDownalodWindow,ExplicitWaitExpextecConditions.VISIBILE);
			}catch(StaleElementReferenceException e) {
				clickUsinBy(closeDownalodWindow,ExplicitWaitExpextecConditions.VISIBILE);
			}
		}
		return this;
		
	}
	public Download selectAllDocs() {
		
		clickUsinBy(selectAll,ExplicitWaitExpextecConditions.VISIBILE);
		return this;
	}
	public Download download() {
		
		clickUsinBy(download, ExplicitWaitExpextecConditions.VISIBILE);
		return this;
	}
	public int countTotalCategories() {
		List<WebElement> cats= findElements(allChkBoxes);
		int size = cats.size();
		return size;
		
	}
	public int countValidateCategories() {
		List<WebElement> cats= findElements(validatedCatsByCategory);
		int size = cats.size();
		return size;
	}
	public int countValidateCategoriesByName() {
		List<WebElement> cats= findElements(validatedCatsByName);
		int size = cats.size();
		return size;
	}
	public int countDiabledCategoriesByName() {
		List<WebElement> cats= findElements(disabledCategories);
		int size = cats.size();
		return size;
	}
	public List<WebElement> getDocumentName(){
		List<WebElement> docNames= findElements(documentName);
		return docNames;
		
	}
	public String verifyTheDocName(String categoryNo) {
		
		String catDocNameLoc = "//div[@class='td' and text()='Document Name']/parent::div/following-sibling::div[@class='tr']["+categoryNo+"]";
		docCatandDocName = By.xpath(catDocNameLoc);
		ExplicitWaitConditions.performExplicitWait(closeDownalodWindow, ExplicitWaitExpextecConditions.PRESENSCE);
		List<WebElement> docDetails = findElements(docCatandDocName);
		String docName = null;
		
		if(docDetails.size()>=1) {
			WebElement row = docDetails.get(0);
			List<WebElement> allData= row.findElements(By.tagName("div"));
				//WebElement elCatName =allData.get(2);
				WebElement elDocName = allData.get(3);
				//String catName = elCatName.getText();
				
				docName = elDocName.getText();
				closeDownloadWindow();
				
		}else {
				closeDownloadWindow();
		}
				return docName;
	}
	
}
