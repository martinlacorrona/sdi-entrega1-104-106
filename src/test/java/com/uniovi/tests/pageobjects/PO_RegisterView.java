package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_RegisterView extends PO_NavView {
	
	static public void fillForm(WebDriver driver, String emailp, String namep, String lastnamep, String passwordp,String passwordconfp) {
			WebElement email = driver.findElement(By.name("email"));
			email.click();
			email.clear();
			email.sendKeys(emailp);
			WebElement name = driver.findElement(By.name("name"));
			name.click();
			name.clear();
			name.sendKeys(namep);
			WebElement lastname = driver.findElement(By.name("lastName"));
			lastname.click();
			lastname.clear();
			lastname.sendKeys(lastnamep);
			WebElement password = driver.findElement(By.name("password"));
			password.click();
			password.clear();
			password.sendKeys(passwordp);
			WebElement passwordConfirm = driver.findElement(By.name("passwordConfirm"));
			passwordConfirm.click();
			passwordConfirm.clear();
			passwordConfirm.sendKeys(passwordconfp);
				//Pulsar el boton de Alta.
			By boton = By.className("btn");
			driver.findElement(boton).click();
		}
	
	/**
	 * Método para comprobar que estamos en esta vista
	 */
	static public void checPOLoginView(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("singup.name", language), getTimeout());
	}
}
