package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage
{
	public WebDriver ldriver;
	public AddCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver,this);
	}
	By lnkCustomers_menu=By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem=By.xpath("//a[@href=\"/Admin/Customer/List\"]//p[contains(text(),'Customers')]");
	By btnAddNew=By.xpath("//a[@class='btn btn-primary']");
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	By txtCustomerRoles=By.xpath("//*[@id=\"customer-info\"]/div[2]/div[10]/div[2]/div/div[1]/div/div");
	By lstItemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
	By lstItemForumModerators=By.xpath("//li[contains(text(),'Forum Moderators')]");
	By lstItemGuests=By.xpath("//li[contains(text(),'Guests')]");
	By lstItemRegistered=By.xpath("//li[contains(text(),'Registered')]");
	By lstItemVendors=By.xpath("//li[contains(text(),'Vendors')]");
	By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
	By lstItemNotAVendor=By.xpath("//*[contains(text(),'Not a vendor')]");
	By lstItemVendor1=By.xpath("//*[contains(text(),'Vendor 1')]");
	By lstItemVendor2=By.xpath("//*[contains(text(),'Vendor 2')]");
	By rdMaleGender=By.id("Gender_Male");
	By rdFemaleGender=By.id("Gender_Female");
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName=By.xpath("//input[@id='Company']");
	By chkBoxIsTaxExempt=By.name("IsTaxExempt");
	By txtAdminComment=By.xpath("//textarea[@id='AdminComment']");
	By btnSave=By.xpath("//button[@name='save']");
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	public void clickOnCustomersMenu()
	{
		ldriver.findElement(lnkCustomers_menu).click();
	}
	public void clickOnCustomersMenuItem()
	{
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	public void clickOnAddnew()
	{
		ldriver.findElement(btnAddNew).click();
	}
	public void setEmail(String email)
	{
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	public void setPassword(String password)
	{
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	public void setCustomerRoles(String role)throws InterruptedException
	{
		ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
		ldriver.findElement(txtCustomerRoles).click();
		WebElement listitem;
		Thread.sleep(1000);
		if(role.equals("Administrators"))
		{
			listitem=ldriver.findElement(lstItemAdministrators);
		}
		else if(role.equals("Forum Moderators"))
		{
			listitem=ldriver.findElement(lstItemForumModerators);
		}
		else if(role.equals("Guests"))
		{
			listitem=ldriver.findElement(lstItemGuests);
		}
		else if(role.equals("Registered"))
		{
			listitem=ldriver.findElement(lstItemRegistered);
		}
		else
		{
			listitem=ldriver.findElement(lstItemVendors);
		}
		listitem.click();
	}
	public void setManagerOfVendor(String value)
	{
		Select drp=new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFemaleGender).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender).click();
		}
	}
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	public void setCompanyName(String comname)
	{
		ldriver.findElement(txtCompanyName).sendKeys(comname);
	}
	public void setIsTaxExempt()
	{
		ldriver.findElement(chkBoxIsTaxExempt).click();;
	}
	public void setAdminComment(String content)
	{
		ldriver.findElement(txtAdminComment).sendKeys(content);
	}
	public void clickOnSave()
	{
		ldriver.findElement(btnSave).click();
	}
}