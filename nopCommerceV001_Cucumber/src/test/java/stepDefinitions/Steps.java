package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass
{
	@Before
	public void setup()throws IOException
	{
		logger=Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("Log4j.properties");
		configProp=new Properties();
		FileInputStream configPropfile=new FileInputStream("config.properties");
		configProp.load(configPropfile);
		String br=configProp.getProperty("browser");
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
			ChromeOptions options=new ChromeOptions();
			options.setBinary("E:\\Automation Training\\Required Files\\Chrome Browser 117\\chrome-win64\\chrome.exe");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("start-maximized");
			driver=new ChromeDriver(options);
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(br.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver",configProp.getProperty("edgepath"));
			EdgeOptions options=new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("start-maximized");
			driver=new EdgeDriver(options);
		}
		logger.info("********Launching browser********");
	}
	@Given("User Launch browser")
	public void user_Launch_browser()
	{
		lp=new LoginPage(driver);
	}
	@When("User opens URL {string}")
	public void user_opens_URL(String url)
	{
		logger.info("********Opening URL********");
	    driver.get(url);
	}
	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password)
	{
		logger.info("********Providing login details********");
	    lp.setUserName(email);
	    lp.setPassword(password);
	}
	@When("Click on Login")
	public void click_on_Login()
	{
		logger.info("********started login********");
	   lp.clickLogin();
	}
	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title)
	{
	   if(driver.getPageSource().contains("Login was unsuccessful."))
	   {
		   driver.close();
		   logger.info("********Login failed********");
		   Assert.assertTrue(false);
	   }
	   else
	   {
		   logger.info("********Login passed********");
		   Assert.assertEquals(title,driver.getTitle());
	   }
	}
	@When("User click on Log out link")
	public void user_click_on_Log_out_link()throws InterruptedException
	{
		logger.info("********Click on logout link********");
	    lp.clickLogout();
	    Thread.sleep(2000);
	}
	@Then("close browser")
	public void close_browser()
	{
		logger.info("********closing browser********");
	    driver.close();
	}
	@Then("User can view Dashboard")
	public void user_can_view_Dashboard()
	{
	    addCust=new AddCustomerPage(driver);
	    Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	}
	@When("User click on customers Menu")
	public void user_click_on_customers_Menu()throws InterruptedException
	{
		Thread.sleep(2000);
	    addCust.clickOnCustomersMenu();
	}
	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item()throws InterruptedException
	{
		Thread.sleep(1000);
	    addCust.clickOnCustomersMenuItem();
	}
	@When("click on Add new button")
	public void click_on_Add_new_button()throws InterruptedException
	{
	    addCust.clickOnAddnew();
	    Thread.sleep(1000);
	}
	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page()
	{
		Assert.assertEquals("Add a new customer / nopCommerce administration",addCust.getPageTitle());
	}
	@When("User enter customer info")
	public void user_enter_customer_info()throws InterruptedException
	{
		logger.info("********Adding new customer********");
		logger.info("********Providing customer details********");
	    String email=randomString()+"@gmail.com";
	    addCust.setEmail(email);
	    addCust.setPassword("test123");
	    addCust.setCustomerRoles("Guests");
	    Thread.sleep(1000);
	    addCust.setManagerOfVendor("Vendor 2");
	    addCust.setGender("Male");
	    addCust.setFirstName("Abhishek");
	    addCust.setLastName("K");
	    addCust.setDob("10/15/1990");
	    addCust.setCompanyName("BusyQA");
	    addCust.setIsTaxExempt();
	    addCust.setAdminComment("This is for testing......");
	}
	@When("click on Save button")
	public void click_on_Save_button()throws InterruptedException
	{
		logger.info("********Saving customer data********");
	    addCust.clickOnSave();
	    Thread.sleep(2000);
	}
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg)
	{
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));
	}
	@When("Enter customer EMail")
	public void enter_customer_Email()
	{
	   logger.info("********Searching customer by email id********");
	   searchCust=new SearchCustomerPage(driver);
	   searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}
	@When("Click on search button")
	public void click_on_search_button()throws InterruptedException
	{
	    searchCust.clickSearch();
	    Thread.sleep(2000);
	}
	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table()throws InterruptedException
	{
	   boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	   Thread.sleep(2000);
	   Assert.assertEquals(true,status);
	}
	@When("Enter customer FirstName")
	public void enter_customer_FirstName()
	{
		logger.info("********Searching customer by Name********");
		searchCust=new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}
	@When("Enter customer LastName")
	public void enter_customer_LastName()
	{
	   searchCust.setLastName("Terces");
	}
	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table()
	{
	   boolean status=searchCust.searchCustomerByName("Victoria Terces");
	   Assert.assertEquals(true,status);
	}
}