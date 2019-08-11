package com.framework.basetest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
public class BaseTest {

	String browser;
	public WebDriver driver = null;
	String url;
	
	public static final Logger log = LogManager.getLogger(BaseTest.class.getName());
	
	@BeforeSuite
	public void initialize() {
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
			Properties prop = new Properties();
			prop.load(fis);
			
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
			if(driver==null) {
				if(browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
					driver = new ChromeDriver();
				}else if(browser.equalsIgnoreCase("ie")){
					driver = new InternetExplorerDriver();
				}
				
				driver.get(url);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
	}
	
	
	
}
