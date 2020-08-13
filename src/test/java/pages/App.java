package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This is a page class that represent the hubdoc application itself
 * It is the entry point to access other pages. For example, the login
 * method in this class returns a HomePage which could be further used
 * to interact with the application
 *
 * @author  Mitul Vaghela
 * @version 1.0
 * @since   2020-08-10
 */
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
        tbUserName.sendKeys(prop.getProperty("username"));
        tbPassword.sendKeys(prop.getProperty("password"));
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
