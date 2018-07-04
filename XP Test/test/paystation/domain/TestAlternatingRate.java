package paystation.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class TestAlternatingRate {

	@Test 
	public void shouldReturn120MinFor300CentsWhenWeekday() {
		RateStrategy rate = new AlternatingRateStrategy(new ProgressiveRateStrategy(), 
				new LinearRateStrategy(), new FixedDecisionStrategy(false));
		assertEquals(120 /*min*/, rate.calculateTime(300));
	}
	
	@Test 
	public void shouldReturn120MinFor350centsWhenWeekend() {
		RateStrategy rate = new AlternatingRateStrategy(new ProgressiveRateStrategy(), 
				new LinearRateStrategy(), new FixedDecisionStrategy(true));
		assertEquals(120 /*min*/, rate.calculateTime(350));
	}

}
