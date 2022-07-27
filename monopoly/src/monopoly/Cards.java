package monopoly;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cards {
	private String[] commChest;
	private String[] chance;
			
	public Cards() {
		commChest = new String[19];
		chance = new String[16];
		
		this.initDeck(commChest, chance);
		System.out.println("Deck init done");
	}
	
	private void initDeck(String[] commChest, String[] chance) {
		
		File chanceFile = new File("src\\monopoly\\chance.txt");
		File commChestFile = new File("src\\monopoly\\commChest.txt");
		int commCount=0, chanceCount=0;
		
		try {
			Scanner chanceInput = new Scanner(chanceFile);
			Scanner commChestInput = new Scanner(commChestFile);
			
			for(int index=0; index<32; index++) {
				
				if(index <= 15) {
					commChest[commCount] = commChestInput.nextLine();
					commCount++;
					//System.out.printf("Comm Chest %d card added %s\n", commCount-1, commChest[commCount-1]);
					continue;
				}
			
				chance[chanceCount] = chanceInput.nextLine();
				chanceCount++;
				//System.out.printf("Chance %d card added %s\n", chanceCount-1, chance[chanceCount-1]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getRandomCard(int type) {
		//0 commChest - 1 chance
		int card = -1, min = 1, max, range;
		
		if(type == 0) {
			max = 15;
			range = max - min + 1;
			card = (int)(Math.random() * range) + min;
			return commChest[card];
		}
		
		if(type == 1) {
			max = 15;
			range = max - min + 1;
			card = (int)(Math.random() * range) + min;
			return chance[card];
		}
		
		return null;
	}
}
