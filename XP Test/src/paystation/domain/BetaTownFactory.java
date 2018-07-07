package paystation.domain;
/** Factory that configure BetaTown. */

public class BetaTownFactory implements PayStationFactory {

	public RateStrategy createRateStrategy() {
		return new ProgressiveRateStrategy();
	}

	public Receipt createReceipt(int parkingTime) {
		return new StandardReceipt(parkingTime,true);
	}

}
