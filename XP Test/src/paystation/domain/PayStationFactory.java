package paystation.domain;
/** A factory that create the objects that configure
 * a pay station for the particular town to operate in.
 */

public interface PayStationFactory {
	
	/** Create an instance of the rate strategy to use. */
	public RateStrategy createRateStrategy();
	
	/** Create an instance of the receipt.
	 * @param parkingTime the number of minutes the receipt
	 * represents.*/
	public Receipt createReceipt(int parkingTime);
	
	/**
	 * Create instance of the display strategy to use.
	 * @param timeBougt the number of minutes bought.
	 */
	public DisplayStrategy createDisplayStrategy();

}
