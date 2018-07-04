package paystation.domain;
/** A linear calculation rate strategy.

  Responsibilities:
			
  1) Calculate rates so each 5 cent gives 2 minutes parking
*/

public class LinearRateStrategy implements RateStrategy {
  public int calculateTime( int amount ) {
    return amount * 2 / 5;
  }
}

