package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
    private static final Logger log = Logger.getLogger(BaseTest.class);

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void beforeSuiteSetUp() {
        //report will here
    }

    @AfterSuite
    public void afterSuiteTearDown() {

    }

    @BeforeTest
    @Parameters({ "browser", "implicitWait", "explicitWait","secretKey" })
    public void beforeTestRun(String browser,int implicitWait, int explicitWait, String secretKey) {
        log.info("***************Test Case Execution Starts********************");
        initWebDriver(browser);
        configureDriver(implicitWait, explicitWait);

    }

    @AfterTest
    public void afterTestRun() {
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
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "IE":
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
