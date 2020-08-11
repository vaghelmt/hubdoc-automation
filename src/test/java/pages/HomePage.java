package pages;

import utilities.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    @FindBy(id = "add-receipt")
    WebElement btUploadDocument;

    @FindBy(xpath = "//div[@id='document-items']//span[@class='filename' or @class='biller-name']")
    List<WebElement> listUploadedDocuments;

    @FindBy(xpath = "//div[@id='document-items']//span[@class='received-date']")
    List<WebElement> listOfDatesOfUploadedDocuments;

    @FindBy(xpath = "//div[@id='document-items']//span[@class='amount']")
    List<WebElement> listOfAmountsOfUploadedDocuments;

    @FindBy(css = "#document-controls .delete-btn")
    WebElement btDelete;

    @FindBy(css = ".modal-footer .ok")
    WebElement btConfirmDelete;

    By tbSearchBar = By.cssSelector("#search input");

    By btAdvancedSearch = By.id("advanced-search-button");

    public Boolean isDisplayed() {
        return btUploadDocument.isDisplayed();
    }

    public uploadDocumentModal openUploadDocumentModal() {
        btUploadDocument.click();
        return new uploadDocumentModal();
    }

    public List<String> getListOfUploadedDocuments() {
        return listUploadedDocuments.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

    }

    public void deleteAFile(String fileName) {
        Optional<WebElement> fileToBeDeleted = listUploadedDocuments.stream()
                .filter(e -> e.getText().equals(fileName))
                .findFirst();

        fileToBeDeleted.get().click();
        btDelete.click();
        btConfirmDelete.click();

    }

    public TransactionDetails selectExistingDocument(String fileName) {
        Optional<WebElement> fileToBeDeleted = listUploadedDocuments.stream()
                .filter(e -> e.getText().equals(fileName))
                .findFirst();

        fileToBeDeleted.get().click();

        return new TransactionDetails();

    }

    public AdvancedSearch openAdvancedSearch() {
        Utils.clickWithRetry(driver, btAdvancedSearch);
        return new AdvancedSearch();
    }

    public List<String> getListOfDatesOfAllDocuments() {
        return listOfDatesOfUploadedDocuments.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public  List<String> getListOfAmountOfAllDocuments() {
        return listOfAmountsOfUploadedDocuments.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public HomePage simpleSearch(String searchString) {
        Utils.sendKeyWithRetry(driver, tbSearchBar,searchString);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        return this;
    }
}
