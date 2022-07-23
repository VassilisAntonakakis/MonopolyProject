package monopoly;

public class GameMaster {
	
	Board b1 = new Board();
	Cards deck = new Cards();
	
	public GameMaster() {
		
	}
	
	public void drawCard(int type) {
		deck.getRandomCard(type);
	}
	
	private int dice() {
		int min = 1, max = 6, range = max - min + 1;
		return (int)(Math.random() * range) + min;
	}
	
}
