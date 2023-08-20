package assignment;

public class RiverItem {
	private int startposition;
	private int endposition;
	private int strength;
	private String symbol;
	
	public RiverItem() {
	    /*
   	    ** ==================== Randomize the positions of traps and currents =======================
   	    */
	    int maxTC = 99;
	    int minTC = 5;
	    int rangeTC = maxTC - minTC + 1;
	    startposition = (int)(Math.random() * rangeTC) + minTC;
	
	    /*
   	    ** ==================== Randomize the strengths of traps and currents =======================
   	    */		
	    int maxSteps = 5;
	    int minSteps = 1;
	    int rangeSteps = maxSteps - minSteps + 1;
	    strength = (int)(Math.random() * rangeSteps) + minSteps;
	}
	
	public int getStartPosition() {
		return startposition;
	}

	public void setStartPosition(int position) {
		this.startposition = position;
	}

	public int getEndPosition() {
		return endposition;
	}

	public void setEndposition(int endposition) {
		this.endposition = endposition;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "RiverItem [startposition=" + startposition + ", endposition=" + endposition + ", strength=" + strength
				+ ", symbol=" + symbol + "]";
	}
}
