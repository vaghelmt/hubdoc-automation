package pages;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utils;

/**
 * This is Base page class that is expected to be extended
 * by all the page classes.
 * It takes care of object initialization for all the child pages
 *
 *
 * @author  Mitul Vaghela
 * @version 1.0
 * @since   2020-08-10
 */

public class BasePage {


    private static final Logger log = Logger.getLogger(BasePage.class);

    public static WebDriver driver;
    public static WebDriverWait wait;

    public String appURL;
    public Properties prop = new Properties();

    public static Robot robot;

    public BasePage(WebDriver driver) {
        log.info("BasePage(WebDriver driver) is invoked");
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);

        //instantiate robot class here so that all the pages can use key and mouse strokes when required
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        //loading the configurations
        prop = Utils.loadProperties();
        appURL = prop.getProperty("url");
        PageFactory.initElements(driver, this);

        log.info("BasePage(WebDriver driver) is completed");
    }


    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public static boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

}

