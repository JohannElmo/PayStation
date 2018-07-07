package paystation.domain;

public class GammaTownFactory implements PayStationFactory {

	public RateStrategy createRateStrategy() {
		return new AlternatingRateStrategy(new ProgressiveRateStrategy(), 
				new LinearRateStrategy(), 
				new ClockBasedDecisionStrategy());
	}

	public Receipt createReceipt(int parkingTime) {
		return new StandardReceipt(parkingTime);
	}

}
