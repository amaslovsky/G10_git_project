import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BasketTests.class,
        BezpruzhinniMatraciTests.class,
        ObranePageTest.class,
        RegistrationTest.class
})
public class RegressionSuite {
}
