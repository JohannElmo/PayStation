package paystation.domain;

import org.junit.*;

import paystation.domain.IllegalCoinException;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.ProgressiveRateStrategy;


import static org.junit.Assert.*;

// Test cases for the BetaTown pay station with progressive rate policy.

public class TestProgressiveRate {
  PayStation ps;
  /** Fixture for pay station testing. */
  @Before
  public void setUp() {
    ps = new PayStationImpl( new ProgressiveRateStrategy() );
  }

  /** Test a single hour parking */
  @Test public void shouldDisplay60MinFor150cent() 
    throws IllegalCoinException { 
    // First hour: $1.5
    addOneDollar();
    addHalfDollar();

    assertEquals( 60 /*minutes*/, ps.readDisplay() ); 
  }

  /** Test two hours parking */
  @Test public void shouldDisplay120MinFor350cent() 
    throws IllegalCoinException { 
    // Two hours: $1.5+2.0
    addOneDollar();
    addOneDollar();
    addOneDollar();
    addHalfDollar();

    assertEquals( 2 * 60 /*minutes*/ , ps.readDisplay() ); 
  }

  /** Test three hours parking */
  @Test public void shouldDisplay180MinFor650cent() 
    throws IllegalCoinException { 
    // Three hours: $1.5+2.0+3.0
    addOneDollar(); addHalfDollar();
    addOneDollar(); addOneDollar();
    addOneDollar(); addOneDollar(); addOneDollar();

    assertEquals( 3 * 60 /*minutes*/ , ps.readDisplay() ); 
  }

  /** Test four hours parking */
  @Test public void shouldDisplay240MinFor950cent() 
    throws IllegalCoinException { 
    // Three hours: $1.5+2.0+3.0
    addOneDollar(); addHalfDollar();
    addOneDollar(); addOneDollar();
    addOneDollar(); addOneDollar(); addOneDollar();
    addOneDollar(); addOneDollar(); addOneDollar();

    assertEquals( 4 * 60 /*minutes*/ , ps.readDisplay() ); 
  }

  private void addHalfDollar() throws IllegalCoinException  {
    ps.addPayment( 25 ); ps.addPayment( 25 ); 
  }
  private void addOneDollar() throws IllegalCoinException {
    addHalfDollar(); addHalfDollar();
  }
} 
