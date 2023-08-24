package com.google.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonFunctions;

public class MyAccountPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	CommonFunctions functions;
	private Logger log = LogManager.getLogger(this.getClass());
	
	@FindBy(className = "page-title")
	private WebElement MyAccountHeader;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		functions = new CommonFunctions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		String text = null;
		if (functions.waitForElement(MyAccountHeader)) {
			text = MyAccountHeader.getText();
			log.info("Header found: " + text);
		}
		return text;
	}
	
}
