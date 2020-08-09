package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.App;
import pages.HomePage;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestUploadDocument extends BaseTest {

    App hubdocApp;
    HomePage homePage;

    @Test
    public void test1() {
        hubdocApp = new App(driver);
        homePage = hubdocApp.login();
        try {
            homePage.openUploadDocumentModal()
                    .uploadFile("/Users/thinker/Documents/testdatapertest1.xlsx")
                    .closeModal();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        assertThat(homePage.getListOfUploadedDocuments(),contains("testdatapertest1"));


    }



}
