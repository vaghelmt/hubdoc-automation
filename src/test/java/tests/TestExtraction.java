package tests;

import org.hamcrest.Description;
import org.testng.annotations.Test;
import pages.TransactionDetails;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * This class covers test cases that validates the
 * data extraction feature
 *
 * @author  Mitul Vaghela
 * @version 1.0
 * @since   2020-08-10
 */
public class TestExtraction extends BaseTest{

    public TransactionDetails transactionDetails;

    //For this test we have assumed that a document for Foxglove Studios was uploaded
    //and successfully extracted. The focus of the test is to validate that the extracted
    //values matches with the document
    @Test(description = "Document Type value is extracted from the uploaded document")
    public void CompareExtractedValueOfDocumentType(){
        transactionDetails = homePage.selectExistingDocument("Foxglove Studios");
        assertThat(transactionDetails.getCurrentValueOfDocumentType(),is(equalTo("Invoice")));
    }


    @Test(description = "Supplier name is extracted from the uploaded document")
    public void CompareExtractedValueOfSupplier(){
        transactionDetails = homePage.selectExistingDocument("Foxglove Studios");
        assertThat(transactionDetails.getCurrentValueOfSupplier(),is(equalTo("Foxglove Studios")));
    }


}
