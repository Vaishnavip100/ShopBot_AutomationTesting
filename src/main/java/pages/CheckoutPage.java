package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private By checkoutBtn=By.id("checkout");

    private By firstName=By.id("first-name");
    private By lastName=By.id("last-name");
    private By postalCode=By.id("postal-code");
    private By continueBtn=By.id("continue");

    private By productName=By.className("inventory_item_name");
    private By productPrice=By.className("inventory_item_price");
    private By totalPrice=By.className("summary_total_label");

    private By finishBtn=By.id("finish");
    private By confirmationMsg=By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckout() {
        click(checkoutBtn);
    }

    public void enterCustomerDetails(String fName,String lName,String zip) {
        type(firstName,fName);
        type(lastName,lName);
        type(postalCode,zip);
        click(continueBtn);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public String getTotalPrice() {
        return getText(totalPrice);
    }

    public void finishOrder() {
        click(finishBtn);
    }

    public String getConfirmationMessage() {
        return getText(confirmationMsg);
    }
}