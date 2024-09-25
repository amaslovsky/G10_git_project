package pages.spalnyaPage.matraciPage.bezprushinniMatraci;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.filterElements.FilterElements;
import pages.spalnyaPage.matraciPage.MatraciPage;

public class BezpruzhinniMatraciPage extends MatraciPage {

    @Override
    public String getRelativeUrl() {
        return super.getRelativeUrl() + "/bezpruzhinni-matraci";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Безпружинні матраци']";
    }

    private String productsSection = "//div[contains(@class, 'product-container')]";
    private String elementName = ".//span[contains(@class, 'name')]";
    private String labelAlwaysLowPrice = ".//div[contains(@class, 'edlp')]";

    @FindBy(xpath = "//div[contains(@class, 'd-none')]//button[contains(@class, 'sort')]")
    private WebElement buttonSorting;

    @FindBy(xpath = "//div[contains(@class, 'd-none')]//button[contains(@class, 'fit')]")
    private WebElement buttonAllFilters;

    public BezpruzhinniMatraciPage(WebDriver webDriver) {
        super(webDriver);
    }

    public BezpruzhinniMatraciPage getBezpruzhinniMatraciPage() {
        return new BezpruzhinniMatraciPage(webDriver);
    }

    public BezpruzhinniMatraciPage checkIsRedirectToBezpruzhinniMatraciPage() {
        checkUrl();
        checkBreadCrumb();
        return this;
    }

    @Step
    public BezpruzhinniMatraciPage clickOnSortingButton() {
        clickOnElement(buttonSorting);
        return new BezpruzhinniMatraciPage(webDriver);
    }

    public BezpruzhinniMatraciPage clickOnAllFiltersButton() {
        clickOnElement(buttonAllFilters);
        return new BezpruzhinniMatraciPage(webDriver);
    }

    public FilterElements getFilterElements() {
        return new FilterElements(webDriver);
    }

}
