package tests;

import org.testng.annotations.Test;
import pages.App;

public class FirstTest extends BaseTest {

    App hubdocApp;

    @Test
    public void test1() {
        hubdocApp = new App(driver);
    }

}
