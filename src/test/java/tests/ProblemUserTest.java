package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProblemUserTest extends BaseTest {
    private ProductPage product;

    @BeforeMethod
    public void setupProblemUser() {
        LoginPage login=new LoginPage(getDriver());
        login.login("problem_user",config.getPassword());

        product=new ProductPage(getDriver());
    }

    //Verify broken behavior on product listing page (images issue)
    @Test
    public void verifyBrokenProductImages() {
        List<WebElement> images=product.getProductImages();

        Set<String> uniqueImages=new HashSet<>();

        for (WebElement img : images) {
            uniqueImages.add(img.getAttribute("src"));
        }

        Assert.assertTrue(uniqueImages.size() < images.size(),"Expected duplicate/broken images for problem_user");
    }

    //Attempt to add product and verify behavior
    @Test
    public void verifyAddToCartWithProblemUser() {
        String productName="Sauce Labs Backpack";
        product.addProductToCartByName(productName);

        Assert.assertTrue(product.isProductAddedToCart(),"Product should still be added even for problem_user");
    }

    //Verify images mismatch with product names
    @Test
    public void verifyProductImageMismatch() {
        Assert.assertTrue(product.isAnyProductImageMismatched(),"Expected image mismatch for problem_user");
    }
}
