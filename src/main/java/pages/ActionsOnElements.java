package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import variables.Variables;

import java.time.Duration;

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

    protected void clickOnElement(String xPath) {
        try {
            Actions actions = new Actions(webDriver);
            WebDriverWait webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
            WebElement webElement = webDriver.findElement(By.xpath(xPath));
            actions.scrollToElement(webElement).build().perform();
            actions.moveToElement(webElement).build().perform();
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
//            WebElement webElement = webDriver.findElement(By.xpath(xPath));
            webElement.click();
            logger.info(" Element was clicked");
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
