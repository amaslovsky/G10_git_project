package pages.spalnya.matraci;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.spalnya.Spalnya;

public class Matraci extends Spalnya {

    protected String breadCrumbXpath = breadCrumbXpathBuilder("Матраци");
    protected String pageUrl = super.pageUrl + "/matraci";

    public Matraci(WebDriver webDriver) {
        super(webDriver);
    }

    public Matraci checkIsRedirectToMatraciPage() {
        checkUrl(pageUrl);
        isElementVisible(breadCrumbXpath);
        return this;
    }

    public BezpruzhinniMatraci openBezpruzhinniMatraciPage() {
        WebElement matraci = webDriver.findElement(By.xpath("//span[text()='Безпружинні матраци']"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(matraci).perform();
        clickOnElement(matraci);
        return new BezpruzhinniMatraci(webDriver);
    }

}
