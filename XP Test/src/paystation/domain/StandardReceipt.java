package paystation.domain;

import java.io.PrintStream;
import java.time.LocalTime;

/**
 * The Standard Receipts job is to print
 * a valid receipt. The print method
 * is currently using a parametric approach
 * in choosing the correct configuration when
 * printing.
 * 
 * @author johann
 *
 */
	public class StandardReceipt implements Receipt {
		private int value;
		private Boolean barcode;
		private Boolean timeBasedReceipt;
  
	
  	/** Constructing a receipt with no barcode and in
  	 * value display stile (prints number of minutes
  	 * the user has bought).
  	 * @param value the minutes bought.
  	 */
  	public StandardReceipt(int value) {
  		this.value = value;
		this.barcode = false;
		this.timeBasedReceipt = false;
	}
  	/**
	 * Constructing a standard receipt.
	 * @param value the minutes bought.
	 * @param barcode barcode option.
	 * @param timeBasedReceipt how should the receipt print
	 * valid parking time.
	 */
  	public StandardReceipt(int value, Boolean barcode,
  			boolean timeBasedReceipt) {
  		this.value = value;
		this.barcode = barcode;
		this.timeBasedReceipt = timeBasedReceipt;
	}
  	
	
	public int value() {
		return value;
	}
	
	public void print(PrintStream stream) {
		String valueString = ""+value;
		if(valueString.length() == 1) {valueString = "00" + valueString;}
		if(valueString.length() == 2) {valueString = "0" + valueString;}
		/** This will only work on java platform 8. */
		LocalTime time = LocalTime.now();
		stream.println("------------------------------------------------");
		stream.println("-------  P A R K I N G    R E C E I P T --------");
		/** If time based receipt stile is chosen the receipt prints "Expires" 
		 * followed by the time the receipt expires. */
		if(timeBasedReceipt) { stream.println("              Expires "+ time);}
		/** If time based receipt stile is not chosen the receipt prints "value" 
		 * followed by the number of minutes bought. */
		else { stream.println("                Value "+valueString+
				" minutes.");}
		stream.println("              Car Parked at "+ time);
		/** If the barcode option is chosen and extra barcode line is printed. */
		if(barcode) 
		{stream.println("|| ||||| | || ||| || ||  ||| | || |||| | || ||||");} 
		stream.println("------------------------------------------------");	
	}
}
