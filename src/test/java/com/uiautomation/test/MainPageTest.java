package com.uiautomation.test;

import com.uiautomation.base.BaseTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pages.MainPage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.when;

public class MainPageTest extends BaseTest {

    public ArrayList<String> companylinks;
    public ArrayList<String> companyImgs;

    @Test(priority = 0)
    public void scrollToBottom(ITestContext iTestContext) throws InterruptedException, IOException {

        //Main Page Object
        MainPage mainPage = new MainPage(driver.get());

        //go to site
        driver.get().get("https://www.trendyol.com/");


        //Maximize
        driver.get().manage().window().maximize();

        //close popup and scroll to bottom
        mainPage.closeOpeningPopup();

        mainPage.scrollToBottom();

        //wait companies for loading
        TimeUnit.SECONDS.sleep(10);

        // get article(Company) count and assertion
        Long i = mainPage.getArticleCount();

        System.out.println(i);
        Assert.assertTrue(663L > i , "Butikler tam olarak çekilemedi!!");


        //Get All Company Links
        companylinks = mainPage.getArticleLinks();


        //Get All Company Img links
        companyImgs = mainPage.getCompanyImages();

        iTestContext.setAttribute("companyImgs", companyImgs);

    }


    @Test(priority = 1)
    public void checkAllCompanyLinks() throws IOException {

        checkAllUrls("companyResponseCodes.csv", companylinks);

    }


    @Test(priority = 1)
    public void checkImgLoadTimes() throws IOException {
        checkAllUrls("companyImagesResponseCodes.csv", companyImgs);
    }


    // all given links called and write to csv file
    public void checkAllUrls(String fileName, ArrayList<String> links) throws IOException {
        for(String link : links){
            getUrlAndWriteCSV(fileName, link);
        }
    }

    // call given url by restassured & get statuseCode and responseTime
    public void getUrlAndWriteCSV (String fileName, String url) throws IOException {
        Response response = when().get(url);

        int statusCode = response.getStatusCode();

        Long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);

        writeToCSV(fileName, url, statusCode, responseTime);

    }

    // write url, statusCode and responseTime to a given file
    public void writeToCSV(String fileName, String url, int statusCode, Long responseTime) throws IOException{
        FileWriter pw = null;

        pw = new FileWriter(fileName , true);

        // url ; statusCode ; responseTime
        pw.append(url+";"+statusCode+";"+responseTime+"\n");

        pw.flush();
        pw.close();
    }

}



