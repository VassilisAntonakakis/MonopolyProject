package monopoly;

public class PropertyCell extends Cell{
	private String colour;
	private int value;
	private int mortgageValue;
	private String owner;
	private int houseNo;
	private int baseRent; 
	private int activeRent;
	private int buildingCost;
	
	public PropertyCell(String title, int value, int mortgageValue, int baseRent, String colour, int buildingCost, int id) {
		this.setTitle(title);
		this.setValue(value);
		this.setMortgageValue(mortgageValue);
		this.setOwner("");
		this.setBaseRent(baseRent);
		this.setHouseNo(0);
		this.setActiveRent();
		this.setColour(colour);
		this.setId(id);
		this.setBuildingCost(buildingCost);
	}
	
	public double getValue() {
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public int getBaseRent() {
		return baseRent;
	}
	public void setBaseRent(int baseRent) {
		this.baseRent = baseRent;
	}
	public int getActiveRent() {
		return activeRent;
	}
	private void setActiveRent() {
		this.activeRent = this.getBaseRent() + (this.getBaseRent() * this.getHouseNo());
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getBuildingCost() {
		return buildingCost;
	}

	public void setBuildingCost(int buildingCost) {
		this.buildingCost = buildingCost;
	}
}
