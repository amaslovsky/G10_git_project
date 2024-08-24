package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import variables.Variables;

public class ActionsOnElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public ActionsOnElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected void checkUrl(String pageUrl) {
        Assert.assertEquals("URL is not expected",
                pageUrl, webDriver.getCurrentUrl());
        logger.info("Current URL " + pageUrl + " equals to expected URL");
    }


    ////////////////////////////////////////////////////////////////////////
    private String getElementName(WebElement webElement) {
        String elementName = "";
        try {
            return webElement.getText();
        } catch (Exception e) {
            return elementName;
        }
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is displayed");
            } else {
                logger.info(getElementName(webElement) + " Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }
    protected boolean isElementVisible(String xPath) {
        try {
            return isElementVisible(webDriver.findElement(By.xpath(xPath)));
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    /////////////////////////////////////////////////////////
    public String breadCrumbXpathBuilder (String pageName) {
        String newXPath = Variables.breadCrumbXpath + "//*[text()='" + pageName + "']";
        return newXPath;
    }

}
