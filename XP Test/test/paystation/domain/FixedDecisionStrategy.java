package paystation.domain;

public class FixedDecisionStrategy implements WeekendDecisionStrategy {

	Boolean isWeekend;
	
	public FixedDecisionStrategy(Boolean isWeekend) {
		this.isWeekend = isWeekend;
	}
	
	public Boolean isWeekend() {
		return isWeekend;
	}

}
