package pages.outletPage;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ProductPage;

import java.time.Duration;
import java.util.ArrayList;

import static variables.Variables.*;

public class OutletPage extends HomePage {

    @Override
    public String getRelativeUrl() {
        return "/outlet";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Outlet']";
    }

    public OutletPage(WebDriver webDriver) {
        super(webDriver);
    }

    public OutletPage checkIsRedirectToOutletPage() {
        waitUtilSpinnerWorks();
        checkUrl();
        checkBreadCrumb();
        return this;
    }

    public OutletPage markRandomProductsAsObrane() {
        ArrayList<Integer> productsArray = makeArrayOfProducts();
        productsArray.forEach(element -> {
            String productName = getProductName(element);
            addProductToObraneList(productName);
            setObraneState(element, productName);
        });
        return this;
    }

    public OutletPage getPropertiesFirstProduct() {
        productName = getElementName(productNameElement);
        productPrice = convertStringValueInDouble(getElementName(productPriceElement));
        return this;
    }

    public OutletPage addProductToBasket() {
        WebDriverWait webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        webDriver.findElement(By.xpath(allProducts)).click();
        String productName = webDriver.findElement(By.xpath("//h1//div[contains(@class, 'sub')]")).getText();
        Integer productPrice = Integer.parseInt(
                webDriver.findElement(By.xpath("//span[contains(@class, 'price__value')]"))
                        .getText().substring(0, webDriver.findElement(By.xpath("//span[contains(@class, 'price__value')]"))
                                .getText().length() - 5));
        webDriver.findElement(By.xpath("//button[contains(@class, 'basket')]")).click();
        webDriverWait15.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//button[contains(@class, 'Notification')]"))));
        webDriver.findElement(By.xpath("//button[contains(@class, 'Notification')]")).click();
        webDriverWait15.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//span[contains(@class, 'minibasket')]"))));
        Integer productNumberDisplayedInBasket = Integer.parseInt(getElementName(webDriver.findElement(By.xpath("//span[contains(@class, 'minibasket')]"))));
        webDriverWait15.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//div[@class='old']"))));
        webDriver.findElement(By.xpath("//div[@class='old']")).click();
        String productNameInBasket = getElementName(webDriver.findElement(By.xpath("//ul//p//a//span")));

        webDriverWait15.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//div[@class='basket-item-quantity']"))));

        Integer productNumberInBasket = Integer.parseInt(webDriver.findElement(By.xpath("//input[@class='amount']")).getAttribute("value"));
        Double productPriceInBasket = Double.parseDouble((webDriver.findElement
                (By.xpath("//ul//p[contains(@class, 'price')]")).getText().substring(0, Integer.valueOf(String.valueOf(webDriver.findElement
                (By.xpath("//ul//p[contains(@class, 'price')]")).getText().length())) - 4)).replace(",", "."));
        Double productsTotalPrice = Double.parseDouble((webDriver.findElement
                (By.xpath("//p[contains(@class, 'total')]//span")).getText().substring(0, Integer.valueOf(String.valueOf(webDriver.findElement
                (By.xpath("//p[contains(@class, 'total')]//span")).getText().length())) - 4)).replace(",", "."));
//        Assert.assertEquals("Product name in basket is not as expected", productName, productNameInBasket);
        Assert.assertEquals("Product price in basket is not as expected", Double.valueOf(productPrice), productPriceInBasket);
        Assert.assertEquals("Total price of products in basket is not as expected", Double.valueOf(productPrice*productNumberInBasket), productsTotalPrice);
        webDriver.findElement(By.xpath("//input[@class='amount']")).clear();
        Integer productNewNumberInBasket = 2;

        webDriver.findElement(By.xpath("//input[@class='amount']")).sendKeys(String.valueOf(productNewNumberInBasket), Keys.TAB);
//        webDriverWait15.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jysk-spinner']")));
//        webDriverWait15.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='jysk-spinner']")));
//        webDriverWait15.until(ExpectedConditions.attributeContains(By.xpath("//input[@class='amount']"), "value", "2"));
        waitABit(1); // wait for all messages to appear

        Double productsNewTotalPrice = Double.parseDouble((webDriver.findElement
                (By.xpath("//p[contains(@class, 'total')]//span")).getText().substring(0, Integer.valueOf(String.valueOf(webDriver.findElement
                (By.xpath("//p[contains(@class, 'total')]//span")).getText().length())) - 4)).replace(",", "."));
        Double tmp = (double) (productPrice * productNewNumberInBasket);
        Assert.assertEquals("Total price of products in basket is not as expected", tmp, productsNewTotalPrice);

        return this;
    }

    public static void waitABit(Integer second){
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ProductPage clickOnFirstProduct() {
//        clickOnElement(allProducts + "[1]", "The first product");
//        clickOnElement(String.format(nameProductXpath, "1"), "The first product");
        clickOnElement(productNameElement);
        return new ProductPage(webDriver);
    }
}
