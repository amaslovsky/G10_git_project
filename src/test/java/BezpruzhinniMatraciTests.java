import org.junit.Test;
import baseTest.BaseTest;

public class BezpruzhinniMatraciTests extends BaseTest {

    /**
     * test verifies product sorting functionality on 'BezpruzhinniMatraci' page
     * radio button sorting options: asc, desc, popular and default (relevance)
     * important note: sorting logic for 'popular' and 'default' is not defined, so these options are not implemented
     */
    @Test
    public void ascSortingBezpruzhinniMatraci() {
        String sortingType = "asc";
        initPage
                .getHomePage()
                .openHomePage()
                .checkRedirectingOnHomePage()
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
                .openProductPage()
                .checkSortingCorrect(sortingType);
    }

    /**
     * This test verifies display of only products marked as 'low price' on the 'BezpruzhinniMatraci' page
     */
    @Test
    public void alwaysLowPriceFilterBezpruzhinniMatraci() {
        initPage
                .getHomePage()
                .openHomePage()
                .checkRedirectingOnHomePage()
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
                .checkOnlyAlwaysLowPriceProductsDisplayed();
    }

}
