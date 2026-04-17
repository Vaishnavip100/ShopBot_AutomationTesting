package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

public class CartTest extends BaseTest {
    private ProductPage product;
    private CartPage cart;

    @BeforeMethod
    public void setupCart() {
        LoginPage lp=new LoginPage(getDriver());
        lp.login(config.getUser(), config.getPassword());

        product = new ProductPage(getDriver());
        cart = new CartPage(getDriver());
    }

    //Add one product → badge = 1
    @Test
    public void verifyAddSingleProduct() {
    	String productName="Sauce Labs Backpack";
    	product.addProductToCartByName(productName);
        Assert.assertEquals(cart.getCartBadgeCount(),1,"Cart badge should be 1");
    }

    //Add two products → badge = 2
    @Test
    public void verifyAddTwoProducts() {
    	String product1="Sauce Labs Fleece Jacket";
        String product2="Sauce Labs Bike Light";

        product.addProductToCartByName(product1);
        product.addProductToCartByName(product2);

        Assert.assertEquals(cart.getCartBadgeCount(),2,"Cart badge should be 2");
    }

    //Remove product from cart
    @Test
    public void verifyRemoveProductFromCart() {
    	String productName="Sauce Labs Bolt T-Shirt";

        product.addProductToCartByName(productName);
        cart.openCart();
        cart.removeProductByName(productName);

        Assert.assertTrue(cart.getCartItemsCount()==0,"Cart should be empty after removal");
    }

    //Cart retains items after navigation
    @Test
    public void verifyCartPersistence() {
    	String productName="Sauce Labs Onesie";

        product.addProductToCartByName(productName);

        cart.openCart();
        cart.continueShopping(); 

        cart.openCart();

        Assert.assertEquals(cart.getCartItemsCount(),1,"Cart should retain items after navigation");
    }
}