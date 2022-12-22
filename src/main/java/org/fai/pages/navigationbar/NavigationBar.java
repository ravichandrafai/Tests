package org.fai.pages.navigationbar;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.base.BasePage;
import org.fai.pages.dashboard.DashBoard;
import org.fai.pages.worklist.WorkList;
import org.fai.reports.ExtentLogger;
import org.fai.utils.ActionsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class NavigationBar extends BasePage{

		
	private By dashBoard_link = By.linkText("Dashboard");
	private By workList_Link = By.linkText("Work List");
	private By mappingInfo_Link = By.linkText("Mapping Info");
	private By admin_Link = By.linkText("Admin");
	private By userProfile_Link = By.xpath("//a[@class='profilelink_nav dropdown-toggle nav-link']");
	private By myAccount_Link = By.linkText("My Account");
	private By logout_Btn = By.xpath("//button[@type='button' and text()='Logout']");
	private By userMenuItems = By.xpath("//div[contains(@class,'navbar-collapse')]/ul/*[not(@class='fai__userlogoutitem dropdown nav-item')]");
	
	
	public void waitforDashBoard() {
		ExplicitWaitConditions.performExplicitWait(dashBoard_link, ExplicitWaitExpextecConditions.PRESENSCE);
	}
	
	public DashBoard gotoDashBorad() {
		clickUsinBy(dashBoard_link,ExplicitWaitExpextecConditions.CLICKABLE);
		waitForPageload();
		return new DashBoard();
	}
	public WorkList gotoWorkList() {
		try {
		waitforReload();
		clickUsinBy(workList_Link,ExplicitWaitExpextecConditions.CLICKABLE);
		waitForPageload();
		
		}catch(StaleElementReferenceException se) {
			clickUsinBy(workList_Link,ExplicitWaitExpextecConditions.PRESENSCE);
			waitForPageload();
			
		}
		return new WorkList();
	}
	
	public void gotoMappingInfo() {
		clickUsinBy(mappingInfo_Link,ExplicitWaitExpextecConditions.PRESENSCE);
		waitForPageload();
	}
	
	public void gotoAdmin() {
		clickUsinBy(admin_Link,ExplicitWaitExpextecConditions.PRESENSCE);
	}
	
	public NavigationBar gotoProfile() {
		ActionsUtil.mouseHover(userProfile_Link);
		return this;
	}
	public void gotMyAccount() {
		clickUsinBy(myAccount_Link,ExplicitWaitExpextecConditions.PRESENSCE);
	}
	public void logout() {
		clickUsinBy(logout_Btn,ExplicitWaitExpextecConditions.PRESENSCE);
	}
	public String getLoggedUserName(){
		ExplicitWaitConditions.performExplicitWait(workList_Link, ExplicitWaitExpextecConditions.VISIBILE);
		String loggedUser = getText(userProfile_Link, ExplicitWaitExpextecConditions.VISIBILE);
		return loggedUser;
	}
	
	public List<String> getUserMenuItems() {
		ExplicitWaitConditions.performExplicitWait(dashBoard_link, ExplicitWaitExpextecConditions.VISIBILE);
		List<WebElement> menuItems = findElements(userMenuItems);
		List<String> actualMenuItems = new ArrayList<String>();
		if(menuItems.size()>0) {
		for(WebElement element:menuItems) {
			actualMenuItems.add(element.getText());
			
		}
		}else {
			ExtentLogger.fail("Menu options are not displayed",true);
		}
		return actualMenuItems;
	}
	public boolean verifyUserMenuItems(String userRole,String expMenuItems) {
			
		String[] expMenus = expMenuItems.split(",");
		List<Object> expMenusList =Arrays.asList(expMenus);
		List<String> actualMenuList = getUserMenuItems();
		System.out.println(expMenusList+" "+actualMenuList);
		boolean isTrue=false;
		if(expMenusList.equals(actualMenuList)) {
			isTrue=true;
			ExtentLogger.pass("User has expected menu items available",true);
		}
		else {
			isTrue=false;
			ExtentLogger.fail("User expected menu items are not as expected",true);
		}
		return isTrue;
	}
}
