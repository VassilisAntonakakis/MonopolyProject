package monopoly;

public class Cards {
	private String[] commChest;
	private String[] chance;
			
	public Cards() {
		commChest = new String[19];
		chance = new String[24];
	}
	
	public void getRandomCard(int type) {
		//0 commChest - 1 chance
		int card = -1, min = 1, max, range;
		
		if(type == 0) {
			max = 19;
			range = max - min + 1;
			card = (int)(Math.random() * range) + min;
			System.out.println(commChest[card]);
		}
		
		if(type == 1) {
			max = 24;
			range = max - min + 1;
			card = (int)(Math.random() * range) + min;
			System.out.println(chance[card]);
		}
	}
}
