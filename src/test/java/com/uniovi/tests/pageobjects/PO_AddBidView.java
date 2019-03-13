package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

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
	
	static public void fillFormOustanding(WebDriver driver, String titulo, String descripcion, String precio) {
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
		WebElement specialBid = driver.findElement(By.name("specialBid"));
		specialBid.click();
			//Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	/**
	 * Método para comprobar que estamos en esta vista
	 */
	static public boolean checkPOAddView(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		List<WebElement> list = SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("bid.add.title", language), getTimeout());
		if(list.size() >= 1) {
			return true;
		}
		return false;
	}
}
