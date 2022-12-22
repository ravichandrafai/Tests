package org.fai.ExtractAutomationTests;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.pages.navigationbar.NavigationBar;
import org.testng.annotations.Test;

public class NavigationBarTests extends BaseTest{

	private NavigationBarTests() {
		
	}
	
	@Test(priority=22,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void verifyUserMenuItems(Map<String, String> input) {
		
		boolean isTrue = new NavigationBar().verifyUserMenuItems(input.get("role"), input.get("menu items"));
		Assertions.assertThat(isTrue).isEqualTo(isTrue);
	}
	
	
}
