package com.example.tests.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class TestBase {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    @BeforeSuite
    public void setUpReport(){
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extent-report.html");
        extent.attachReporter(spark);
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser){
        logger.info("Setting up WebDriver for browser: {}", browser);

        switch (browser.toLowerCase()){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().driverVersion("132.0.6834.160").setup();
                driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver");
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }

    protected void navigateToUrl(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
    }
}
