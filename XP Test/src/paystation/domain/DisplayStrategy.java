package paystation.domain;
/** Interface that decides how the inserted amount of coins
 * is represented as parking time. */

public interface DisplayStrategy {
	/**
	 * Calculates how time bought should be represented 
	 * to customer.
	 * @param timeBought is the minutes of parking bought.
	 * @return the representation of time bought.
	 */
	public int calculateOutput(int timeBought);
}
