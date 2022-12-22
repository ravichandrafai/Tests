package org.fai.ExtractAutomationTests;

import java.awt.AWTException;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.driver.DriverManager;
import org.fai.pages.ManageDocuments.ManageDocuments;
import org.fai.pages.extactions.Extraction;
import org.fai.pages.navigationbar.NavigationBar;
import org.fai.pages.worklist.UploadFiles;
import org.fai.pages.worklist.WorkList;
import org.fai.reports.ExtentLogger;
import org.testng.annotations.Test;

public class ManageDocumentTests extends BaseTest{
	
	private ManageDocumentTests() {
		
	}
	@Test(priority=3,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void verifyZoominZoomOutandReset(Map<String, String> input) throws InterruptedException, AWTException {

		Thread.sleep(3000);
		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
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

		new ManageDocuments()
					.selectPage(input.get("gotopage"))
					.zoominPage()
					.zoominPage()
					.zoominPage()
					.zoomOutPage()
					.resetChanges();
					
	}
	@Test(priority=18,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void splitDocuments(Map<String, String> input) throws InterruptedException, AWTException {

		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));

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

		String newCatgrName= new ManageDocuments().splitDocument(input.get("split threshhold")).getNewDocCategoryName();
		ExtentLogger.pass("New catgory is created with no and Name as "+newCatgrName);
		
	}
	@Test(priority=15,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void verifyLabelsandNotes(Map<String, String> input) throws InterruptedException, AWTException {
		
		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
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

		new ManageDocuments()
					.clickNotes()
					.enterNotes("Notes")
					.addLabel("Test");
		new ManageDocuments()
					.changeTheCategoryName("1", "WCAB")
					.saveChanges()
					.gotoExtractions();
		
		new NavigationBar().gotoWorkList().sort().clickValidate(input.get("searchText"));
		
		new Extraction().retrunToManageDocuments();
		String notes= new ManageDocuments()
				.clickNotes()
		.getNotes();
		
		Assertions.assertThat(notes).isEqualTo("Notes");
		
		String label= new ManageDocuments()
				.clickNotes()
		.getLabel();
		Assertions.assertThat(label).isEqualTo("Test");
	}
	@Test(priority=6,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void dragnDrop(Map<String, String> input) throws InterruptedException, AWTException {
//		Thread.sleep(5000);
//		new NavigationBar()
//		.gotoWorkList();
//		String fileNames= new UploadFiles()
//		.clickOnUploadFiles()
//		.selectQueue(input.get("selectQueue"))
//		.browseFiles(input.get("uploadfile"))
//		.getUploadedFileName("NONE");
//		new UploadFiles().clickUpload();
//		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
		new NavigationBar().gotoWorkList().searchFiles("verifyMappingInfoSearchWithAllcontext.doc");
		
		new WorkList()
		.clickValidate("verifyMappingInfoSearchWithAllcontext.doc");

		
		String currentURL = DriverManager.getDriver().getCurrentUrl();
		String docFileName = new ManageDocuments()
				.getDocFileName();

		//Assertions.assertThat(input.get("searchText")).contains(docFileName);

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

//		Assertions.assertThat(input.get("searchText"))
//		.contains(mgDocFileName);

//		new ManageDocuments()
//					.selectPage(input.get("gotopage"))
//					.closeViewer()
//					.dragAndDrop(input.get("gotopage"),input.get("move to"),input.get("position to move"));
				
		new ManageDocuments()
			.dragdrop(input.get("gotopage"),input.get("move to"));
	}
	@Test(priority=9,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void VerifyClassificationOfCategories(Map<String, String> input) throws InterruptedException {
		
		
		new NavigationBar()
		.gotoWorkList().searchFiles(input.get("searchText"));
			
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
		new ManageDocuments().selectCatByName(input.get("new category name"));
		
	}
	@Test(priority=16,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void verifyMovePagesByMoveRibbon(Map<String, String> input) throws InterruptedException, AWTException {

		
		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
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

	
					new ManageDocuments()
					.selectPageToMove(input.get("gotopage"))
					.selectCategoryTOMovePage(input.get("move to"))
					.selectPositionToMove(input.get("position to move"))
					.clickOnCopy()
					.saveChanges();
					
	}
	@Test(priority=17,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void verifyPageRotation(Map<String, String> input) throws InterruptedException, AWTException {

		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
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

		new ManageDocuments()
		.selectPage(input.get("gotopage"))
					.rotateLeft()
					.rotateLeft()
					.rotateRight()
					.saveChanges();
					
	}
	@Test(priority=40,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void getdetailsFromDiv(Map<String, String> input) throws InterruptedException {
		
		Thread.sleep(3000);
		new NavigationBar().gotoWorkList();
		
		new WorkList().searchFiles(input.get("searchText"))
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

		

	
	}
	@Test(priority=50,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void splitDocumentsIssueTest(Map<String, String> input) throws InterruptedException, AWTException {

		Thread.sleep(5000);
		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));

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

		 new ManageDocuments().splitDocumentIssue(input.get("split threshhold"));
		
		
	}

}
