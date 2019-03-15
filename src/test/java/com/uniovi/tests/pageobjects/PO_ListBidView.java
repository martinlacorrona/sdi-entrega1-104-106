package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_ListBidView extends PO_NavView {

    static public void fillForm(WebDriver driver, String search) {
	WebElement searchText = driver.findElement(By.name("searchText"));
	searchText.click();
	searchText.clear();
	searchText.sendKeys(search);
	// Pulsar el boton de Alta.
	By boton = By.className("btn");
	driver.findElement(boton).click();

    }

    /**
     * Método para comprobar que estamos en esta vista
     */
    static public boolean checkPOAddView(WebDriver driver, int language) {
	// Esperamos a que se cargue el saludo de bienvenida en Español
	List<WebElement> list = SeleniumUtils.EsperaCargaPagina(driver, "text",
		p.getString("bid.search.search", language), getTimeout());
	if (list.size() >= 1) {
	    return true;
	}
	return false;
    }
}
