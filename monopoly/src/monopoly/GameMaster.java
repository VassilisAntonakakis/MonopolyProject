package monopoly;

public class GameMaster {
	
	Board b1;
	Cards deck;
	
	public GameMaster() {
		b1 = new Board();
		deck = new Cards();
	}
	
	public void drawCard(int type) {
		deck.getRandomCard(type);
	}
	
	private int dice() {
		int min = 1, max = 6, range = max - min + 1;
		return (int)(Math.random() * range) + min;
	}
	
}
