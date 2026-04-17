package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

public class CheckoutTest extends BaseTest {

    private ProductPage product;
    private CartPage cart;
    private CheckoutPage checkout;

    @BeforeMethod
    public void setupCheckout() {
        LoginPage lp= new LoginPage(getDriver());
        lp.login(config.getUser(),config.getPassword());

        product=new ProductPage(getDriver());
        cart=new CartPage(getDriver());
        checkout=new CheckoutPage(getDriver());

        product.addProductToCartByName("Sauce Labs Backpack");
        cart.openCart();
        checkout.clickCheckout();
    }

    //Fill details and navigate to summary
    @Test
    public void verifyCheckoutInformation() {
        checkout.enterCustomerDetails("Vaishnavi","Perumalla","520001");

        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout-step-two"),"User not navigated to overview page");
    }

    //Verify order summary
    @Test
    public void verifyOrderSummary() {
        String expectedProduct="Sauce Labs Backpack";

        checkout.enterCustomerDetails("Vaishnavi","Perumalla","520001");

        //Verify product name
        Assert.assertEquals(checkout.getProductName(),expectedProduct,"Product name mismatch");

        String itemPriceText=checkout.getProductPrice();
        double itemPrice=Double.parseDouble(itemPriceText.replace("$",""));

        String totalText=checkout.getTotalPrice();
        double totalPrice=Double.parseDouble(totalText.replaceAll("[^0-9.]",""));

        //Verify total price
        Assert.assertTrue(totalPrice > itemPrice,"Total price should include tax and be greater than item price");
    }

    //Complete order
    @Test
    public void verifyOrderCompletion() {
        checkout.enterCustomerDetails("vaishnavi","Perumalla","520001");
        checkout.finishOrder();

        Assert.assertTrue(checkout.getConfirmationMessage().contains("Thank you"),"Order not completed successfully");
    }
}