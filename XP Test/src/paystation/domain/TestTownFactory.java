package paystation.domain;


public class TestTownFactory implements PayStationFactory {

	public RateStrategy createRateStrategy() {
		return new One2OneRateStrategy();
		}
	
	@Override
	public Receipt createReceipt(int parkingTime) {
		return new StandardReceipt(parkingTime);
		}

	@Override
	public DisplayStrategy createDisplayStrategy() {
		return new ValueBasedDisplayStrategy();
	}
	
}
