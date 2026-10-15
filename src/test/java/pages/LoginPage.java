package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By erroMessagem = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage open(){
        driver.get("https://www.saucedemo.com/");
        return this;
    }


    public InventoryPage loginAs(String user, String pass){

        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();

        return new InventoryPage(driver);

    }

    public String getError(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(erroMessagem)).getText();
    }
}
