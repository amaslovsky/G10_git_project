package utilities;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

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

    /**
     * method converts string value of price to double value
     * besides, method replaces separator marks
     * and gets rid of spaces
     */
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
            logger.error("Can not work with value " + e);
            Assert.fail("Can not work with value " + e);
        }
        return price;
    }

    /**
     * method converts string value of price to integer value
     */
    public static int convertStringValueInInt(String digit) {
        int value = 0;
        try {
            value = Integer.parseInt(digit);
            return value;
        } catch (Exception e) {
            logger.error("Can not work with value " + e);
            Assert.fail("Can not work with value " + e);
        }
        return value;
    }

    public static void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    public static void compareElementsPrices(ArrayList<WebElement> products, String sortingType) {
        for (int i = 0; i < products.size() - 1; i++) {
            int productPrice1 = Integer.valueOf(products.get(i)
                    .findElement(By.xpath(".//span[contains(@class, 'price-value')]"))
                    .getText().replaceAll("[^\\d]", ""));
            int productPrice2 = Integer.valueOf(products.get(i + 1).
                    findElement(By.xpath(".//span[contains(@class, 'price-value')]"))
                    .getText().replaceAll("[^\\d]", ""));
            switch (sortingType) {
                case "asc":
                    Assert.assertTrue("Product price " + productPrice1 + " is not chipper than " + productPrice2,
                            productPrice1 <= productPrice2);
                    break;
                case "desc":
                    Assert.assertTrue("Product price " + productPrice1 + " is not more expensive than " + productPrice2,
                            productPrice1 >= productPrice2);
                    break;
                default:
                    logger.error("The sorting type '" + sortingType + "' is not valid, test is interrupted");
                    Assert.fail("The sorting type '" + sortingType + "' is not valid");
                    break;
            }
        }
    }
}
