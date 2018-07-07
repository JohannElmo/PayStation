package paystation.domain;

import paystation.domain.RateStrategy;
import paystation.domain.PayStationFactory;

public class PayStationImpl implements PayStation {
  private int insertedSoFar;
  private int timeBought;

  /** the strategy for rate calculations */
  private RateStrategy rateStrategy;
  /** The factory defining strategies. */
  private PayStationFactory factory;
  
  /** Construct a pay station instance with the given
      rate calculation strategy.
      @param rateStrategy the rate calculation strategy to use */
  public PayStationImpl( PayStationFactory factory ) {
    this.factory = factory;
    this.rateStrategy = factory.createRateStrategy();
    reset();
  }

  public void addPayment( int coinValue ) 
    throws IllegalCoinException {
    switch ( coinValue ) {
    case 5: break;
    case 10: break;  
    case 25: break;  
    default: 
      throw new IllegalCoinException("Invalid coin: "+coinValue);
    }
    insertedSoFar += coinValue;
    timeBought = rateStrategy.calculateTime(insertedSoFar);
  }
  public int readDisplay() {
    return timeBought;
  }
  
  /* Issuing a receipt */
  public Receipt buy() {
    Receipt r = factory.createReceipt(timeBought);
    reset();
    return r;
  }
  /* Canceling a transaction */
  public void cancel() {
    reset();
  }
  
  /* Method that resets the pay station */
  private void reset() {
    timeBought = insertedSoFar = 0;
  }

}

