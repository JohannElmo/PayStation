package paystation.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import paystation.domain.TestPayStation;

@RunWith(Suite.class)
@SuiteClasses({ TestPayStation.class, TestProgressiveRate.class, TestIntegration.class, 
	TestLinearRate.class, TestUnitPayStationTest.class, TestUnitProgressiveRate.class})


public class AllTests {

}
