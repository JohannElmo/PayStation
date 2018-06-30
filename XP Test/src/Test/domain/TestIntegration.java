package Test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import paystation.domain.IllegalCoinException;
import paystation.domain.LinearRateStrategy;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.ProgressiveRateStrategy;

class TestIntegration {
	PayStation ps;

	@Test
	void shouldIntegrateLinearRateCorrectly() throws IllegalCoinException {
		//set up pay station with Linear rate strategy
		ps = new PayStationImpl(new LinearRateStrategy() );
		addOneDollar(); addOneDollar();
		//1st hour $1.5 20 min for 50 cents
		assertEquals(80, ps.readDisplay() );
	}
	
	@Test
	void shouldIntegrateProgressiveRateCorrectly() throws IllegalCoinException {
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
