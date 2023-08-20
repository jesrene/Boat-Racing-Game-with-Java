package assignment;

import java.util.*;

public class River {
	private Map<String, Integer> boat;
	RiverItem[] riveritems = new RiverItem[100];
	
    public River() {
	    /*
   	    ** ==================== Define the range for number of traps =======================
   	    */
	    int maxNumOfTraps = 10;
	    int minNumOfTraps = 1;
	    int rangeNumOfTraps = maxNumOfTraps - minNumOfTraps + 1;
	    int randNumOfTraps = (int)(Math.random() * rangeNumOfTraps) + minNumOfTraps;
	    
	    /*
   	    ** ==================== Define the range for number of currents =======================
   	    */
	    int maxNumOfCurrents = 10;
	    int minNumOfCurrents = 1;
	    int rangeNumOfCurrents = maxNumOfCurrents - minNumOfCurrents + 1;
	    int randNumOfCurrents = (int)(Math.random() * rangeNumOfCurrents) + minNumOfCurrents;
	       
	    /*
   	    ** ====================Location of traps and currents =======================
   	    */
	    for(int i=0; i<randNumOfCurrents; i++) {
			Current c = new Current();
			
			if(riveritems[c.getStartPosition()] == null) {
				riveritems[c.getStartPosition()] = c;
			}
		}
		
		for(int i=0; i<randNumOfTraps; i++) {
			Trap t = new Trap();
			
			if(riveritems[t.getStartPosition()] == null) {
				riveritems[t.getStartPosition()] = t;
			}
		}
		
		this.boat = new HashMap<String, Integer>();
	}	

	public Map<String, Integer> getBoat() {
	    return boat;
	}

	public void setBoat(Map<String, Integer> boat) {
	    this.boat = boat;
	}
	
	public RiverItem[] getRiverItems() {
		return riveritems;
	}

	public void setRiverItems(RiverItem[] riveritems) {
		this.riveritems = riveritems;
	}
	    
	 /*
   	 ** ==================== Display river =======================
   	 */
	public void printRiver() {
	    System.out.print("River: [Start] ");
			for(RiverItem r: riveritems) {
				if(r != null) {
					System.out.print(r.getSymbol());
				}
				else {
					System.out.print(".");
				}
			}
			System.out.print(" [End]");
			System.out.println("\nLocation:      0123456789111111111122222222223333333333444444444455555555556666666666777777777788888888889999999999");
			System.out.println("                         012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
		}
	
	@Override
	public String toString() {
		return String.format("River [riveritems=%s]", Arrays.toString(riveritems));
	}
}
