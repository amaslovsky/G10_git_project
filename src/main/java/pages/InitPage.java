package pages;

import org.openqa.selenium.WebDriver;

public class InitPage extends ActionsOnElements{

    public InitPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver) {
            @Override
            protected String getRelativeUrl() {
                return "/";
            }

            @Override
            protected String getRelativeBreadCrumb() {
                return null;
            }
        };
    }

}
