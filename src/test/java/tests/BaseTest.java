package tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.App;
import pages.HomePage;
import reporters.ExtentReporter;
import utilities.Utils;

/**
 * This is a Base Test class that is expected to be extended
 * by all the test classes.
 * It takes care of environment set up and tear down
 * It also takes care of test configurations
 *
 * @author  Mitul Vaghela
 * @version 1.0
 * @since   2020-08-10
 */
public class BaseTest {
    private static final Logger log = Logger.getLogger(BaseTest.class);

    public App hubdocApp;
    public HomePage homePage;

    public WebDriver driver;
    public WebDriverWait wait;

    public static Properties prop;

    public static String testDataDirectory;


    @BeforeSuite
    public void beforeSuiteSetUp() {
        prop = Utils.loadProperties();
        initResourcesAndUtilities();

    }

    @AfterSuite
    public void afterSuiteTearDown() {

    }


    @BeforeMethod
    public void beforeMethodRun() {
        log.info("***************Test Case Execution Starts********************");
        //loading properties file
//        prop = Utils.loadProperties();
//        initResourcesAndUtilities();
        initWebDriver(prop.getProperty("browser"));
        configureDriver(prop.getProperty("implicitWait"), prop.getProperty("explicitWait"));
        ExtentReporter.assignDriver(driver);
        hubdocApp = new App(driver);
        homePage = hubdocApp.login();

    }

    private void initResourcesAndUtilities() {
        testDataDirectory = prop.getProperty("TestDataDirectory");
        Utils.initUtilities(prop);
    }

    @AfterMethod
    public void afterMethodRun(ITestResult result) {
        closeWebDriver();
        log.info("***************Test Case Execution Ends********************");
    }

    private void initWebDriver(String browser) {
        /**
         * Initialize webdriver as per configuration file
         */
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
            log.info("Driver initialization is completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureDriver(String implicitWait, String explicitWait) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(implicitWait), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Integer.parseInt(explicitWait));
        log.info("Driver configuration is completed");
    }

    private void closeWebDriver() {
        driver.quit();
    }

}
