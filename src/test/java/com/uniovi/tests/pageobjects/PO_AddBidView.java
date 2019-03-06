package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_AddBidView extends PO_NavView {
	
	static public void fillForm(WebDriver driver, String titulo, String descripcion, String precio) {
			WebElement title = driver.findElement(By.name("title"));
			title.click();
			title.clear();
			title.sendKeys(titulo);
			WebElement description = driver.findElement(By.name("description"));
			description.click();
			description.clear();
			description.sendKeys(descripcion);
			WebElement price = driver.findElement(By.name("price"));
			price.click();
			price.clear();
			price.sendKeys(precio);
				//Pulsar el boton de Alta.
			By boton = By.className("btn");
			driver.findElement(boton).click();
		}
}
