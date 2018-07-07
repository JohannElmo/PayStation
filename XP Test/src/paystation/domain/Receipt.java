package paystation.domain;

import java.io.PrintStream;

/** 
*/
public interface Receipt {

  /**
   * Return the number of minutes this receipt is valid for.
   * @return number of minutes parking time
  */
  public int value();

  /**
   * Prints the receipt
   * @param ps takes a PrintStream 
   */
public void print(PrintStream ps);
}

