package pages.filterElements;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ActionsOnElements;

public class FilterElements<SharedPage extends ActionsOnElements> extends ActionsOnElements {
    private final SharedPage page;


    @FindBy(xpath = "//span[text()='Всі фільтри']")
    private WebElement buttonFilters;

    @FindBy(xpath = "//button[contains(text(), 'Показати')]")
    private WebElement buttonShowSortingResult;

    protected String sortingTypeRadioButton = "//input[contains(@value, '%s')]";
    protected String sortingTypeName = sortingTypeRadioButton + "/ancestor::*[2]//label";

    public FilterElements(WebDriver webDriver, SharedPage page) {
        super(webDriver);
        this.page = page;
    }

    public SharedPage setSortingType(String sortingType) {
        String formattedXPath = String.format(sortingTypeRadioButton, sortingType);
        String displayedName = String.format(sortingTypeName, sortingType);
        switch (sortingType) {
            case "asc":
                clickOnElement(formattedXPath, displayedName);
                break;
            case "desc":
                clickOnElement(formattedXPath, displayedName);
                break;
            case "default":
                clickOnElement(formattedXPath, displayedName);
                break;
            case "popular":
                clickOnElement(formattedXPath, displayedName);
                break;
            default:
                logger.error("The sorting type '" + sortingType + "' is not valid, test is interrupted");
                Assert.fail("The sorting type '" + sortingType + "' is not valid");
                break;
        }
        return page;
    }

    public SharedPage clickOnAgreeSortingButton() {
        clickOnElement(buttonShowSortingResult);
        return page;
    }

}
