package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_UsersView extends PO_NavView {

    /**
     * Método para comprobar que estamos en esta vista
     */
    static public boolean checkPOUsersView(WebDriver driver, int language) {
	// Esperamos a que se cargue el saludo de bienvenida en Español
	List<WebElement> list = SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("users.id", language),
		getTimeout());

	if (list.size() == 1) {
	    return true;
	} else {
	    return false;
	}
    }

}
