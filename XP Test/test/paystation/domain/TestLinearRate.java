package paystation.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import paystation.domain.LinearRateStrategy;
import paystation.domain.RateStrategy;

class TestLinearRate {
	RateStrategy rate;
	
	@BeforeEach
	void setUp() throws Exception {
		rate = new LinearRateStrategy();
	}
	
	/**
	 * Only one testcase is needed because first hour calculations
	 * are identical with ProgressiveRate calculations. And
	 * if 2nd hour is correct calculated so will any hours 
	 * be calculated correct.
	 */

	@Test
	void sholdDisplay120MinFor300Cent() 
	{
		assertEquals(300/5 * 2 /*minutes*/ , rate.calculateTime(300));
	}
	
//	@Test
//	void thisShouldFail()
//	{
//		assertFalse(true);
//	}

}
