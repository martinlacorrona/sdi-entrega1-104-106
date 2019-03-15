package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_ForbiddenView extends PO_NavView {

    /**
     * Método para comprobar que estamos en esta vista
     */
    static public boolean checkPOForbiddenView(WebDriver driver, int language) {
	// Esperamos a que se cargue el saludo de bienvenida en Español
	List<WebElement> list = SeleniumUtils.EsperaCargaPagina(driver, "text",
		p.getString("forbidden.message", language), getTimeout());
	if (list.size() >= 1) {
	    return true;
	}
	return false;
    }
}
