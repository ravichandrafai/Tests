package org.fai.pages.extactions;

import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.pages.base.BasePage;
import org.openqa.selenium.By;

public class Navigate extends BasePage {
	
	
	private By previousCat = By.xpath("");
	private By nextCat = By.xpath("");
	 
	public Navigate gotoNextCategory() {
		clickUsinBy(previousCat,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public Navigate gotoPrevCategory() {
		clickUsinBy(nextCat,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	

}
