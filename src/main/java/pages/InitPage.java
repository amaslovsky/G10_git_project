package pages;

import org.openqa.selenium.WebDriver;
import pages.spalnya.Spalnya;

public class InitPage extends ActionsOnElements{

    public InitPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }

    public Spalnya getSpalnyaPage() {
        return new Spalnya(webDriver);
    }
}
