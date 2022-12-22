package org.fai.pages.extactions;

import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.ManageDocuments.ManageDocuments;
import org.fai.pages.base.BasePage;
import org.fai.pages.download.Download;
import org.fai.reports.ExtentLogger;
import org.fai.utils.ActionsUtil;
import org.fai.utils.JSExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class Extraction extends BasePage{

	private By backToManage = By.xpath("//button[text()='Back to Manage Documents']");
	private By catsCollapse = By.className("cats_collapsemenu_open"
			+ "");
	private By categories = By.xpath("//div[@class='managedocscat_nav']//ul/li");
	private By zoomin = By.xpath("//div[@class='fai__docZoom_btns']/button[1]");
	private By zoomout = By.xpath("//div[@class='fai__docZoom_btns']/button[2]");
	private By imgLoc = By.xpath("//div[@class='show resultdisplay_Xtractdoccrop_wrap']//child::div[@id=1]/div");
	private By rotate = By.xpath("//div[@class='fai__docZoom_btns']/button[3]");
	private By addLabel = By.xpath("//button[text()='Add Label']");
	private By enterLaelName = By.name("label");
	private By confrimLabelName = By.xpath("//input[@name='label']/parent::div/following-sibling::button[1]");
	private By closeLabelInput = By.xpath("//input[@name='label']/parent::div/following-sibling::button[2]");
	private By docImage = By.id("ProcesseImgsPdf");
	private By download = By.xpath("//button[text()='Download']");
	
	public ManageDocuments retrunToManageDocuments() {
		ActionsUtil.scrollUP(backToManage);
		clickUsinBy(backToManage,ExplicitWaitExpextecConditions.PRESENSCE);
		new ManageDocuments().waitForManageDocumentsLoad();
		return new ManageDocuments();
		
	}
	
	public Extraction getAllCategories() {
		
		clickUsinBy(categories,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	
	public Extraction collapseCategoriesMenu() {
		
		clickUsinBy(catsCollapse, ExplicitWaitExpextecConditions.CLICKABLE);
		return new Extraction();
	}
	
	public Extraction zoomin() {
		WebElement element = ExplicitWaitConditions.performExplicitWait(imgLoc, ExplicitWaitExpextecConditions.VISIBILE);
		Dimension beforeSize = element.getSize();
		ExtentLogger.pass("Before zoomin dimesions are "+beforeSize.toString(),true);
		clickUsinBy(zoomin,ExplicitWaitExpextecConditions.PRESENSCE);
		Dimension afterSize = element.getSize();
		ExtentLogger.pass("After zoomin dimesions are "+afterSize.toString(),true);
		//boolean isChanged = compareDimensions(beforeSize, afterSize);
		return this;
	}
	public Extraction zoomout() {
		WebElement element = ExplicitWaitConditions.performExplicitWait(imgLoc, ExplicitWaitExpextecConditions.VISIBILE);
		Dimension beforeSize = element.getSize();
		ExtentLogger.pass("Before zoomout dimesions are "+beforeSize.toString(),true);
		clickUsinBy(zoomout,ExplicitWaitExpextecConditions.PRESENSCE);
		Dimension afterSize = element.getSize();
		ExtentLogger.pass("After zoomout dimesions are "+afterSize.toString(),true);
	//	boolean isChanged = compareDimensions(afterSize,beforeSize);
		return this;
	}
	public boolean reset() {
		WebElement element = ExplicitWaitConditions.performExplicitWait(imgLoc, ExplicitWaitExpextecConditions.VISIBILE);
		Dimension beforeSize = element.getSize();
		ExtentLogger.pass("Before rest size is "+beforeSize.toString());
		clickUsinBy(rotate, ExplicitWaitExpextecConditions.PRESENSCE);
		Dimension afterSize = element.getSize();
		ExtentLogger.pass("After rest size is "+afterSize.toString());
		Dimension actualSize = new Dimension(576, 745);
		if(afterSize.equals(actualSize))
		return true;
		else
			return false;
	}
	public Extraction addLabel() {
		
		clickUsinBy(addLabel,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public Extraction enterLabel(String label) {
		
		enterText(enterLaelName,label, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public Extraction confrimLabelName() {
		
		clickUsinBy(confrimLabelName,ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public Extraction closeLableInput() {
		clickUsinBy(closeLabelInput,ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	
	public Extraction scrollDocument() throws InterruptedException {
		
		JSExecutor.scrolltoView(docImage);
		return this;
		
	}
	public Download clickDownload() {
		ExplicitWaitConditions.performExplicitWait(backToManage, ExplicitWaitExpextecConditions.CLICKABLE);
		clickUsinBy(download,ExplicitWaitExpextecConditions.CLICKABLE);
		return new Download();
	}
	public void waitForExtraction() {
		ExplicitWaitConditions.performExplicitWait(backToManage, ExplicitWaitExpextecConditions.CLICKABLE);
	}
	

}
