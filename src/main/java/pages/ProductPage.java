package pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.filterElements.FilterElements;
import pages.headerElements.HeaderElements;
import pages.spalnyaPage.SpalnyaPage;

import java.util.ArrayList;

import static utilities.Utilities.convertStringValueInDouble;
import static utilities.Utilities.getElementName;
import static variables.Variables.*;


public class ProductPage extends ActionsOnElements {
    private String productsSection = "//div[contains(@class, 'product-container')]";
    private String elementName = ".//span[contains(@class, 'name')]";
    private String labelAlwaysLowPrice = ".//div[contains(@class, 'edlp')]";

    HomePage homePage = new HomePage(webDriver) {
        @Override
        protected String getRelativeUrl() {
            return null;
        }

        @Override
        protected String getRelativeBreadCrumb() {
            return null;
        }
    };

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public Logger logger = Logger.getLogger(getClass());

    public ProductPage openProductPage() {
        //waitUtilSpinnerWorks();
        //TODO: getRelativeUrl() is not implemented
        //TODO: getRelativeBreadCrumb() is not implemented
        logger.info("ProductPage is opened " + webDriver.getCurrentUrl());
        return this;
    }

    public ProductPage checkProductNameAndPriceAreCorrect() {
        String currentProductName = getElementName(homePage.productNameElementOnProductPage);
        Double currentProductPrice = convertStringValueInDouble(getElementName(homePage.productPriceElement));
        Assert.assertEquals("Product name is not the same as expected",
                productName, currentProductName);
        Assert.assertEquals("Product price is not the same as expected",
                productPrice, currentProductPrice);
        return this;
    }

    public Basket addProductToBasket(int quantity) {
        productQuantity = quantity;
        clearInputFieldAndEnterText(homePage.productQuantityInputField, String.valueOf(productQuantity));
        clickOnElement(homePage.addToBasketButton);
        return new Basket(webDriver);
    }

    public HeaderElements getHeaderElement() {
        return new HeaderElements(webDriver);
    }

    public ProductPage checkIsAscSortingCorrect() {
//        ArrayList<WebElement> products = getWebElementsArrayByXpath(productsSection);
        ArrayList<WebElement> products = getWebElementsArrayByXpath(homePage.allProducts);
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
        return this;
    }

    public void checkOnlyAlwaysLowPriceProductsDisplayed() {
        SoftAssertions softAssertions = new SoftAssertions();
//        ArrayList<WebElement> products = getWebElementsArrayByXpath(productsSection);
        ArrayList<WebElement> products = getWebElementsArrayByXpath(homePage.allProducts);
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
