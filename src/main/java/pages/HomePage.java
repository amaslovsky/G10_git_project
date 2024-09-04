package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.headerElements.HeaderElements;
import pages.spalnyaPage.matraciPage.bezprushinniMatraci.BezpruzhinniMatraciPage;

import static utilities.Utilities.isElementVisible;
import static variables.Variables.breadCrumbXpath;
import static variables.Variables.url;

    abstract public class HomePage extends ActionsOnElements {

    public String allProducts = "(//div[contains(@class, 'product-container')])";
    public String productXpath = allProducts + "[%s]";
    public String nameProductXpath = productXpath + "//span[contains(@class, 'name')]";
    public String obraneButtonProductXpath = productXpath + "//button";
//    public static String spinnerXpath = "//div[@class='jysk-spinner']";

    @FindBy (xpath = "//div[@class='jysk-spinner']")
    public static WebElement spinnerXpath;

    @FindBy (xpath = "//button[contains(@onclick, 'declineAll')]")
    protected WebElement cookieAgreement;

    @FindBy (xpath = "//*[@title='Домашня сторінка']")
    private WebElement homePageContent;

    @FindBy(xpath = "//span[contains(@class, 'name')]")
    protected WebElement productNameElement;

    @FindBy(xpath = "//span[contains(@class, 'value')]")
    protected WebElement productPriceElement;

    @FindBy(xpath = "//span[text()='Обране']")
    protected WebElement anchor4topOfPage;

    @FindBy(xpath = "//h1//div[contains(@class, 'sub')]")
    protected WebElement productNameElementOnProductPage;

    @FindBy(xpath = "//input[contains(@class, 'quantity')]")
    protected WebElement productQuantityInputField;

    @FindBy(xpath = "//button[contains(@class, 'basket')]")
    protected WebElement addToBasketButton;

    protected abstract String getRelativeUrl();

    protected abstract String getRelativeBreadCrumb();

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected",
                url + getRelativeUrl(),
                webDriver.getCurrentUrl());
            logger.info("Current URL " + webDriver.getCurrentUrl() + " equals to expected URL");
    }

    protected void checkBreadCrumb() {
        if (getRelativeBreadCrumb() != null) {
            Assert.assertTrue("BreadCrumb is not displayed ",
                    isElementVisible(getCurrentBreadCrumb()));
            logger.info("Current BreadCrumb " + getCurrentBreadCrumb().getText() + " is displayed correctly");
        } else {
            logger.info("Page does not have BreadCrumb");
        }
    }

    public WebElement getCurrentBreadCrumb() {
        String fullXpath = breadCrumbXpath + getRelativeBreadCrumb();
        return webDriver.findElement(By.xpath(fullXpath));
    }

    public HomePage openHomePage() {
        webDriver.get(url);
        closeCookiePopup();
        Assert.assertTrue("HomePage page is not loaded",
                isElementVisible(homePageContent, "Домашня сторінка"));
        logger.info("HomePage was opened " + url);
        return this;
    }

    private HomePage closeCookiePopup() {
        clickOnElement(cookieAgreement);
        logger.info("Cookie popup was closed");
        webDriver.switchTo().defaultContent();
        return this;
    }

    public HomePage checkIsRedirectOnHomePage() {
        checkUrl();
        checkBreadCrumb();
        return this;
        }

    public HeaderElements getHeaderElement() {
        return new HeaderElements(webDriver);
    }

    public BezpruzhinniMatraciPage getBezpruzhinniMatraciPage() {
        return new BezpruzhinniMatraciPage(webDriver);
    }

    protected void setObraneState(Integer index, String productName) {
        try {
            returnToTopOfPage();
            WebElement webElement = webDriver.findElement
                    (By.xpath(String.format(productXpath, index)));
            actions.moveToElement(webElement).perform();
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement = webDriver.findElement
                    (By.xpath(String.format(obraneButtonProductXpath, index)));
            setObraneStateON(webElement, productName);
        } catch (Exception e) {
            logger.error("Can not work with element " + e);
            Assert.fail("Can not work with element " + e);
        }
    }

    protected void setObraneStateON(WebElement webElement, String productName) {
        if (webElement.getAttribute("class").contains("added")) {
            logger.info("'Obrane' state for " + productName + " is already ON");
        } else {
            webElement.click();
            logger.info("'Obrane' state for product " + productName + " is set ON");
        }
    }

    public void returnToTopOfPage() {
        actions.moveToElement(anchor4topOfPage).perform();
        webDriverWait15.until(ExpectedConditions.visibilityOf(anchor4topOfPage));
    }

}

