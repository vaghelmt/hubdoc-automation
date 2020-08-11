package tests;

import enums.AmountFilters;
import enums.DateFilters;
import org.testng.annotations.Test;
import pages.AdvancedSearch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestSearchRecords extends BaseTest {
    public AdvancedSearch advancedSearch;

    @Test
    public void simpleSearch_searchUsingDocumentName() {
        homePage.simpleSearch("Waterview");
        assertThat(homePage.getListOfUploadedDocuments(),everyItem(containsString("Waterview")));
    }

    @Test()
    public void advancedSearch_searchUsingDateAndAmount() {
        advancedSearch = homePage.openAdvancedSearch();
        advancedSearch.setDateFilter(DateFilters.This_month, "Upload Date")
                .setAmountFilter(AmountFilters.MORE_THAN, "80")
                .search();
        Boolean isAmountMatching = homePage.getListOfAmountOfAllDocuments().stream()
                .map(e -> e.substring(1, e.length() - 4))
                .map(e -> e.replace(",", ""))
                .map(Float::parseFloat)
                .allMatch(e -> e > 80);

        assertThat(isAmountMatching, is(equalTo(true)));

    }
}
