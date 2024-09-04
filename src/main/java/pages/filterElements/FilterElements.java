package pages.filterElements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.ActionsOnElements;
import pages.ProductPage;

public class FilterElements {
//public class FilterElements<SharedPage extends ActionsOnElements> extends ActionsOnElements {
//    private final SharedPage page;

    WebDriver webDriver;
    ActionsOnElements actionsOnElements;

    public FilterElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // This initializes the WebElements
        this.actionsOnElements = new ActionsOnElements(this.webDriver);
    }

    public Logger logger = Logger.getLogger(FilterElements.class);

    @FindBy(xpath = "//span[text()='Всі фільтри']")
    public WebElement buttonFilters;

    @FindBy(xpath = "//button[contains(text(), 'Показати')]")
    public WebElement buttonShowSortingResult;

    @FindBy(xpath = "//button//span[text()='ЗАВЖДИ НИЗЬКА ЦІНА']")
    public WebElement buttonAlwaysLowPrice;

    @FindBy(xpath = "//label[text()='ЗАВЖДИ НИЗЬКА ЦІНА']//..//input")
    public WebElement alwaysLowPriceCheckBox;

    @FindBy(xpath = "//label[text()='ЗАВЖДИ НИЗЬКА ЦІНА']")
    public WebElement alwaysLowPriceCheckBoxName;

    public String sortingTypeRadioButton = "//input[contains(@value, '%s')]";
    public String sortingTypeName = sortingTypeRadioButton + "/ancestor::*[2]//label";

    public FilterElements setSortingType(String sortingType) {
        String formattedXPath = String.format(sortingTypeRadioButton, sortingType);
        String displayedName = String.format(sortingTypeName, sortingType);
        switch (sortingType) {
            case "asc", "desc", "default", "popular":
                actionsOnElements.clickOnElement(formattedXPath, displayedName);
                break;
            default:
                logger.error("The sorting type '" + sortingType + "' is not valid, test is interrupted");
                Assert.fail("The sorting type '" + sortingType + "' is not valid");
                break;
        }
        return this;
    }

    public FilterElements clickOnAlwaysLowPriceAccordion() {
        actionsOnElements.clickOnElement(buttonAlwaysLowPrice);
        return this;
    }

    public ProductPage clickOnAgreeSortingButton() {
        actionsOnElements.clickOnElement(buttonShowSortingResult, "agree");
        return new ProductPage(webDriver);
    }

    public FilterElements setAlwaysLowPriceCheckBoxON() {
        actionsOnElements.setCheckBoxON(alwaysLowPriceCheckBox, alwaysLowPriceCheckBoxName);
        return this;
    }

}
