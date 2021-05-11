package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class AutomationLib {

    public WebDriver driver;

    public AutomationLib(WebDriver driver){
        this.driver=driver;
    }

    public WebElement fluentWait(WebElement element){


        //wait for 180, and try every 3 seconds
        //ignore NoSuchElementException and StaleElementReferenceException and continue trying
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(180))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);


        WebElement waitElement = (WebElement) wait.until(ExpectedConditions.visibilityOf(element));

        return waitElement;
    }


    public void fluentClick(WebElement element){
        fluentWait(element).click();
    }

    public void fluentSendKeys(WebElement element, String text){
        fluentWait(element).sendKeys(text);
    }
}
