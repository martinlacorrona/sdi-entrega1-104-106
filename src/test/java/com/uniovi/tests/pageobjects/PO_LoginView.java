package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_LoginView extends PO_NavView {

    static public void fillForm(WebDriver driver, String username, String passwordp) {
	WebElement email = driver.findElement(By.name("username"));
	email.click();
	email.clear();
	email.sendKeys(username);
	WebElement password = driver.findElement(By.name("password"));
	password.click();
	password.clear();
	password.sendKeys(passwordp);
	// Pulsar el boton de Alta.
	By boton = By.className("btn");
	driver.findElement(boton).click();
    }

    /**
     * Método para comprobar que estamos en esta vista
     */
    static public void checkPOLoginView(WebDriver driver, int language) {
	// Esperamos a que se cargue el saludo de bienvenida en Español
	SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("login.login", language), getTimeout());
    }

    static public void log(WebDriver driver, String dni, String pass) {
	// Vamos al formulario de logueo.
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// Rellenamos el formulario
	PO_LoginView.fillForm(driver, dni, pass);
    }

}
