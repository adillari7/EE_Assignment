package com.pages.modules;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.eeassignment.util.UtilFunction;

public class ProductsPage extends UtilFunction{


	WebDriver driver ;
	public ProductsPage(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	
	@FindBy(xpath="*//a[contains(text(),'Women') and @title='Women']")
	WebElement womenProductsLink;
	
	@FindBy(xpath="//a[@title='Add to cart' and @data-id-product='1']")
	WebElement firstProduct;
	
	@FindBy(xpath="//a[@title='Add to cart' and @data-id-product='2']")
	WebElement secondProduct;
	
	@FindBy(xpath="//a[@title='Add to cart' and @data-id-product='3']")
	WebElement thirdProduct;
	
	@FindBy(xpath="//span[@title='Continue shopping']")
	WebElement continueShopping;
	
	@FindBy(xpath="*//a[@title='View my shopping cart']")
	WebElement goToCart;
	
	
	
	public void selectItems(Logger log)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(this.womenProductsLink));
		click(womenProductsLink);
		Actions action = new Actions(driver);
		List<WebElement> listDress = driver.findElements(By.xpath("//a[@class='product_img_link']/img"));
		for(int i=0;i<listDress.size()-3;i++) {
			wait.until(ExpectedConditions.invisibilityOf(continueShopping));
			action.moveToElement(listDress.get(i)).build().perform();
			WebElement eleProduct = driver.findElement(By.xpath("//a[@title='Add to cart' and @data-id-product='"+(i+1)+"']"));
			click(eleProduct);
			log.debug("Added Product "+listDress.get(i).getAttribute("alt")+" to Cart");
			wait.until(ExpectedConditions.visibilityOf(continueShopping));
			click(continueShopping);
			log.info("Clicked on Continue shopping");
		}
			
		log.info("****************************************** ITEMS ADDED TO CART ********************************************");
	}
	
	public void goToCart(Logger log) {
		click(goToCart);
		log.info("Clicked on Cart");
	}
}
