package com.example.tests;

import com.example.pages.DashboardPage;
import com.example.pages.LoginPage;
import com.example.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    private LoginPage loginPage;
    private static final String BASE_URL = "https://practicetestautomation.com/practice-test-login/";

    @BeforeMethod
    public void setUpTest(){
        navigateToUrl(BASE_URL);
        loginPage = new LoginPage(driver);
        test = extent.createTest(getClass().getName());
    }

    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin(){
        Assert.assertTrue(loginPage.isDisplayed(), "Login page displayed");
        test.info("Starting successful login test");
        DashboardPage dashboardPage = loginPage.login("student", "Password123");
        Assert.assertTrue(dashboardPage.isDisplayed(), "Dashboard page displayed after successful login");
        Assert.assertTrue(dashboardPage.getWelcomeMessage().contains("Logged In Successfully"),
                "Welcome message displayed");

        test.pass("Login test passed successfully");
    }

    @Test(description = "Verify failed login with invalid credentials")
    public void testFailedLogin(){
        Assert.assertTrue(loginPage.isDisplayed(), "Login Page displayed");
        test.info("Starting failed login test");
        DashboardPage dashboardPage = loginPage.login("student", "invalidpass");

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Your password is invalid!",
                "Incorrect error message displayed");

        test.pass("Failed login test passed successfully");
    }


}
