package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Optional;

public class TransactionDetails extends BasePage {

    @FindBy(css = "#editor-document-type-div input")
    WebElement dpDocumentType;


    @FindBy(css = "#editor-vendor-id-div input")
    WebElement dpSupplier;


    @FindBy(css = "#editor-document-type-div .searchable-dropdown-option")
    List<WebElement> listOfDocumentType;


    @FindBy(css = "#editor-vendor-id-div .searchable-dropdown-option")
    List<WebElement> listOfSupplier;

    public String getCurrentValueOfDocumentType(){
        dpDocumentType.click();
        Optional<WebElement> currentlySelectionValue = listOfDocumentType.stream()
                .filter(e -> isAttribtuePresent(e,"hovered"))
                .findFirst();
        return currentlySelectionValue.get().getAttribute("data-value");

    }

    public String getCurrentValueOfSupplier(){
        dpSupplier.click();
        Optional<WebElement> currentlySelectionValue = listOfSupplier.stream()
                .filter(e -> isAttribtuePresent(e,"hovered"))
                .findFirst();
        return currentlySelectionValue.get().getText();

    }
}
