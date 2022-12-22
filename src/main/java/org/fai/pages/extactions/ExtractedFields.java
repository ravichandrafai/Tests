package org.fai.pages.extactions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fai.db.DbOperations;
import org.fai.driver.DriverManager;
import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.enums.PropertyEnums;
import org.fai.exceptions.FrameworkException;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.base.BasePage;
import org.fai.reports.ExtentLogger;
import org.fai.reports.FrameworkLogger;
import org.fai.utils.ActionsUtil;
import org.fai.utils.Helper;
import org.fai.utils.JSExecutor;
import org.fai.utils.ReadProperties;
import org.fai.utils.ReadTaxanomyDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.sisyphsu.dateparser.DateParserUtils;

public class ExtractedFields extends BasePage{

	private By extractedFieldsLabel = By.xpath("//div[@class='extractresults_wrap']//a");

	private By fieldsDisplayed = By.xpath("//div[@class='PR_keyValuesGrid_body']//div//div[contains(@class,'PR_keyValGrid_item_1')]/font");

	private By catName = By.cssSelector("div.fai__flexbox3col_main>h5");

	private By caseNameLoc = By.xpath("//font[text()='Case Name']/parent::div/following-sibling::div/font");

	private By applicantFirstNameLoc = By.xpath("//font[text()='Applicant First Name']/parent::div/following-sibling::div/font");

	private By applicantLastNameLoc = By.xpath("//font[text()='Applicant Last Name']/parent::div/following-sibling::div/font");

	private By matterIDLoc = By.xpath("//font[text()='Matter ID']/parent::div/following-sibling::div/font//input");

	private By matterIDSearchInput = By.xpath("//input[@placeholder='Search']");

	private By matterIDSearch =By.xpath("//input[@placeholder='Search']/following-sibling::button[1]");

	private By initMatterIDSearch = By.xpath("//div[contains(@class,'MatterIDSearch')]");

	private By selectMatterID =By.xpath("//th[text()='Matter Id']/ancestor::thead/following-sibling::tbody/tr/td[1]");

	private By sender = By.xpath("//font[text()='Sender']/parent::div/following-sibling::div/font//input");

	private By doctor = By.xpath("//font[text()='Doctor']/parent::div/following-sibling::div/font");

	private By docDateLoc =By.xpath("//font[text()='Document Date']/parent::div/following-sibling::div/font");

	private By errorMsgLoc = By.xpath("//div[@class='PR_keyValGrid_item_errorhead']");

	private By docTypeDropDownLoc = By.xpath("//font[text()='Document Type']/parent::div/following-sibling::div//div");

	private By selectDocCatType;

	private By selectDocSubCatType;

	private By getTags = By.cssSelector("div.css-12jo7m5");

	private By tagsDropDownLoc = By.xpath("//font[text()='Tags']/parent::div/following-sibling::div/div[contains(@class,'zindex')]/child::div/child::Div[2]");

	private By notesLoc = By.xpath("//font[text()='Notes']/parent::div/following-sibling::div/font");

	private By notesTextSelector = By.xpath("//div[contains(@class,'Notes')]//div[3]//img");



	private By docNameLoc = By.xpath("//div[text()='Document Name']/following-sibling::div");

	private By validate= By.xpath("//button[text()='Validate']");

	private By confirmationWindow = By.xpath("//h2[text()='Document Validation is Complete']");

	private By confirmValidation = By.xpath("//h2[text()='Document Validation is Complete']/parent::div/following-sibling::div/button[text()='Confirm']");

	private By goBackToExtraction = By.xpath("//h2[text()='Document Validation is Complete']/parent::div/following-sibling::div/button[text()='Go Back']");

	private By reviewOutput = By.xpath("//h2[text()='Document Validation is Complete']/parent::div/following-sibling::div/button[text()='Review File Output']");

	private By dateFormatErrorMsg= By.xpath("//div[@class='PR_keyValGrid_item_errorhead']//div[contains(text(),'Please enter date in recognized format')]");
	private By categoryLeftPane = By.xpath("//ul[@class='section-nav']/li/a/div");

	private By pagePerdown = By.xpath("//button[@id='pageDropDown']");
	private By selectCatByNameandNumber;

	public ExtractedFields verifyCaseName(String expCaseName) {

		String actualCaseName = getText(caseNameLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualCaseName.equals(expCaseName))
			//ExtentLogger.pass("Actual case name "+actualCaseName+" is same as epected case name "+expCaseName,true);
			FrameworkLogger.logInfo("Actual case name "+actualCaseName+" is same as epected case name "+expCaseName);
		else {
			//ExtentLogger.fail("Actual case name "+actualCaseName+ "different from expected case name"+expCaseName,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;

	}
	public ExtractedFields verifyApplicantFirstName(String expFirstName) {
		String actualFirstName = getText(applicantFirstNameLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualFirstName.equals(expFirstName))
			//ExtentLogger.pass("Actual first name "+actualFirstName+" is same as epected first name "+expFirstName,true);
			FrameworkLogger.logInfo("Actual first name "+actualFirstName+" is same as epected first name "+expFirstName);
		else {
			//ExtentLogger.fail("Actual first name "+actualFirstName+ "different from expected first name"+actualFirstName,true);
			FrameworkLogger.logInfo("Actual first name "+actualFirstName+ "different from expected first name"+actualFirstName);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	public ExtractedFields verifyApplicantLastName(String expLastName) {
		String actualLastName = getText(applicantLastNameLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualLastName.equals(expLastName))
			//ExtentLogger.pass("Actual last name "+actualLastName+" is same as epected last name "+expLastName,true);
			FrameworkLogger.logInfo("Actual last name "+actualLastName+" is same as epected last name "+expLastName);
		else {
			///ExtentLogger.fail("Actual last name "+actualLastName+ "different from expected last name"+expLastName,true);
			FrameworkLogger.logInfo("Actual last name "+actualLastName+ "different from expected last name"+expLastName);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	public ExtractedFields verifyMatterID(String expMatterID) {
		String actualMatterId = getAttribute(matterIDLoc, ExplicitWaitExpextecConditions.PRESENSCE,"value");
		if(actualMatterId.isEmpty()||!(actualMatterId.equals(expMatterID))) {
			//ExtentLogger.fail("Actual Matter Id is different from expected MatterID",true);
			throw new FrameworkException("Extracted details are not as expected");
		}else {
			FrameworkLogger.logInfo("Actual Matter Id is same as expected MatterID");
			//ExtentLogger.pass("Actual Matter Id is same as expected MatterID",true);
		}
		return this;
	}
	public ExtractedFields setetMatterId(String expMatterID) throws InterruptedException {

		String actualMatterId = getText(matterIDLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		String matterIDSearchResLoc = "//th[text()='Matter Id']/ancestor::thead/following-sibling::tbody/tr/td[text()='"+expMatterID+"']";
		selectMatterID = By.xpath(matterIDSearchResLoc);
		if(actualMatterId.isEmpty()||!(actualMatterId.equals(expMatterID))) {
			JSExecutor.scrollByPixel("0", "300");
			JSExecutor.scrolltoView(matterIDLoc);
			clickUsinBy(initMatterIDSearch, ExplicitWaitExpextecConditions.PRESENSCE);
			enterText(matterIDSearchInput,expMatterID,ExplicitWaitExpextecConditions.PRESENSCE);
			clickUsinBy(matterIDSearch, ExplicitWaitExpextecConditions.PRESENSCE);

			ExplicitWaitConditions.performExplicitWait(selectMatterID, ExplicitWaitExpextecConditions.PRESENSCE);
			clickUsinBy(selectMatterID, ExplicitWaitExpextecConditions.CLICKABLE);
			ExplicitWaitConditions.invisbileOfElement(selectMatterID, ExplicitWaitExpextecConditions.NONE);
			String selectedMatterID = getAttribute(matterIDLoc, ExplicitWaitExpextecConditions.PRESENSCE,"value");
			ExtentLogger.pass("Selected the matter id "+selectedMatterID,true);

		}else {
			ExtentLogger.pass("Actual matter id "+actualMatterId+" is same as expected "+expMatterID+"matter id",true);
		}
		return this;
	}
	public ExtractedFields setDocDate(String expDocDate) throws InterruptedException {
		String actualDocDate = getText(docDateLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDocDate.isEmpty()||!(actualDocDate.equals(expDocDate))) {
			JSExecutor.scrolltoView(docDateLoc);
			enterText(docDateLoc,expDocDate, ExplicitWaitExpextecConditions.PRESENSCE);
			clickUsinBy(catName, ExplicitWaitExpextecConditions.PRESENSCE);
			List<WebElement> errorMsgs = findElements(dateFormatErrorMsg);
			int size = errorMsgs.size();
			if(size>0)
				ExtentLogger.fail("Date "+expDocDate+"is not in valid format",true);


		}		
		return this;
	}
	public ExtractedFields verifyDocDate(String expDocDate) {
		String actualDocDate = getText(docDateLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDocDate.isEmpty()||!(actualDocDate.equals(expDocDate))) {
			ExtentLogger.fail("Actual doc date "+actualDocDate+" is same as epected doc date "+expDocDate,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		else {
			ExtentLogger.pass("Actual doc date "+actualDocDate+ "different from expected doc date "+expDocDate,true);

		}

		return this;
	}
	public ExtractedFields verifyDocType(String expDocType) {
		String actualDocType = getText(docTypeDropDownLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDocType.isEmpty()||!(actualDocType.equals(expDocType))) {
			ExtentLogger.fail("Actual doc type "+actualDocType+ "different from expected doc type "+expDocType,true);
			throw new FrameworkException("Extracted details are not as expected");
		}

		else {
			ExtentLogger.pass("Actual doc type "+actualDocType+" is same as epected doc type "+expDocType,true);
		}
		return this;
	}
	public String getDocType() {

		String actualDocType = getText(docTypeDropDownLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDocType.isEmpty()) {
			ExtentLogger.fail("Document type value is not poupated",true);
			throw new FrameworkException("Document type is empty");
		}
		return actualDocType;
	}
	
	public ExtractedFields verifyTags(String expTags) {
		String[] expTagsArray = expTags.split(",");
		String[] actualsTags = new String[expTagsArray.length];
		List<WebElement> tagsList = findElements(getTags);
		int i=0;
		for(WebElement el:tagsList) {

			actualsTags[i]=el.getText();
			i++;
		}
		if(Arrays.equals(expTagsArray, actualsTags)) {
			ExtentLogger.pass("Actual tags "+actualsTags+" is same as expected tags "+expTagsArray,true);


		}else {
			ExtentLogger.fail("Actual tags "+actualsTags+ "different from  epected tags "+expTagsArray,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	public ExtractedFields setTags(String expTag) throws InterruptedException {

		String[] tags =expTag.split(",");
		if(tags.length>0) {
			for(String tag:tags) {
				JSExecutor.scrolltoView(tagsDropDownLoc);
				ActionsUtil.moveToEleAndClick(tagsDropDownLoc);
				ActionsUtil.sendText(tagsDropDownLoc, tag);
				ActionsUtil.clickEnter();

			}

		}
		return this;
	}
	public ExtractedFields verifyNotes(String expNotes) {

		String actualNotes = getText(notesLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualNotes.isEmpty()||!(actualNotes.equals(expNotes)))
		{
			ExtentLogger.pass("Actual notes "+actualNotes+" is same as expected notes "+expNotes,true);
		}else {
			ExtentLogger.fail("Actual notes "+actualNotes+ "different from  expected notes "+expNotes,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	public ExtractedFields setNotes(String expNotes) {
		String actualNotes = getText(notesLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualNotes.isEmpty()||!(actualNotes.equals(expNotes)))
			enterText(notesLoc,expNotes, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public String getDocumentName() throws InterruptedException {
		JSExecutor.scrolltoView(docNameLoc);
		String docName = getText(docNameLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		return docName;
	}
	public ExtractedFields verifyDocName(String expDocName) throws InterruptedException {

		String actualDocName = getDocumentName();
		if(actualDocName.isEmpty()||(!actualDocName.equals(expDocName)))
		{
			ExtentLogger.fail("Actual doc name "+actualDocName+ "different from expected doc name "+expDocName,true);
			throw new FrameworkException("Extracted details are not as expected");

		}else {
			ExtentLogger.pass("Actual doc name "+actualDocName+" is same as expected doc name "+expDocName,true);
		}
		return this;
	}
	public String getdateErrorMsg() {
		String dtError = getText(errorMsgLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		return dtError;
	}
	public ExtractedFields clickOnValidate() {
		clickUsinBy(validate, ExplicitWaitExpextecConditions.CLICKABLE);
		ExplicitWaitConditions.performExplicitWait(confirmationWindow, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ExtractedFields verifySender(String expSender) {

		String actualSender = getAttribute(sender, ExplicitWaitExpextecConditions.PRESENSCE,"value");
		if(actualSender.isEmpty()||!(actualSender.equals(expSender)))
		{
			ExtentLogger.fail("Actual Sender  "+actualSender+ "different from expected Sender  "+expSender,true);
			//throw new FrameworkException("Extracted details are not as expected");

		}else {
			ExtentLogger.pass("Actual Sender  "+actualSender+" is same as expected Sender  "+expSender,true);
		}
		return this;
	}
	public ExtractedFields setSender(String expSender) {
		String actualSender = getText(sender, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualSender.isEmpty()||!(actualSender.equals(expSender)))
			ActionsUtil.sendText(sender, expSender);
		//enterText(sender,expSender, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;

	}
	public ExtractedFields confirmValidations() {
		clickUsinBy(confirmValidation, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public ExtractedFields goBackToExtractions() {

		clickUsinBy(goBackToExtraction, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public ExtractedFields reviewOutputFile() {

		clickUsinBy(reviewOutput, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public ExtractedFields verifyDoctor(String expDoctor) {
		String actualDoctor = getAttribute(doctor, ExplicitWaitExpextecConditions.PRESENSCE,"value");
		if(actualDoctor.isEmpty()||!(actualDoctor.equals(expDoctor)))
		{
			ExtentLogger.pass("Actual Doctor  "+actualDoctor+" is same as expected Doctor  "+expDoctor,true);
		}else {
			ExtentLogger.fail("Actual Doctor  "+actualDoctor+ "different from  expected Doctor  "+expDoctor,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	public ExtractedFields verifyFieldOrder(String expFieldOrder) {
		String[] expFldArray = expFieldOrder.split(",");
		String[] actualFldArray = new String[expFldArray.length];
		int i=0;
		List<WebElement> actualFlds = findElements(fieldsDisplayed);
		for(WebElement fld:actualFlds) {
			ExplicitWaitConditions.waitWithWebElemt(fld);
			actualFldArray[i] = fld.getText();
			String actualfld = actualFldArray[i];
			String expFld = expFldArray[i].trim();
			if(expFld.equals(actualfld))
				ExtentLogger.pass("Field at location "+i+" is "+actualfld+" and expected is "+expFld);
			else {
				ExtentLogger.fail("Field at location "+i+" is "+actualfld+" and expected is "+expFld);
				throw new FrameworkException(actualfld+" does not match with expected "+expFld);
			}
			i++;
		}

		return this;
	}
	public String getCategoryName() {
		String categoryName = getText(catName, ExplicitWaitExpextecConditions.PRESENSCE);

		return categoryName;
	}
	public ExtractedFields searchMaappingInfo(String expMatterIDs) throws InterruptedException {

		String[] listOfMatterIds = expMatterIDs.split(",");
		for(String expMatterID:listOfMatterIds ) {
			String matterIDSearchResLoc = "//th[text()='Matter Id']/ancestor::thead/following-sibling::tbody/tr/td[text()='"+expMatterID+"']";
			selectMatterID = By.xpath(matterIDSearchResLoc);
			JSExecutor.scrolltoView(matterIDLoc);
			clickUsinBy(initMatterIDSearch, ExplicitWaitExpextecConditions.PRESENSCE);
			enterText(matterIDSearchInput,expMatterID,ExplicitWaitExpextecConditions.PRESENSCE);
			clickUsinBy(matterIDSearch, ExplicitWaitExpextecConditions.PRESENSCE);
			ExplicitWaitConditions.performExplicitWait(pagePerdown, ExplicitWaitExpextecConditions.CLICKABLE);
			List<WebElement> resultList = findElements(selectMatterID);
			if(resultList.size()>=1) {
				ExtentLogger.pass("Details are available for the given search "+expMatterID,true);
				clickUsinBy(initMatterIDSearch, ExplicitWaitExpextecConditions.PRESENSCE);
			}else {

				ExtentLogger.fail("Details are not available for the matterid "+expMatterID,true);
				clickUsinBy(initMatterIDSearch, ExplicitWaitExpextecConditions.PRESENSCE);
			}

		}
		return this;
	}
	public ExtractedFields selectText() throws InterruptedException {
		JSExecutor.scrolltoView(notesTextSelector);

		Thread.sleep(2000);
		Actions actions = new Actions(DriverManager.getDriver());
		actions.moveByOffset(503, 622).clickAndHold().moveByOffset(503, 626).moveByOffset(523, 645);
		Thread.sleep(2000);
		ExtentLogger.pass("end of extract text",true);

		return this;
	}
	public ExtractedFields selectCategory(String categoryName) throws InterruptedException {
		String[] catDtls = categoryName.split("\\.");
		String number = catDtls[0];
		String name = catDtls[1];
		String cateNameLoc = "//span[text()='"+number+"']/parent::div[contains(@class,'navdocsstatus')]/following-sibling::div//div[text()='"+name+"']";
		selectCatByNameandNumber = By.xpath(cateNameLoc);
		JSExecutor.scrolltoView(selectCatByNameandNumber);
		clickUsinBy(selectCatByNameandNumber, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public String validateFileNameFormat(String fieldNames,String seperators, String dateFormat) throws InterruptedException, AWTException {


		fieldNames= fieldNames.replace("[", "").replace("]", "");
		seperators = seperators.replace("[", "").replace("]", "");

		String[] fldNameArray = fieldNames.split(",");
		String[] sprtrArray =seperators.split(",");

		String expDocName = "",fldData;

		String fldNameFromDb,fldName ;
		String fldSeperatorfromDb,fldSeperator;
		String fildNameLoc; 
		try {
			for(int i=0;i<fldNameArray.length;i++) {
				fldNameFromDb = fldNameArray[i].trim();
				fldSeperatorfromDb = sprtrArray[i].trim();
				fldName= fldNameFromDb.trim().replace("\"", "");
				fldSeperator = fldSeperatorfromDb.replace("\"", "");
				By getFldValue = null;

				if(fldName.equals("Sender")||fldName.equals("Doctor")) {
					fildNameLoc = "//font[text()='"+fldName+"']/parent::div/following-sibling::div/font//div/input";
					getFldValue = By.xpath(fildNameLoc);
					fldData = getAttribute(getFldValue, ExplicitWaitExpextecConditions.PRESENSCE, "value");
					if(!fldData.isEmpty()) {
						expDocName=expDocName+fldData;
						expDocName=expDocName+fldSeperator;
					}else {
						JSExecutor.scrolltoView(getFldValue);
						String setTextLoc = "//font[text()='"+fldName+"']/parent::div/following-sibling::div/font//div/input";
						By enterText = By.xpath(setTextLoc);
						JSExecutor.setAttributValue(enterText, fldName);
						clickUsinBy(enterText, ExplicitWaitExpextecConditions.NONE);
						setAttributValue();
						clickUsinBy(extractedFieldsLabel, ExplicitWaitExpextecConditions.PRESENSCE);
						fldData = getAttribute(enterText, ExplicitWaitExpextecConditions.PRESENSCE, "value");
						expDocName=expDocName+fldData;
						expDocName=expDocName+fldSeperator;
					}

				}else if (fldName.equals("Document Date")||fldName.equals("Event Date")){
						fildNameLoc = "//font[text()='"+fldName+"']/parent::div/following-sibling::div/font";
						getFldValue = By.xpath(fildNameLoc);
						
						String setTextLoc = "//font[text()='"+fldName+"']/parent::div/following-sibling::div[1]";
						By enterText = By.xpath(setTextLoc);
						try {
						enterText(enterText, "12-13-2022", ExplicitWaitExpextecConditions.PRESENSCE);
						String date = getText(enterText, ExplicitWaitExpextecConditions.PRESENSCE);
						clickUsinBy(extractedFieldsLabel, ExplicitWaitExpextecConditions.PRESENSCE);
						fldData= convertDateFormat(date,dateFormat);
						expDocName=expDocName+fldData;
						expDocName=expDocName+fldSeperator;
					
						}catch(StaleElementReferenceException se) {
							enterText(enterText, "12-13-2022", ExplicitWaitExpextecConditions.PRESENSCE);
							String date = getText(enterText, ExplicitWaitExpextecConditions.PRESENSCE);
							clickUsinBy(extractedFieldsLabel, ExplicitWaitExpextecConditions.PRESENSCE);
							fldData= convertDateFormat(date,dateFormat);
							expDocName=expDocName+fldData;
							expDocName=expDocName+fldSeperator;
						}

				}else if(fldName.equals("Document Type")){
					By getDocType = By.xpath("//font[text()='Document Type']/parent::div/following-sibling::div//div[contains(@class,'placeholder')]");
					fldData = getText(getDocType, ExplicitWaitExpextecConditions.PRESENSCE);
					if(!fldData.isEmpty()) {
						expDocName=expDocName+fldData;
						expDocName=expDocName+fldSeperator;
					}else {
						JSExecutor.scrolltoView(getFldValue);
						String setTextLoc = "//font[text()='"+fldName+"']/parent::div/following-sibling::div[1]";
						By enterText = By.xpath(setTextLoc);
						enterText(enterText, fldName.trim(), ExplicitWaitExpextecConditions.PRESENSCE);
						fldData = getText(getFldValue, ExplicitWaitExpextecConditions.PRESENSCE);
						expDocName=expDocName+fldData;
						expDocName=expDocName+fldSeperator;
					}

				}else{
					fildNameLoc = "//font[text()='"+fldName+"']/parent::div/following-sibling::div/font";
					getFldValue = By.xpath(fildNameLoc);
					fldData = getText(getFldValue, ExplicitWaitExpextecConditions.PRESENSCE);
					if(!fldData.isEmpty()) {
						expDocName=expDocName+fldData;
						expDocName=expDocName+fldSeperator;
					}else {
						JSExecutor.scrolltoView(getFldValue);
						String setTextLoc = "//font[text()='"+fldName+"']/parent::div/following-sibling::div[1]";
						By enterText = By.xpath(setTextLoc);
						enterText(enterText, fldName, ExplicitWaitExpextecConditions.CLICKABLE);
						fldData = getText(enterText, ExplicitWaitExpextecConditions.PRESENSCE);
						expDocName=expDocName+fldData;
						expDocName=expDocName+fldSeperator;
					}
				}

			}
			clickUsinBy(extractedFieldsLabel, ExplicitWaitExpextecConditions.PRESENSCE);
			return expDocName;
		}
		finally {
			seperators=null;
			fieldNames=null;
			fldNameArray = null;
			sprtrArray = null;
			expDocName = null;
			fldName= null;
			fldSeperator = null;
			fldNameFromDb= null;
			fldSeperatorfromDb = null;
		}

	}
	public static String convertDateFormat(String inputDate, String inputFormat) {
		String expDate;

		Date date = DateParserUtils.parseDate(inputDate);
		SimpleDateFormat last_date_date = new SimpleDateFormat(inputFormat);
		expDate = last_date_date.format(date);
		return expDate;


	}
	public ExtractedFields selectCategoryByNumber(String catNumber) throws InterruptedException {

		String cateNameLoc = "//span[text()='"+catNumber+"']/parent::div[contains(@class,'navdocsstatus')]";
		By selectCatByNumber = By.xpath(cateNameLoc);
		JSExecutor.scrolltoView(selectCatByNumber);
		clickUsinBy(selectCatByNumber, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;

	}
	public ExtractedFields validateDocumentName(){
		By docList = By.xpath("//div[contains(@class,'navdocsstatus')]");

		try {
			List<Map<String,String>> fieldNameFormatDetails = DbOperations.getFileNameFormat(ReadProperties.get(PropertyEnums.CLIENTNAME));
			List<WebElement> documents = findElements(docList);
			for(WebElement document:documents) {

				document.click();
				WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
				int j= documents.indexOf(document);

				String dbCatName=fieldNameFormatDetails.get(j).get("category_name");
				String dbDocType = fieldNameFormatDetails.get(j).get("customer_subcategory");
				String nameFields = fieldNameFormatDetails.get(j).get("fileNameFields");
				String seperators = fieldNameFormatDetails.get(j).get("seperators");
				FrameworkLogger.logInfo("Start of validateFileNameFormat for the document no "+j+" with name "+dbCatName+" and document type is "+dbDocType);
				FrameworkLogger.logInfo("Expected fields in the doc name are "+nameFields+" followed by seperators "+seperators);
				new ExtractedFields()
				.setDocType(dbCatName, dbDocType);

				String expDocName=validateFileNameFormat(nameFields, seperators,ReadProperties.get(PropertyEnums.DATEFORMAT));
				String actualDocName = getDocumentName();
				if(actualDocName.equals(expDocName)) {
					FrameworkLogger.logInfo("Documet name "+actualDocName+" for the document type "+dbDocType+" is as expected "+expDocName);
					ExtentLogger.pass("Documet name "+actualDocName+" for the document type "+dbDocType+" is as expected ", true);
				}else {
					FrameworkLogger.logInfo("Documet name "+actualDocName+" for the document type "+dbDocType+" is not as expected "+expDocName);	
					ExtentLogger.fail("Documet name "+actualDocName+" for the document type "+dbDocType+" is not as expected ", true);
				}

				FrameworkLogger.logInfo("End of validateFileNameFormat for the document no "+j);	
			}

		}catch(SQLException se) {
			se.printStackTrace();
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}catch(AWTException ae) {
			ae.printStackTrace();
		}
		return this;
	}
	
	public ExtractedFields setDocType(String expDocType, String expDocSubcat) throws InterruptedException {

		String specificCategory = "//div[@class='main_container']//ul//li[contains(@class,'main-items')]/button/div[text()='"+expDocType+"']";

		selectDocCatType= By.xpath(specificCategory);
		FrameworkLogger.logInfo("Document category xpath is "+selectDocCatType);
		String specificSubCategory = "//div[@class='main_container']//div[@class='left_side']/ul/li/button[text()='"+expDocSubcat+"']";
		selectDocSubCatType = By.xpath(specificSubCategory);
		FrameworkLogger.logInfo("Document type xpath is "+selectDocCatType);
		By leftSideMenu = By.xpath("//div[@class='main_container']/div[@class='left_side']");
		String actualDocType = getText(docTypeDropDownLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDocType.isEmpty()||!(actualDocType.equals(expDocType))) {
			
			JSExecutor.scrolltoView(docTypeDropDownLoc);
			ActionsUtil.moveToEleAndClick(docTypeDropDownLoc);
			ActionsUtil.moveToElement(selectDocCatType);
			//JSExecutor.scrolltoView(selectDocCatType);
			Thread.sleep(300);
			ActionsUtil.moveToElement(selectDocCatType);
			//JSExecutor.scrolltoView(selectDocCatType);
			Thread.sleep(300);
			JSExecutor.mouseHover(selectDocCatType);
			Thread.sleep(500);
			ExplicitWaitConditions.performExplicitWait(leftSideMenu, ExplicitWaitExpextecConditions.VISIBILE);
			JSExecutor.mouseHover(leftSideMenu);
			JSExecutor.scrolltoView(selectDocSubCatType);
			Thread.sleep(300);
			JSExecutor.mouseHover(selectDocSubCatType);
			clickUsingJS(selectDocSubCatType, ExplicitWaitExpextecConditions.PRESENSCE, false);
			//JSExecutor.scrolltoView(docNameLoc);
		}
		return this;

	}
	public static void setAttributValue() throws AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);

	}
	public ExtractedFields validateFieldOrder(String docCategory, String docTypeList)  {
		String[] categoryNames = Helper.splitInput(docCategory);
		String[] subCatName = Helper.splitInput(docTypeList);
		String catName;
		String docType;
		try {
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
					}
				}
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		catch(InterruptedException ie) {
			ie.printStackTrace();
		}finally {
			categoryNames=null;
			subCatName=null;

		}
		return this;
	}
	public ExtractedFields verifyExtractionsKeyValue() {


		return this;
	}
}
