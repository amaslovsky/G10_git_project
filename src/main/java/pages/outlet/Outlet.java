package pages.outlet;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.MenuElement;
import pages.obrane.Obrane;

import java.time.Duration;
import java.util.ArrayList;

public class Outlet extends HomePage {

    protected String breadCrumbXpath = breadCrumbXpathBuilder("Outlet");
    protected String pageUrl = super.pageUrl + "/outlet";
    protected String displayedProductsXpath = "(//div[contains(@class, 'product-container')])";
//    public ArrayList<String> namesOfSelectedProducts = new ArrayList<>();

    public Outlet(WebDriver webDriver) {
        super(webDriver);
    }

    public Outlet checkIsRedirectToOutletPage() {
        checkUrl(pageUrl);
        Assert.assertTrue("It is not Outlet page", isElementVisible(breadCrumbXpath));
        return this;
    }

    public Outlet markRandomProductsAsObrane() {
        int numberOfDisplayedElements = getNumberOfDisplayedElements();
        int numberOfSelectedElements = getNumberOfSelectedElements(numberOfDisplayedElements);
        ArrayList<Integer> positionsOfSelectedProducts = new ArrayList<>(numberOfSelectedElements);
        for (int i = 0; i < numberOfSelectedElements; i++) {
            int positionOfSelectedProduct = getRandomInt(numberOfDisplayedElements);
            if (!positionsOfSelectedProducts.contains(positionOfSelectedProduct)) {
                positionsOfSelectedProducts.add(positionOfSelectedProduct);
                setObraneState(positionOfSelectedProduct);
                String nameOfSelectedProduct = getProductName(positionOfSelectedProduct );
                addProductToObraneList(nameOfSelectedProduct);
            } else {
                i--;
            }
        }
        return this;
    }

    public String getProductName(int index) {
        try {
            String webElementText =
                    webDriver.findElement(By.xpath(displayedProductsXpath + "[" + index + "]" +
                            "//span[contains(@class, 'name')]")).getText();
            logger.info(webElementText + " product selected to be added to list");
            return webElementText;
        } catch (Exception e) {
            logger.error("Can not get product name");
            Assert.fail("Can not get product name");
        }
        return "webElementText";
    }

    protected int getNumberOfDisplayedElements() {
        try {
            int elementsNumber = webDriver.findElements(By.xpath(displayedProductsXpath)).size();
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

    protected int getNumberOfSelectedElements(int numberOfDisplayedElements) {
        int numberOfSelectedElements = (int) ((Math.random() * numberOfDisplayedElements) + 1);
        logger.info(numberOfSelectedElements + " elements are selected from " + numberOfDisplayedElements + " elements on page");
        return numberOfSelectedElements;
    }

    protected void addProductToObraneList(String nameOfSelectedProduct) {
        namesOfSelectedProducts.add(nameOfSelectedProduct);
        logger.info(nameOfSelectedProduct + " is added to list of selected products");
    }

    protected int getRandomInt(Integer range) {
        return (int) (Math.random() * range) + 1;
    }

    protected void setObraneState(int index) {
        try {
            returnToTop();
            WebElement webElement = webDriver.findElement(By.xpath(displayedProductsXpath + "[" + index + "]"));
//            WebElement webElement = webDriver.findElement(By.xpath(displayedProductsXpath));
            Actions actions = new Actions(webDriver);
            WebDriverWait webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
            actions.scrollToElement(webElement).build().perform();
            actions.moveToElement(webElement).build().perform();
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement = webDriver.findElement(By.xpath(displayedProductsXpath + "[" + index + "]" + "//button"));
            setObraneStateON(webElement);
            } catch (Exception e) {
            logger.error("Can not work with element " + e);
            Assert.fail("Can not work with element " + e);
        }
    }

    protected void setObraneStateON(WebElement webElement) {
        if (webElement.getAttribute("class").contains("added")) {
            logger.info("'Obrane' state is already ON");
        } else {
            webElement.click();
            logger.info("'Obrane' state set ON");
        }
    }

    public Obrane openObranePage() {
        returnToTop();
        clickOnElement("//span[text()='Обране']");
        return new Obrane(webDriver);
    }

    public void returnToTop() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollTo(0, 0);");
        logger.info("Returned to top of the page");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        wait.until(driver -> {
            return (Boolean) js.executeScript("return document.readyState").equals("complete");
        });
    }

    public Outlet printObraneList() {
        System.out.println("List of selected products:");
        for (String name : getNamesOfSelectedProducts()) {
            System.out.println(name);
        }
        return this;
    }

}
