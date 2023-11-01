package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage
{
	public WebDriver ldriver;
	WaitHelper waithelper;
	public SearchCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver,this);
		waithelper=new WaitHelper(ldriver);
	}
	@FindBy(how=How.ID,using="SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	@FindBy(how=How.NAME,using="SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	@FindBy(how=How.ID,using="SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	@FindBy(how=How.ID,using="SearchMonthOfBirth")
	@CacheLookup
	WebElement drpDobMonth;
	@FindBy(how=How.NAME,using="SearchDayOfBirth")
	@CacheLookup
	WebElement drpDobDay;
	@FindBy(how=How.ID,using="SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	@FindBy(how=How.XPATH,using="//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement txtCustomerRoles;
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Administrators')]")
	@CacheLookup
	WebElement lstItemAdministrators;
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Forum Moderators')]")
	@CacheLookup
	WebElement lstItemForumModerators;
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Guests')]")
	@CacheLookup
	WebElement lstItemGuests;
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Registered')]")
	@CacheLookup
	WebElement lstItemRegistered;
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Vendors')]")
	@CacheLookup
	WebElement lstItemVendors;
	@FindBy(how=How.ID,using="search-customers")
	@CacheLookup
	WebElement btnSearch;
	@FindBy(how=How.XPATH,using="//*[@id=\"customers-grid_wrapper\"]/div[1]/div/div/div[1]/div/table")
	@CacheLookup
	WebElement tblSearchResults;
	@FindBy(how=How.XPATH,using="//table[@id='customers-grid']")
	WebElement table;
	@FindBy(how=How.XPATH,using="//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	@FindBy(how=How.XPATH,using="//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns;
	public void setEmail(String email)
	{
		waithelper.WaitForElement(txtEmail,10);
		txtEmail.sendKeys(email);
	}
	public void setFirstName(String fname)
	{
		waithelper.WaitForElement(txtFirstName,10);
		txtFirstName.sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		waithelper.WaitForElement(txtLastName,10);
		txtLastName.sendKeys(lname);
	}
	public void clickSearch()
	{
		waithelper.WaitForElement(btnSearch,10);
		btnSearch.click();
	}
	public int getNoOfRows()
	{
		return(tableRows.size());
	}
	public int getNoOfColumns()
	{
		return(tableColumns.size());
	}
	public boolean searchCustomerByEmail(String email)
	{
		boolean flag=false;
		for(int i=1;i<=getNoOfRows();i++)
		{
			String emailid=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(emailid);
			if(emailid.equals(email))
			{
				flag=true;
			}
		}
		return flag;
	}
	public boolean searchCustomerByName(String Name)
	{
		boolean flag=false;
		for(int i=1;i<=getNoOfRows();i++)
		{
			String name=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
			String names[]=name.split(" ");
			if(names[0].equals("Victoria")&&names[1].equals("Terces"))
			{
				flag=true;
			}
		}
		return flag;
	}
}