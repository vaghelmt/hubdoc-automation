package pages;

import utilities.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;

public class uploadDocumentModal extends BasePage {

    @FindBy(id = "uploadifive-file-upload")
    WebElement btBrowse;


    @FindBy(id = "uploadifive-file-upload-file-0")
    WebElement uploadedFile;

    @FindBy(css = ".uploadifive-queue-item.complete")
    WebElement uploadStatus;

    @FindBy(css = "button.close-modal")
    WebElement closeModal;

    public uploadDocumentModal uploadFile(String filePath) throws AWTException {
        btBrowse.click();
        Utils.fileUpload(filePath);
        wait.until(ExpectedConditions.visibilityOf(uploadStatus));
        return this;
    }

    public void closeModal() {
        closeModal.click();

    }
}
