package tests;

import org.testng.annotations.Test;
import pages.TransactionDetails;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestExtraction extends BaseTest{

    public TransactionDetails transactionDetails;

    @Test
    public void CompareExtractedValueOfDocumentType(){
        transactionDetails = homePage.selectExistingDocument("Foxglove Studios");
        assertThat(transactionDetails.getCurrentValueOfDocumentType(),is(equalTo("Invoice")));
    }


    @Test
    public void CompareExtractedValueOfSupplier(){
        transactionDetails = homePage.selectExistingDocument("Foxglove Studios");
        assertThat(transactionDetails.getCurrentValueOfSupplier(),is(equalTo("Foxglove Studios")));
    }


}
