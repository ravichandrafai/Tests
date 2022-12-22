package org.fai.ExtractAutomationTests;

import java.awt.AWTException;
import java.lang.reflect.Method;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.pages.navigationbar.NavigationBar;
import org.fai.pages.worklist.UploadFiles;
import org.fai.pages.worklist.WorkList;
import org.fai.reports.FrameworkLogger;
import org.testng.annotations.Test;

public class UploadFileTests extends BaseTest{

	private UploadFileTests() {
		
	}
	
	@Test(priority=2,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void uploadAndVerifyFileStatus(Map<String, String> input) throws InterruptedException, AWTException {
		
		Thread.sleep(3000);
		
		new NavigationBar()
		.gotoWorkList();
		
		new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.selectAssignIds(input.get("matter id"));
		String fileNames= new UploadFiles().getUploadedFileName(input.get("matter id"));
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
		
	}
	@Test(priority=11,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void batchIntoSingleFileUpalod(Map<String, String> input) throws InterruptedException, AWTException{
		
		new NavigationBar()
		.gotoWorkList().clearInputText();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.selectBatchIntoSingleFile()
		.browseFiles(input.get("uploadfile"))
		.getBatchFilename();
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
	}
	@Test(priority=12,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void verifyBulkuploadAndVerifyFileStatus(Map<String, String> input) throws InterruptedException, AWTException {
		
		new NavigationBar()
		.gotoWorkList();
		new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.selectAssignIds(input.get("matter id"));
		String fileNames= new UploadFiles().getUploadedFileName(input.get("matter id"));
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
		
	}
	@Test(priority=21,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void uploadInvalidFile(Map<String, String> input) throws InterruptedException, AWTException {
		
		new NavigationBar()
		.gotoWorkList().clearInputText();
		new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.selectBatchIntoSingleFile()
		.renameFile("batchFile.pdf")
		.browseFiles(input.get("uploadfile"))
		.closeWindow();
		
	}
	@Test(priority=25,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void assignSinleIDToAllFiles(Map<String, String> input) throws InterruptedException, AWTException {
		
			new NavigationBar()
			.gotoWorkList().clearInputText();
			String selectedMatterId = new UploadFiles()
			.clickOnUploadFiles()
			.selectQueue(input.get("selectQueue"))
			.selectAssignIds(input.get("matter id"));
			Assertions.assertThat(selectedMatterId).isEqualTo(input.get("matter id"));
			String fileNames = new UploadFiles().browseFiles(input.get("uploadfile"))
			.getUploadedFileName(input.get("matter id"));
			new UploadFiles()
			.clickUpload();
			new WorkList()
			.waitForFilestatusChange(fileNames, input.get("exp status"));
			}
}
