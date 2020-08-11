package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Utils {
    public Utils() {
    }


    public static void fileUpload(String filePath){
        try {
            ProcessBuilder pb = new ProcessBuilder(System.getProperty("user.dir") +
                    "\\src\\test\\resources\\scripts\\uploadFile.exe", filePath);
            pb.start();
            Thread.sleep(10000);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickWithRetry(WebDriver driver, By locator){
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
}
