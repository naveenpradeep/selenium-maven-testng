package com.example.pages;

import com.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    // Locators
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("submit");
    private final By errorMessage = By.id("error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String username){
        type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password){
        type(passwordInput, password);
        return this;
    }

    public DashboardPage clickLoginButton (){
        click(loginButton);
        return new DashboardPage(driver);
    }

    public DashboardPage login(String username, String password){
        logger.info("Performing login with username: {}", username);
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }

    public String getErrorMessage(){
        return getText(errorMessage);
    }

    public boolean isDisplayed(){
        return isElementDisplayed(usernameInput) && isElementDisplayed(passwordInput) && isElementDisplayed(loginButton);
    }

}
