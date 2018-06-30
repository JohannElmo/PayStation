package Test.domain;

/**
 * This is a unit test of the pay station. It
 * is using a very simple rateStrategy so that 
 * the pay station can be tested for functionality
 * that is not related to rate calculations.
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import paystation.domain.IllegalCoinException;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.Receipt;

class TestUnitPayStationTest {
	PayStation ps;
	@BeforeEach
	void setUp() throws Exception {
		ps = new PayStationImpl(new One2OneRateStrategy());
	}
	/*
	 * The buy functionality is not depended on rate
	 * calculation.
	 */
	@Test		
	void shouldReturnValidReceiptWhenBuy() throws IllegalCoinException 
	{
		// 5 cent = 5 minutes parking
		ps.addPayment(5);
		Receipt r = ps.buy();
		assertEquals(5, r.value() );
	}
	
	/**
	 * Testing that cancel functionality is working
	 * independently of rate calculation.
	 * @throws IllegalCoinException 
	 */
	@Test
	void shouldResetWhenCancel() throws IllegalCoinException
	{
		ps.addPayment(5);
		ps.cancel();
		assertEquals(0, ps.readDisplay() );
	}

}
