package org.fai.ExtractAutomationTests;

import java.awt.AWTException;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.db.DbOperations;
import org.fai.driver.DriverManager;
import org.fai.enums.PropertyEnums;
import org.fai.pages.ManageDocuments.ManageDocuments;
import org.fai.pages.download.Download;
import org.fai.pages.extactions.ExtractedFields;
import org.fai.pages.extactions.Extraction;
import org.fai.pages.navigationbar.NavigationBar;
import org.fai.pages.worklist.UploadFiles;
import org.fai.pages.worklist.WorkList;
import org.fai.reports.ExtentLogger;
import org.fai.utils.GetDateFormatsFromExcel;
import org.fai.utils.Helper;
import org.fai.utils.ReadProperties;
import org.fai.utils.ReadTaxanomyDetails;
import org.testng.annotations.Test;



public class ExtractionTests extends BaseTest {

	private ExtractionTests() {

	}
	@Test(priority = 7, groups = { "smoke" }, dependsOnGroups = { "prerequisites" })
	public void docViewerZoom(Map<String, String> input) throws AWTException, InterruptedException {

		new NavigationBar().gotoWorkList();
		String fileNames = new UploadFiles().clickOnUploadFiles().selectQueue(input.get("selectQueue"))
				.browseFiles(input.get("uploadfile")).getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		new WorkList().clickValidate(input.get("searchText"));

		String mgDocFileName = new ManageDocuments().getFileName();
		Assertions.assertThat(input.get("searchText")).contains(mgDocFileName);

		String currentURL = DriverManager.getDriver().getCurrentUrl();

		if (currentURL.contains("categorization")) {
			new ManageDocuments().gotoExtractions();

		}
		new Extraction().zoomin().zoomin().zoomin().zoomin().zoomout().reset();

	}
	@Test(priority = 14, groups = { "smoke" }, dependsOnGroups = { "prerequisites" })
	public void validatedDocNameAndDownload(Map<String, String> input) throws InterruptedException, AWTException {

		new NavigationBar().gotoWorkList();
		String fileNames = new UploadFiles().clickOnUploadFiles().selectQueue(input.get("selectQueue"))
				.browseFiles(input.get("uploadfile")).getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));

		new WorkList().clickValidate(input.get("searchText"));
		String currentURL = DriverManager.getDriver().getCurrentUrl();
		String docFileName = new ManageDocuments().getDocFileName();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if (currentURL.contains("categorization")) {

			String pageHeading = new ManageDocuments().getPageHeading();
			Assertions.assertThat(pageHeading).isEqualTo("Manage Documents");

		} else if (currentURL.contains("extractions")) {

			new Extraction().retrunToManageDocuments();

		}

		String mgDocFileName = new ManageDocuments().getFileName();

		Assertions.assertThat(input.get("searchText")).contains(mgDocFileName);

		new ManageDocuments().changeTheCategoryName("1", input.get("new category name")).saveChanges();

		new ManageDocuments().gotoExtractions();

		String nameBeforeValidation = new ExtractedFields().getDocumentName();

		//ExtentLogger.pass("Doc Name before validation " + nameBeforeValidation);
		new ExtractedFields().setetMatterId(input.get("matter id")).setDocDate(input.get("doc date"))
				.verifyCaseName(input.get("case name")).setDocType(input.get("doc cat"), input.get("doc sub cat"))
				.setTags(input.get("tags")).clickOnValidate().goBackToExtractions();
		String nameAfterValidation = new ExtractedFields().getDocumentName();
		new Extraction().clickDownload();
		String downloadDocName = new Download().verifyTheDocName("1");
		Assertions.assertThat(nameAfterValidation).isEqualTo(downloadDocName);
	}
	@Test(priority = 8, groups = { "smoke" }, dependsOnGroups = { "prerequisites" })
	public void uploadAndValidateFiles(Map<String, String> input) throws Exception {

//		Thread.sleep(5000);
//		 new NavigationBar().gotoWorkList();
//		 String fileNames= new UploadFiles()
//		 .clickOnUploadFiles()
//		 .selectQueue(input.get("selectQueue"))
//		 .browseFiles(input.get("uploadfile"))
//		 .getUploadedFileName("NONE");
//		 new UploadFiles().clickUpload();
//		 new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		new NavigationBar().gotoWorkList().searchFiles(input.get("searchText")).clickValidate(input.get("searchText"));
		
		String docFileName = new ManageDocuments().getDocFileName();

		String currentURL = DriverManager.getDriver().getCurrentUrl();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if (currentURL.contains("categorization")) {

			new ManageDocuments().gotoExtractions();

		}

		Assertions.assertThat(input.get("searchText")).contains(docFileName);
		//new ExtractedFields().validateFieldOrder(input.get("expected doc name"),input.get("doc sub cat"));

		String[] categoryNames = Helper.splitInput(input.get("expected doc name"));
		String[] subCatName = Helper.splitInput(input.get("doc sub cat"));
		String catName;
		String docType;

		List<Map<String, String>> fieldOrderDetails = DbOperations
				.getFieldOrder(ReadProperties.get(PropertyEnums.CLIENTNAME));

		for (int i = 0; i < categoryNames.length; i++) {
			catName = categoryNames[i];
			docType = subCatName[i];
			String fieldOrder = null;
			String externalTags;
			for (int j = 0; j < fieldOrderDetails.size(); j++) {
				String dbCatName = fieldOrderDetails.get(j).get("category_name");
				String dbDocType = fieldOrderDetails.get(j).get("customer_subcategory");
				String docNameFields = fieldOrderDetails.get(j).get("fieldnames");
				if (catName.contains(dbCatName) && docType.contains(dbDocType)) {
					fieldOrder = fieldOrderDetails.get(j).get("fieldOrder").replace("[", "").replace("]", "");
					externalTags = fieldOrderDetails.get(j).get("external_tags");
					if (!externalTags.isEmpty()) {
						fieldOrder = fieldOrder + ",Tags";
					}
					Map<String, String> expExtractDetails = ReadTaxanomyDetails.getExtractiondetails(catName, docType);

					new ExtractedFields().selectCategory(catName).verifyFieldOrder(fieldOrder);
					
											new ExtractedFields().verifyCaseName(expExtractDetails.get("Case Name"))
																.verifyApplicantFirstName(expExtractDetails.get("Applicant First Name"))
																.verifyApplicantLastName(expExtractDetails.get("Applicant Last Name"))
																.verifyMatterID(expExtractDetails.get("Matter ID"));
											if(docNameFields.contains("Document Date"))
												new ExtractedFields().verifyDocDate(expExtractDetails.get("Document Date"));
												if(docNameFields.contains("Document Type"))
													new ExtractedFields().verifyDocType(expExtractDetails.get("Document Type"));
												if(docNameFields.contains("Sender"))
													new ExtractedFields().verifySender(expExtractDetails.get("Sender"));
													if(docNameFields.contains("Notes"))
														new ExtractedFields().verifyNotes(expExtractDetails.get("Notes"));
													if(docNameFields.contains("Doctor"))
														new ExtractedFields().verifyNotes(expExtractDetails.get("Doctor"));
													if(docNameFields.contains("Event Date"))
														new ExtractedFields().verifyNotes(expExtractDetails.get("Event Date"));
													
													new ExtractedFields()
																.verifyTags(externalTags)
																.verifyDocName(expExtractDetails.get("Document Name"));				
					break;
				}
				
			

		}
		// .clickOnValidate()
		// .goBackToExtractions();
		//
		}
	}
	@Test(priority = 13, groups = { "smoke" }, dependsOnGroups = { "prerequisites" })
	public void validateDateFromats(Map<String, String> input) throws InterruptedException, AWTException {

		

		new NavigationBar().gotoWorkList();
		String fileNames = new UploadFiles().clickOnUploadFiles().selectQueue(input.get("selectQueue"))
				.browseFiles(input.get("uploadfile")).getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().sort().waitForFilestatusChange(input.get("searchText"), input.get("exp status"))
				.clickValidate(input.get("searchText"));

		String docFileName = new ManageDocuments().getDocFileName();
		 new ManageDocuments()
		 .selectCatByName(input.get("new category name"))
		 .gotoExtractions();
		List<String> datesList = GetDateFormatsFromExcel.getDateFormats();
		for (int i = 0; i <= datesList.size() - 1; i++) {
			String date = datesList.get(i);
			new ExtractedFields().setDocDate(date);
		}

	}
	@Test(priority = 5, groups = { "smoke" }, dependsOnGroups = { "prerequisites" })
	public void verifyMappingInfoSearchWithAllcontext(Map<String, String> input)
			throws InterruptedException, AWTException {
			
		new NavigationBar().gotoWorkList();
		String fileNames = new UploadFiles().clickOnUploadFiles().selectQueue(input.get("selectQueue"))
				.browseFiles(input.get("uploadfile")).getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		new WorkList().clickValidate(input.get("searchText"));
		String docFileName = new ManageDocuments().getDocFileName();

		String currentURL = DriverManager.getDriver().getCurrentUrl();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if (currentURL.contains("categorization")) {

			new ManageDocuments().gotoExtractions();

		}

		Assertions.assertThat(input.get("searchText")).contains(docFileName);
		new ExtractedFields().searchMaappingInfo(input.get("matter id"));
	}
	@Test(priority = 6, groups = { "smoke" }, dependsOnGroups = { "prerequisites" })
	public void extractText(Map<String, String> input) throws InterruptedException {

		Thread.sleep(6000);
		new NavigationBar().gotoWorkList();
		new WorkList().searchFiles(input.get("searchText")).clickValidate(input.get("searchText"));
		String docFileName = new ManageDocuments().getDocFileName();

		String currentURL = DriverManager.getDriver().getCurrentUrl();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if (currentURL.contains("categorization")) {

			new ManageDocuments().gotoExtractions();

		}

		new ExtractedFields().selectText();
	}
	@Test(priority = 32, groups = { "smoke" }, dependsOnGroups = { "prerequisites" })
	public void verifyFileNameFormat(Map<String, String> input) throws Exception {
		Thread.sleep(8000);
		new NavigationBar().gotoWorkList();
		new WorkList().searchFiles(input.get("searchText"))
		.clickValidate(input.get("searchText"));
		String docFileName = new ManageDocuments()
				.getDocFileName();

		String currentURL = DriverManager.getDriver().getCurrentUrl();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if(currentURL.contains("categorization")) {

			new ManageDocuments().gotoExtractions();

		}
		Assertions.assertThat(input.get("searchText")).contains(docFileName);
		new ExtractedFields().validateDocumentName();
//		String[] categoryNames = Helper.splitInput(input.get("expected doc name"));
//		String catName;
//		List<Map<String,String>> fieldNameFormatDetails = DbOperations.getFileNameFormat(ReadProperties.get(PropertyEnums.CLIENTNAME));
//		for(int i=0;i<categoryNames.length;i++) {
//			catName=categoryNames[i];
//			for(int j=0; j<fieldNameFormatDetails.size();j++) {
//				String dbCatName=fieldNameFormatDetails.get(j).get("category_name");
//				if(catName.contains(dbCatName))
//				{
//					String dbDocType = fieldNameFormatDetails.get(j).get("customer_subcategory");
//					String nameFields = fieldNameFormatDetails.get(j).get("fileNameFields");
//					String seperators = fieldNameFormatDetails.get(j).get("seperators");
//							
//					String[] catnoName = catName.split("\\.");
//					String docNo= catnoName[0];
//					String docName = catnoName[1];
//					
//					new ExtractedFields()
//					.selectCategoryByNumber(docNo);
//					
//					new ExtractedFields()
//					.setDocType(docName, dbDocType);
//					
//					boolean isFileNameValid = new ExtractedFields()
//								.validateFileNameFormat(nameFields, seperators,ReadProperties.get(PropertyEnums.DATEFORMAT));
//					Assertions.assertThat(isFileNameValid).isEqualTo(true);
//					
//				}
//				
//				
//				
//			}
//
//		}
	}

}
