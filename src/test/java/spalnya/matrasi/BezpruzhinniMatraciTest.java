package spalnya.matrasi;

import org.junit.Test;
import initWebdriver.InitWebDriver;

public class BezpruzhinniMatraciTest extends InitWebDriver {

    @Test
    public void reachBezprMatrasUsingPageElements() {
//        initPage
//                .getHomePage()
//                .openHomePage()
//                .openSpalnyaPage()
//                .checkIsRedirectToSpalnyaPage()
//                .openMatraciPage()
//                .checkIsRedirectToMatraciPage()
//                .openBezpruzhinniMatraciPage()
//                .checkIsRedirectToBezpruzhinniMatraciPage()
//                .selectFirstMatrac()
//                .checkIsRedirectToSelectedBezpruzhinniMatraciPage();

    }

    @Test
    public void ascSortingBezprMatras() {
        String sortingType = "asc"; // radio-button variants: asc, desc, popular, default (means relevant)
//        String tmp = "11000 грн.";
//        System.out.println(tmp.substring(0, tmp.length() - 5));
        initPage
                .getHomePage()
                .openHomePage()
                .checkIsRedirectOnHomePage()
                .getHeaderElement()
                .clickOnMenuButton()
                .getMenuElement()
                .clickOnSlapnyaMenuButton()
                .clickOnMatraciMenuButton()
                .clickOnBezpruzhinniMatraciMenuButton()
                .checkIsRedirectToBezpruzhinniMatraciPage()
                .clickOnFiltersButton()
                .getFilterElements()
                .setSortingType(sortingType)
                .clickOnAgreeSortingButton()
                .getBezpruzhinniMatraciPage()
                .checkIsAscSortingCorrect()

        ;

    }
}
