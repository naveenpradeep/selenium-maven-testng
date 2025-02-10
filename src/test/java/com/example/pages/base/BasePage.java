package com.example.pages.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    private static final int TIMEOUT = 10;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    protected WebElement waitForElementVisible(By locator){
        logger.debug("Waiting for element to be visible : {}", locator);
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator){
        logger.debug("Clicking element: {}", locator);
        waitForElementVisible(locator).click();
    }

    protected void type(By locator, String text){
        logger.debug("Typing '{}' in to element: {}", text, locator);
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator){
        logger.debug("Getting text from element: {}", locator);
        return waitForElementVisible(locator).getText();
    }

    protected Boolean isElementDisplayed(By locator){
        try {
           return waitForElementVisible(locator).isDisplayed();
        } catch(Exception e) {
            logger.debug("Element not displayed: {}", locator);
            return false;
        }
    }
}
