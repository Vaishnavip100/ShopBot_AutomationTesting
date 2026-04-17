package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    private By cartIcon=By.className("shopping_cart_link");
    private By cartBadge=By.className("shopping_cart_badge");
    private By cartItems=By.className("cart_item");
    private By removeBtn=By.xpath("//button[text()='Remove']");
    private By continueShoppingBtn=By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        click(cartIcon);
    }

    public int getCartBadgeCount() {
        try {
            return Integer.parseInt(getText(cartBadge));
        } catch (Exception e) {
            return 0;
        }
    }

    public void removeProductByName(String productName) {
        By removeBtn=By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button");
        click(removeBtn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(removeBtn));
    }

    public int getCartItemsCount() {
        return getElements(cartItems).size();
    }

    public void continueShopping() {
        click(continueShoppingBtn);
    }
}