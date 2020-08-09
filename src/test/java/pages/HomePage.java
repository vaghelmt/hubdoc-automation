package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    @FindBy(id = "add-receipt")
    WebElement btUploadDocument;

    @FindBy(css = "#document-items .filename")
    List<WebElement> listUploadedDocuments;

    public Boolean isDisplayed(){
        return btUploadDocument.isDisplayed();
    }

    public uploadDocumentModal openUploadDocumentModal(){
        btUploadDocument.click();
        return new uploadDocumentModal();
    }

    public List<String> getListOfUploadedDocuments(){
        return listUploadedDocuments.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

    }

}
