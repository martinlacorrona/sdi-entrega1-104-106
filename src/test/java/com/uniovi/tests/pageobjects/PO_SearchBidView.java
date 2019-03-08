package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_SearchBidView extends PO_NavView {
	
	static public void fillForm(WebDriver driver, String search ) {
			WebElement searchText = driver.findElement(By.name("searchText"));
			searchText.click();
			searchText.clear();
			searchText.sendKeys(search);
			//Pulsar el boton de Alta.
			By boton = By.className("btn");
			driver.findElement(boton).click();
		
		}
}
