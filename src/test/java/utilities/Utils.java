package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * This is a utility class that incorporate helper methods that
 * helps with small repeatable tasks
 *
 * @author Mitul Vaghela
 * @version 1.0
 * @since 2020-08-10
 */
public class Utils {

    public static String ExternalScriptsDirectory;

    public Utils() {
    }

    public static void initUtilities(Properties prop) {
        ExternalScriptsDirectory = prop.getProperty("ExternalScriptsDirectory");

    }

    public static void fileUpload(String filePath) {
        try {
            ProcessBuilder pb = new ProcessBuilder(System.getProperty("user.dir") +
                    ExternalScriptsDirectory + "uploadFile.exe",
                    filePath);

            pb.start();
            Thread.sleep(10000);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickWithRetry(WebDriver driver, By locator) {
        boolean staleElement = true;
        while (staleElement) {
            try {
                driver.findElement(locator).click();
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }

    }

    public static void sendKeyWithRetry(WebDriver driver, By locator, String searchString) {
        boolean staleElement = true;
        while (staleElement) {
            try {
                driver.findElement(locator).sendKeys(searchString);
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }

    public static Properties loadProperties() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties")) {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return prop;

    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;

    }
}
