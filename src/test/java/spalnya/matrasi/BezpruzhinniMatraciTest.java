package spalnya.matrasi;

import org.junit.Test;
import initWebdriver.InitWebDriver;

public class BezpruzhinniMatraciTest extends InitWebDriver {

    @Test
    public void test1() throws InterruptedException {
        initPage
                .getHomePage()
                .openHomePage()
                .openSpalnyaPage()
                .checkIsRedirectToSpalnyaPage()
                .openMatraciPage()
                .checkIsRedirectToMatraciPage()
                .openBezpruzhinniMatraciPage()
                .checkIsRedirectToBezpruzhinniMatraciPage()
                .selectFirstMatrac()
                .checkIsRedirectToSelectedBezpruzhinniMatraciPage();

    }
}
