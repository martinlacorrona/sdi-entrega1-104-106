package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_OfertasView extends PO_NavView {

    static public boolean checkPOfertasView(WebDriver driver, int language) {
	// Esperamos a que se cargue el saludo de bienvenida en Español
	List<WebElement> list = SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("bid.list.title", language),
		getTimeout());
	if (list.size() >= 1) {
	    return true;
	}
	return false;
    }

}
