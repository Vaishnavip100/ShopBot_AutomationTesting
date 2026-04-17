package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends BasePage {
    private By productList=By.className("inventory_item");
    private By productNames=By.className("inventory_item_name");
    private By productPrices=By.className("inventory_item_price");
    private By sortDropdown=By.className("product_sort_container");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public int getProductCount() {
        return getElements(productList).size();
    }

    public void sortBy(String value) {
        Select select = new Select(waitForElement(sortDropdown));
        select.selectByValue(value);
    }

    public String getFirstProductName() {
        return getElements(productNames).get(0).getText();
    }

    public List<WebElement> getAllProductNames() {
        return getElements(productNames);
    }

    public List<WebElement> getAllProductPrices() {
        return getElements(productPrices);
    }

    public void openFirstProduct() {
        getElements(productNames).get(0).click();
    }

    public String getDetailName() {
        return getText(By.className("inventory_details_name"));
    }

    public String getDetailPrice() {
        return getText(By.className("inventory_details_price"));
    }
    
    //For CartTest
    public void addProductToCartByName(String productName) {
        By addBtn=By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        click(addBtn);
    }

    public void openProductByName(String productName) {
        By product=By.xpath("//div[text()='" + productName + "']");
        click(product);
    }

    public String getPriceByProductName(String productName) {
        By price=By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']");
        return getText(price);
    }
    
    
    //For ProblemUserTest
    public List<WebElement> getProductImages() {
        return getElements(By.className("inventory_item_img"));
    }
    
    public boolean isProductAddedToCart() {
        return getElements(By.className("shopping_cart_badge")).size() > 0;
    }
    
    public List<WebElement> getAllProducts() {
        return getElements(By.className("inventory_item"));
    }
    
    public boolean isAnyProductImageMismatched() {
        List<WebElement> products=getElements(By.className("inventory_item"));
        for (WebElement item : products) {
            String name=item.findElement(By.className("inventory_item_name")).getText();
            String imageSrc=item.findElement(By.tagName("img")).getAttribute("src");
            if (!imageSrc.toLowerCase().contains(name.split(" ")[2].toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}