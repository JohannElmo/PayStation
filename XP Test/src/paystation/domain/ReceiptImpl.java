package paystation.domain;


/**
 * The Receipt object receive the right time 
 * (in minutes) calculated based on the
 * amount of coins inserted into the pay station
 * in some currency.
 * 
 * @author johann
 *
 */
public class ReceiptImpl implements Receipt {
  private int value;
  
  public ReceiptImpl(int value) { this.value = value; }
  
  public int value() { return value;}
}
