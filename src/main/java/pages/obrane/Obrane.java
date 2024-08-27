package pages.obrane;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;

import java.util.ArrayList;

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

    public Obrane compareSelectedProductsAndDisplayedProducts() {
        ArrayList<WebElement> elements = new ArrayList<>(webDriver.findElements
                (By.xpath("//div[contains(@class, 'wishlist-products')]//div[contains(@class, 'product-container')]")));
        int pageElementsNumber = elements.size();
        boolean isPresent = findElementOnPage(elements);

        Assert.assertEquals("Number of elements on the page is not equal to the number of selected products",
                getSizeOfSelectedProducts(),pageElementsNumber);
        logger.info("Number of elements on the page " + pageElementsNumber
                + " equals to the number " + getSizeOfSelectedProducts() + " of selected products");

        Assert.assertTrue("Not all elements from the selected products are displayed on the page", isPresent);
        logger.info("Number of elements on the page " + pageElementsNumber
                + " equals to the number " + getSizeOfSelectedProducts() + " of selected products");
        return this;
    }

    private boolean findElementOnPage(ArrayList<WebElement> elements) {
        for (String name : getNamesOfSelectedProducts()) {
            boolean isPresent = false;
            for (WebElement element : elements) {
                if (element.getText().contains(name)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                return false;
            }
        }
        return true;
    }
}
