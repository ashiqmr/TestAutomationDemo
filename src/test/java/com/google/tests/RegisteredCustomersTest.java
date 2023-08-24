package com.google.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.pages.LoginPage;
import com.google.pages.MyAccountPage;
import com.google.pages.HomePage;

import config.DriverConfig;

@Listeners(listeners.TestNGListener.class)
public class RegisteredCustomersTest extends DriverConfig {
	public WebDriver driver;
	private Logger log = LogManager.getLogger(this.getClass());

	/**
	 * initializes driver
	 * 
	 */
	@BeforeTest
	public void setup() {
		driver = initializeDriver();
	}
	
	/**
	 * launches home page URL
	 * 
	 */
	@BeforeMethod
	public void launchURL() {
		String url = getUrl("url");
		log.info("Launching URL: " + url);
		driver.get(url);
	}

	/**
	 * testNG data provider for Validate Login Test
	 * 
	 */
	@DataProvider
	public Object[][] validateLoginData() {
		return new Object[][] { { "Customer Login", "Test1 Automation" } };
	}

	/**
	 * Validate Login Test to verify is a user with valid credentials is able to login
	 * 
	 */
	@Test(dataProvider = "validateLoginData")
	public void validateLogin(String loginPageHeader, String homePageGreeting){
		HomePage homePage = new HomePage(driver);
		homePage.clickSignInButton();
		
		LoginPage loginPage = new LoginPage(driver);
		String loginPageHeaderData = loginPage.getPageHeader();
		
		Assert.assertTrue(loginPageHeaderData.equalsIgnoreCase(loginPageHeader), "Actual: " + loginPageHeaderData);
		
		loginPage.login(getUsername("username"), getPassword("password"));
		
		homePageGreeting = "Welcome, " + homePageGreeting + "!";
		String homePageGreetingData = homePage.getNavbarGreeting(); 
		
		Assert.assertTrue(homePageGreetingData.equals(homePageGreeting), "Actual: " + homePageGreetingData);
		
		loginPage.signOut();
		
	}

	/**
	 * testNG data provider for Validate Forgot Password Test
	 * 
	 */
	@DataProvider
	public Object[][] validateForgotPasswordData() {
		return new Object[][] { { "Forgot Your Password", "Test1 Automation" } };
	}
	
	/**
	 * Validate Forgot Password Test to verify that user is displayed
	 *  with proper message when clicking in Forgot Password Link
	 *  and giving valid email id.
	 */
	@Test(dataProvider = "validateForgotPasswordData")
	public void validateForgotPassword(String loginPageHeader, String homePageGreeting){
		HomePage homePage = new HomePage(driver);
		homePage.clickSignInButton();
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.clickForgotPassword();
		String loginPageHeaderData = loginPage.getPageHeader();
		loginPageHeader = loginPageHeader + "?";
		
		Assert.assertTrue(loginPageHeaderData.equalsIgnoreCase(loginPageHeader), "Actual: " + loginPageHeaderData);
		
		loginPage.inputForgotPasswordEmailInput(getUsername("username"));
		
		loginPage.clickResetMyPasswordButton();

		Assert.assertTrue(loginPage.getMessageText(getUsername("username")));
	}
	
	/**
	 * testNG data provider for Validate SignOut Test
	 * 
	 */
	@DataProvider
	public Object[][] validateSignOutData() {
		return new Object[][] { { "You are signed out" } };
	}

	/**
	 * Validate Sign Out for valid user credentials
	 */
	@Test(dataProvider = "validateSignOutData")
	public void validateSignOut(String signOutPageHeader){
		HomePage homePage = new HomePage(driver);
		homePage.clickSignInButton();
		
		LoginPage loginPage = new LoginPage(driver);		
		loginPage.login(getUsername("username"), getPassword("password"));
				
		loginPage.signOut();
		String signOutPageHeaderData = loginPage.getPageHeader();
		
		Assert.assertTrue(signOutPageHeaderData.equals(signOutPageHeader), "Actual: " + signOutPageHeader);
	}
	
	
	/**
	 * closes driver
	 */
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			log.debug("End of Test. Closing driver" + System.lineSeparator());
			driver.close();
		}
	}

}
