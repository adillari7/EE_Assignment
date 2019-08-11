package com.pages.modules;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.eeassignment.util.GlobalVariables;
import com.qa.eeassignment.util.UtilFunction;

public class RegistrationPage extends UtilFunction {

	WebDriver driver ;
	public RegistrationPage(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	
	@FindBy(id="id_gender1")
	WebElement Mr;
	
	@FindBy(id="id_gender2")
	WebElement Mrs;
	
	@FindBy(id="customer_firstname")
	WebElement firstName;
	
	@FindBy(id="customer_lastname")
	WebElement lastName;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="passwd")
	WebElement passwd;
	
	@FindBy(id="days")
	WebElement selectDays;
	
	@FindBy(id="months")
	WebElement selectMonths;
	
	@FindBy(id="years")
	WebElement selectYears;
	

	@FindBy(id="firstname")
	WebElement firstNameAddress;
	
	@FindBy(id="lastname")
	WebElement lastNameAddress;
	
	@FindBy(id="company")
	WebElement companyName;
	
	@FindBy(id="address1")
	WebElement address;
	
	@FindBy(id="address2")
	WebElement address2;
	
	@FindBy(id="city")
	WebElement city;
	
	@FindBy(id="id_state")
	WebElement state;
	
	@FindBy(id="postcode")
	WebElement postcode;
	
	@FindBy(id="id_country")
	WebElement country;
	
	@FindBy(id="other")
	WebElement additionalInformation;
	
	@FindBy(id="phone")
	WebElement homePhone;
	
	@FindBy(id="phone_mobile")
	WebElement mobilePhone;
	
	@FindBy(id="alias")
	WebElement myAddressAlias;
	
	@FindBy(id="submitAccount")
	WebElement register;
	
	public void registerUser(String emailID,String gender,String firstName,String lastName,String password, String daysDOB, String monthDOB,
			String yearDOB,String company, String address, String address2, String city, String state, String postalCode, String country,
			String additionalInfo, String mobilePhone, String aliasAddress,Logger log){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(this.firstName));
		
		log.info("Email ID is "+email.getAttribute("value"));
		
		if(gender.equals("Mr")) {
			click(Mr);
		}else {
			click(Mrs);
		}
		
		sendKeys(this.firstName, firstName);
		log.debug("Set first name as "+firstName);
		sendKeys(this.lastName, lastName);
		log.debug("Set first name as "+lastName);
		sendKeys(this.passwd, password);
		log.debug("Set first name as "+password);
		selectValue(this.selectDays, daysDOB,"value");
		log.debug("Select Days as "+daysDOB);
		selectValue(this.selectMonths, monthDOB,"visiblePartialtext");
		log.debug("Select Days as "+monthDOB);
		selectValue(this.selectYears, yearDOB,"value");
		log.debug("Select Days as "+yearDOB);
		log.info("Verified first name as "+getValue(firstNameAddress));
		log.info("Verified last name as "+getValue(lastNameAddress));
		sendKeys(this.companyName, company);
		log.debug("Set fcompany as "+company);
		sendKeys(this.address, address);
		log.debug("Set first name as "+firstName);
		sendKeys(this.address2, address2);
		log.debug("Set first name as "+firstName);
		sendKeys(this.city, city);
		selectValue(this.state, state,"visiblePartialtext");
		sendKeys(this.postcode, postalCode);
		selectValue(this.country, country,"visiblePartialtext");
		sendKeys(this.additionalInformation, additionalInfo);
		sendKeys(this.mobilePhone, mobilePhone);
		sendKeys(this.myAddressAlias, aliasAddress);		
		click(register);
		log.info("Successfully clicked on register!!");
	}
	
	
}
