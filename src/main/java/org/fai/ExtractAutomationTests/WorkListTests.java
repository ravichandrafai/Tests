package org.fai.ExtractAutomationTests;

import java.awt.AWTException;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.driver.DriverManager;
import org.fai.pages.ManageDocuments.ManageDocuments;
import org.fai.pages.extactions.Extraction;
import org.fai.pages.navigationbar.NavigationBar;
import org.fai.pages.worklist.MergeFiles;
import org.fai.pages.worklist.UploadFiles;
import org.fai.pages.worklist.WorkList;
import org.testng.annotations.Test;

public class WorkListTests extends BaseTest{

	private WorkListTests() {

	}

	@Test(priority=19,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void sortByLastUpdated(Map<String, String> input) throws InterruptedException {

		
		
		new NavigationBar().gotoWorkList()
		.searchFiles(input.get("searchText")).sort();
		
	}
	@Test(priority=23,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void verifyNumberOfPages(Map<String, String> input) throws InterruptedException, AWTException{

		
		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		String worklistCount = new WorkList().waitForFilestatusChange(fileNames, input.get("exp status")).getNoOfPagesInFile();
	
		new WorkList()
		.clickValidate(input.get("searchText"));

		String currentURL = DriverManager.getDriver().getCurrentUrl();
		String docFileName = new ManageDocuments()
				.getDocFileName();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if(currentURL.contains("categorization")) {

			String pageHeading = new ManageDocuments()
					.getPageHeading();
			Assertions
			.assertThat(pageHeading)
			.isEqualTo("Manage Documents");

		}else if(currentURL.contains("extractions")) {

			new Extraction().retrunToManageDocuments();

		}

		String mgDocFileName = new ManageDocuments()
				.getFileName();

		Assertions.assertThat(input.get("searchText"))
		.contains(mgDocFileName);

		ManageDocuments objMngDoc= new ManageDocuments();
		String docPageCount= objMngDoc.getCountOfPages();
		Assertions.assertThat(worklistCount).isEqualTo(docPageCount);
		
	}
	@Test(priority=20,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void navigateToPages(Map<String, String> input) throws InterruptedException {

		new NavigationBar()
		.gotoWorkList().clearInputText()
		.gotoSpecificPageNumber(input.get("gotopage")).gotoNextPage().gotoLastPage().gotoPreviousPage().gotoFirstPage();
		

	}
	@Test(priority=23,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void mergeFilesWithSameorWithoutMatterID(Map<String, String> input) throws InterruptedException, AWTException {
		
		Thread.sleep(5000);
		new NavigationBar()
		.gotoWorkList().clearInputText();
		
		new UploadFiles()
				.clickOnUploadFiles()
				.selectQueue(input.get("selectQueue"))
				.browseFiles(input.get("uploadfile"))
				.selectAssignIds(input.get("matter id"));
		
		String fileNames = new UploadFiles().getUploadedFileName(input.get("matter id"));
				new UploadFiles().clickUpload();
		new WorkList().sort().selectFiles(fileNames).mergeFiles();
		String filestobeMerged=new MergeFiles().getFileNames();
		System.out.println("Files to be merged are"+filestobeMerged);
		String mergedFileName = new MergeFiles().getMergedFileName();
		System.out.println("Merged File Name"+mergedFileName);
		new MergeFiles().proceedtoMerge();
		
		String fileStatus = new WorkList()
		.searchFiles(mergedFileName).waitForFilestatusChange(mergedFileName, input.get("exp status")).getFileStatus(mergedFileName);
		Assertions.assertThat(fileStatus).isEqualTo(input.get("exp status"));
				
	}
	@Test(priority=24,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void mergeFiles(Map<String, String> input) throws AWTException {
		
		new NavigationBar()
		.gotoWorkList();	
		new WorkList().clearInputText().sort().selectFiles(input.get("searchText")).mergeFiles();
		String filestobeMerged=new MergeFiles().getFileNames();
		System.out.println("Files to be merged are"+filestobeMerged);
		String mergedFileName = new MergeFiles().getMergedFileName();
		System.out.println("Merged File Name"+mergedFileName);
		new MergeFiles().proceedtoMerge();
		
		String fileStatus = new WorkList()
		.searchFiles(mergedFileName).waitForFilestatusChange(mergedFileName, input.get("exp status")).getFileStatus(mergedFileName);
		Assertions.assertThat(fileStatus).isEqualTo(input.get("exp status"));
	}
	@Test(priority=4,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void verifyAutoIngestionFileStatus(Map<String, String> input) throws AWTException {
		new NavigationBar()
		.gotoWorkList();	
		new WorkList()
		.clearInputText()
		.searchFiles(input.get("searchText"))
		.waitForFilestatusChange(input.get("searchText"), input.get("exp status"));
		
	}
}
