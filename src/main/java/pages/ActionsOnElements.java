package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static pages.HomePage.spinnerXpath;
import static utilities.Utilities.*;

public class ActionsOnElements {

    Actions actions;
    WebDriverWait webDriverWait15;
    WebDriverWait webDriverWait1sec;
    WebDriverWait webDriverWait05sec;

    public WebDriver webDriver;
    public Logger logger = Logger.getLogger(getClass());

    public ActionsOnElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.actions = new Actions(webDriver);
        this.webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        this.webDriverWait1sec = new WebDriverWait(webDriver, Duration.ofSeconds(1));
        this.webDriverWait05sec = new WebDriverWait(webDriver, Duration.ofMillis(500));
    }

    public void clickOnElement(WebElement webElement) {
        try {
            webDriverWait1sec.until(ExpectedConditions.elementToBeClickable(webElement));
            actions.moveToElement(webElement).perform();
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait1sec.until(ExpectedConditions.elementToBeClickable((webElement)));
            actions.moveToElement(webElement).perform();
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickOnElement(String xPath, String elementName) {
        try {
            webDriver.findElement(By.xpath(xPath)).click();
            logger.info(webDriver.findElement(By.xpath(elementName))
                    .getAttribute("textContent") + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected ArrayList<WebElement> getWebElementsArrayByXpath(String webElements) {
        ArrayList<WebElement> elements = new ArrayList<>(webDriver.findElements(By.xpath(webElements)));
        return elements;
    }

    protected boolean isElementPresentInArray(WebElement element, String elementXPath) {
        Duration originalWait = webDriver.manage().timeouts().getImplicitWaitTimeout();
        try {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return !element.findElements(By.xpath(elementXPath)).isEmpty();
        } catch (Exception e) {
            return false;
        } finally {
            webDriver.manage().timeouts().implicitlyWait(originalWait);
        }
    }

    protected ArrayList<Integer> makeArrayOfProducts(String elementsXpath) {
        int displayedElementsNumber = getNumberOfDisplayedElements(elementsXpath);
        int selectedElementsNumbers = makeRandomNumberForSelectedElements(displayedElementsNumber);
        ArrayList<Integer> selectedProductsArray = new ArrayList<>();
        for (int i = 0; i < selectedElementsNumbers; i++) {
            int selectedProductPosition = getRandomInt(displayedElementsNumber);
            if (!selectedProductsArray.contains(selectedProductPosition)) {
                selectedProductsArray.add(selectedProductPosition);
            } else {
                i--;
            }
        }
        return selectedProductsArray;
    }

    public String getProductName(String nameProductXpath, int index) {
        try {
            String webElementText = getElementName(webDriver.findElement
                    (By.xpath(String.format(nameProductXpath, index))));
//            logger.info(webElementText + " product selected to be added to list");
            if (webElementText.isEmpty()) {
                logger.info("Product name is not present");
                Assert.fail("Product name is not present");
            }else {
                return webElementText;
            }

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
        return null;
    }

    protected int getNumberOfDisplayedElements(String allProductsXpath) {
        try {
            int elementsNumber = webDriver.findElements
                    (By.xpath(allProductsXpath)).size();
            if (elementsNumber !=0) {
                logger.info(elementsNumber + " elements are displayed");
                return elementsNumber;
            }else {
                logger.info("Elements are not displayed");
                Assert.fail("Elements are not displayed");
            }
        } catch (Exception e) {
            logger.info("Elements are not displayed");
        }
        return 0;
    }

    public void setCheckBoxON(WebElement checkbox, WebElement checkboxName) {
        if (!isCheckBoxSelected(checkbox)) {
            clickOnElement(checkbox);
            logger.info(getElementName(checkboxName) + " checkbox set to 'ON' status");
        } else {
            logger.info(getElementName(checkboxName) + " checkbox is already in 'ON' status");
        }
    }

    public void setCheckBoxON(String checkboxXpath, String checkboxName) {
        WebElement checkbox = null;
        try {
            checkbox= webDriver.findElement(By.xpath(checkboxXpath));
        } catch (Exception e) {
            logger.info("Can't find check-box");
        }
        if (!isCheckBoxSelected(checkbox)) {
            clickOnElement(checkbox);
            logger.info(checkboxName + " checkbox set to 'ON' status");
        } else {
            logger.info(checkboxName + " checkbox is already in 'ON' status");
        }
    }

    public void waitUtilSpinnerWorks() {
        boolean isSpinnerPresent;
        try {
//            webDriverWait05sec.until(spinnerXpath.isEnabled());
            isSpinnerPresent = spinnerXpath.isEnabled();
            logger.info("Spinner is appeared");
            if (isSpinnerPresent) {
                webDriverWait05sec.until(ExpectedConditions
                        .stalenessOf(spinnerXpath));
                logger.info("Spinner is disappeared");
            }
        } catch (Exception e) {
            logger.info("Spinner is not displayed");
        }
    }

    protected void clearInputFieldAndEnterText(WebElement webElement, String text) {
        try {
//            waitUtilSpinnerWorks();
//            webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            webDriverWait1sec.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text, Keys.TAB);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clearInputFieldAndEnterTextAndClickAwayFromField(String xPath, String text) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(xPath));
            webDriverWait1sec.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            webElement.findElement(By.xpath(xPath+"/../label")).click();
            logger.info(text + " was inputted into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

}
