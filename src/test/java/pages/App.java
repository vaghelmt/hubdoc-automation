package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class App extends BasePage {

    private static final Logger log = Logger.getLogger(App.class);
    //home page constructor
    public App(WebDriver driver) {
        super(driver);
        launchApp(appURL);
        log.info("Home page constructor is invoked");
        log.info("Home page objects are initialized successfully and constructor ends");
    }

    //private actions to be used in page classes only
    private void launchApp(String url) {
        log.info("launchApp(String url) is invoked");
        driver.get(url);
        log.info("launchApp(String url) is completed");

    }
}
