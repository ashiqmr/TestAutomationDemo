package com.google.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonFunctions;

public class LoginPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	CommonFunctions functions;
	private Logger log = LogManager.getLogger(this.getClass());

	@FindBy(xpath = "//h1[@class='page-title']//span")
	private WebElement pageHeader;
	
	@FindBy(id = "email")
	private WebElement emailInput;
	
	@FindBy(name = "login[password]")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//div[@class='login-container']//button[@id='send2']")
	private WebElement signInButton;

	@FindBy(xpath = "//a[@class='action remind']//span")
	private WebElement forgotPasswordLink;
	
	@FindBy(xpath = "//button[@class='action submit primary']")
	private WebElement resetMyPasswordButton;
	
	@FindBy(id = "email_address")
	private WebElement forgotPasswordEmailInput;
	
	@FindBy(xpath = "//header//button[@class='action switch']")
	private WebElement navBarDropdown;
	
	@FindBy(xpath = "//li[@class='customer-welcome active']//a[contains(.,'Sign Out')]")
	private WebElement signOutSelection;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		functions = new CommonFunctions(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * returns first search result
	 * 
	 * @return - String
	 */
	public String getPageHeader() {
		String text = null;
		if (functions.waitForElement(pageHeader)) {
			text = pageHeader.getText();
			log.info("Header found: " + text);
		}
		return text;
	}
	
	public void login(String username, String password) {
		inputUsername(username);
		inputPassword(password);
		
		if(functions.waitForElement(signInButton)) {
			signInButton.click();
		}
	}
	
	public void inputUsername(String username) {
		functions.inputText(emailInput, username);
	}
	
	public void inputPassword(String password) {
		functions.inputText(passwordInput, password);
	}
	
	public void inputForgotPasswordEmailInput(String email) {
		functions.inputText(forgotPasswordEmailInput, email);
	}
	
	public void clickForgotPassword() {
		if(functions.waitForElement(forgotPasswordLink)) {
			forgotPasswordLink.click();
		}
	}
	
	public String getForgotPassHeader() {
		String text = null;

		if (functions.waitForElement(pageHeader)) {
			text = pageHeader.getText();
			log.info("Header found: " + text);
		}
		return text;
	}
	
	public void clickResetMyPasswordButton() {
		if(functions.waitForElement(resetMyPasswordButton)) {
			resetMyPasswordButton.click();
		}
	}
	
	public boolean getMessageText(String email) {
		String xpath = "//div[contains(text(),'"+ email +"')]";
		if(functions.waitForElement(driver.findElement(By.xpath(xpath)))) {
			return true;
		}
		return false;
	}

	public void signOut() {
		if(functions.waitForElement(navBarDropdown)) {
			navBarDropdown.click();
			if(functions.waitForElement(signOutSelection)) {
				signOutSelection.click();
			}
		}
	}
	
}
