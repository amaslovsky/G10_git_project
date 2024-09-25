package pages.obranePage;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;

import java.util.ArrayList;

import static utilities.Utilities.getElementName;
import static variables.Variables.listNamesSelectedProducts;

public class ObranePage extends HomePage {
    private String productsSection = "//div[contains(@class, 'wishlist-products')]//span[contains(@class, 'name')]";

    @FindBy(xpath = "//span[contains(@class, 'count')]")
    private WebElement productsInHeartElement;

    @Override
    protected String getRelativeUrl() {
        return "/customer/favourites";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//ul[contains(@class,'breadcrumb')]/li[last()]";
    }

    public ObranePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ObranePage checkRedirectingToObranePage() {
        checkUrl();
        checkBreadCrumb();
        return this;
    }

    @Step
    public ObranePage checkSelectedProductsDisplayedOnObranePage() {
        ArrayList<WebElement> products = getWebElementsArrayByXpath(productsSection);

        int obraneNumberInHeart = Integer.valueOf(getElementName(productsInHeartElement));
        boolean isPresent = checkProductsFromObraneListPresentOnPage(products, listNamesSelectedProducts);

        Assert.assertEquals("Number in the 'heart' icon does not match the number of 'Obrane' products.",
                listNamesSelectedProducts.size(), obraneNumberInHeart);
        logger.info("Number in the 'heart' icon " + obraneNumberInHeart
                + " equals to the number " + listNamesSelectedProducts.size() + " of 'Obrane' products");

        Assert.assertEquals("Number of elements on the page is not equal to the number of selected products",
                listNamesSelectedProducts.size(),products.size());
        logger.info("Number of elements on the page " + products.size()
                + " equals to the number " + listNamesSelectedProducts.size() + " of selected products");

        Assert.assertTrue("Not all elements from the selected products are displayed on the page", isPresent);
        logger.info("All elements from the selected products are displayed on the page");
        return this;
    }

    @Step
    protected boolean checkProductsFromObraneListPresentOnPage (ArrayList<WebElement> elements, ArrayList<String> getNamesOfSelectedProducts) {
        SoftAssertions softAssertions = new SoftAssertions();
        boolean isPresent = false;
        ArrayList<String> elementsNames = new ArrayList<>();
        for (WebElement element : elements) elementsNames.add(element.getText());

        for (String name : getNamesOfSelectedProducts) {
            softAssertions
                    .assertThat(name)
                    .as("Can't see " + name)
                    .isIn(elementsNames);
            if (elementsNames.contains(name)) isPresent = true;
        }
        softAssertions.assertAll();
        return isPresent;
    }


}
