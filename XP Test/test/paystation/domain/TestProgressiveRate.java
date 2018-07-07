package paystation.domain;

import org.junit.*;

import paystation.domain.IllegalCoinException;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;
import paystation.domain.ProgressiveRateStrategy;


import static org.junit.Assert.*;

// Test cases for the BetaTown pay station with progressive rate policy.


public class TestProgressiveRate {
  RateStrategy rs;
  /** Fixture for pay station testing. */
  @Before
  public void setUp() {
    rs = new ProgressiveRateStrategy();
  }

  /** Test a single hour parking */
  @Test public void shouldDisplay60MinFor150cent() 
    throws IllegalCoinException { 
    // First hour: $1.5
    assertEquals( 60 /*minutes*/, rs.calculateTime(150) ); 
  }

  /** Test two hours parking */
  @Test public void shouldDisplay120MinFor350cent() 
    throws IllegalCoinException { 
    // Two hours: $1.5+2.0
    assertEquals( 2 * 60 /*minutes*/ , rs.calculateTime(350) ); 
  }

  /** Test three hours parking */
  @Test public void shouldDisplay180MinFor650cent() 
    throws IllegalCoinException { 
    // Three hours: $1.5+2.0+3.0
    assertEquals( 3 * 60 /*minutes*/ , rs.calculateTime(650) ); 
  }

  /** Test four hours parking */
  @Test public void shouldDisplay240MinFor950cent() 
    throws IllegalCoinException { 
    // Three hours: $1.5+2.0+3.0
    assertEquals( 4 * 60 /*minutes*/ , rs.calculateTime(950) ); 
  }

} 
