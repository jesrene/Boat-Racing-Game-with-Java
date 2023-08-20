package assignment;

public class Trap extends RiverItem{
	public Trap(){
		super();
		setSymbol("#");
	}

	@Override
	public String toString() {
		return String.format("Trap [%s]", super.toString());
	}
    
}
