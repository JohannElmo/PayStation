package Test.domain;

/*
 *  This test case is testing the ProgressiveRateStrategy class
 *  in isolation. 
 */


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paystation.domain.ProgressiveRateStrategy;
import paystation.domain.RateStrategy;

class TestUnitProgressiveRate {
	RateStrategy rate;
	@BeforeEach
	void setUp() throws Exception {
		rate = new ProgressiveRateStrategy();
	}

	// 1 hour test
	@Test
	void shouldDisplay60MinFor150Cent()
	{
		assertEquals(60 /*minutes*/, rate.calculateTime(150));
	}
	
	// 2 hour test
	@Test
	void shouldDisplay120MinFor350Cent() 
	{
		// Two hours: $1.5 + $2.
		assertEquals( 2*60 /*minutes*/, rate.calculateTime(350));
	}
	
	// 3 hour test
		@Test
		void shouldDisplay180MinFor650Cent() 
		{
			// Three hours: $1.5 + $2 + $3.
			assertEquals( 3 * 60 /*minutes*/, rate.calculateTime(650));
		}
		
	// 4 hour test
	@Test
	void shouldDisplay240MinFor950Cent() 
	{
		// Four hours: $1.5 + $2 + $3 + $3.
		assertEquals( 4 * 60 /*minutes*/, rate.calculateTime(950));
	}
		

}
