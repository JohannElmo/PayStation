package paystation.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClockBasedDecisionStrategy implements WeekendDecisionStrategy {

	public Boolean isWeekend() {
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate date = currentTime.toLocalDate();
		DayOfWeek toDay = date.getDayOfWeek();
		return toDay.compareTo(DayOfWeek.SATURDAY) == 0 || 
				toDay.compareTo(DayOfWeek.SUNDAY) == 0 ? true : false;
	}

}
