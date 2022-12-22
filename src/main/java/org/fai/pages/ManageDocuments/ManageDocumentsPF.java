package org.fai.pages.ManageDocuments;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ManageDocumentsPF {

	WebDriver driver;
	
	@FindAll(@FindBy(how = How.XPATH, using = "//ul[@class='section-nav']/li/a/div"))
	List<WebElement> categoryElements;
	
	
	@FindAll(@FindBy(how = How.XPATH, using = "//div[@class='managedocscat_nav']/parent::div/following-sibling::div//div[@class='mb-4 cf-manage-docs selectedCategory']//ul/ul/li"))
	List<WebElement> categoryPageElements;
	
	
	
	
	public ManageDocuments selectPages(String pageNumber) {
		
		for(WebElement ele:categoryElements) {
			ele.click();
			
			for(WebElement page:categoryPageElements) {
				page.click();
			}
		}
//		String pageLoc= "//div[@class='docImg' and @id='"+pageNumber+"']";
//		selectSpecificPage = By.xpath(pageLoc);
		return new ManageDocuments();
	}
	
}
