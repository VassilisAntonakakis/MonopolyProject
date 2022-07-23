package monopoly;

public class UtilityCell extends Cell{
	private int value;
	private int mortgageValue;
	
	public UtilityCell(String title, int value, int mortgageValue){
		this.setTitle(title);
		this.setValue(value);
		this.setMortgageValue(mortgageValue);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getMortgageValue() {
		return mortgageValue;
	}

	public void setMortgageValue(int mortgageValue) {
		this.mortgageValue = mortgageValue;
	}
}
