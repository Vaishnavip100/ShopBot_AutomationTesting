package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By username=By.id("user-name");
    private By password=By.id("password");
    private By loginBtn=By.id("login-button");
    private By errorMsg=By.xpath("//h3");

    private By menuBtn=By.id("react-burger-menu-btn");
    private By logoutBtn=By.id("logout_sidebar_link");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String user,String pass) {
        type(username,user);
        type(password,pass);
        click(loginBtn);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("inventory");
    }

    public void logout() {
        click(menuBtn);
        click(logoutBtn);
    }

    public boolean isAtLoginPage() {
        return driver.getCurrentUrl().contains("saucedemo");
    }
}