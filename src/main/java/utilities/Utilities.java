package utilities;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import static variables.Variables.listNamesSelectedProducts;

public class Utilities {
    public static Logger logger = Logger.getLogger(Utilities.class);

    public static String getElementName(WebElement webElement) {
        String elementName = "";
        try {
            return webElement.getAttribute("textContent").trim();
        } catch (Exception e) {
            return elementName;
        }
    }

    public static boolean isElementVisible(WebElement webElement) {
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

    public static boolean isElementVisible(WebElement webElement, String elementName) {
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

    public static boolean isCheckBoxSelected(WebElement checkbox) {
        return checkbox.isSelected();
    }

    public static void addProductToObraneList(String nameOfSelectedProduct) {
        listNamesSelectedProducts.add(nameOfSelectedProduct);
//        logger.info(nameOfSelectedProduct + " is added to list of selected products");
    }

    public static int getRandomInt(Integer range) {
        return (int) (Math.random() * range) + 1;
    }

    public static int makeRandomNumberForSelectedElements(int numberOfDisplayedElements) {
        int numberOfSelectedElements = (int) ((Math.random() * numberOfDisplayedElements) + 1);
        logger.info(numberOfSelectedElements + " elements are selected from "
                + numberOfDisplayedElements + " elements on page");
        return numberOfSelectedElements;
    }

    public static Double convertStringValueInDouble(String productPrice) {
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

    public static int convertStringValueInInt(String digit) {
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

    public static void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
