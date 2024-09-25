package pages.headerElements;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ActionsOnElements;
import pages.Basket;
import pages.RegistrationPage;
import pages.obranePage.ObranePage;

import static utilities.Utilities.convertStringValueInInt;
import static utilities.Utilities.getElementName;
import static variables.Variables.productQuantity;

public class HeaderElements extends ActionsOnElements {
    @FindBy(xpath = "//a[contains(@class,'megamenu')]/span")
    private WebElement buttonMenu;

    @FindBy(xpath = "//*[@id='wishlist-link']")
    private WebElement buttonObrane;

    @FindBy(xpath = "//span[contains(@class, 'minibasket')]")
    private WebElement numberOfProductsInBasket;

    @FindBy(xpath = "//div[contains(@class, 'basket-teaser')]")
    private WebElement buttonBasket;

    @FindBy(xpath = "//*[contains(@*, 'Profile')]")
    private WebElement buttonProfile;

    @FindBy(xpath = "//div[@class='d-none']/../a")
    private WebElement buttonRegistration;

    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    public MenuElement clickOnMenuButton() {
        clickOnElement(buttonMenu);
        return new MenuElement(webDriver);
    }

    public ObranePage clickOnObraneMenuButton() {
        clickOnElement(buttonObrane);
        return new ObranePage(webDriver);
    }

    public Basket clickOnBasketButton() {
        clickOnElement(buttonBasket, "Basket button");
        return new Basket(webDriver);
    }

    public HeaderElements checkProductAddedToBasket() {
        Assert.assertEquals("Number of products in basket is not correct",
                productQuantity, convertStringValueInInt(getElementName(numberOfProductsInBasket)));
        logger.info("Number of products in basket is correct");
        return this;
    }

    public HeaderElements clickOnProfileButton() {
        clickOnElement(buttonProfile, "Profile button");
        return this;
    }

    public RegistrationPage clickOnRegistrationButton() {
        clickOnElement(buttonRegistration, "Registration button");
        return new RegistrationPage(webDriver);
    }



    }
