package paystation.domain;

public class ValueBasedDisplayStrategy implements DisplayStrategy {

	public int calculateOutput(int timeBought) {
		return timeBought;
	}

}
