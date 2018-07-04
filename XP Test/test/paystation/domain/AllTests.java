package paystation.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPayStation.class, TestProgressiveRate.class, 
	 TestAlternatingRate.class, TestIntegration.class, TestLinearRate.class, 
	 TestProgressiveRate.class })
public class AllTests {

}
