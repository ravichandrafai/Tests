package org.fai.pages.worklist;

import java.awt.AWTException;
import java.util.List;

import org.fai.constants.FrameworkConstants;
import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.exceptions.FrameworkException;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.base.BasePage;
import org.fai.reports.ExtentLogger;
import org.fai.utils.ActionsUtil;
import org.fai.utils.JSExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class UploadFiles extends BasePage{
	private By pageHeading = By.xpath("//div[@class='uploadnewfiles__modal_wrap modal-content p-0']//div[@class='modal-header']/h5");
	private By closeUploadWindow = By.xpath("//h5[contains(text(),'Upload')]/following-sibling::button[text()='X Cancel']");
	private By uploadFiles_Btn = By.xpath("//button[text()='Upload Files']");
	private By queueName = By.id("react-select-2-input");
	private By browseFiles = By.xpath("//input/following::h4[text()='Drag Files Here']");
	private By uploadedFileNames = By.xpath("//div[@class='dzu-previewContainer']/span");
	private By uploadButton = By.xpath("//button[text()='Upload']");
	private By uploadStatus = By.xpath("//div[@class='fai_loadinganim_st1bar']");
	private By selectQueueErrorMsg = By.xpath("//div[text()='Please Select Queue for Upload']");
	private By batchIntoSingleFileChkBox =By.xpath("//div[@class='fai__checkbox_button d-flex form-check']//label[@for='upload__checkbox_01']");
	private By getBatchFileName =By.xpath("//span[text()='File Name: ']/parent::div//form//input");
	private By autoSplitFIleChkBox = By.xpath("//div[@class='fai__checkbox_button d-flex form-check']//label[@for='upload__checkbox_02']");
	private By assignSingleIdToAllFiles = By.xpath("//div[@class='fai__checkbox_button d-flex form-check']//label[@for='upload__checkbox_03']");
	private By renameFile = By.xpath("//form/input");
	private By invalidFileRejectMsg = By.xpath("//div[@class='modal-content']//div/button[contains(text(),'Ok')]");
	private By enterMatterID = By.xpath("//div[@class='uploadnewfile__options_ch']//input");
	private By searchMatterID = By.xpath("//input[contains(@class,'MI_search')]/parent::div/following-sibling::div/img");
	private By selectMatterID = By.xpath("//th[text()='Matter Id']/ancestor::thead/following-sibling::tbody/tr/td[1]");
	private By customerMappingDetails = By.xpath("//div[@class='uploadscreen__CustomerMapping_table row']");
	public String getPageHeading() {
		String heading = getText(pageHeading, ExplicitWaitExpextecConditions.PRESENSCE);
		return heading;
	}
	
	public UploadFiles closeWindow() throws InterruptedException {
		JSExecutor.scrolltoView(closeUploadWindow);
		clickUsinBy(closeUploadWindow, ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	
	public UploadFiles clickOnUploadFiles() {
		clickUsinBy(uploadFiles_Btn,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public UploadFiles selectQueue(String fileQueueName) {
		ExplicitWaitConditions.performExplicitWait(closeUploadWindow, ExplicitWaitExpextecConditions.VISIBILE);
		enterText(queueName, fileQueueName, ExplicitWaitExpextecConditions.PRESENSCE);
		ActionsUtil.clickEnter();
		return this;
	}
	public UploadFiles browseFiles(String fileName) {
		String[] files =fileName.split(",");
		String filePath = FrameworkConstants.getUploadFilePath();
		try {
		for(String file:files) {
			ActionsUtil.moveToEleAndClick(browseFiles);
			Thread.sleep(000);
			file = filePath+file;
			enterFileName(file);
			List<WebElement> invalidFile = findElements(invalidFileRejectMsg);
			if(invalidFile.size()>0) {
				clickUsinBy(invalidFileRejectMsg, ExplicitWaitExpextecConditions.CLICKABLE);
				
			}
			ExtentLogger.pass("Selected the File "+file,true);
		}
		}catch(AWTException ae) {
			ae.printStackTrace();
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}finally {
			files=null;
		}
		return this;
		
	}
	
	public String getUploadedFileName(String matterID) {
		//Thread.sleep(2000);
		ActionsUtil.moveToElement(uploadButton);
		List<WebElement> uplaodedFiles= findElements(uploadedFileNames);
		String uFileName = "";
		String fileNamePrefix;
		String[] onlyName;
		if(matterID.equals("NONE")) {
			fileNamePrefix="";
		}else {
			fileNamePrefix="id_"+matterID+"_";
		}
		
		if(uplaodedFiles.size()>=1)
			try {
				
			for(WebElement fileName:uplaodedFiles) {
				onlyName = fileName.getText().split(",");
				
				if(!matterID.equals("NONE")) {
					uFileName = fileNamePrefix+onlyName[0]+","+uFileName;
				}
				else
					uFileName=fileNamePrefix+onlyName[0]+","+uFileName;
			}
			}catch(IndexOutOfBoundsException ie) {
				ie.printStackTrace();
			}finally {
				onlyName=null;
				
			}
		return uFileName;
	}
	public UploadFiles clickUpload() {
		ExplicitWaitConditions.performExplicitWait(uploadButton,ExplicitWaitExpextecConditions.CLICKABLE);
		ActionsUtil.moveToEleAndClick(uploadButton);
		List<WebElement> errMsg = findElements(selectQueueErrorMsg);
		if(errMsg.size()>0)
			throw new FrameworkException("Please select queue name to upload file");
		else {
		try {
		ExplicitWaitConditions.invisbileOfElement(uploadStatus, ExplicitWaitExpextecConditions.NONE);
		}catch(TimeoutException timeoutex) {
			throw new FrameworkException("FileUpload is taking more than expected time");
		}
		}
		return this;
	}
	public UploadFiles verifyDefaultUploadpage() {
		
		WebElement batchinto= ExplicitWaitConditions.performExplicitWait(batchIntoSingleFileChkBox, ExplicitWaitExpextecConditions.CLICKABLE);
		
		WebElement autoSplit= ExplicitWaitConditions.performExplicitWait(autoSplitFIleChkBox, ExplicitWaitExpextecConditions.CLICKABLE);
		
		WebElement assignID= ExplicitWaitConditions.performExplicitWait(assignSingleIdToAllFiles, ExplicitWaitExpextecConditions.CLICKABLE);
		
		if(batchinto.isSelected()&&autoSplit.isSelected()&&assignID.isSelected())
			ExtentLogger.fail("Check boes are selected by default", true);
		
		return this;
	}
	
	public UploadFiles selectBatchIntoSingleFile() {
		clickUsingJS(batchIntoSingleFileChkBox, ExplicitWaitExpextecConditions.CLICKABLE,true);
		return this;
	}
	public String getBatchFilename() {
		String batchfileName = getAttribute(getBatchFileName, ExplicitWaitExpextecConditions.PRESENSCE,"value");
		return batchfileName;
	}
	public UploadFiles selectAutoSplitFile() {
		clickUsingJS(autoSplitFIleChkBox, ExplicitWaitExpextecConditions.CLICKABLE,true);
		return this;
	}
	public String selectAssignIds(String matterId) {
		
		String selectedMatterID="None";
		if(matterId.contains("NONE")) {
			
		}else {
			System.out.println("Matter ID is"+matterId);
		clickUsingJS(assignSingleIdToAllFiles, ExplicitWaitExpextecConditions.CLICKABLE,true);
		enterText(enterMatterID,matterId,ExplicitWaitExpextecConditions.PRESENSCE);
		clickUsinBy(searchMatterID, ExplicitWaitExpextecConditions.PRESENSCE);
		waitForPageload();
		clickUsinBy(selectMatterID, ExplicitWaitExpextecConditions.CLICKABLE);
		ExplicitWaitConditions.invisbileOfElement(customerMappingDetails, ExplicitWaitExpextecConditions.NONE);
		selectedMatterID = getAttribute(enterMatterID, ExplicitWaitExpextecConditions.PRESENSCE,"value");
		}
		return selectedMatterID;
	}
	public UploadFiles renameFile(String batchFileName) {
		enterText(renameFile, batchFileName, ExplicitWaitExpextecConditions.VISIBILE);
		return this;
	}
	public UploadFiles clickOkOnInvalidFileProcessing() {
		
		clickUsinBy(invalidFileRejectMsg,ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}


}
