package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.headerElements.HeaderElements;
import pages.spalnyaPage.matraciPage.bezprushinniMatraci.BezpruzhinniMatraciPage;

import static variables.Variables.breadCrumbXpath;
import static variables.Variables.url;

public abstract class HomePage extends ActionsOnElements {

    @FindBy (xpath = "//button[contains(@onclick, 'declineAll')]")
    private WebElement cookieAgreement;

    @FindBy (xpath = "//*[@title='Домашня сторінка']")
    private WebElement homePageContent;

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
//        Assert.assertFalse("Cookie popup is not closed",
//                isElementVisible(cookieAgreement));
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

}

