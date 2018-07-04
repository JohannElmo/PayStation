package paystation.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import paystation.domain.IllegalCoinException;
import paystation.domain.LinearRateStrategy;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.ProgressiveRateStrategy;


public class TestIntegration {
	PayStation ps;

	@Test
	public void shouldIntegrateLinearRateCorrectly() throws IllegalCoinException {
		//set up pay station with Linear rate strategy
		ps = new PayStationImpl(new LinearRateStrategy() );
		addOneDollar(); addOneDollar();
		//1st hour $1.5 20 min for 50 cents
		assertEquals(80, ps.readDisplay() );
	}
	
	@Test
	public void shouldIntegrateProgressiveRateCorrectly() throws IllegalCoinException {
		// Set up pay station with progressive rate strategy
		ps = new PayStationImpl(new ProgressiveRateStrategy() );
		addOneDollar(); addOneDollar();
		assertEquals(75, ps.readDisplay() );
	}

	private void addOneDollar() throws IllegalCoinException {
		ps.addPayment(25); ps.addPayment(25); 
		ps.addPayment(25); ps.addPayment(25);
	}
}
