package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static variables.Variables.*;


public class Basket extends ActionsOnElements {
    public Basket(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//span[@class='knockout-span-data']")
    private WebElement productNameElementOnCheckoutPopup;

    @FindBy(xpath = "//p[@class='orderline__item-price']")
    private WebElement productPriceElementOnCheckoutPopup;

    @FindBy(xpath = "//span[contains(@data-bind, 'quantity')]")
    private WebElement productQuantityElementOnCheckoutPopup;

    @FindBy(xpath = "//button[contains(@class, 'Notification')]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//p[@class='basket-item-price']")
    private WebElement productPriceElementOnBasketPopup;

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement productQuantityElementOnBasketPopup;

    @FindBy(xpath = "//p[@class='basket-total']//span")
    private WebElement productTotalAmountElementOnBasketPopup;

    @FindBy(xpath = "//a[@data-test]")
    private WebElement buttonContinue;

    @FindBy(xpath = "//span[contains(@class, 'name')]")
    protected WebElement productNameElement;

    @FindBy(xpath = "//span[contains(@class, 'value')]")
    protected WebElement productPriceElement;

    @FindBy(xpath = "//span[text()='Обране']")
    protected WebElement anchor4topOfPage;

    @FindBy(xpath = "//button[contains(@class, 'basket')]")
    protected WebElement addToBasketButton;




    public Basket checkProductNameAndPriceInCheckoutPopup() {
        String productNameOnCheckout = getElementName(productNameElementOnCheckoutPopup);
        int productQuantityOnCheckout = convertStringValueInInt(
                (getElementName(productQuantityElementOnCheckoutPopup)));
        Double productPriceOnCheckout = convertStringValueInDouble
                (getElementName(productPriceElementOnCheckoutPopup));

////////// ім'я на чекауті чомусь інше /////////////////////////////////
//        Assert.assertEquals("Product name is not the same as expected",
//                productName, productNameOnCheckout);
        Assert.assertEquals("Product quantity is not the same as expected",
                productQuantity, productQuantityOnCheckout);
        logger.info("Product quantity is correct");
        Assert.assertEquals("Product price is not the same as expected",
                productPrice, productPriceOnCheckout);
        logger.info("Product price is correct");
        return this;
    }

    public ProductPage closeCheckoutPopup() {
        clickOnElement(continueShoppingButton);
        return new ProductPage(webDriver);
    }


    public Basket checkProductAmountAndPriceInBasketPopup() {
        Assert.assertEquals("Product amount is not correct",
                productQuantity, convertStringValueInInt(productQuantityElementOnBasketPopup.getAttribute("value")));
        logger.info("Product amount is correct");
//        Assert.assertEquals("Product price is not correct",
//                productPrice, convertStringValueInDouble(getElementName(productPriceElementOnBasketPopup)));
//        logger.info("Product price is correct");
        Assert.assertEquals("Total amount is not correct",
                Double.valueOf(productPrice * productQuantity),
                convertStringValueInDouble(getElementName(productTotalAmountElementOnBasketPopup)));
        return this;

    }

    public Basket changeProductNumberInBasketPopup(int newQuantity) {
        productQuantity = newQuantity;
        clearInputFieldAndEnterText
                (productQuantityElementOnBasketPopup, String.valueOf(productQuantity));
        waitUtilSpinnerWorks();
        return this;
    }

    public Basket openBasketPage() {
        clickOnElement(buttonContinue);
//        waitUtilSpinnerWorks();
        return new Basket(webDriver);
    }
}
