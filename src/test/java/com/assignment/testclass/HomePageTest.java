package com.assignment.testclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.basetest.BaseTest;
import com.pages.modules.HomePage;
import com.pages.modules.ProductSummaryPage;
import com.pages.modules.ProductsPage;
import com.pages.modules.RegistrationPage;
import com.qa.eeassignment.util.GlobalVariables;

import net.bytebuddy.utility.RandomString;

public class HomePageTest extends BaseTest {

	HomePage homePage;
    Properties propRegister ;
	ProductsPage productPage;
	RegistrationPage registerPage;
	ProductSummaryPage prodSummaryPage;
	
	public static final Logger log = LogManager.getLogger(HomePageTest.class.getName());
	
	@Test(priority=-1)
	public void preRequisites() throws FileNotFoundException, IOException
	{
		homePage = PageFactory.initElements(driver, HomePage.class);
		registerPage = PageFactory.initElements(driver, RegistrationPage.class);
		productPage = PageFactory.initElements(driver, ProductsPage.class);
		prodSummaryPage = PageFactory.initElements(driver, ProductSummaryPage.class);
		propRegister = new Properties();
		propRegister.load(new FileInputStream(System.getProperty("user.dir")+"/registeruser.properties"));
	}
	
	
	@Test(priority=0)
	public void signIn() {
		try {
			homePage.ClickOnSignIn();
			log.debug("Clicked on Sign In");
			GlobalVariables.email = RandomString.make(5)+"@xyz.com";
			homePage.EnterEmailAndClickOnCreateAccount(GlobalVariables.email);
			
		}catch(Exception e) {
			log.error("Sign in failed!!"+e);
			Assert.assertEquals(true, false);
		}
		
		
	}
	
	@Test(priority=1)
	public void registerUser() {
		try {
			String gender = propRegister.get("gender").toString();
			String firstName = propRegister.get("firstname").toString();
			String lastName = propRegister.get("lastname").toString();
			String password = propRegister.get("password").toString();
			String daysDOB = propRegister.get("daysDOB").toString();
			String monthDOB = propRegister.get("monthDOB").toString(); 
			String yearDOB = propRegister.get("yearDOB").toString();
			String company = propRegister.get("company").toString(); 
			String address = propRegister.get("address").toString(); 
			String address2 = propRegister.get("address2").toString(); 
			String city = propRegister.get("city").toString();
			String state = propRegister.get("state").toString();    
			String postalCode = propRegister.get("postalCode").toString();
			String country = propRegister.get("country").toString();
			String additionalInfo = propRegister.get("additionalInfo").toString();
			String mobilePhone = propRegister.get("mobilePhone").toString();
			String aliasAddress = propRegister.get("aliasAddress").toString();			
			registerPage.registerUser(GlobalVariables.email, gender, firstName, lastName, password, daysDOB, monthDOB, yearDOB, company, address, address2, city, state, postalCode, country, additionalInfo, mobilePhone, aliasAddress, log);
		}catch(Exception e) {
			log.error("Sign in failed!!"+e);
			Assert.assertEquals(true, false);
		}
	}
	
	@Test(priority=2)
	public void addToCart()
	{
		try {
			productPage.selectItems(log);
			productPage.goToCart(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=3)
	public void verifyPriceOnSummaryPage()
	{
		try {
			log.info("***************************** Start - Verify Price Summary Screen *********************************");
			prodSummaryPage.verifyfinalPriceWithoutTax(log);
			prodSummaryPage.verifyfinalPriceWithTax(log);
			prodSummaryPage.individualQuantityAndSumVerify(log);
			log.info("***************************** End   - Verify Price Summary Screen *********************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
