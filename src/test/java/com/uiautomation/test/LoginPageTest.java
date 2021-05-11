package com.uiautomation.test;

import com.uiautomation.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;


public class LoginPageTest extends BaseTest {



    @Test
    public void loginSuccesfull(ITestContext iTestContext){

        //Page Objects
        WebDriver driverL = driver.get();
        MainPage mainPage = new MainPage(driverL);
        LoginPage loginPage = new LoginPage(driverL);


        //go to site
        driverL.get("https://www.trendyol.com/");


        //Maximize
        driverL.manage().window().maximize();


        //close popup and scroll to bottom
        mainPage.closeOpeningPopup();

        mainPage.openLoginPage();

        loginPage.enterEmail("keremulusoy.1905@gmail.com");
        loginPage.enterPassword("keremulusoy");

        loginPage.clickGirisYap();

        Assert.assertEquals(true,loginPage.checkLoginIsSuccessfull() ,"Scenario failed, login operation is failed");

    }

    @Test
    public void WrongEmailLogin(ITestContext iTestContext){

        //Page Objects
        WebDriver driverL = driver.get();
        MainPage mainPage = new MainPage(driverL);
        LoginPage loginPage = new LoginPage(driverL);


        //go to site
        driverL.get("https://www.trendyol.com/");


        //Maximize
        driverL.manage().window().maximize();


        //close popup and scroll to bottom
        mainPage.closeOpeningPopup();

        mainPage.openLoginPage();

        loginPage.enterEmail("keremo@gmail.com");
        loginPage.enterPassword("keremulusoy");

        loginPage.clickGirisYap();

        Assert.assertEquals(loginPage.getErrorMessage(),"E-posta adresiniz ve/veya şifreniz hatalı." ,"Scenario failed, because of the error message!!");

    }

    @Test
    public void WrongPasswordLogin(ITestContext iTestContext){

        //Page Objects
        WebDriver driverL = driver.get();
        MainPage mainPage = new MainPage(driverL);
        LoginPage loginPage = new LoginPage(driverL);


        //go to site
        driverL.get("https://www.trendyol.com/");


        //Maximize
        driverL.manage().window().maximize();


        //close popup and scroll to bottom
        mainPage.closeOpeningPopup();

        mainPage.openLoginPage();

        loginPage.enterEmail("keremulusoy.1905@gmail.com");
        loginPage.enterPassword("1234566");

        loginPage.clickGirisYap();

        Assert.assertEquals(loginPage.getErrorMessage(),"E-posta adresiniz ve/veya şifreniz hatalı." ,"Scenario failed, because of the error message!!");

    }


    @Test
    public void EmptyEmailLogin(ITestContext iTestContext){

        //Page Objects
        WebDriver driverL = driver.get();
        MainPage mainPage = new MainPage(driverL);
        LoginPage loginPage = new LoginPage(driverL);


        //go to site
        driverL.get("https://www.trendyol.com/");


        //Maximize
        driverL.manage().window().maximize();


        //close popup and scroll to bottom
        mainPage.closeOpeningPopup();

        mainPage.openLoginPage();

        loginPage.enterEmail("");
        loginPage.enterPassword("keremulusoy");

        loginPage.clickGirisYap();

        Assert.assertEquals(loginPage.getErrorMessage(),"Lütfen geçerli bir e-posta adresi giriniz." ,"Scenario failed, because of the error message!!");

    }

    @Test
    public void EmptyPasswordLogin(ITestContext iTestContext){

        //Page Objects
        WebDriver driverL = driver.get();
        MainPage mainPage = new MainPage(driverL);
        LoginPage loginPage = new LoginPage(driverL);


        //go to site
        driverL.get("https://www.trendyol.com/");


        //Maximize
        driverL.manage().window().maximize();


        //close popup and scroll to bottom
        mainPage.closeOpeningPopup();

        mainPage.openLoginPage();

        loginPage.enterEmail("keremulusoy.1905@gmail.com");
        loginPage.enterPassword("");

        loginPage.clickGirisYap();

        Assert.assertEquals(loginPage.getErrorMessage(),"Lütfen şifrenizi giriniz." ,"Scenario failed, because of the error message!!");

    }

    @Test
    public void fullEmptyLogin(ITestContext iTestContext){

        //Page Objects
        WebDriver driverL = driver.get();
        MainPage mainPage = new MainPage(driverL);
        LoginPage loginPage = new LoginPage(driverL);


        //go to site
        driverL.get("https://www.trendyol.com/");


        //Maximize
        driverL.manage().window().maximize();


        //close popup and scroll to bottom
        mainPage.closeOpeningPopup();

        mainPage.openLoginPage();

        loginPage.enterEmail("");
        loginPage.enterPassword("");

        loginPage.clickGirisYap();

        Assert.assertEquals(loginPage.getErrorMessage(),"Lütfen geçerli bir e-posta adresi giriniz." ,"Scenario failed, because of the error message!!");

    }
}
