package monopoly;

public class CardCell extends Cell{
	private int type; //0 commChest - 1 chance
	
	public CardCell(String title, int type, int id) {
		this.setTitle(title);
		this.setType(type);
		this.setId(id);
	}
	
	public void drawCard() {
		super.drawCard(this.getType());
	}

	public int getType() {
		return type;
	}
	
	public String getTypeName() {
		if(this.getType() == 0) return "Community chest";
		return "Chance";
	}
	
	public void setType(int type) {
		this.type = type;
	}
}
