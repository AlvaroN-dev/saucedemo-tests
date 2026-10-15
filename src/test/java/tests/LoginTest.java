package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest {
    private WebDriver driver;

    @BeforeEach  // se ejecuta ANTES de cada test: navegador nuevo
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null) {                 // GitHub Actions define la variable CI
            options.addArguments("--headless=new");        // sin ventana
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--incognito");
        }
        driver = new ChromeDriver(options);
    }

    @AfterEach                  // se ejecuta DESPUÉS de cada test, aunque falle: cierra todo
    void tearDown() {
        driver.quit();
    }

    @Test
    void loginValidoMuestraProductos() {
        InventoryPage inventory = new LoginPage(driver).open().loginAs("standard_user", "secret_sauce");
        assertEquals("Products", inventory.getTitle());
    }

    @Test
    void usuarioBloqueadoVeMensajeDeError() {
        LoginPage login = new LoginPage(driver).open();
        login.loginAs("locked_out_user", "secret_sauce");
        assertTrue(login.getError().contains("locked out"));
    }

    @Test
    void agregarMochilaAlCarrito(){
        InventoryPage inventory = new LoginPage(driver).open().loginAs("standard_user", "secret_sauce");
        inventory.addBackpackToCart();
        assertEquals("1", inventory.getCartCount());
    }

    @Test
    void claveIncorrectaMuestraError() {
        LoginPage login = new LoginPage(driver).open();
        login.loginAs("standard_user", "123f993jfaslas");
        String error = login.getError();
        assertTrue(error.contains("do not match"), "El mensaje fue: " + error);
    }

}