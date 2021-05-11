package pages;

import locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AutomationLib;


public class LoginPage {

    public WebDriver driver;
    public AutomationLib al;



    @FindBy(id = LoginPageLocators.EMAIL_INPUT)
    WebElement emailInput;

    @FindBy(id = LoginPageLocators.PASSWORD_INPUT)
    WebElement passwordInput;

    @FindBy(xpath = LoginPageLocators.LOGIN_BUTTON)
    WebElement loginButton;

    @FindBy(xpath = LoginPageLocators.ERROR_MESSAGE)
    WebElement errorMessage;

    @FindBy(xpath = LoginPageLocators.LOGIN_HESABIM)
    WebElement hesabım;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        //Initialise Elements, all elements above found by their selectors.
        PageFactory.initElements(driver, this);
        this.al = new AutomationLib(this.driver);
    }




    public void enterEmail(String email){
        al.fluentSendKeys(emailInput, email);
    }

    public void enterPassword(String password){
        al.fluentSendKeys(passwordInput, password);
    }

    public void clickGirisYap(){
        al.fluentClick(this.loginButton);
    }

    public String getErrorMessage(){
        return al.fluentWait(this.errorMessage).getText();
    }

    public boolean checkLoginIsSuccessfull(){
       return al.fluentWait(hesabım).isDisplayed();
    }

}
