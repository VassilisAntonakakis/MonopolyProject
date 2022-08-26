package monopoly;

import java.net.UnknownHostException;
import java.util.Scanner;

public class Menu {
	
	private Scanner input;
	private int choice;
	
	public Menu() throws UnknownHostException {
		this.setChoice(-1);
		this.input = new Scanner(System.in);
		printMenu();
	}

	private void printMenu() throws UnknownHostException {
		System.out.printf("Welcome to Monopoly!\nChoose game mode:\n1)Single Player\n2)Host LAN Multiplayer\n3)Join LAN Multiplayer\n4)Exit\n");
		choice = Integer.parseInt(input.nextLine());
		
		switch (choice){
			case 1:
				GameMaster gm1 = new GameMaster();
				break;
			case 2:
				LanServer lan = new LanServer();
				break;
			case 3:
				break;
			case 4:
				break;
		}
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}
}
