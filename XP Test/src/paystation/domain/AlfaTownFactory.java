package paystation.domain;
/* Factory that configure ALfaTown. */
public class AlfaTownFactory implements PayStationFactory {

	public RateStrategy createRateStrategy() {
		return new LinearRateStrategy();
	}

	public Receipt createReceipt(int parkingTime) {
		return new StandardReceipt(parkingTime);
	}

}
