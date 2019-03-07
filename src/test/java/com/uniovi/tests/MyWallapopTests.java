package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_AddBidView;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_SearchBidView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MyWallapopTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	 static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\marco\\OneDrive\\Escritorio\\PL-SDI-Sesio╠ün5-material\\geckodriver024win64.exe";

	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}
	

	@Test
	//Registro de Usuario con datos válidos.
	public void Prueba1(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "pruuebaa@gmail.aaacom", "Prueba", "Antonio", "77777", "77777");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		// Comprobamos que entramos en la sección privada del usuario mirando salgo
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'user-menu')]/a");
		elementos.get(0).click();
		//Comprobamos que está el texto del saldo al tener role user
		PO_View.checkElement(driver, "text", "Saldo disponible:");
	}
	
	@Test
	//Registro de Usuario con email vacío, nombre vacío, apellidos vacío.
	public void Prueba2(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "", "", "", "77777", "77777");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		PO_View.getP();
		// COmprobamos el error de campo vacio.
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}
	
	@Test
	//Registro de Usuario con repetición contraseña inválida.
	public void Prueba3(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "prueba3@gmail.com", "Lorenzo", "Berto", "77778", "77777");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		PO_View.getP();
		// COmprobamos el error de campo vacio.
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}
	
	@Test
	//Registro de Usuario con email existente.
	public void Prueba4(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "pedro@gmail.com", "Lorenzo", "Berto", "77777", "77777");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		PO_View.getP();
		// COmprobamos el error de campo vacio.
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}
	
	@Test
	//Iniciar correctamente siendo admin
	public void Prueba5(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Comprobamos que está el texto de gestion de usuarios que solo posee el admin
		PO_View.checkElement(driver, "text", "Gestion de usuarios");
	}
	
	@Test
	//Iniciar correctamente siendo user
	public void Prueba6(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		// Comprobamos que entramos en la sección privada del usuario mirando salgo
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'user-menu')]/a");
		elementos.get(0).click();
		//Comprobamos que está el texto del saldo al tener role user
		PO_View.checkElement(driver, "text", "Saldo disponible:");
	}
	
	@Test
	//Fallo inicio de sesion campos vacios
	public void Prueba7(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "", "");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		PO_View.getP();
		// COmprobamos el error de campo vacio.
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
		
	}
	
	@Test
	//Fallo de inicio de sesion contraseña incorrecta
	public void Prueba8(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "12345");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		PO_View.getP();
		// COmprobamos el error de campo vacio.
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
		
	}
	
	@Test
	//Fallo de inicio de sesion email incorrecto
	public void Prueba9(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro12345@gmail.com", "12345");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		PO_View.getP();
		// COmprobamos el error de campo vacio.
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
		
	}
	
	@Test
	//Fallo de inicio de sesion email incorrecto
	public void Prueba10(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		// Click en el email
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'user-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'logout')]");
		// Pinchamos en desconectar.
		elementos.get(0).click();
		//Comprobamos pagina login
		PO_View.checkElement(driver, "text", "Email:");
		
	}
	
	@Test
	//Fallo de inicio de sesion email incorrecto
	public void Prueba11(){
		//Comprobamos que no está la opcion de desconectarse sin autentificacion
		SeleniumUtils.textoNoPresentePagina(driver, "Desconectar");
		
		
	}
	
	@Test
	//Mostrar el listado de usuarios y comprobar que se muestran todos los que existen en el sistema
	public void Prueba12(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en gestion de usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		// Pinchamos en ver usuarios
		elementos.get(0).click();
		//Como hay 7 usuarios registrados comprobamso que salgan todos
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 7);
		
	}
	
	@Test
	//Ir a la lista de usuarios, borrar el primer usuarios de la lista y comprobar que se actualiza y dicho usuario no aparece
	public void Prueba13(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en gestion de usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		// Pinchamos en ver usuarios
		elementos.get(0).click();
		//Cargamos los usuarios y vemos que estan todos
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 7);
		//Cogemos el nombre del que se va a borrar para su posterior comprobacion
		String emailYnombre = elementos.get(0).getText();
		String parts[] = emailYnombre.split(" ");
		String nombre = parts[1];
		//Le damos al checkbox
		elementos = PO_View.checkElement(driver, "free", "//input");
		elementos.get(0).click();
		//Click a borrar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		//Comprobamos que el usuario no esta 
		SeleniumUtils.textoNoPresentePagina(driver, nombre);
		//Y que la lista es 1 menos
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 6);
	}
	
	@Test
	//Ir a la lista de usuarios, borrar el primer usuarios de la lista y comprobar que se actualiza y dicho usuario no aparece
	public void Prueba14(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en gestion de usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		// Pinchamos en ver usuarios
		elementos.get(0).click();
		//Cargamos los usuarios y vemos que estan todos
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 7);
		//Cogemos el nombre del que se va a borrar para su posterior comprobacion
		String emailYnombre = elementos.get(elementos.size()-1).getText();
		String parts[] = emailYnombre.split(" ");
		String nombre = parts[1];
		//Le damos al checkbox
		elementos = PO_View.checkElement(driver, "free", "//input");
		elementos.get(elementos.size()-1).click();
		//Click a borrar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		//Comprobamos que el usuario no esta 
		SeleniumUtils.textoNoPresentePagina(driver, nombre);
		//Y que la lista es 1 menos
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 6);
	}
	
	@Test
	//Ir a la lista de usuarios, borrar 3 usuarios y comprobar que se actualiza y dichos usuarios no aparecen
	public void Prueba15(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en gestion de usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		// Pinchamos en ver usuarios
		elementos.get(0).click();
		
		//Cargamos los usuarios y vemos que estan todos
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 7);
		
		//Cogemos el nombre del primer Usuario
		String emailYnombre1 = elementos.get(0).getText();
		String parts1[] = emailYnombre1.split(" ");
		String nombre1 = parts1[1];
		//Cogemos el nombre del segundo Usuario
		String emailYnombre2 = elementos.get(1).getText();
		String parts2[] = emailYnombre2.split(" ");
		String nombre2 = parts2[1];
		//Cogemos el nombre del tercer Usuario
		String emailYnombre3 = elementos.get(2).getText();
		String parts3[] = emailYnombre3.split(" ");
		String nombre3 = parts3[1];
		
		//Le damos a los checkboxs
		elementos = PO_View.checkElement(driver, "free", "//input");
		elementos.get(0).click();
		elementos.get(1).click();
		elementos.get(2).click();
		
		//Click a borrar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
		//Comprobamos que los usuarios no estan
		SeleniumUtils.textoNoPresentePagina(driver, nombre1);
		SeleniumUtils.textoNoPresentePagina(driver, nombre2);
		SeleniumUtils.textoNoPresentePagina(driver, nombre3);
		//Y que la lista es 3 menos
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
	}
	
	@Test
	//Dar de alta una nueva oferta y comprobarla
	public void Prueba16(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/add')]");
		// Pinchamos añadir oferta
		elementos.get(0).click();
		// Rellenamos el formulario.
		PO_AddBidView.fillForm(driver, "Prueba", "Descricpion","10");
		//Comprobamos que este
		PO_View.checkElement(driver, "text", "Prueba");
	}
	
	@Test
	//Dar de alta una nueva oferta con el titulo vacio y comprobar error.
	public void Prueba17(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/add')]");
		// Pinchamos añadir oferta
		elementos.get(0).click();
		// Rellenamos el formulario.
		PO_AddBidView.fillForm(driver, "", "Descricpion","10");
		//Comprobamos que este
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}
	
	@Test
	//Ir al listado de ofertas del usuario y comprobar que se muestran todas sus ofertas
	public void Prueba18(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/mybids')]");
		// Pinchamos en ver mis ofertas
		elementos.get(0).click();
		//Como sabemos que tiene 4 ofertas miramos que sean 4
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
	}
	
	@Test
	//Ir al listado de ofertas del usuario y borrar la primera comprobandolo
	public void Prueba19(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/mybids')]");
		// Pinchamos en ver mis ofertas
		elementos.get(0).click();
		
		//Cogemos el nombre de la primera oferta para luego comprobarlo
		elementos = PO_View.checkElement(driver, "free", "//tr[contains(@id, 'ofertas')]");
		String datos = elementos.get(0).getText();
		String parts[] = datos.split(" ");
		String nombre = parts[0]+ " "+ parts[1];
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 2);
		//Borramos la oferta
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id, 'boton')]");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 2);
		//Comprobamos que no esta
		SeleniumUtils.textoNoPresentePagina(driver, nombre);
	}
	
	@Test
	//Ir al listado de ofertas del usuario y borrar la ultima comprobandolo
	public void Prueba20(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/mybids')]");
		// Pinchamos en ver mis ofertas
		elementos.get(0).click();
		
		//Cogemos el nombre de la primera oferta para luego comprobarlo
		elementos = PO_View.checkElement(driver, "free", "//tr[contains(@id, 'ofertas')]");
		String datos = elementos.get(elementos.size()-1).getText();
		String parts[] = datos.split(" ");
		String nombre = parts[0]+ " "+ parts[1];
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 2);
		//Borramos la oferta
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id, 'boton')]");
		elementos.get(elementos.size()-1).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 2);
		//Comprobamos que no esta
		SeleniumUtils.textoNoPresentePagina(driver, nombre);
		
	}
	
	@Test
	//Hacer una busqueda de las ofertas con el campo vacio y 
	//comprobar que se muestra la pagina que corresponde con el listado de paginas existentes
	public void Prueba21(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/list')]");
		// Pinchamos en ver mis ofertas
		elementos.get(0).click();
		
		//Cogemos el nombre de las ofertas de la primera pagina
		elementos = PO_View.checkElement(driver, "free", "//tr[contains(@id, 'ofertas')]");
		String nombres[] = new String[5];
		for(int i = 0; i<elementos.size();i++) {
			String datos = elementos.get(i).getText();
			String parts[] = datos.split(" ");
			nombres[i]= parts[0]+ " "+ parts[1];
		}
		
		//Clickamos el buscador y buscamos vacio
		elementos = PO_View.checkElement(driver, "free", "//input");
		elementos.get(0).click();
		PO_SearchBidView.fillForm(driver,"");
		
		SeleniumUtils.esperarSegundos(driver, 3);
		//Comprobamos la pagina que está igual
		elementos = PO_View.checkElement(driver, "free", "//tr[contains(@id, 'ofertas')]");
		for(int i = 0; i<elementos.size();i++) {
			PO_View.checkElement(driver, "text", nombres[i]);
		}
		
		
	}
	
	@Test
	//Hacer una busqueda de las ofertas con el campo algo que no aparece
	//comprobar que se muestra la pagina vacia
	public void Prueba22(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/list')]");
		// Pinchamos en ver mis ofertas
		elementos.get(0).click();
		
		//Cogemos el nombre de las ofertas de la primera pagina
		elementos = PO_View.checkElement(driver, "free", "//tr[contains(@id, 'ofertas')]");
		String nombres[] = new String[5];
		for(int i = 0; i<elementos.size();i++) {
			String datos = elementos.get(i).getText();
			String parts[] = datos.split(" ");
			nombres[i]= parts[0]+ " "+ parts[1];
		}
		//Clickamos el buscador y buscamos algo que no existe
		elementos = PO_View.checkElement(driver, "free", "//input");
		elementos.get(0).click();
		PO_SearchBidView.fillForm(driver,"zzzzzzz");
		SeleniumUtils.esperarSegundos(driver, 3);
		
		//Comprobamos que esta vacia mirando a veri salen las ofertas que salian antes
		SeleniumUtils.textoNoPresentePagina(driver, nombres[0]);
		SeleniumUtils.textoNoPresentePagina(driver, nombres[1]);
		SeleniumUtils.textoNoPresentePagina(driver, nombres[2]);
		SeleniumUtils.textoNoPresentePagina(driver, nombres[3]);
		SeleniumUtils.textoNoPresentePagina(driver, nombres[4]);
		
	}
	
	@Test
	//Comprar y ver que se actualiza correctamente el saldo
	public void Prueba23(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/list')]");
		// Pinchamos en ver ofertas
		elementos.get(0).click();
		SeleniumUtils.esperarSegundos(driver, 2);
		//Cogemos el dinero que tiene
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'user-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'DropDownInfo2')]/a");
		String saldoActual = elementos.get(0).getText();
		String parts[] = saldoActual.split(",");
		String saldo = parts[0];
		
		//Cogemos el precio de lo que vamos a comprar
		elementos = PO_View.checkElement(driver, "free", "//tr[contains(@id, 'ofertas')]");
		String partsPrecio[] = elementos.get(0).getText().split(" ");
		String precio = partsPrecio[6];
		String partsPrecioSinComa[] = precio.split(",");
		String precioFinal = partsPrecioSinComa[0];
		//Compramos
		elementos = PO_View.checkElement(driver, "free", "//form[contains(@id, 'botonBuy')]");
		elementos.get(0).click();
		SeleniumUtils.esperarSegundos(driver, 2);
		
		//Miramos salgo
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'user-menu')]/a");
		elementos.get(0).click();
		
		//Comprbamos que el saldo sea la resta
		int resta = Integer.parseInt(saldo) - Integer.parseInt(precioFinal);
		//Comprobamos que está el texto del saldo al tener role user
		System.out.println(String.valueOf(resta)+",00 €");
		PO_View.checkElement(driver, "text", String.valueOf(resta)+",00 €");
		
		
	}
	
	@Test
	//Comprar y ver que se actualiza correctamente el saldo a 0 en este caso
	public void Prueba24(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/list')]");
		// Pinchamos en ver ofertas
		elementos.get(0).click();
		SeleniumUtils.esperarSegundos(driver, 2);
		//Cogemos el dinero que tiene
		//Miramos saldo
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'user-menu')]/a");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "100,00 €");
		
		//Clickamos el buscador y buscamos la oferta que cuesta 100
		elementos = PO_View.checkElement(driver, "free", "//input");
		elementos.get(0).click();
		PO_SearchBidView.fillForm(driver,"D1");
		//Compramos
		elementos = PO_View.checkElement(driver, "free", "//form[contains(@id, 'botonBuy')]");
		elementos.get(0).click();
		SeleniumUtils.esperarSegundos(driver, 2);
		
		//Miramos saldo
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'user-menu')]/a");
		elementos.get(0).click();
		
		
		PO_View.checkElement(driver, "text", "0,00 €");
		
		
	}
	
	@Test
	//Comprar y ver que da error 
	public void Prueba25(){
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		//Click en ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'bids-menu')]/a");
		elementos.get(0).click();
		//Esperamos
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/bid/list')]");
		// Pinchamos en ver ofertas
		elementos.get(0).click();
		SeleniumUtils.esperarSegundos(driver, 2);
		//Cogemos el dinero que tiene
		//Miramos saldo
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'user-menu')]/a");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "100,00 €");
		
		//Clickamos el buscador y buscamos la oferta que cuesta 100
		elementos = PO_View.checkElement(driver, "free", "//input");
		elementos.get(0).click();
		PO_SearchBidView.fillForm(driver,"D2");
		//Compramos
		elementos = PO_View.checkElement(driver, "free", "//form[contains(@id, 'botonBuy')]");
		elementos.get(0).click();
		SeleniumUtils.esperarSegundos(driver, 2);
		
		//Buscamos ese producto para ver el error 
		PO_SearchBidView.fillForm(driver,"D2");
		SeleniumUtils.esperarSegundos(driver, 2);
		PO_RegisterView.checkKey(driver, "Error.buy", PO_Properties.getSPANISH());
		
		//Miramos saldo
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'user-menu')]/a");
		elementos.get(0).click();	
		PO_View.checkElement(driver, "text", "100,00 €");
		
		
	}



//
//	// PR03. OPción de navegación. Pinchar en el enlace Identificate en la página
//	// home
//	@Test
//	public void PR03() {
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//	}
//	//PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta a Español
//	@Test
//	public void PR04() {
//		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
//				PO_Properties.getENGLISH());
//		// SeleniumUtils.esperarSegundos(driver, 2);
//	}
//	
	//PR05. Prueba del formulario de registro. registro con datos correctos
//	@Test
//	public void PR05() {
//		// Vamos al formulario de registro
//		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
//		// Rellenamos el formulario.
//		PO_RegisterView.fillForm(driver, "77777774A", "Josefo", "Perez", "77777", "77777");
//		
//		// Comprobamos que entramos en la sección privada
//		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'dropdown-menu')]/a");
//		elementos.get(0).click();
//	}
//	
//	//PR06. Prueba del formulario de registro. DNI repetido en la BD, Nombre corto, .... pagination pagination-­‐centered, Error.signup.dni.length
//	@Test
//	public void PR06() {
//		// Vamos al formulario de registro
//		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
//		// Rellenamos el formulario.
//		PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
//		PO_View.getP();
//		// COmprobamos el error de DNI repetido.
//		PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());
//		// Rellenamos el formulario.
//		PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");
//		// COmprobamos el error de Nombre corto .
//		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
//		// Rellenamos el formulario.
//		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Per", "77777", "77777");
//	}
//	
//	//PRN. Loguearse con exito desde el ROl de Usuario, 99999990D, 123456
//	@Test
//	public void PR07() {
//		// Vamos al formulario de logueo.
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "99999990A", "123456");
//		// COmprobamos que entramos en la pagina privada de Alumno
//		PO_View.checkElement(driver, "text", "Notas del usuario");
//	}
//	
//	@Test
//	public void PR08() {
//		// Vamos al formulario de logueo.
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "99999993D", "123456");
//
//		PO_View.checkElement(driver, "text", "99999993D");
//	}
//	
//	@Test
//	public void PR09() {
//		// Vamos al formulario de logueo.
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "99999988F", "123456");
//		// COmprobamos que entramos en la pagina privada del admin mirando su nav
//		PO_View.checkElement(driver, "text", "Gestión de Usuarios");
//	}
//	
//	@Test
//	public void PR10() {
//		// Vamos al formulario de logueo.
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "99999990AA", "123456");
//		// COmprobamos que sigue en la misma pagina.
//		PO_View.checkElement(driver, "text", "DNI");
//	}
//	
//	@Test
//	public void PR11() {
//		// Vamos al formulario de logueo.
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "99999990A", "123456");
//		//COmprobamos que entramos en la pagina privada de Alumno
//		PO_View.checkElement(driver, "text", "Notas del usuario");
//		//Y desconexion
//		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//	}
//	
//	//PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse usando el rol de
//	//estudiante.
//	@Test
//	public void PR12() {
//		PO_PrivateView.log(driver,"99999990A","123456");
//		// Contamos el número de filas de notas
//		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
//				PO_View.getTimeout());
//		assertTrue(elementos.size() == 4);
//		// Ahora nos desconectamos
//		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//	}
//	
//	// PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion
//	// = Nota A2.
//	// P13. Ver la lista de Notas.
//	@Test
//	public void PR13() {
//		PO_PrivateView.log(driver,"99999990A","123456");
//		SeleniumUtils.esperarSegundos(driver, 1);
//		// Contamos las notas
//		By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]"); //Segundo TD despues de NOTA A2
//		driver.findElement(enlace).click();
//		SeleniumUtils.esperarSegundos(driver, 1);
//		// Esperamos por la ventana de detalle
//		PO_View.checkElement(driver, "text", "Detalles de la nota");
//		SeleniumUtils.esperarSegundos(driver, 1);
//		// Ahora nos desconectamos
//		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//	}
//
//	// P14. Loguearse como profesor y Agregar Nota A2.
//	// P14. Esta prueba podría encapsularse mejor ...
//	@Test
//	public void PR14() {
//		PO_PrivateView.log(driver,"99999993D","123456");
//		// Pinchamos en la opción de menu de Notas: //li[contains(@id,
//		// 'marks-­‐menu')]/a
//		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'marks-menu')]/a");
//		elementos.get(0).click();
//		// Esperamos a aparezca la opción de añadir nota: //a[contains(@href,
//		// 'mark/add')]
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/add')]");
//		// Pinchamos en agregar Nota.
//		elementos.get(0).click();
//		// Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
//		PO_PrivateView.fillFormAddMark(driver, 3, "Nota Nueva 1", "8");
//		// Esperamos a que se muestren los enlaces de paginación la lista de notas
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//		// Nos vamos a la última página
//		elementos.get(3).click();
//		// Comprobamos que aparece la nota en la pagina
//		elementos = PO_View.checkElement(driver, "text", "Nota Nueva 1");
//		// Ahora nos desconectamos
//		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//	}
//	
//	//PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota Nueva 1.
//	//PRN. Ver la lista de Notas.
//	@Test
//	public void PR15() {
//		PO_PrivateView.log(driver,"99999993D","123456");
//		//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-­‐menu')]/a
//		List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'marks-menu')]/a");
//		elementos.get(0).click();
//		//Pinchamos en la opción de lista de notas.
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'mark/list')]");
//		elementos.get(0).click();
//		//Esperamos a que se muestren los enlaces de paginacion la lista de notas
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//		//Nos vamos a la última página
//		elementos.get(3).click();
//		//Esperamos a que aparezca la Nueva nota en la ultima pagina
//		//Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"
//		//td[contains(text(), 'Nota Nueva 1')]/following-­‐sibling::*/a[contains(text(),'mark/delete')]"
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//		elementos.get(0).click();
//		//Volvemos a la última pagina
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//		elementos.get(3).click();
//		//Y esperamos a que NO aparezca la ultima "Nueva Nota 1"
//		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva1",PO_View.getTimeout() );
//		//Ahora nos desconectamos
//		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//		}
//	
//	//TEST Agregar usuario
//	@Test
//	public void PR16() {
//		//Iniciamos como un admin
//		PO_PrivateView.log(driver,"99999988F","123456");
//		//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-­‐menu')]/a
//		List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
//		elementos.get(0).click();
//		//Pinchamos en la lista de agregar usuario
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/add')]");
//		elementos.get(0).click();
//		// Ahora vamos a rellenar el usuario
//		PO_PrivateView.fillFormAddUser(driver, "asdasdasdasda","aaaaaaaa","aaaaaa","123456");
//		//Esperamos
//		SeleniumUtils.esperarSegundos(driver, 1);
//		// Comprobamos que aparece la nota en la pagina
//		elementos = PO_View.checkElement(driver, "text", "aaaaaaaa");
//		// Ahora nos desconectamos
//		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//		
//	}
//	
//	@Test
//	public void PR17() {
//		//Iniciamos como un admin
//		PO_PrivateView.log(driver,"99999988F","123456");
//		//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-­‐menu')]/a
//		List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
//		elementos.get(0).click();
//		//Pinchamos en la lista de agregar usuario
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
//		elementos.get(0).click();
//		//Esperamos
//		SeleniumUtils.esperarSegundos(driver, 1);
//		By enlace = By.xpath("//td[contains(text(), 'Pedro')]/following-sibling::*[2]"); //Segundo TD despues de NOTA A2
//		driver.findElement(enlace).click();
//		SeleniumUtils.esperarSegundos(driver, 1);
//		
//		// Esperamos por la ventana de detalle
////		PO_View.checkElement(driver, "text", "Detalles de usuario");
////		SeleniumUtils.esperarSegundos(driver, 1);
////		// Ahora nos desconectamos
////		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//		
//	}
//	
//	@Test
//	public void PR18() {
//		//Iniciamos como un admin
//		PO_PrivateView.log(driver,"99999988F","123456");
//		//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-­‐menu')]/a
//		List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
//		elementos.get(0).click();
//		//Pinchamos en la lista de agregar usuario
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
//		elementos.get(0).click();
//		//Esperamos
//		SeleniumUtils.esperarSegundos(driver, 1);
//		By enlace = By.xpath("//td[contains(text(), 'Edward')]/following-sibling::*[1]"); 
//		driver.findElement(enlace).click();
//		SeleniumUtils.esperarSegundos(driver, 1);
//		
//		
//		//Rellenariaos los datos como en el anterior pero como no existe la vista edit....
//		
//	}
//	
//	@Test
//	public void PR19() {
//		//Iniciamos como un admin
//		PO_PrivateView.log(driver,"99999988F","123456");
//		//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-­‐menu')]/a
//		List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
//		elementos.get(0).click();
//		//Pinchamos en la lista de agregar usuario
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
//		elementos.get(0).click();
//		//Esperamos
//		SeleniumUtils.esperarSegundos(driver, 1);
//		By enlace = By.xpath("//td[contains(text(), 'Josefo')]/following-sibling::*[4]"); 
//		driver.findElement(enlace).click();
//		SeleniumUtils.esperarSegundos(driver, 1);
//		
//		//TODO Comprobar que se ha borrado
//		// Esperamos por la ventana de detalle
//		int numEl = PO_View.checkElement(driver, "text", "Josefo").size();
//		assertEquals(0,numEl);
//		SeleniumUtils.esperarSegundos(driver, 1);
//		// Ahora nos desconectamos
//		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
//		
//	}
//		
	
	
	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
	
	
}