package pages.spalnya.matraci.bezprushinniMatraci;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.filterElements.FilterElements;
import pages.spalnya.matraci.Matraci;

import java.util.ArrayList;

public class BezpruzhinniMatraci extends Matraci {

    ///////////
    private FilterElements<BezpruzhinniMatraci> filterElements;
    public BezpruzhinniMatraci(WebDriver webDriver) {
        super(webDriver);
        this.filterElements = new FilterElements<>(webDriver, this);
    }
    ///////////

    @Override
    public String getRelativeUrl() {
        return super.getRelativeUrl() + "/bezpruzhinni-matraci";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Безпружинні матраци']";
    }

//    @FindBy(xpath = "(//div[contains(@class, 'product-list')]//div[contains(@class, 'product-container')]//a)[2]")
//    private WebElement firstMatrac;

    @FindBy(xpath = "//div[contains(@class, 'd-none')]//button[contains(@class, 'sort')]")
    private WebElement buttonSorting;

//    public BezpruzhinniMatraci(WebDriver webDriver) {
//        super(webDriver);
//    }

//    public BezpruzhinniMatraci getBezpruzhinniMatraciPage() {
//        return new BezpruzhinniMatraci(webDriver);
//    }

    public BezpruzhinniMatraci checkIsRedirectToBezpruzhinniMatraciPage() {
        checkUrl();
        checkBreadCrumb();
        return this;
    }

    public BezpruzhinniMatraci clickOnFiltersButton() {
        clickOnElement(buttonSorting);
        return new BezpruzhinniMatraci (webDriver);
    }

    public FilterElements<BezpruzhinniMatraci> getFilterElements() {
        return new FilterElements<>(webDriver, this);
    }

    public BezpruzhinniMatraci clickOnAgreeSortingButton() {
        return filterElements.clickOnAgreeSortingButton();
    }

    public void checkIsAscSortingCorrect() {
        ArrayList<WebElement> products = getWebElements("//div[contains(@class, 'product-container')]");
        for (int i = 0; i < products.size() - 1; i++) {
            int productPrice1 = Integer.valueOf(products.get(i)
                    .findElement(By.xpath(".//span[contains(@class, 'price-value')]"))
                    .getText().replaceAll("[^\\d]", ""));
            int productPrice2 = Integer.valueOf(products.get(i + 1).
                    findElement(By.xpath(".//span[contains(@class, 'price-value')]"))
                    .getText().replaceAll("[^\\d]", ""));
            Assert.assertTrue("Product price " + productPrice1 + " is not chipper than " + productPrice2,
                    productPrice1 <= productPrice2);
        }
        logger.info("Products are sorted correctly");
    }

}
