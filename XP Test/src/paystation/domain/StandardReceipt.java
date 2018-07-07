package paystation.domain;

import java.io.PrintStream;
import java.time.LocalTime;

/**
 * The Receipt object receive the right time 
 * (in minutes) calculated based on the
 * amount of coins inserted into the pay station
 * in some currency.
 * 
 * @author johann
 *
 */
public class StandardReceipt implements Receipt {
  private int value;
  private Boolean barcode;
  
	/* Constructing a standard receipt option 1 */
	public StandardReceipt(int value, Boolean barcode) {
		this.value = value;
		this.barcode = barcode;
	}
	
	/* Constructing a standard receipt option 2 */
	public StandardReceipt(int value) {
		this(value,false);
	}
	
	public int value() {
		return value;
	}
	
	public void print(PrintStream stream) {
		String valueString = ""+value;
		if(valueString.length() == 1) {valueString = "00" + valueString;}
		if(valueString.length() == 2) {valueString = "0" + valueString;}
		LocalTime time = LocalTime.now();
		String hour = ""+time.getHour();
		if(hour.length() == 1) {hour = "0"+hour;}
		String min = ""+time.getMinute();
		if(min.length() == 1) {min = "0"+min;}
		stream.println("------------------------------------------------");
		stream.println("-------  P A R K I N G    R E C E I P T --------");
		stream.println("                Value "+valueString+
				" minutes.");
		stream.println("              Car Parked at "+hour + ":"+min);
		if(barcode) 
		{stream.println("|| ||||| | || ||| || ||  ||| | || |||| | || ||||");} 
		stream.println("------------------------------------------------");	
	}
}
