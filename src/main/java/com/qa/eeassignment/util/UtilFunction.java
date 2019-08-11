package com.qa.eeassignment.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class UtilFunction {

	public static void sendKeys(WebElement ele,String keys) {
		ele.sendKeys(keys);
	}
	
	public static void click(WebElement ele) {
		ele.click();
	}
	
	public static String getValue(WebElement ele) {
		return ele.getAttribute("value");
	}
	
	public static String getText(WebElement ele) {
		return ele.getText();
	}
	
	public static void selectValue(WebElement ele,String value,String typeOf) {
		Select s = new Select(ele);

		switch (typeOf) {
		case "value":
			s.selectByValue(value);
			break;

		case "visibletext":
			s.selectByVisibleText(value);
			break;
			
		case "visiblePartialtext":
			List<WebElement> month = s.getOptions();
			for(WebElement monthVal : month) {
				if(monthVal.getText().contains(value)) {
					s.selectByValue(monthVal.getAttribute("value"));
					break;
				}
			}
			break;
		default:
			break;
		}
		
	}
	
	public int getColumnNumber(WebElement table,String column) {
		List<WebElement> lstColumn = table.findElements(By.tagName("th"));
		int colNum = -1;
		for(int i=0;i<lstColumn.size();i++)
		{
			if(lstColumn.get(i).getText().equalsIgnoreCase(column)) {
				colNum =  i;
			}
		}
		return colNum+1;
	}
	
	public String getColumnData(WebElement table,String columnName)
	{
		String colData = "";
		int colNum = getColumnNumber(table, columnName);
		if(colNum==-1){
			return null;
		}
		List<WebElement> lstData = table.findElements(By.xpath("//tbody/tr/td["+colNum+"]"));
		for(int i=0;i<lstData.size();i++)
		{
			if(colData.isEmpty()) {
				colData = lstData.get(i).getAttribute("textContent").trim();
			}else {
				colData = colData+"~"+lstData.get(i).getAttribute("textContent").trim();;
			}
		}
		return colData;
	}
	
}
