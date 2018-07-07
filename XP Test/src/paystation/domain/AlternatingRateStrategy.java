package paystation.domain;

public class AlternatingRateStrategy implements RateStrategy {
	
	RateStrategy WeekendStrategy, WeekDayStrategy, currentState;
	WeekendDecisionStrategy weekendDecision;
	
	/** Constructing an Alternating rate strategy */
	public AlternatingRateStrategy(RateStrategy WeekendStrategy, 
			RateStrategy WeekDayStrategy,
			WeekendDecisionStrategy isWeekend) {
		this.WeekendStrategy = WeekendStrategy;
		this.WeekDayStrategy = WeekDayStrategy;
		currentState = null;
		this.weekendDecision = isWeekend;
	}

	public int calculateTime(int amount) {
		if(weekendDecision.isWeekend())
			currentState = WeekendStrategy;
		else
			currentState = WeekDayStrategy;
		
		return currentState.calculateTime(amount);
	}

}
