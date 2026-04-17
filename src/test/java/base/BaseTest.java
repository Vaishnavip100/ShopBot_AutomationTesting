package base;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import utils.ConfigReader;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
    protected ConfigReader config;

    @BeforeMethod
    public void setUp() {
        config=new ConfigReader();

        String browser=config.getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options=new ChromeOptions();

            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-save-password-bubble");

            HashMap<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            driver.set(new ChromeDriver(options));
        }        
       
        getDriver().manage().window().maximize();
        getDriver().get(config.getBaseUrl());

       
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get()!= null) {
            getDriver().quit();
            driver.remove();
        }
    }
}