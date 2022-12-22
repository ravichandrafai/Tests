package org.fai.pages.login;

import java.util.List;

import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.exceptions.FrameworkException;
import org.fai.pages.base.BasePage;
import org.fai.pages.navigationbar.NavigationBar;
import org.fai.reports.ExtentLogger;
import org.fai.utils.DecodeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Login extends BasePage{

	
	private By btnRefresh = By.xpath("//button[text()='Refresh']");
	private By inputUserName = By.name("email");
	private By inputPassword = By.name("password");
	private By btnLogin = By.xpath("//button[@type='submit' and text()='Login']");
	private By alertMsg = By.xpath("//div[@class='Toastify__toast-body']");
	
	
	public Login refreshOnPageLoad() {
		
		clickUsinBy(btnRefresh, ExplicitWaitExpextecConditions.CLICKABLE);		
			
		return this;
		
	}
	
	public Login enterUserName(String userName) {
		enterText(inputUserName, userName , ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	
	public Login enterPassword(String password) {
		String decryptedPwd = DecodeUtils.getDecodedString(password);
		enterText(inputPassword, decryptedPwd,ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	
	public NavigationBar clickLogin() {
		clickUsingJS(btnLogin, ExplicitWaitExpextecConditions.CLICKABLE, true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		};
		List<WebElement> alerts = findElements(alertMsg);
		System.out.println("size is "+alerts.size());
		if(alerts.size()>0) {
			String alertMessage = getText(alertMsg, ExplicitWaitExpextecConditions.NONE);
			ExtentLogger.fail("Login failed with the reason"+alertMessage,true);
			throw new FrameworkException("Login Failed");
		}
		return new NavigationBar();
		
	}
}
