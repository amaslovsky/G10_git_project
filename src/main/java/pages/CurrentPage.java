package pages;

import org.openqa.selenium.WebDriver;

import static variables.Variables.breadCrumbXpath;

public abstract class CurrentPage extends HomePage {
    protected String productURL;
    protected String productName;

    public CurrentPage(WebDriver webDriver) {
        super(webDriver);
    }

    // Constructor
    public CurrentPage(String productURL, String productName, WebDriver webDriver) {
        super(webDriver);
        this.productURL = productURL;
        this.productName = productName;
    }

    // Abstract methods
    public abstract String getProductURL();
    public abstract String getProductName();



    public CurrentPage checkIsRedirectToSelectedBezpruzhinniMatraciPage() {
        System.out.println("URL " + productURL);
        System.out.println("bread " + productName);
        checkUrl(productURL);
        isElementVisible(productName);
        return this;
    }
}
