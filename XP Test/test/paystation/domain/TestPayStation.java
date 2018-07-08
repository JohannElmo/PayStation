package paystation.domain;

import org.junit.*;

import paystation.domain.PayStation;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import paystation.domain.IllegalCoinException;
import paystation.domain.LinearRateStrategy;
import paystation.domain.PayStationImpl;
import paystation.domain.ProgressiveRateStrategy;
import paystation.domain.Receipt;
import paystation.domain.StandardReceipt;

// Test cases for the Pay Station system.
public class TestPayStation {
  PayStation ps;
  /** Fixture for pay station testing. */
  @Before
  public void setUp() {
    ps = new PayStationImpl( new TestTownFactory() );
  }

  /**
   * Entering 5 cents should make the display report 2 minutes 
   * parking time.
  */
  @Test
  public void shouldDisplay2MinFor5Cents() throws IllegalCoinException {
    ps.addPayment( 5 );
    assertEquals( "Should display 5 min for 5 cents", 
                  5, ps.readDisplay() ); 
  }


  /** Verify that illegal coin values are rejected. */
  @Test(expected=IllegalCoinException.class)
  public void shouldRejectIllegalCoin() throws IllegalCoinException {
    ps.addPayment(17);
  }

  /** legal coins should be accepted. */
  @Test
  public void shouldAcceptLegalCoins() throws IllegalCoinException {
	  ps.addPayment(5);
	  ps.addPayment(10);
	  ps.addPayment(25);
	  assertEquals( "Should accept 5, 10 and 25 cents. ",
                  5+10+25, ps.readDisplay() );
  }

  /**
   * Buy should return a valid receipt of the 
   * proper amount of parking time
  */
  @Test 
  public void shouldReturnCorrectReceiptWhenBuy() 
    throws IllegalCoinException {
    ps.addPayment(5);
    ps.addPayment(10);
    ps.addPayment(25);
    Receipt receipt;
    receipt = ps.buy();
    assertNotNull( "Receipt reference cannot be null",
                   receipt );
    assertEquals( "Receipt value must be 16 min.",
                  5+10+25 , receipt.value() );
  }
 
  /** Receipts must be able to store parking time values. */
  @Test 
  public void shouldStoreTimeInReceipt() {
    Receipt receipt = new StandardReceipt(30);
    assertEquals( "Receipt can store 30 minute value",
                  30, receipt.value() );
  }

  /** Buy for 100 cents and verify the receipt. */
  @Test 
  public void shouldReturnReceiptWhenBuy100c() 
          throws IllegalCoinException {
    ps.addPayment(10);
    ps.addPayment(10);
    ps.addPayment(10);
    ps.addPayment(10);
    ps.addPayment(10);
    ps.addPayment(25);
    ps.addPayment(25);

    Receipt receipt;
    receipt = ps.buy();
    assertEquals((5*10+2*25), receipt.value() );
  }
  
  /** Verify that the pay station is cleared after a buy scenario. */
  @Test 
  public void shouldClearAfterBuy() 
          throws IllegalCoinException {
    ps.addPayment(25);
    ps.buy(); // I do not care about the result
    // verify that the display reads 0
    assertEquals( "Display should have been cleared",
                  0 , ps.readDisplay() );
    // verify that a following buy scenario behaves properly
    ps.addPayment(10); ps.addPayment(25);
    assertEquals( "Next add payment should display correct time",
                  10+25, ps.readDisplay() );
    Receipt r = ps.buy();
    assertEquals( "Next buy should return valid receipt",
                  10+25, r.value() );
    assertEquals( "Again, display should be cleared",
                  0 , ps.readDisplay() );
  }
  /** Verify that cancel clears the pay station. */
  @Test 
  public void shouldClearAfterCancel() 
          throws IllegalCoinException {
    ps.addPayment(10);
    ps.cancel();
    assertEquals( "Cancel should clear display",
                  0 , ps.readDisplay() );
    ps.addPayment(25);
    assertEquals( "Insert after cancel should work",
                  25 , ps.readDisplay() );
  }
  
  @Test
	public void shouldPrintStandardReceiptCorrectly() {
	 /* Constructing a new StandardReceipt */
	 Receipt receipt = new StandardReceipt(100);
	 /* Constructing a ByteArrayOutputStream */
	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 PrintStream ps = new PrintStream(baos);
	 
	 /* Receipt is printing itself */
	  receipt.print(ps);
	 /* Converting baos buffer's content to string */ 
	  String output = baos.toString();
	  /* Splitting the string at line break and saving it 
	   * to string array 
	   */
	  String [] lines = output.split("\n");
	  /* asserting that the 3 first characters in the
	   * first line of the receipt is --- */
	  assertEquals("---", lines[0].substring(0, 3));
	  assertEquals("P A R K I N G", lines[1].substring(9,22));
	  assertEquals("Value", lines[2].substring(16, 21));
	  /* Asserting that receipt show correct number of parking minutes. */
	  assertEquals(receipt.value(), Integer.parseInt(lines[2].substring(22, 25)));
	  assertEquals("Car", lines[3].substring(14,17));
	  /* Asserting that receipt has recorded the right hour
	   * when car was parked  */
	  assertEquals(LocalTime.now().getHour(), 
			  Integer.parseInt(lines[3].substring(28,30)));
	  /* Asserting that receipt has recorded the right minute
	   * when car was parked */
	  assertEquals(LocalTime.now().getMinute(), 
			  Integer.parseInt(lines[3].substring(31,33)));
	  /* Asserting that parked parked hour and minute is separated
	   * by and : */
	  assertEquals(":", lines[3].substring(30, 31));
	  /* Asserting the last line of receipt starts with --- */
	  assertEquals("---", lines[4].substring(0, 3));
	}
  
  @Test
  public void shoulPrintBarcodeReceiptCorrectly()
  {
	  Receipt receipt = new StandardReceipt(100,true,false);
	  
	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  PrintStream ps = new PrintStream(baos);
	  receipt.print(ps);
	  
	  String output = baos.toString();
	  String[] lines = output.split("\n");
	  
	  assertEquals("receipt with barcode should b 6 lines long", 6, lines.length);
  }
  
  
  /* Making one dollar insert.*/
  private void addOneDollar() throws IllegalCoinException 
  {
    ps.addPayment(25); ps.addPayment(25); 
    ps.addPayment(25); ps.addPayment(25); 
  }

}
