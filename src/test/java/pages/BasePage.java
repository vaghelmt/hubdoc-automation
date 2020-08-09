package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {


    private static final Logger log = Logger.getLogger(BasePage.class);

    public WebDriver driver;
    public WebDriverWait wait;

    public String appURL;
    public Properties prop = new Properties();


    public BasePage(WebDriver driver) {
        log.info("BasePage(WebDriver driver) is invoked");
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);

        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties")) {

            // load a properties file

            prop.load(input);
            appURL = prop.getProperty("url");


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        PageFactory.initElements(driver, this);

        log.info("BasePage(WebDriver driver) is completed");
    }


    public BasePage() {
        log.info("BasePage() is invoked and there is nothing logic to handle here");
        // TODO Auto-generated constructor stub
    }

}

