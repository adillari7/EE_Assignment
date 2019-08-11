package com.pages.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.eeassignment.util.UtilFunction;

public class HomePage extends UtilFunction{

	WebDriver driver;
	public HomePage(WebDriver driver) {
		
		System.out.println("Into HomePage");
		this.driver = driver;
	}
	
	
	@FindBy(xpath="//*[@class='login' and normalize-space(text())='Sign in']")
	WebElement signIn;
	
	@FindBy(id="email_create")
	WebElement createEmail;
	
	@FindBy(id="SubmitCreate")
	WebElement createAccountButton;
	
	public void ClickOnSignIn() {
		click(signIn);
	}
	
	public void EnterEmailAndClickOnCreateAccount(String emailID) {
		sendKeys(createEmail, emailID);
		click(createAccountButton);
		
	}
	
}
