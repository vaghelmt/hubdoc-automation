package pages;

import enums.AmountFilters;
import enums.DateFilters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Optional;

public class AdvancedSearch extends BasePage{

    @FindBy(id = "advanced_search_date_type_chosen")
    WebElement dateField;

    @FindBy(xpath = "//div[@class='control-group date-field']//input[@value='created_at']")
    WebElement rbUploadDate;

    @FindBy(id = "advanced_search_amount_chosen")
    WebElement amountField;

    @CacheLookup
    @FindBy(css="#advanced_search_date_type_chosen .chosen-results li")
    List<WebElement> dateFilterOptions;

    @FindBy(css="#advanced_search_amount_chosen .chosen-results li")
    List<WebElement> AmountFilterOptions;


    @FindBy(id = "advanced-search-amount-value-1")
    WebElement tbAmountValue;

    @FindBy(id="advanced-search-search")
    WebElement btSearch;


    public AdvancedSearch setDateFilter(DateFilters dateFilter, String dateCategory){
        dateField.click();
        Optional<WebElement> dateFilterSelection = dateFilterOptions.stream()
                .filter(e -> e.getText().equals(getDateMapping(dateFilter)))
                .findFirst();

        dateFilterSelection.get().click();
        setDateCategory(dateCategory);
        return this;
    }

    private void setDateCategory(String dateCategory) {
        switch (dateCategory){
            case "Upload Date":
                rbUploadDate.click();
                break;
        }
    }

    public AdvancedSearch setAmountFilter(AmountFilters amountFilter, String value){
        amountField.click();
        Optional<WebElement> AmountFilterSelection = AmountFilterOptions.stream()
                .filter(e -> e.getText().equals(getAmountMapping(amountFilter)))
                .findFirst();
        AmountFilterSelection.get().click();
        setAmountValue(value);
        return this;
    }

    private void setAmountValue(String value) {
        tbAmountValue.sendKeys(value);
    }

    private String getAmountMapping(AmountFilters amountFilter) {
        String AmountFilterMappedValue = "All amounts";
        switch(amountFilter) {
            case MORE_THAN:
                AmountFilterMappedValue = "More than";
                break;
            case LESS_THAN:
                AmountFilterMappedValue= "Less than";
                break;
            case RANGE:
                AmountFilterMappedValue= "Range";
                break;
            case EQUALS:
                AmountFilterMappedValue= "Equals";
                break;
            case ALL_AMOUNTS:
                AmountFilterMappedValue= "All amounts";
        }

        return AmountFilterMappedValue;
    }

    private String getDateMapping(DateFilters dateFilters) {
        String dateFilterMappedValue = "All dates";
        switch(dateFilters) {
            case This_month:
                dateFilterMappedValue = "This month";
                break;
            case Last_month:
                dateFilterMappedValue= "Last month";
                break;
            case This_quarter:
                dateFilterMappedValue= "This quarter";
                break;
            case Last_quarter:
                dateFilterMappedValue= "Last quarter";
                break;
            case This_year:
                dateFilterMappedValue= "This year";
                break;
            case Last_year:
                dateFilterMappedValue = "Last year";
                break;
            case All_Dates:
                dateFilterMappedValue = "All dates";
                break;
            case Custom_date_range:
                dateFilterMappedValue = "Custom date range";
        }

        return dateFilterMappedValue;
    }

    public void search() {
        btSearch.click();
    }
}
