package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static variables.Variables.*;


public class ProductPage extends HomePage {
    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        //TODO: calculate relative URL
        return null;
    }

    @Override
    protected String getRelativeBreadCrumb() {
        //TODO: calculate relative BreadCrumb
        return null;
    }

    @FindBy(xpath = "//h1//div[contains(@class, 'sub')]")
    private WebElement productNameElement;

    @FindBy(xpath = "//input[contains(@class, 'quantity')]")
    private WebElement productQuantityInputField;

    public ProductPage openProductPage() {
        waitUtilSpinnerWorks();
        //TODO: getRelativeUrl() is not implemented
        //TODO: getRelativeBreadCrumb() is not implemented
        logger.info("ProductPage is opened " + webDriver.getCurrentUrl());
        return this;
    }


    public ProductPage checkProductNameAndPriceAreCorrect() {
        String currentProductName = getElementName(productNameElement);
        Double currentProductPrice = convertStringValueInDouble(getElementName(productPriceElement));
        Assert.assertEquals("Product name is not the same as expected",
                productName, currentProductName);
        Assert.assertEquals("Product price is not the same as expected",
                productPrice, currentProductPrice);
        return this;
    }

    public Basket addProductToBasket(int quantity) {
        productQuantity = quantity;
        clearInputFieldAndEnterText(productQuantityInputField, String.valueOf(productQuantity));
        clickOnElement(addToBasketButton);
        return new Basket(webDriver);
    }

}
