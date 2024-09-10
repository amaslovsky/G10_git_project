package pages.spalnyaPage.matraciPage;

import org.openqa.selenium.WebDriver;
import pages.spalnyaPage.SpalnyaPage;

public class MatraciPage extends SpalnyaPage {

    public MatraciPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeUrl() {
        return super.getRelativeUrl() + "/matraci";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Матраци']";
    }

}
