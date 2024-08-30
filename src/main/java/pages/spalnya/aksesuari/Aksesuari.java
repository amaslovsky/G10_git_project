package pages.spalnya.aksesuari;

import org.openqa.selenium.WebDriver;
import pages.HomePage;

import static variables.Variables.breadCrumbXpath;

public class Aksesuari extends HomePage {

    @Override
    protected String getRelativeUrl() {
        return null;
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return null;
    }

    public Aksesuari(WebDriver webDriver) {
        super(webDriver);
    }

}
