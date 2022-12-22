package org.fai.pages.ManageDocuments;

import java.awt.AWTException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.fai.driver.DriverManager;
import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.exceptions.FrameworkException;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.base.BasePage;
import org.fai.pages.extactions.Extraction;
import org.fai.reports.ExtentLogger;
import org.fai.utils.ActionsUtil;
import org.fai.utils.JSExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageDocuments extends BasePage{


	private By title = By.xpath("//h1[contains(@class,'main-head mb-30')]");
	private By mngDocFileName = By.className("mngdocsfile_title");
	private By notes = By.xpath("//button[@class='fai__buttons faibtn__plain pr-3 b-r']//img[@class='img-responsive mr-2']");
	private By enterNotes = By.xpath("//textarea");
	private By closeNotes = By.xpath("//button[text()='X']");
	private By save = By.xpath("//button[@class='fai__buttons faibtn__plain pr-4']");
	private By splitDoc = By.xpath("//button[text()='Split Document']");
	private By getNewDocCateName = By.xpath("//span[@class='splittedDoc_new']/following-sibling::div");
	private By getNewCatNumber = By.xpath("//span[@class='splittedDoc_new']/ancestor::div//a[@class='mdcn-active']//div[@class='navdocsstatus_num_icon navdoc_notvalidated']");
	private By goBackToPrevCatgr = By.xpath("//div[text()=' Go back to the previous category']");
	private By gotoExtractions = By.xpath("//button[normalize-space()='Go to Extractions']");
	private By trash = By.xpath("//a[normalize-space()='Trash']");
	private By selectSpecificPage;
	private By pageCategory = By.xpath("//div[@class='docviewer_doc_name_txt']");
	private By zoomin = By.xpath("//button[@title='Zoom In']//img");
	private By zoomOut = By.xpath("//button[@title='Zoom Out']//img");
	private By reset = By.xpath("//button[@title='Reset']//img");
	private By rotateLeft = By.xpath("//a[@id='previewimage_RotateLeft']//img");
	private By rotateRight = By.xpath("//a[@id='previewimage_RotateRight']//img");
	private By moveOrCopyFromCategory;
	private By chkBoxForCatgory = By.xpath("//div[contains(text(), '. Claims')]/ancestor::div[@class='mngDocsCats__selectbox_search']/following-sibling::label/input");
	private By closePage = By.xpath("//button[@class='sidebarCollapse fai__buttons faibtn__dgrey']");
	private By renameFile = By.xpath("//button/img[@class='img-responsive']");
	private By renameInput = By.xpath("//input[@id='file_name']");
	private By moveToCategoryChkBox = By.xpath("//div[@class='col-md-6 col-sm-12 form-group']//span[@class='text']/parent::span/parent::label/input");
	private By moveat ;
	private By movePage = By.xpath("//button[@id='submit-button-create_user']");
	private By copyPage = By.xpath("//button[@id='submit-button-copy']");
	private By cancel = By.xpath("//button[@id='addcatCollapse']");
	private By lastPageNo= By.xpath("//div[contains(text(),'Trash')]/preceding::ul[contains(@class,'cf-manage-docslist mt-2 fbox')]/ul/li[last()]");
	private By selectCatByName ; 
	private By categoryLeftPane = By.xpath("//ul[@class='section-nav']/li/a/div");
	private By categoryNoLeftPane = By.xpath("//ul[@class='section-nav']/li/a//div[contains(@class,'navdocsstatus_num_icon navdoc')]");
	private By selectCategoryName; //div[@class='managedocscat_nav']/parent::div/following-sibling::div//div[@class='mb-4 cf-manage-docs selectedCategory']//div[@class=' css-2b097c-container']//div[contains(text(),'1')];
	private By categorywisePages = By.xpath("//div[@class='managedocscat_nav']/parent::div/following-sibling::div//div[@class='mb-4 cf-manage-docs selectedCategory']//ul/ul/li");
	private By selectCategoryToMove = By.xpath("//div[@class='col-md-6 col-sm-12 form-group']/label//span[@class='text']") ;
	private By extracteFields = By.xpath("//div[@class='extractresults_wrap']");
	private By closeDocViewer = By.xpath("//div[contains(@class,'pagenclose')]//button");
	private By addLabel = By.xpath("//div[@class='mdcl__addlabel_wrap']");
	private By enterLabel = By.xpath("//div[@class='mdcl__addlabel_wrap']//div//input");
	private By saveLabel = By.xpath("//div[@class='mdcl__addlabel_wrap']//div/following-sibling::button[contains(@class,'blue')]");
	private By getLabel = By.xpath("//div[@class='mdcl__addlabel_wrap']//h5");
	private By dragAndDropTo;
	private By alert = By.xpath("//div[@class='Toastify__toast-body']");
	private By documentsDetails = By.xpath("//div[@class='fai_wrapper']");
	private By docViewerPane = By.xpath("docpreview_image docpreviewimage_active");
	public String getDocFileName() {

		String docFileName =getText(mngDocFileName, ExplicitWaitExpextecConditions.VISIBILE);
		return docFileName;

	}
	public String getPageHeading() {

		String pageHeading = getText(title, ExplicitWaitExpextecConditions.PRESENSCE);
		return pageHeading;
	}
	public String getFileName() {
		String fileName= getText(mngDocFileName, ExplicitWaitExpextecConditions.PRESENSCE);
		return fileName;

	}
	public void waitForFileName() {

		ExplicitWaitConditions.performExplicitWait(mngDocFileName, ExplicitWaitExpextecConditions.VISIBILE);

	}
	public ManageDocuments clickNotes() {
		clickUsinBy(notes, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public ManageDocuments enterNotes(String notes) {
		enterText(enterNotes, notes, ExplicitWaitExpextecConditions.VISIBILE);
		return this;
	}
	public ManageDocuments closeNotes() {
		clickUsinBy(closeNotes, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments saveChanges() {
		clickUsinBy(save, ExplicitWaitExpextecConditions.PRESENSCE);
		waitForPageload();
		return this;
	}
	public ManageDocuments clickSplitDocument() {
		clickUsinBy(splitDoc,ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public Extraction gotoExtractions() {
		waitForPageload();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickUsingJS(gotoExtractions,ExplicitWaitExpextecConditions.CLICKABLE,true);
		ExplicitWaitConditions.performExplicitWait(extracteFields, ExplicitWaitExpextecConditions.PRESENSCE);
		return new Extraction();
	}
	public ManageDocuments renameFile(String fileName) {
		clickUsinBy(renameFile, ExplicitWaitExpextecConditions.PRESENSCE);
		enterText(renameInput, fileName, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments gotoTrash() {

		clickUsinBy(trash,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments selectPage(String pageNumber) {
		ExplicitWaitConditions.performExplicitWait(save, ExplicitWaitExpextecConditions.CLICKABLE);
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
		List<WebElement> categoryList = DriverManager.getDriver().findElements(categoryLeftPane);
		boolean isSelected = false;
		for(WebElement ele:categoryList) {
			try {
				JSExecutor.click(ele);
				Thread.sleep(200);
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
				List<WebElement> categoryPagesList = DriverManager.getDriver().findElements(categorywisePages);
				for(WebElement page:categoryPagesList) {
					Thread.sleep(500);
					String attValue = page.getAttribute("data-id");
					if(attValue.equals(pageNumber)){
						String pageLoc= "//div[@class='docImg' and @id='"+pageNumber+"']";
						selectSpecificPage = By.xpath(pageLoc);
						clickUsinBy(selectSpecificPage, ExplicitWaitExpextecConditions.CLICKABLE);
						isSelected=true;
					}
					if(isSelected)
						break;

				}
			}catch(StaleElementReferenceException ex) {

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isSelected)
				break;
		}

		return this;

	}
	public String getPageCategory() {

		String pageCategoryName = getTextJS(pageCategory,ExplicitWaitExpextecConditions.PRESENSCE);
		return pageCategoryName;
	}
	public ManageDocuments selectCatByName(String inputCatName) throws InterruptedException {
		String[] arrayOfNames= inputCatName.split(",");
		int noofCategories = arrayOfNames.length;
		if(noofCategories>1) {
			List<String> namesList = new ArrayList <>();
			try {
				for(String name:arrayOfNames) {
					namesList.add(name);

				}
				List<String> newList = namesList.stream()
						.distinct()
						.collect(Collectors.toList());
				Iterator<String> iter = newList.iterator();

				while(iter.hasNext()) {
					String categoryName = iter.next();
					String catNameLoc=  "//ul[@class='section-nav']//li//a//div[text()='"+categoryName+"']";
					selectCatByName = By.xpath(catNameLoc);
					List<WebElement> elList = findElements(selectCatByName);
					int size= elList.size();
					if(size>1) {

						for(WebElement el:elList) {

							JSExecutor.scrolltoView(selectCatByName);
							ActionsUtil.moveToElement(selectCatByName);
							JSExecutor.click(el);
							WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
							wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));				
							ExtentLogger.pass("Clicked on"+catNameLoc+el, true);

						}


					}else {
						JSExecutor.scrolltoView(selectCatByName);
						ActionsUtil.moveToElement(selectCatByName);
						clickUsinBy(selectCatByName, ExplicitWaitExpextecConditions.PRESENSCE);
						ExtentLogger.pass("Clicked on"+selectCatByName, true);
					}
				}


			}catch(InterruptedException ie) {
				ie.printStackTrace();
			}finally {
				arrayOfNames=null;
			}
		}
		return this;
	}
	public ManageDocuments zoominPage() {

		clickUsinBy(zoomin,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments zoomOutPage() {

		clickUsinBy(zoomOut, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments resetChanges() {
		clickUsinBy(reset,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments rotateLeft() {
		String beforeRotationAngle = getRotationAngle(selectSpecificPage);
		ExtentLogger.pass("Rotations angle before rotate left "+beforeRotationAngle);
		clickUsinBy(rotateLeft, ExplicitWaitExpextecConditions.PRESENSCE);
		String afterRotationAngle = getRotationAngle(selectSpecificPage);
		ExtentLogger.pass("Rotations angle before rotate left "+afterRotationAngle);
		return this;
	}
	public ManageDocuments rotateRight() {
		String beforeRotationAngle = getRotationAngle(selectSpecificPage);
		ExtentLogger.pass("Rotations angle before rotate left "+beforeRotationAngle);
		clickUsinBy(rotateRight,ExplicitWaitExpextecConditions.PRESENSCE);
		String afterRotationAngle = getRotationAngle(selectSpecificPage);
		ExtentLogger.pass("Rotations angle before rotate left "+afterRotationAngle);
		return this;
	}
	public ManageDocuments selectPageToMove(String pageNumber)  {

		String[] pagenos= pageNumber.split(",");
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
		List<WebElement> categoryList = DriverManager.getDriver().findElements(categoryLeftPane);
		try {
			for(String pageno:pagenos) {
				boolean isSelected = false;
				String selectToMoveLoc = "//div[@class='docImg' and @id='"+pageno+"']/ancestor::div[@class='docImg']/following-sibling::div/child::div[2]/img";
				moveOrCopyFromCategory= By.xpath(selectToMoveLoc);
				for(WebElement ele:categoryList) {
					try {
						JSExecutor.click(ele);
						Thread.sleep(1200);
						wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
						List<WebElement> categoryPagesList = DriverManager.getDriver().findElements(categorywisePages);
						for(WebElement page:categoryPagesList) {
							Thread.sleep(1500);
							String attValue = page.getAttribute("data-id");
							System.out.println("page number is "+attValue);
							if(attValue.equals(pageno)){
								clickUsinBy(moveOrCopyFromCategory, ExplicitWaitExpextecConditions.CLICKABLE);
								isSelected=true;
								By selectedCat= By.xpath("//div[@class='item selected']");
								ExplicitWaitConditions.performExplicitWait(selectedCat, ExplicitWaitExpextecConditions.PRESENSCE);
								break;
							}
							if(isSelected)
								break;

						}
					}catch(StaleElementReferenceException ex) {

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(isSelected)
						break;
				}
			}
			Thread.sleep(2000);
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}finally {
			pagenos = null;
		}
		return this;

	}
	public ManageDocuments clicktoMove() {
		clickUsinBy(movePage,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments selctCategory() {
		clickUsinBy(chkBoxForCatgory, ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public ManageDocuments selectCategoryToMoveORCopy() {

		clickUsingJS(moveToCategoryChkBox, ExplicitWaitExpextecConditions.NONE,true);
		return this;
	}
	public ManageDocuments selectPositionToMove(String positionToMove) {

		String moveToPosition = "//form//span[contains(text(),'"+positionToMove+"')]/parent::label/input";
		moveat = By.xpath(moveToPosition);
		clickUsingJS(moveat,ExplicitWaitExpextecConditions.NONE,true);
		return this;
	}
	public ManageDocuments clickOnMove() {
		clickUsinBy(movePage,ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public ManageDocuments clickOnCopy() {
		clickUsinBy(copyPage, ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public ManageDocuments clickOnClose() {
		clickUsinBy(closePage,ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public ManageDocuments cancelSelection() {
		clickUsinBy(cancel,ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public String getCountOfPages() throws InterruptedException {
		List<WebElement> lastPage= findElements(lastPageNo);
		int elemntCount=lastPage.size();
		while(elemntCount==0) {
			JSExecutor.scrollPage();
			lastPage= findElements(lastPageNo);
			elemntCount=lastPage.size();
		}

		String no = getAttribute(lastPageNo, ExplicitWaitExpextecConditions.VISIBILE,"data-id");
		return no;
	}
	public ManageDocuments splitDocument(String splitThreshold) {

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
		List<WebElement> categoryList = DriverManager.getDriver().findElements(categoryLeftPane);
		boolean isSelected = false;
		for(WebElement ele:categoryList) {
			try {

				JSExecutor.click(ele);
				Thread.sleep(1500);
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
				List<WebElement> categoryPagesList = DriverManager.getDriver().findElements(categorywisePages);
				int noOfPages = categoryPagesList.size();

				if(noOfPages>Integer.parseInt(splitThreshold)) {
					int count =0;
					for(WebElement individualpages:categoryPagesList) {
						WebElement page=individualpages;
						Thread.sleep(2000);
						count++;
						if(count>Integer.parseInt(splitThreshold)){

							((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", page);
							String text= page.getText();
							System.out.println(text);
							Thread.sleep(2000);
							ActionsUtil.scrollUP(page);
							Thread.sleep(2000);
							page.click();
							clickUsingJS(splitDoc,ExplicitWaitExpextecConditions.PRESENSCE,true);
							isSelected=true;
						}

						if(isSelected)
							break;

					}
				}
			}catch(StaleElementReferenceException ex) {

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isSelected)
				clickUsingJS(splitDoc,ExplicitWaitExpextecConditions.PRESENSCE,true);
			break;
		}

		clickUsingJS(splitDoc,ExplicitWaitExpextecConditions.PRESENSCE,true);
		ExplicitWaitConditions.performExplicitWait(getNewDocCateName, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;

	}

	public void splitDocumentIssue(String splitThreshold) throws InterruptedException {

		Thread.sleep(1500);
		List<WebElement> categoryPagesList = DriverManager.getDriver().findElements(categorywisePages);
		
		for(int j=1;j<=categoryPagesList.size();j++) {
				String pageLoc= "//div[@class='docImg' and @id='"+j+"']";
				selectSpecificPage = By.xpath(pageLoc);
				try {
				ActionsUtil.moveToEleAndClick(selectSpecificPage);
				JSExecutor.scrolltoView(selectSpecificPage);
				clickUsingJS(splitDoc,ExplicitWaitExpextecConditions.PRESENSCE,true);
				ExplicitWaitConditions.performExplicitWait(getNewDocCateName, ExplicitWaitExpextecConditions.PRESENSCE);
				}catch(StaleElementReferenceException se) {
					ActionsUtil.moveToEleAndClick(selectSpecificPage);
					JSExecutor.scrolltoView(selectSpecificPage);
					clickUsingJS(splitDoc,ExplicitWaitExpextecConditions.PRESENSCE,true);
					Thread.sleep(500);
					//ExplicitWaitConditions.performExplicitWait(getNewDocCateName, ExplicitWaitExpextecConditions.PRESENSCE);
					
					
				}
			}
	}




	public String getNewDocCategoryName() {
		String newDocCatNumber = getText(getNewCatNumber, ExplicitWaitExpextecConditions.VISIBILE);
		String newDocCateoryName = getText(getNewDocCateName, ExplicitWaitExpextecConditions.VISIBILE);
		return newDocCatNumber+"."+newDocCateoryName;
	}
	public ManageDocuments goBackToPreviousCategory() {
		clickUsinBy(goBackToPrevCatgr, ExplicitWaitExpextecConditions.VISIBILE);
		return this;
	}
	public ManageDocuments selectCategoryTOMovePage(String categoryNo) {

		String[] categories =categoryNo.split(",");
		try {
			for(String catno:categories) {
				List<WebElement> list = findElements(selectCategoryToMove);
				int totalCategories = list.size();
				if(totalCategories>=Integer.parseInt(catno)) {
					for(WebElement el:list) {
						try {
							String getCatNo = el.getText();
							if(getCatNo.startsWith(catno)) {
								JSExecutor.scrolltoView(selectCategoryToMove);
								el.click();
								Thread.sleep(1000);
								break;
							}
						}catch(NoSuchElementException ne) {

						}
					}
				}else {
					throw new FrameworkException("Category Number provided is more than the categories available");
				}
			}
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}finally {
			categories= null;
		}
		return this;
	}
	public ManageDocuments changeTheCategoryName(String categoryno ,String categoryName) throws AWTException, InterruptedException {

		String catNameLoc = "//div[contains(text(),'"+categoryno+".')]";
		selectCategoryName= By.xpath(catNameLoc);
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryNoLeftPane)));
		List<WebElement> categoryList = DriverManager.getDriver().findElements(categoryNoLeftPane);
		boolean isSelected =false;
		for(WebElement category:categoryList) {
			category.click();
			String changeNameOfCategoryNo = category.getText();
			if(changeNameOfCategoryNo.equals(categoryno)) {
				ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(selectCategoryName));
				ExplicitWaitConditions.performExplicitWait(selectCategoryName, ExplicitWaitExpextecConditions.CLICKABLE);
				JSExecutor.scrolltoView(selectCategoryName);
				Thread.sleep(2000);
				ActionsUtil.scrollUP(selectCategoryName);
				Thread.sleep(2000);
				ActionsUtil.moveToEleAndClick(selectCategoryName);
				Thread.sleep(2000);
				ActionsUtil.sendText(selectCategoryName, categoryName);
				ActionsUtil.clickEnter();
				isSelected=true;
			}
			if(isSelected)
				break;
		}

		return this;
	}
	public String getRotationAngle(By by) {
		String styleAttribute= getAttribute(by, ExplicitWaitExpextecConditions.PRESENSCE, "style");
		String[] styleAtts = styleAttribute.split(";");
		String rotationAngle= styleAtts[1];
		return rotationAngle;
	}

	public ManageDocuments addLabel(String label) {
		clickUsinBy(addLabel, ExplicitWaitExpextecConditions.CLICKABLE);
		enterText(enterLabel, label, ExplicitWaitExpextecConditions.CLICKABLE);
		clickUsinBy(saveLabel, ExplicitWaitExpextecConditions.CLICKABLE);

		return this;
	}
	public String getLabel() {
		String label = getText(getLabel, ExplicitWaitExpextecConditions.VISIBILE);

		return label;
	}
	public String getNotes() {
		String notes = getText(enterNotes, ExplicitWaitExpextecConditions.VISIBILE);

		return notes;
	}

	public ManageDocuments dragAndDrop(String fromloca, String moveTo, String direction) throws InterruptedException, AWTException {
		Thread.sleep(5000);
		String fromLoc = "//div[@class='docImg' and @id='"+fromloca+"']";
		By fromLocation = By.xpath(fromLoc);


		WebElement from = DriverManager.getDriver().findElement(fromLocation);

		String toLoc= "//div[@class='docImg' and @id='"+moveTo+"']";
		dragAndDropTo = By.xpath(toLoc);
		WebElement to = DriverManager.getDriver().findElement(dragAndDropTo);

		Actions builder1=new Actions(DriverManager.getDriver());

		int[] fromxypoints = getCoordinates(from);
		int sourceX = fromxypoints[0];
		int sourceY = fromxypoints[1];
		System.out.println("Source co ordinates are"+sourceX+" "+sourceY);
		int[] toxypoints = getCoordinates(to);
		int destx= toxypoints[0];
		int desty=toxypoints[1];
		System.out.println("Dest co ordinates are"+destx+" "+desty);
		System.out.println("X and Y axis to move are"+ (destx-sourceX)+"   "+(desty-sourceY));
		//int xFractionsOffset = (destx-sourceX)/5;
		System.out.println("Dest co ordinates are"+destx+" "+desty);
		System.out.println("X and Y axis to move are"+ (destx-sourceX)+"   "+(desty-sourceY));
		boolean isMovedto = false;
		while(isMovedto==false) {
			try {
				builder1.moveToElement(from)
				.pause(Duration.ofSeconds(1))
				.clickAndHold(from)
				.pause(Duration.ofSeconds(1))
				.moveByOffset(destx-sourceX, desty-sourceY)
				.pause(Duration.ofSeconds(2))
				.moveToElement(to).perform();
				builder1.release().perform();
				isMovedto= true;
			}catch(NoSuchElementException ne) {
				isMovedto=false;
			}
		}

		
		ExtentLogger.pass("draga and drop pass",true);
		return this;
	}

	public ManageDocuments closeViewer() {
		clickUsinBy(closeDocViewer, ExplicitWaitExpextecConditions.CLICKABLE);

		return this;
	}
	public void waitForManageDocumentsLoad() {
		ExplicitWaitConditions.performExplicitWait(gotoExtractions, ExplicitWaitExpextecConditions.CLICKABLE);

	}
	public static void DragAndDropJS(WebElement source, WebElement destination) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("function createEvent(typeOfEvent) {\n" +"var event =document.createEvent(\"CustomEvent\");\n" +"event.initCustomEvent(typeOfEvent,true, true, null);\n" +"event.dataTransfer = {\n" +"data: {},\n" +"setData: function (key, value) {\n" +"this.data[key] = value;\n" +"},\n" +"getData: function (key) {\n" +"return this.data[key];\n" +"}\n" +"};\n" +"return event;\n" +"}\n" +"\n" +"function dispatchEvent(element, event,transferData) {\n" +"if (transferData !== undefined) {\n" +"event.dataTransfer = transferData;\n" +"}\n" +"if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n" +"} else if (element.fireEvent) {\n" +"element.fireEvent(\"on\" + event.type, event);\n" +"}\n" +"}\n" +"\n" +"function simulateHTML5DragAndDrop(element, destination) {\n" +"var dragStartEvent =createEvent('dragstart');\n" +"dispatchEvent(element, dragStartEvent);\n" +"var dropEvent = createEvent('drop');\n" +"dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n" +"var dragEndEvent = createEvent('dragend');\n" +"dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" +"}\n" +"\n" +"var source = arguments[0];\n" +"var destination = arguments[1];\n" +"simulateHTML5DragAndDrop(source,destination);",source, destination);
		Thread.sleep(1500);

	}

	public int[] getCoordinates(WebElement element) throws InterruptedException {

		ExplicitWaitConditions.invisbileOfElement(docViewerPane, null);
		Point elePoint = element.getLocation();
		int x = elePoint.getX();
		int y = elePoint.getY();
		int[] coordinates = new int[2];
		coordinates[0]=x;
		coordinates[1] =y;
		return coordinates;

	}
	public ManageDocuments dragdrop(String selectpage,String droptopage) throws InterruptedException {
		ExplicitWaitConditions.performExplicitWait(addLabel, ExplicitWaitExpextecConditions.CLICKABLE);
		boolean isMoved=true;
		By dropto;
		while(isMoved) {
				String selectpageLoc= "//div[@class='docImg' and @id='"+selectpage+"']";
				String droptoPageLoc = "//div[@class='docImg' and @id='"+droptopage+"']";
				selectSpecificPage = By.xpath(selectpageLoc);
				dropto = By.xpath(droptoPageLoc);
				WebElement from = DriverManager.getDriver().findElement(selectSpecificPage);
				WebElement to = DriverManager.getDriver().findElement(dropto);
				try {
				ActionsUtil.moveToEleAndClick(selectSpecificPage);
				JSExecutor.scrolltoView(selectSpecificPage);
				Actions builder1=new Actions(DriverManager.getDriver());
				builder1		.dragAndDrop(from,to).perform();
								
								isMoved=false;
				}catch(StaleElementReferenceException se) {
					ActionsUtil.moveToEleAndClick(selectSpecificPage);
					JSExecutor.scrolltoView(selectSpecificPage);
					clickUsingJS(splitDoc,ExplicitWaitExpextecConditions.PRESENSCE,true);
					Thread.sleep(500);
					//ExplicitWaitConditions.performExplicitWait(getNewDocCateName, ExplicitWaitExpextecConditions.PRESENSCE);
					isMoved=false;
					
				}catch(ElementNotInteractableException el) {
					
				}
			}
		
		return this;
	}

}
