package pages.spalnya.matraci;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CurrentPage;

import java.time.Duration;

public class BezpruzhinniMatraci extends Matraci {

    protected String breadCrumbXpath = breadCrumbXpathBuilder("Безпружинні матраци");
    protected String pageUrl = super.pageUrl + "/bezpruzhinni-matraci";
    String selectedMatracUrl;
    String selectedMatracName;

    @FindBy(xpath = "(//div[contains(@class, 'product-list')]//div[contains(@class, 'product-container')]//a)[2]")
    private WebElement firstMatrac;


    public BezpruzhinniMatraci(WebDriver webDriver) {
        super(webDriver);
    }

    public BezpruzhinniMatraci checkIsRedirectToBezpruzhinniMatraciPage() {
        checkUrl(pageUrl);
        isElementVisible(breadCrumbXpath);
        return this;
    }

    public CurrentPage selectFirstMatrac() {
        Actions actions = new Actions(webDriver);
        WebDriverWait webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        actions.moveToElement(firstMatrac).build().perform();
        webDriverWait15.until(ExpectedConditions.visibilityOf(firstMatrac));

        this.selectedMatracUrl = firstMatrac.getAttribute("href");
        this.selectedMatracName = deleteSymbolsBeforeAndAfterNewLine(firstMatrac.getAttribute("outerText"));
        System.out.println("selectedMatracUrl is " + selectedMatracUrl);
        System.out.println("selectedMatracName is " + selectedMatracName);

        clickOnElement(firstMatrac);
        return new CurrentPage(selectedMatracUrl, selectedMatracName, webDriver) {
            @Override
            public String getProductURL() {
                return null;
            }

            @Override
            public String getProductName() {
                return null;
            }
        };
    }

    private String deleteSymbolsBeforeAndAfterNewLine(String text) {
        String outText = new String (text.replaceFirst(".*?\\n", ""));
        return outText;
    }

}
