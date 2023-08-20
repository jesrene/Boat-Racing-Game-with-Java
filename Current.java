package assignment;

public class Current extends RiverItem{
	public Current(){
		super();
		setSymbol("C");
	}

	@Override
	public String toString() {
		return String.format("Current [%s]", super.toString());
	}
    
}
