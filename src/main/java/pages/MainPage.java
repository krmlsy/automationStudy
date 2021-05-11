package pages;

import locators.LoginPageLocators;
import locators.MainPageLocators;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AutomationLib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage{

    public WebDriver driver;
    public AutomationLib al;

    @FindBy(xpath = MainPageLocators.CLOSE_POPUP)
    WebElement openingPopupCloseIcon;

    @FindBy(xpath = MainPageLocators.SEARCH_INPUT)
    WebElement searchInput;

    @FindBy(xpath = MainPageLocators.ARTICLE_A_ELEMENTS)
    List<WebElement> articleLinks;

    @FindBy(xpath = MainPageLocators.COMPANIES_IMAGES)
    List<WebElement> companyImages;

    @FindBy(xpath = MainPageLocators.OPEN_LOGIN_PAGE)
    WebElement openLoginPage;

    public MainPage(WebDriver driver){
        this.driver=driver;
        //Initialise Elements, all elements above found by their selectors.
        PageFactory.initElements(driver, this);
        this.al = new AutomationLib(this.driver);
    }



    // close openning popup
    public void closeOpeningPopup(){
        al.fluentClick(this.openingPopupCloseIcon);
    }


    // search product and enter
    public void searchForInput(String search){
        this.searchInput.sendKeys(search , Keys.ENTER);
    }


    public void scrollToBottom() throws InterruptedException {

        boolean isBottomOfThePage = false;

        while(!isBottomOfThePage){

            JavascriptExecutor js = (JavascriptExecutor) driver;
            isBottomOfThePage = (Boolean) js.executeScript(" window.scrollBy(0,screen.height/2);\n" +
                                "return (window.innerHeight + window.scrollY) >= document.body.offsetHeight;");

            TimeUnit.MILLISECONDS.sleep(750);

        }

    }

    // how many companies found in main page
    public Long getArticleCount(){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        return  (Long) js.executeScript("return document.getElementsByTagName('article').length;");
    }


    //Get all article elements and href links
    public ArrayList<String> getArticleLinks(){

        ArrayList<String> linkList = new ArrayList<>();

        for(int i = 0; i<articleLinks.size() ; i++){

            WebElement article = articleLinks.get(i);
            linkList.add(article.getAttribute("href"));
        }

        return linkList;
    }

    //Get all img src links from companies
    public ArrayList<String> getCompanyImages(){

        ArrayList<String> linkList = new ArrayList<>();

        for(int i = 0; i<companyImages.size() ; i++){

            WebElement article = companyImages.get(i);
            linkList.add(article.getAttribute("src"));
        }

        return linkList;
    }


    public void openLoginPage(){
        al.fluentClick(this.openLoginPage);
    }
}
