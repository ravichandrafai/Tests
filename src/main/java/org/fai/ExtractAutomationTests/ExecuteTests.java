package org.fai.ExtractAutomationTests;


import java.util.List;

import org.fai.driver.Driver;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class ExecuteTests {
	public static void main(String[] args) {
	    TestNG testng = new TestNG();
	    List<String> suites = Lists.newArrayList();
	    suites.add(System.getProperty("user.dir")+"/config/testng.xml");//path to xml..
	    testng.setTestSuites(suites);
	    testng.run();
	    Driver.quitDriver();
	}
}
