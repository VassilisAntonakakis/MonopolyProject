package monopoly;

public class RailroadCell extends Cell{
	private int value;
	private int mortgageValue;
	private int owner;
	private int baseRent; 
	
	public RailroadCell(String title, int id) {
		this.setTitle(title);
		this.setId(id);
		this.setValue(200);
		this.setMortgageValue(100);
		this.setOwner(-1);
		this.setBaseRent(50);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public double getMortgageValue() {
		return mortgageValue;
	}
	
	public void setMortgageValue(int mortgageValue) {
		this.mortgageValue = mortgageValue;
	}
	
	public int getOwner() {
		return owner;
	}
	
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	public int getBaseRent() {
		return baseRent;
	}
	
	public void setBaseRent(int baseRent) {
		this.baseRent = baseRent;
	}

}
