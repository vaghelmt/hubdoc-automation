package tests;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.App;
import pages.HomePage;

public class BaseTest {
    private static final Logger log = Logger.getLogger(BaseTest.class);

    public App hubdocApp;
    public HomePage homePage;

    public WebDriver driver;
    public WebDriverWait wait;



    @BeforeSuite
    public void beforeSuiteSetUp() throws AWTException {

    }

    @AfterSuite
    public void afterSuiteTearDown() {

    }

    @BeforeMethod
    @Parameters({ "browser", "implicitWait", "explicitWait","secretKey" })
    public void beforeMethodRun(String browser,int implicitWait, int explicitWait, String secretKey) {
        log.info("***************Test Case Execution Starts********************");
        initWebDriver(browser);
        configureDriver(implicitWait, explicitWait);
        hubdocApp = new App(driver);
        homePage = hubdocApp.login();

    }

    @AfterMethod
    public void afterMethodRun() {
        closeWebDriver();
        log.info("***************Test Case Execution Ends********************");
    }

    private void initWebDriver(String browser) {
        /**
         * Initialize webdriver as per configuration file
         */
        log.info("initWebDriver(String browser, String grid) invoked");
        try {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "IE":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                default:
                    break;
            }
            log.info("initWebDriver(String browser, String grid) is completed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void configureDriver(int implicitWait, int explicitWait) {

        log.info("configureDriver(int implicitWait, int explicitWait) is invoked");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, explicitWait);
        log.info("configureDriver(int implicitWait, int explicitWait) is completed");
    }


    private void closeWebDriver() {
        driver.quit();
    }

}
