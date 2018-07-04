package paystation.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import paystation.domain.LinearRateStrategy;
import paystation.domain.RateStrategy;

public class TestLinearRate {
	RateStrategy rate;
	
	@Before
	public void setUp() throws Exception {
		rate = new LinearRateStrategy();
	}
	
	/**
	 * Only one testcase is needed because first hour calculations
	 * are identical with ProgressiveRate calculations. And
	 * if 2nd hour is correct calculated so will any hours 
	 * be calculated correct.
	 */

	@Test
	public void sholdDisplay120MinFor300Cent() 
	{
		assertEquals(300/5 * 2 /*minutes*/ , rate.calculateTime(300));
	}
	
//	@Test
//	void thisShouldFail()
//	{
//		assertFalse(true);
//	}

}
