package paystation.domain;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import org.junit.Test;

import paystation.domain.IllegalCoinException;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;

public class TestIntegration {
	PayStation ps;

	@Test
	public void shouldIntegrateLinearRateCorrectly() throws IllegalCoinException {
		//set up pay station with AlfaTown factory
		ps = new PayStationImpl(new AlfaTownFactory() );
		addOneDollar(); addOneDollar();
		/**Alfa town implements time based display strategy
		 * so pay station should display current time plus number
		 * of minutes bought. $2 = 80 minutes */
		LocalTime displayTime = LocalTime.now().plusMinutes(80);
		String parkingTime = "" + displayTime.getHour();
		parkingTime = parkingTime + displayTime.getMinute();
		assertEquals("Should display time parked plus 80 minutes formatted "
		  		+ "so that there is no semicolon separating hour and minute", 
				  Integer.parseInt(parkingTime), ps.readDisplay() );
		/* Issuing the fatory's receipt. */
		Receipt receipt = ps.buy();
		/* AlfaTown should not print barcode. */
		assertEquals("Alfa Town receipt shold have 5 lines",
				5, getReceiptLinesCount(receipt) );
	}
	
	
	@Test
	public void shouldIntegrateProgressiveRateCorrectly() throws IllegalCoinException {
		// Set up pay station with BetaTown factory
		ps = new PayStationImpl(new BetaTownFactory() );
		addOneDollar(); addOneDollar();
		assertEquals(75, ps.readDisplay() );
		Receipt receipt = ps.buy();
		/* BetaTown should  print barcode. */
		assertEquals("BetaTown receipt shold have 6 lines",
				6, getReceiptLinesCount(receipt) );
	}
	
	@Test
	public void shoudIntegrateAlternatingRateCorrectly() throws IllegalCoinException
	// Set up pay station with GammaTown factory
	{
		ps = new PayStationImpl(new GammaTownFactory());
		addOneDollar(); addOneDollar();
		assertEquals("THIS FAILS AT WEEK DAYS: progressive rate $2 = 75 minutes", 
				75, ps.readDisplay());
		Receipt receipt = ps.buy();
		/** Standard receipt should have 5 lines. */
		assertEquals("receipt should have 5 lines", 
				5, getReceiptLinesCount(receipt) );
	}

	
	private int getReceiptLinesCount(Receipt receipt)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(baos);
		receipt.print(stream);
		String output = baos.toString();
		String[] lines = output.split("\n");
		return lines.length;
	}
	
	private void addOneDollar() throws IllegalCoinException {
		ps.addPayment(25); ps.addPayment(25); 
		ps.addPayment(25); ps.addPayment(25);
	}
	
}
