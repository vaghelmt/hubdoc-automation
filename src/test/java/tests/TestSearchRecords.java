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

/**
 * This class covers two test cases that tests Simple and
 * Advanced search features
 *
 * @author  Mitul Vaghela
 * @version 1.0
 * @since   2020-08-10
 */
public class TestSearchRecords extends BaseTest {
    public AdvancedSearch advancedSearch;

    @Test(description = "User is able to search documents by the name using the global search bar")
    public void simpleSearch_searchUsingDocumentName() {
        homePage.simpleSearch("Waterview");
        assertThat(homePage.getListOfUploadedDocuments(),everyItem(containsString("Waterview")));
    }

    @Test(description = "User is able to search documents by the amount using the global search bar")
    public void simpleSearch_searchUsingAmount() {
        homePage.simpleSearch("81.48");
        assertThat(homePage.getListOfAmountOfAllDocuments(),everyItem(containsString("81.48")));
    }

    @Test(description = "User is able to do an advanced fine grained search" +
            "on documents by using multiple filters and relational operators")
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
