package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtil;

public class LoginTest extends BaseTest {

    @DataProvider(name="loginData")
    public Object[][] getLoginData() {
        String path="src/test/resources/testdata/LoginData.xlsx";
        return ExcelUtil.getData(path, "Login");
    }

    @Test(dataProvider="loginData")
    public void verifyLoginScenarios(String username, String password, String scenario) {
        LoginPage lp=new LoginPage(getDriver());

        //Empty fields login
        if (scenario.equalsIgnoreCase("empty")) {
            lp.login(username, password);

            String error=lp.getErrorMessage();
            Assert.assertTrue(error.contains("Username is required"),"Expected error for empty credentials");
            return;
        }

        lp.login(username, password);

        switch (scenario.toLowerCase()) {

            case "valid":
                //Successful login
                Assert.assertTrue(lp.isLoginSuccessful(),"Login should be successful");

                //Logout validation
                lp.logout();
                Assert.assertTrue(lp.isAtLoginPage(),"User should be redirected to login page after logout");
                break;

            case "locked":
                //Locked user error
                String errorMsg=lp.getErrorMessage();
                Assert.assertTrue(errorMsg.contains("locked out"),"Locked user error message not shown");
                break;

            default:
                Assert.fail("Invalid scenario provided in Excel");
        }
    }
}