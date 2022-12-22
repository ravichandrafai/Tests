package org.fai.ExtractAutomationTests;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.pages.login.Login;
import org.fai.pages.navigationbar.NavigationBar;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

	private LoginTests() {
		
	}
	
	@Test(priority=1,groups={"prerequisites"})
	public void appLogin(Map<String, String> input) throws InterruptedException {
		Thread.sleep(5000);
			String loggedUser = new Login().refreshOnPageLoad()
				.enterUserName(input.get("user name"))
				.enterPassword(input.get("password"))
				.clickLogin().getLoggedUserName();
		Assertions.assertThat(loggedUser).contains(input.get("profile"));

	}
	@Test(priority=30,groups={"prerequisites"})
	public void appLoginIncognito(Map<String, String> input) {
			String loggedUser = new Login().refreshOnPageLoad()
				.enterUserName(input.get("user name"))
				.enterPassword(input.get("password"))
				.clickLogin().getLoggedUserName();
		Assertions.assertThat(loggedUser).contains(input.get("profile"));

	}
	@Test(priority=31,groups={"smoke"})
	public void logout() {
		
		new NavigationBar().gotoProfile().logout();
		
		
	}
	
	
}
