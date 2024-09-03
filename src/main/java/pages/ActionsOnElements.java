package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static variables.Variables.listNamesSelectedProducts;

public class ActionsOnElements {

    Actions actions;
    WebDriverWait webDriverWait15;
    WebDriverWait webDriverWait1sec;

    public WebDriver webDriver;
    public Logger logger = Logger.getLogger(getClass());



    public ActionsOnElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.actions = new Actions(webDriver);
        this.webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        this.webDriverWait1sec = new WebDriverWait(webDriver, Duration.ofSeconds(1));
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait1sec.until(ExpectedConditions.elementToBeClickable((webElement)));
            actions.moveToElement(webElement).perform();
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait1sec.until(ExpectedConditions.elementToBeClickable((webElement)));
            actions.moveToElement(webElement).perform();
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

    protected void addProductToObraneList(String nameOfSelectedProduct) {
        listNamesSelectedProducts.add(nameOfSelectedProduct);
//        logger.info(nameOfSelectedProduct + " is added to list of selected products");
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

    public void waitUtilSpinnerWorks() {
        boolean isSpinnerPresent = false;
        try {
            webDriverWait1sec.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//div[@class='jysk-spinner']")));
            isSpinnerPresent = true;
            if (isSpinnerPresent) {
                logger.info("Spinner is displayed");
                webDriverWait1sec.until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath("//div[@class='jysk-spinner']")));
                logger.info("Spinner is disappeared");
            }
        } catch (Exception e) {
            logger.info("Spinner is not disappeared");
        }
    }

    public Double convertStringValueInDouble(String productPrice) {
        Double price = null;
        try {
            if (productPrice.contains(" ")) {
                price = Double.parseDouble(productPrice.substring(0, productPrice.indexOf(" ")).replace(",", "."));
            } else {
                price = Double.parseDouble(productPrice.replace(",", "."));
            }
            return price;
        } catch (Exception e) {
            logger.error("Can not work with element " + e);
            Assert.fail("Can not work with element " + e);
        }
        return price;
    }

    public int convertStringValueInInt(String digit) {
        int value = 0;
        try {
            value = Integer.parseInt(digit);
            return value;
        } catch (Exception e) {
            logger.error("Can not work with element " + e);
            Assert.fail("Can not work with element " + e);
        }
        return value;
    }

    protected void clearInputFieldAndEnterText(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text, Keys.TAB);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

}
