package spalnya.matrasi;

import org.junit.Test;
import initWebdriver.InitWebDriver;

public class BezpruzhinniMatraciTestPage extends InitWebDriver {

//    @Test
//    public void reachBezprMatrasUsingPageElements() {
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
//
//    }

    @Test
    public void ascSortingBezprMatras() {
        String sortingType = "asc"; // radio-button variants: asc, desc, popular, default (means relevant)
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
                .clickOnSortingButton()
                .getFilterElements()
                .setSortingType(sortingType)
                .clickOnAgreeSortingButton()
                .getBezpruzhinniMatraciPage()
                .checkIsAscSortingCorrect();
    }

    @Test
    public void alwaysLowPriceFilterBezprMatras() {
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
                .clickOnAllFiltersButton()
                .getFilterElements()
                .clickOnAlwaysLowPriceAccordion()
                .setAlwaysLowPriceCheckBoxON()
                .clickOnAgreeSortingButton()
                .getBezpruzhinniMatraciPage()
                .checkOnlyAlwaysLowPriceProductsDisplayed()
        ;
    }

}
