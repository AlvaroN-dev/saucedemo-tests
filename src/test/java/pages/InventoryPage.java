package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By title = By.className("title");
    private final By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle(){
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public InventoryPage addBackpackToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addBackpack)).click();
        return this;
    }

    public String getCartCount(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

}
