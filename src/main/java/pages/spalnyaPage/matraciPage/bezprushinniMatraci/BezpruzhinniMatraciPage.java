package pages.spalnyaPage.matraciPage.bezprushinniMatraci;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.filterElements.FilterElements;
import pages.spalnyaPage.matraciPage.MatraciPage;

import java.util.ArrayList;

public class BezpruzhinniMatraciPage extends MatraciPage {

    ///////////
//    private FilterElements<BezpruzhinniMatraci> filterElements;
//    public BezpruzhinniMatraci(WebDriver webDriver) {
//        super(webDriver);
//        this.filterElements = new FilterElements<>(webDriver, this);
//    }
    ///////////

    @Override
    public String getRelativeUrl() {
        return super.getRelativeUrl() + "/bezpruzhinni-matraci";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Безпружинні матраци']";
    }

//    @FindBy(xpath = "(//div[contains(@class, 'product-list')]//div[contains(@class, 'product-container')]//a)[2]")
//    private WebElement firstMatrac;

    private String productsSection = "//div[contains(@class, 'product-container')]";
    private String elementName = ".//span[contains(@class, 'name')]";
    private String labelAlwaysLowPrice = ".//div[contains(@class, 'edlp')]";

    @FindBy(xpath = "//div[contains(@class, 'd-none')]//button[contains(@class, 'sort')]")
    private WebElement buttonSorting;

    @FindBy(xpath = "//div[contains(@class, 'd-none')]//button[contains(@class, 'fit')]")
    private WebElement buttonAllFilters;

//    @FindBy(xpath = ".//div[contains(@class,'edlp')]")
//    private WebElement labelAlwaysLowPrice;
//
//    @FindBy(xpath = ".//span[contains(@class,'name')]")
//    private WebElement elementName;

    public BezpruzhinniMatraciPage(WebDriver webDriver) {
        super(webDriver);
    }

    public BezpruzhinniMatraciPage getBezpruzhinniMatraciPage() {
        return new BezpruzhinniMatraciPage(webDriver);
    }

    public BezpruzhinniMatraciPage checkIsRedirectToBezpruzhinniMatraciPage() {
        checkUrl();
        checkBreadCrumb();
        return this;
    }

    public BezpruzhinniMatraciPage clickOnSortingButton() {
        clickOnElement(buttonSorting);
        return new BezpruzhinniMatraciPage(webDriver);
    }

    public BezpruzhinniMatraciPage clickOnAllFiltersButton() {
        clickOnElement(buttonAllFilters);
        return new BezpruzhinniMatraciPage(webDriver);
    }

    public FilterElements getFilterElements() {
        return new FilterElements(webDriver);
    }

//    public FilterElements<BezpruzhinniMatraci> getFilterElements() {
//        return new FilterElements<>(webDriver, this);
//    }

//    public BezpruzhinniMatraci clickOnAgreeSortingButton() {
//        return filterElements.clickOnAgreeSortingButton();
//    }

    public void checkIsAscSortingCorrect() {
        ArrayList<WebElement> products = getWebElements(productsSection);
        for (int i = 0; i < products.size() - 1; i++) {
            int productPrice1 = Integer.valueOf(products.get(i)
                    .findElement(By.xpath(".//span[contains(@class, 'price-value')]"))
                    .getText().replaceAll("[^\\d]", ""));
            int productPrice2 = Integer.valueOf(products.get(i + 1).
                    findElement(By.xpath(".//span[contains(@class, 'price-value')]"))
                    .getText().replaceAll("[^\\d]", ""));
            Assert.assertTrue("Product price " + productPrice1 + " is not chipper than " + productPrice2,
                    productPrice1 <= productPrice2);
        }
        logger.info("Products are sorted correctly");
    }


    public void checkOnlyAlwaysLowPriceProductsDisplayed() {
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<WebElement> products = getWebElements(productsSection);
        for (WebElement product : products) {
            String productName = getElementName(product.findElement(By.xpath(elementName)));
            boolean isAlwaysLowPricePresentInProduct = isElementPresentInArray(product, labelAlwaysLowPrice);
            softAssertions.assertThat(isAlwaysLowPricePresentInProduct)
                    .as(productName + " Element is not marked as 'ЗАВЖДИ НИЗЬКА ЦІНА'")
                    .isTrue();
        }
        softAssertions.assertAll();
        logger.info("Products are marked 'ЗАВЖДИ НИЗЬКА ЦІНА' correctly");
    }
}
