package pages.obrane;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.outlet.Outlet;

public class Obrane extends HomePage {
    protected String breadCrumbXpath = breadCrumbXpathBuilder("Обране");
    protected String pageUrl = super.pageUrl + "/customer/favourites";

    public Obrane(WebDriver webDriver) {
        super(webDriver);
    }

    public Obrane checkIsRedirectToObranePage() {
        checkUrl(pageUrl);
        Assert.assertTrue(breadCrumbXpath+"It is not Obrane page", isElementVisible(breadCrumbXpath));
        return this;
    }

    public Obrane printObraneList() {
        System.out.println("List of selected products:");
        for (String name : getNamesOfSelectedProducts()) {
            System.out.println(name);
        }
        return this;
    }
}
