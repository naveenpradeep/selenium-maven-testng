package com.example.pages;

import com.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    // Locators
    private final By successMessage = By.className("post-title");
    private final By logoutButton = By.xpath("//a[text()='Log out']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeMessage() {
        return getText(successMessage);
    }

    public void logout() {
        click(logoutButton);
    }

    public boolean isDisplayed() {
        return isElementDisplayed(successMessage) && isElementDisplayed(logoutButton);
    }
}
