package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.InitPage;

import java.time.Duration;

public class BaseTest {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected InitPage initPage;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser was opened");
        if (initPage == null) {
            initPage = new InitPage(webDriver);
        }
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }
}
