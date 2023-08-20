package assignment;

import java.util.Random;

public class DiceService {
	 public static int roll() {
	        return new Random().nextInt(6) + 1; 
	         /**
		  * ===== Create a six sided dice numbered from 1 to 6 which produces random number when rolling it =====
		  */
	    }

	@Override
	public String toString() {
		return String.format("DiceService [getClass()=%s, hashCode()=%s, toString()=%s]", getClass(), hashCode(),
				super.toString());
	}
	 
}
