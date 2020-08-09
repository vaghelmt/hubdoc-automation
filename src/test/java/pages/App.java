package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class App extends BasePage {

    private static final Logger log = Logger.getLogger(App.class);

    @FindBy(id="email")
    WebElement tbUserName;

    @FindBy(id="password")
    WebElement tbPassword;

    @FindBy(id="btn-signin")
    WebElement btSignInSecurely;



    //home page constructor
    public App(WebDriver driver) {
        super(driver);
        launchApp(appURL);
        log.info("Home page constructor is invoked");
        log.info("Home page objects are initialized successfully and constructor ends");
    }

    //public actions
    public HomePage login(){
        tbUserName.sendKeys("mitul.vaghela@gmail.com");
        tbPassword.sendKeys("Test@1234");
        btSignInSecurely.click();
        return new HomePage();
    }
    //private actions to be used in page classes only
    private void launchApp(String url) {
        log.info("launchApp(String url) is invoked");
        driver.get(url);
        log.info("launchApp(String url) is completed");

    }


}
