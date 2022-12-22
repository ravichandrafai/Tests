package org.fai.pages.worklist;

import java.awt.AWTException;
import java.util.List;

import org.fai.constants.FrameworkConstants;
import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.exceptions.FrameworkException;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.ManageDocuments.ManageDocuments;
import org.fai.pages.base.BasePage;
import org.fai.pages.navigationbar.NavigationBar;
import org.fai.reports.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class WorkList extends BasePage {


	private By search_Input = By.xpath("//input[@placeholder='Search']");
	private By clearSearchTxt_Btn = By.xpath("//button[normalize-space()='Clear']");
	private By sortByDate = By.xpath("//th[text()='Uploaded Date']");
	private By fileStatusLoc;
	private By selectFile;
	private By firstRecordBy = By.xpath("//table/thead/following-sibling::tbody/tr/td[3]//div/div[contains(@class,'worklist__filename_col')]");
	private By validate;
	private By mergeFiles = By.xpath("//button[contains(@class,'blue roundcorner')]");
	private By noOfPages;
	private By lockUnlock_Tooltip ;
	private By confirmFileLock_Btn = By.xpath("//button[normalize-space()='Proceed']");
	private By lastUpdateBy ;
	private By noOfRecords_Btn = By.xpath("//button[@id='pageDropDown']");
	private By specificPage ;
	private By nextPage = By.xpath("//a[normalize-space()='>']");
	private By lastPage = By.xpath("//a[normalize-space()='>>']");
	private By previousPage = By.xpath("//a[normalize-space()='<']");
	private By firstPage = By.xpath("//a[normalize-space()='<<']");
	private By noFilesMessage = By.xpath("//table/thead/following-sibling::tbody/tr[1]/td");
	private By activePageNumber = By.xpath("//div[contains(@class,'table-pagination')]/div[2]//li[@class='active page-item']/a");
	private By alert = By.xpath("//div[@class='Toastify__toast-body']");
	private By pages = By.xpath("//ul[contains(@class,'pagination')]/li");
	private By noOfRecords = By.xpath("//table//tbody//tr");
	
	
	

	public WorkList clearInputText() {
		waitForPageload();
		clickUsinBy(clearSearchTxt_Btn,ExplicitWaitExpextecConditions.CLICKABLE);
		waitForPageload();
		return this;
	}
	public WorkList searchFiles(String searchText) {

		ExplicitWaitConditions.performExplicitWait(noOfRecords_Btn, ExplicitWaitExpextecConditions.VISIBILE);
		String noOfPagesLoc = "//td//div[text()='"+searchText+"']/following::td[2]/div[@class='table_count_pill']";
		noOfPages = By.xpath(noOfPagesLoc);
		waitForPageload();
		clearInputText();
		enterText(search_Input, searchText , ExplicitWaitExpextecConditions.CLICKABLE);
		waitForPageload();
		String resultMessage= getText(noFilesMessage, ExplicitWaitExpextecConditions.PRESENSCE);
		if(resultMessage.equals("No Files"))
		{
			ExtentLogger.fail("No files found with the search text "+searchText,true);
			clearInputText();
			throw new FrameworkException("No files found with the search text");
		}
		return this;
	}
	public String getSearchCriteria() {
		String searchCriteria = getText(search_Input, ExplicitWaitExpextecConditions.VISIBILE);
		return searchCriteria;
	}
	public ManageDocuments clickValidate(String searchText) {

		String fileName_Validate = "//div[text()='"+searchText+"']/ancestor::td/following::div[@data-tooltip='Validate']/button";
		String status_Validate = "//td[text()='"+searchText+"']/following::div[@data-tooltip='Validate']/button";
		String modifiedBY = "//td//div[text()='"+searchText+"']/following::td[4]";
		String tooltip= "//td//div[text()='"+searchText+"']/following::td[6]//div[@class='input-wrapper'][2]";

		lockUnlock_Tooltip =By.xpath(tooltip);
		lastUpdateBy= By.xpath(modifiedBY);

		String loggedinuser= new NavigationBar().getLoggedUserName();

		String attribute = getAttribute(lockUnlock_Tooltip, ExplicitWaitExpextecConditions.CLICKABLE, "data-tooltip");
		String modifiedBy = getText(lastUpdateBy,ExplicitWaitExpextecConditions.CLICKABLE);
		if(searchText.contains("pdf")||searchText.contains("jpg")||searchText.contains("tif")||searchText.contains("jpeg")||searchText.contains("png")
				||searchText.contains("docx")||searchText.contains("doc")||searchText.contains("html")||searchText.contains("eml")) {
			validate= By.xpath(fileName_Validate);
			waitForPageload();
			clickUsinBy(validate, ExplicitWaitExpextecConditions.CLICKABLE);

			if(attribute.equalsIgnoreCase("Unlock")&& (!modifiedBy.equalsIgnoreCase(loggedinuser))) {
				clickUsinBy(confirmFileLock_Btn, ExplicitWaitExpextecConditions.CLICKABLE);
			}
			

		}else if(searchText.contains("Progress")||searchText.contains("Validation")||searchText.contains("Validated")||searchText.contains("Merged")) {
			validate= By.xpath(status_Validate);
			waitForPageload();
			clickUsinBy(validate, ExplicitWaitExpextecConditions.CLICKABLE);

			if(attribute.equalsIgnoreCase("Unlock")) {
				clickUsinBy(confirmFileLock_Btn, ExplicitWaitExpextecConditions.CLICKABLE);
			}
		}
		new ManageDocuments().waitForFileName();
		return new ManageDocuments();
	}
	
	public ManageDocuments validateFile(String searchText) {

		String fileName_Validate = "(//div[@data-tooltip='Validate']/button)[1]";
		String modifiedBY = "//td//div[text()='"+searchText+"']/following::td[4]";
		String tooltip= "//td//div[text()='"+searchText+"']/following::td[6]//div[@class='input-wrapper'][2]";

		lockUnlock_Tooltip =By.xpath(tooltip);
		lastUpdateBy= By.xpath(modifiedBY);

		String loggedinuser= new NavigationBar().getLoggedUserName();

		String attribute = getAttribute(lockUnlock_Tooltip, ExplicitWaitExpextecConditions.CLICKABLE, "data-tooltip");
		String modifiedBy = getText(lastUpdateBy,ExplicitWaitExpextecConditions.CLICKABLE);
		validate= By.xpath(fileName_Validate);
		waitForPageload();
		clickUsinBy(validate, ExplicitWaitExpextecConditions.CLICKABLE);

		if(attribute.equalsIgnoreCase("Unlock")&& (!modifiedBy.equalsIgnoreCase(loggedinuser))) {
			clickUsinBy(confirmFileLock_Btn, ExplicitWaitExpextecConditions.CLICKABLE);
		}		
		new ManageDocuments().waitForFileName();
		return new ManageDocuments();
	}

	public WorkList sort() {
		clickUsingJS(sortByDate, ExplicitWaitExpextecConditions.NONE,true);
		return this;
	}
	public String getNoOfPagesInFile() {

		String pageCount = getText(noOfPages, ExplicitWaitExpextecConditions.VISIBILE);
		return pageCount;

	}
	public WorkList gotoSpecificPageNumber(String pageNumber) throws InterruptedException {


		String pageLoc= "//a[text()='"+pageNumber+"']";
		specificPage = By.xpath(pageLoc);
		if(findElements(specificPage).size()>0) {
			clickUsinBy(specificPage, ExplicitWaitExpextecConditions.VISIBILE);
			waitForPageload();
			String firstRecord = getText(noFilesMessage, ExplicitWaitExpextecConditions.VISIBILE);
			if(firstRecord.equals("No Files")) {
				ExtentLogger.fail("No Files message is displayed at page no"+pageNumber,true);
			}
		}
		else
		{

			ExtentLogger.fail("Not able to select the page number",true);
		}
		return this;
	}

	public WorkList gotoNextPage() {
		if(findElements(nextPage).size()>0) {
			clickUsinBy(nextPage, ExplicitWaitExpextecConditions.NONE);
			waitForPageload();
			String firstRecord = getText(noFilesMessage, ExplicitWaitExpextecConditions.VISIBILE);
			if(firstRecord.equals("No Files")) {
				String pageNo = getCurrentPageNumber();
				ExtentLogger.fail("No Files message is displayed at page no"+pageNo,true);
			}
		}
		else {

			ExtentLogger.fail("Next page is not available",true);
		}
		return this;
	}
	public WorkList gotoLastPage() {

		if(findElements(lastPage).size()>0) {
			clickUsingJS(lastPage, ExplicitWaitExpextecConditions.NONE,true);
			waitForPageload();
			String firstRecord = getText(noFilesMessage, ExplicitWaitExpextecConditions.VISIBILE);
			if(firstRecord.equals("No Files")) {
				String pageNo = getCurrentPageNumber();
				ExtentLogger.fail("No Files message is displayed at page no"+pageNo,true);
			}
		}
		else{

			ExtentLogger.fail("Last page is not available",true);
		}
		return this;
	}

	public WorkList gotoPreviousPage() {
		if(findElements(previousPage).size()>0) {
			clickUsingJS(previousPage, ExplicitWaitExpextecConditions.NONE,true);
			waitForPageload();
			String firstRecord = getText(noFilesMessage, ExplicitWaitExpextecConditions.VISIBILE);
			if(firstRecord.equals("No Files")) {
				String pageNo = getCurrentPageNumber();
				ExtentLogger.fail("No Files message is displayed at page no"+pageNo,true);
			}
		}
		else {

			ExtentLogger.fail("Previous page is not availabl",true);
		}
		return this;
	}
	public WorkList gotoFirstPage() {
		if(findElements(firstPage).size()>0) {
			clickUsingJS(firstPage, ExplicitWaitExpextecConditions.NONE,true);
			waitForPageload();
			String firstRecord = getText(noFilesMessage, ExplicitWaitExpextecConditions.VISIBILE);
			if(firstRecord.equals("No Files")) {
				String pageNo = getCurrentPageNumber();
				ExtentLogger.fail("No Files message is displayed at page no"+pageNo,true);
			}
		}
		else {

			ExtentLogger.fail("First page is not available",true);
		}
		return this;
	}

	public WorkList waitForFilestatusChange(String files,String expectedFileStaus){
		
		String[] uFileNames = files.split(",");
		try {
		for(String file:uFileNames) {
		
			if(!file.isEmpty()) {
				String fileStatus = "////div[contains(text(),'"+file+"')]/parent::div/parent::td/preceding-sibling::td[1]";
				fileStatusLoc = By.xpath(fileStatus);
				waitForPageload();
				searchFiles(file);
				String actualStatus = getFileStatus(file);
				if(!actualStatus.equals(expectedFileStaus)) {
				try {
				ExplicitWaitConditions.waitFortextToPresent(fileStatusLoc, expectedFileStaus);
				ExtentLogger.pass("File "+file+"status is in "+expectedFileStaus,true);
				}catch(TimeoutException te) {
					ExtentLogger.fail("File "+file+" not in "+expectedFileStaus+" even after waiting for "+FrameworkConstants.getExplicitWaitTime()+" Seconds",true);
				}
				}else
				{
					ExtentLogger.pass("File is in "+expectedFileStaus+"",true);
				}
			}
			}
		}finally {
			uFileNames=null;
		}
		return this;

	}
	
	public WorkList sortByLastUpdateandVerifyStatus(String file,String expStatus) {
		sort();
		waitForPageload();
		List<WebElement> firstRecord = findElements(firstRecordBy);
		List<WebElement> noFiles = findElements(noFilesMessage);
		if(firstRecord.size()>=1 || noFiles.size()==0) {
			String fileName = getText(firstRecordBy, ExplicitWaitExpextecConditions.PRESENSCE);
			if(fileName.contains(file)) {
				
					waitForFilestatusChange(fileName,expStatus);
				
			}
		}
		
		return this;
	}
	public String getFileStatus(String file) {
		
		String fileStatusXpath = "//div[contains(text(),'"+file+"')]/parent::div/parent::td/preceding-sibling::td[1]";
		fileStatusLoc = By.xpath(fileStatusXpath);
		String  fileStatus= getText(fileStatusLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		
		return fileStatus;
	}
	
	public String getCurrentPageNumber() {
		
		String activePageNo= getText(activePageNumber, ExplicitWaitExpextecConditions.CLICKABLE);
		return activePageNo;
	}
	public WorkList selectFiles(String fileName) {
		
		String fileSelect;
		String[] files =fileName.split(",");
		for(String file:files) {
			fileSelect = "//div[contains(text(),'"+file+"')]/parent::div/parent::td/preceding-sibling::td[2]";
			selectFile=By.xpath(fileSelect);
			clickUsinBy(selectFile, ExplicitWaitExpextecConditions.CLICKABLE);
			
		}
		return this;
	}
	public WorkList mergeFiles() {
		clickUsingJS(mergeFiles, ExplicitWaitExpextecConditions.VISIBILE,true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		List<WebElement> alertList = findElements(alert);
		System.out.println("Size"+alertList.size());
		if(alertList.size()>0) {
			
			String alertMsg= getText(alert, ExplicitWaitExpextecConditions.VISIBILE);
			switch(alertMsg) {
			
			case "Merging Files in Different Queues is not Available. Please select files from a single Queue to merge.":
				ExtentLogger.fail("Merge is failed with the reason "+alertMsg,true);
				throw new FrameworkException("Exiting the test");
			case "Unable to merge Files since one or more File(s) has an assigned matter ID that does not match the Matter ID of the other File(s)":
				ExtentLogger.fail("Merge is failed with the reason "+alertMsg,true);
				throw new FrameworkException("Exiting the test");
			}
		}
		return this;
	}
	public WorkList getFileCount() {
		List<WebElement> pageNos = findElements(pages);
		int count=0;
		for(WebElement ele:pageNos) {
			List<WebElement> noOfFiles = findElements(noOfRecords);
			count = count+noOfFiles.size();
			gotoNextPage();
		}
		return this;
	}
}
