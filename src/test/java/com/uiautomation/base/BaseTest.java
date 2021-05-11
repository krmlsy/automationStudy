package com.uiautomation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.base.BasePage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    public ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public ThreadLocal<BasePage> basePage = new ThreadLocal<>();


    @BeforeSuite
    public void before(){

        // if csv files exists delete them
        File imgsFile = new File("companyImagesResponseCodes.csv");
        if(imgsFile.exists())
            imgsFile.delete();

        File companyFile = new File("companyResponseCodes.csv");
        if(companyFile.exists())
            companyFile.delete();
    }

    @BeforeMethod
    @Parameters({"browser", "author"})
    public void init(String browser, String author, ITestContext iTestContext) throws MalformedURLException {


        //driver setup by browser
        if("chrome".equals(browser)){
            ChromeOptions options = new ChromeOptions();

            options.setCapability("browserName" , "chrome");
            options.setCapability("platform" , "LINUX");

            this.driver.set(new RemoteWebDriver(new URL("http://127.0.0.1:4446/wd/hub"), options));

        }
        if("firefox".equals(browser)){
            FirefoxOptions options = new FirefoxOptions();

            options.setCapability("browserName" , "firefox");
            options.setCapability("platform" , "LINUX");

            this.driver.set(new RemoteWebDriver(new URL("http://127.0.0.1:4446/wd/hub"), options));
        }

        // set driver, browser and author for listener and extentreports
        iTestContext.setAttribute("driver", this.driver.get());
        iTestContext.setAttribute("browser", browser);
        iTestContext.setAttribute("author", author);

        // create a new BasePage object for page objects
        basePage.set(new BasePage(this.driver.get()));
    }

    @AfterMethod
    public void afterTest(){
        // kill driver.
        driver.get().quit();
    }
}
