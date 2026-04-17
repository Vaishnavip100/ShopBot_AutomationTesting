package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductTest extends BaseTest {

    private ProductPage product;

    //Login before each test
    @BeforeMethod
    public void loginToApp() {
        LoginPage lp=new LoginPage(getDriver());
        lp.login(config.getUser(),config.getPassword());

        product=new ProductPage(getDriver());
    }

    //Verify product list is displayed
    @Test
    public void verifyProductListDisplayed() {
        Assert.assertTrue(product.getProductCount()>0,"No products displayed");
    }

    //Sort by Name (A → Z)
    @Test
    public void verifySortByNameAZ() {
        product.sortBy("az");

        List<String> actualNames=new ArrayList<>();
        product.getAllProductNames().forEach(e -> actualNames.add(e.getText()));

        List<String> sortedNames=new ArrayList<>(actualNames);
        Collections.sort(sortedNames);

        Assert.assertEquals(actualNames,sortedNames,"Products are not sorted A-Z");
    }

    //Sort by Price (Low → High)
    @Test
    public void verifySortByPriceLowToHigh() {
        product.sortBy("lohi");

        List<Double> actualPrices=new ArrayList<>();
        product.getAllProductPrices().forEach(e ->actualPrices.add(Double.parseDouble(e.getText().replace("$",""))));

        List<Double> sortedPrices=new ArrayList<>(actualPrices);
        Collections.sort(sortedPrices);

        Assert.assertEquals(actualPrices,sortedPrices,"Prices are not sorted low to high");
    }

    //Product detail validation
    @Test
    public void verifyProductDetailMatchesListing() {
        String listName=product.getFirstProductName();
        String listPrice=product.getAllProductPrices().get(0).getText();

        product.openFirstProduct();
        
        Assert.assertEquals(product.getDetailName(),listName,"Product name mismatch");

        Assert.assertEquals(product.getDetailPrice(),listPrice,"Product price mismatch");
    }
}