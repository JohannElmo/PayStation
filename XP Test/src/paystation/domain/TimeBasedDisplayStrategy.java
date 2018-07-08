package paystation.domain;

import java.time.LocalTime;

public class TimeBasedDisplayStrategy implements DisplayStrategy {

	public int calculateOutput(int timeBought) {
		LocalTime t = LocalTime.now().plusMinutes(timeBought);
		String parkingTime = "" + t.getHour();
		parkingTime = parkingTime + t.getMinute();
		return Integer.parseInt(parkingTime);
	}

}
