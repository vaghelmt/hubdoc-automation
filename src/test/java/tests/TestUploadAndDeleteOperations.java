package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * This class covers two test cases that test the upload and
 * download operations available in hubdoc
 *
 * @author  Mitul Vaghela
 * @version 1.0
 * @since   2020-08-10
 */
public class TestUploadAndDeleteOperations extends BaseTest {

    @DataProvider
    public Object[] testDataForTestUploadDocument(Method testMethod){

        switch (testMethod.getName()){
            case "documentUploadedSuccessfully":
            case "documentDeletedSuccessfully":
                return new Object[]{"Registration_error.PNG","restaurant-receipt-with-tip-sample.jpg"};
            case "messageDisplayedForUnsupportedDocumentType":
                return new Object[]{"unsupported.xlsx"};
            default:
                return new Object[]{};

        }

    }
    @Test(dataProvider = "testDataForTestUploadDocument",
    description = "user is able to upload a document successfully")
    public void documentUploadedSuccessfully(String fileToUpload) {
        try {
            homePage.openUploadDocumentModal()
                    .uploadFile(System.getProperty("user.dir") +
                                    testDataDirectory +
                                    fileToUpload
                            )
                    .closeModal();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        assertThat(homePage.getListOfUploadedDocuments(), hasItem(startsWith(fileToUpload.substring(0,10))));

    }

    @Test(dataProvider = "testDataForTestUploadDocument",
    dependsOnMethods = "documentUploadedSuccessfully",
    description = "User is able to delete a document successfully")
    public void documentDeletedSuccessfully(String fileToUpload) {
        int beforeDeleteFileCount = homePage.getListOfUploadedDocuments().size();
        homePage.deleteAFile(fileToUpload);
        int afterDeleteFileCount = homePage.getListOfUploadedDocuments().size();
        assertThat(beforeDeleteFileCount-afterDeleteFileCount,is(equalTo(1)));


    }

    @Test(dataProvider = "testDataForTestUploadDocument",
            description = "When user uploads unsupported document, appropriate message is displayed")
    public void messageDisplayedForUnsupportedDocumentType(String fileToUpload){
        String expectedUnsupportedFileMessage = prop.getProperty("UnsupportedFileMessage");
        try {
            homePage.openUploadDocumentModal()
                    .uploadFile(System.getProperty("user.dir") +
                            testDataDirectory +
                            fileToUpload
                    )
                    .closeModal();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        homePage.selectExistingDocument(fileToUpload);

        assertThat(homePage.getUnsupportedFileMessage(),
                is(equalTo(expectedUnsupportedFileMessage)));


    }


}
