package pages.spalnya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.HomePage;
import pages.spalnya.matraci.Matraci;

public class Spalnya extends HomePage {

    protected String breadCrumbXpath = breadCrumbXpathBuilder("Спальня");
    protected String pageUrl = super.url + "/spalnya";

    public Spalnya(WebDriver webDriver) {
        super(webDriver);
    }

    public Spalnya checkIsRedirectToSpalnyaPage() {
        checkUrl(pageUrl);
        isElementVisible(breadCrumbXpath);
        return this;
    }

    public Matraci openMatraciPage() {
        WebElement matraci = webDriver.findElement(By.xpath("//span[text()='Матраци']"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(matraci).perform();
        clickOnElement(matraci);
        return new Matraci(webDriver);
    }



}
