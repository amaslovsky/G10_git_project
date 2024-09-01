package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static variables.Variables.listNamesSelectedProducts;

public class ActionsOnElements {

    Actions actions;
    WebDriverWait webDriverWait15;

    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected String allProducts = "(//div[contains(@class, 'product-container')])";
    protected String productXpath = allProducts + "[%s]";
    protected String nameProductXpath = productXpath + "//span[contains(@class, 'name')]";
    protected String obraneButtonProductXpath = productXpath + "//button";

    @FindBy(xpath = "//span[text()='Обране']")
    private WebElement anchor4topOfPage;

    public ActionsOnElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.actions = new Actions(webDriver);
        this.webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable((webElement)));
            actions.moveToElement(webElement).perform();
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xPath, String elementName) {
        try {
            webDriver.findElement(By.xpath(xPath)).click();
            logger.info(webDriver.findElement(By.xpath(elementName))
                    .getAttribute("textContent") + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected String getElementName(WebElement webElement) {
        String elementName = "";
        try {
            return webElement.getAttribute("textContent");
        } catch (Exception e) {
            return elementName;
        }
    }

    protected ArrayList<WebElement> getWebElements(String webElements) {
        ArrayList<WebElement> elements = new ArrayList<>(webDriver.findElements(By.xpath(webElements)));
        return elements;
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
            logger.info(getElementName(webElement) + " Element is not displayed");
            return false;
        }
    }

    protected boolean isElementVisible(WebElement webElement, String elementName) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(elementName + " Element is displayed");
            } else {
                logger.info(elementName + " Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info(elementName + " Element is not displayed");
            return false;
        }
    }

    protected boolean isElementPresentInArray(WebElement element, String elementXPath) {
//        WebDriverWait waitZeroSeconds = new WebDriverWait(webDriver, Duration.ofSeconds(0));
//        boolean isPresent;
//        try { waitZeroSeconds.until(ExpectedConditions.visibilityOf(element.findElement(By.xpath(elementXPath))));
//            isPresent = true;
//        } catch (Exception e) {
//            isPresent = false;
//        }
//        return isPresent;

//        try {
//            List<WebElement> foundElements = element.findElements(By.xpath(elementXPath));
//            return !foundElements.isEmpty();
//        }catch (Exception e) {
//            return false;
//        }

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

    protected int getRandomInt(Integer range) {
        return (int) (Math.random() * range) + 1;
    }

    protected ArrayList<Integer> makeArrayOfProducts() {
        int displayedElementsNumber = getNumberOfDisplayedElements();
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

    public void returnToTopOfPage() {
        actions.moveToElement(anchor4topOfPage).perform();
        webDriverWait15.until(ExpectedConditions.visibilityOf(anchor4topOfPage));
    }

    protected void setObraneStateON(WebElement webElement, String productName) {
        if (webElement.getAttribute("class").contains("added")) {
            logger.info("'Obrane' state for " + productName + " is already ON");
        } else {
            webElement.click();
            logger.info("'Obrane' state for product " + productName + " is set ON");
        }
    }

    public String getProductName(Integer index) {
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

    protected void addProductToObraneList(String nameOfSelectedProduct) {
        listNamesSelectedProducts.add(nameOfSelectedProduct);
//        logger.info(nameOfSelectedProduct + " is added to list of selected products");
    }

    protected int getNumberOfDisplayedElements() {
        try {
            int elementsNumber = webDriver.findElements
                    (By.xpath(allProducts)).size();
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

    protected int makeRandomNumberForSelectedElements(int numberOfDisplayedElements) {
        int numberOfSelectedElements = (int) ((Math.random() * numberOfDisplayedElements) + 1);
        logger.info(numberOfSelectedElements + " elements are selected from "
                + numberOfDisplayedElements + " elements on page");
        return numberOfSelectedElements;
    }

    public void setCheckBoxON(WebElement checkbox, WebElement checkboxName) {
        if (!isCheckBoxSelected(checkbox)) {
            clickOnElement(checkbox);
            logger.info(getElementName(checkboxName) + " checkbox set to 'ON' status");
        } else {
            logger.info(getElementName(checkboxName) + " checkbox is already in 'ON' status");
        }
    }

    public boolean isCheckBoxSelected(WebElement checkbox) {
        return checkbox.isSelected();
    }


}
