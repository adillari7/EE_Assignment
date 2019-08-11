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
import org.testng.asserts.SoftAssert;

import com.qa.eeassignment.util.UtilFunction;

public class ProductSummaryPage extends UtilFunction{

	WebDriver driver ;
	public ProductSummaryPage(WebDriver driver) {

		this.driver = driver;

	}


	@FindBy(id="total_product")
	WebElement totalOfProductPrice;
	@FindBy(id="total_shipping")
	WebElement totalShippingPrice;
	@FindBy(id="total_price_without_tax")
	WebElement totalPriceWOTax;
	@FindBy(id="total_tax")
	WebElement totalTax;
	@FindBy(id="total_price")
	WebElement totalPrice;
	@FindBy(id="cart_summary")
	WebElement table;
	
	

	public void verifyfinalPriceWithoutTax(Logger log)
	{
		try {
			SoftAssert assertSoft = new SoftAssert();
			log.trace("********************** Without Tax Price Comparision ***********************");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(this.totalOfProductPrice));
			Actions action = new Actions(driver);
			action.moveToElement(totalPrice).build().perform();
			float totapProdPriceF =  Float.parseFloat(getText(totalOfProductPrice).replace("$", ""));
			float totalShippingPriceF =  Float.parseFloat(getText(totalShippingPrice).replace("$", ""));
			float totalPriceWOTaxF =  Float.parseFloat(getText(totalPriceWOTax).replace("$", ""));

		
			float totalOfShippingAndTotaProduct = totapProdPriceF+totalShippingPriceF;
			log.info("Total of  Total products ,Total shipping : "+(totalOfShippingAndTotaProduct));
			if(totalOfShippingAndTotaProduct==totalPriceWOTaxF) {
				log.info("Expected Total Price of Shipping And Total Product "+totalOfShippingAndTotaProduct+" is equal to Total Price W/O Tax "+totalPriceWOTaxF );
			}else {
				log.info("Expected Total Price of Shipping And Total Product "+totalOfShippingAndTotaProduct+" is not equal to Total Price W/O Tax "+totalPriceWOTaxF );
				assertSoft.assertEquals(true, false);
			}

		} catch (Exception e) {
			log.error(e);
		}
		log.trace("********************** Without Tax Price Comparision End ***********************");
	}
	
	
	public void verifyfinalPriceWithTax(Logger log)
	{
		try {
			SoftAssert assertSoft = new SoftAssert();
			log.trace("********************** With Tax Price Comparision ***********************");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(this.totalOfProductPrice));
			float totapProdPriceF =  Float.parseFloat(getText(totalOfProductPrice).replace("$", ""));
			float totalShippingPriceF =  Float.parseFloat(getText(totalShippingPrice).replace("$", ""));
			float totalTaxF =  Float.parseFloat(getText(totalTax).replace("$", ""));

			float totalPriceF = Float.parseFloat(getText(totalPrice).replace("$", ""));

			log.info("Total Price : "+totalPriceF);
			float expectedPrice = totapProdPriceF+totalShippingPriceF+totalTaxF;
			log.info("Sum of Total products ,Total shipping and Tax : "+expectedPrice);
			if(expectedPrice==totalPriceF) {
				log.info("Expected Total Price "+expectedPrice+" is equal to Actual Price "+totalPriceF );
			}else{
				log.error("Expected Total Price "+expectedPrice+" is not equal to Actual Price "+totalPriceF );
				assertSoft.assertEquals(true, false);
			}
			log.trace("********************** With Tax Price Comparision End ***********************");
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public void individualQuantityAndSumVerify(Logger log)
	{
		log.trace("**********************Start : Verify number of items*price of items ***********************");
		String qtyValues = "";
		List<WebElement> eleQty = driver.findElements(By.xpath("//input[@class='cart_quantity_input form-control grey']"));
		for(WebElement individualQty : eleQty)
		{
			if(qtyValues.isEmpty()) {
				qtyValues = individualQty.getAttribute("value");
			}else {
				qtyValues = qtyValues +"~"+individualQty.getAttribute("value");
			}
		}
		
		String unitPrice = getColumnData(table, "Unit Price").replace("$", "");
		
		System.out.println(unitPrice);
		
		int qtySize = qtyValues.split("~").length;
		int unitPriceSize = unitPrice.split("~").length;
		
		String qtyArray[]=qtyValues.split("~",-1);
		String unitPriceArray[]=unitPrice.split("~",-1);
		
		float price = 0.0f;
		
		if(qtySize==unitPriceSize) {
			
			for(int i=0;i<qtySize;i++) {
				
				if(price==0.0) {
					price = Float.parseFloat(qtyArray[i])*Float.parseFloat(unitPriceArray[i]); 
				}else {
					price = price + Float.parseFloat(qtyArray[i])*Float.parseFloat(unitPriceArray[i]);
				}
			}
		}
		
		float totalProductPriceF = Float.parseFloat(getText(totalOfProductPrice).replace("$", ""));
		float totalTaxF =  Float.parseFloat(getText(totalTax).replace("$", ""));
		
		float totalPriceF = totalProductPriceF+totalTaxF;
		
		if(totalPriceF==price) {
			log.info("Total Cost is (number of items*price of items) with tax include : Expected Price "+totalPriceF+" Actual Price "+price);
		}else {
			log.error("Total Cost is not same (number of items*price of items) with tax include : Expected Price "+totalPriceF+" Actual Price "+price);
		}
		
		System.out.println(price);
		
		log.trace("**********************End : Verify number of items*price of items ***********************");
	}
	
}
