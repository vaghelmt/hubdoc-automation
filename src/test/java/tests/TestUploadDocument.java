package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.App;
import pages.HomePage;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestUploadDocument extends BaseTest {

    @Test(enabled = false)
    public void documentUploadedSuccessfully() {
        try {
            homePage.openUploadDocumentModal()
                    .uploadFile("C:\\Users\\mitvaghe\\OneDrive - Capgemini\\Documents\\Registration_error.PNG")
                    .closeModal();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        assertThat(homePage.getListOfUploadedDocuments(), hasItem("Registration_error.PNG"));

    }

    @Test
    public void documentDeletedSuccessfully() {
        int beforeDeleteFileCount = homePage.getListOfUploadedDocuments().size();
        homePage.deleteAFile("Registration_error.PNG");
        int afterDeleteFileCount = homePage.getListOfUploadedDocuments().size();
        assertThat(beforeDeleteFileCount-afterDeleteFileCount,is(equalTo(1)));


    }


}
