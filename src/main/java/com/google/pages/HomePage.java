package com.google.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.beust.jcommander.internal.Console;

import utilities.CommonFunctions;

public class HomePage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	CommonFunctions functions;
	private Logger log = LogManager.getLogger(this.getClass());

	@FindBy(xpath = "//header//a[contains(text(),'Sign In')]")
	private WebElement signInButton;
	
	@FindBy(xpath = "//div[@class='panel header']//li[@class='greet welcome']//span")
	private WebElement navBarGreeting;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		functions = new CommonFunctions(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * click Sign In button in Navbar
	 */
	
	public void clickSignInButton(){
		if(functions.waitForElement(signInButton)) {
			signInButton.click();			
		}	
	}

	public String getNavbarGreeting() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = null;
		if (functions.waitForElement(navBarGreeting)) {
			text = navBarGreeting.getText();
			log.info("Greeting found: " + text);
		}
		return text;
	}
}
