package pages.spalnyaPage;

import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class SpalnyaPage extends HomePage {


    @Override
    protected String getRelativeUrl() {
        return "/spalnya";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Спальня']";
    }

    public SpalnyaPage(WebDriver webDriver) {
        super(webDriver);
    }

}
