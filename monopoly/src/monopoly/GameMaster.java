package monopoly;

import java.util.Scanner;

public class GameMaster {
	
	private Scanner input;
	private Board b1;
	private Cards deck;
	private Player[] playerList;
	private StateHolder stateHolder;
	private int playerNo;
	
	public GameMaster() {
		input = new Scanner(System.in);
		b1 = new Board();
		deck = new Cards();
		initPlayers();
		stateHolder = new StateHolder(this.playerList.length);
		gameRun();
		input.close();
	}
	
	private void initPlayers() {
		System.out.println("Give # of players:");
		this.setPlayerNo(input.nextInt());
		int isHuman;
		input.nextLine();
		playerList = new Player[playerNo];
		String name;
		
		for(int index=0; index<playerNo; index++) {	
			System.out.printf("Give name for player %d:\n", index+1);
			name = input.nextLine();
			/*System.out.printf("Is player %d cpu controlled? (0 No/1 Yes):\n", index+1);
			isHuman = Integer.parseInt(input.nextLine());*/
			playerList[index] = new Player(name, /*((isHuman == 0)?true:false)*/true);
		}
	}

	public void drawCard(int type) {
		deck.getRandomCard(type);
	}
	
	public StateHolder getStateHolder() {
		return stateHolder;
	}
	
	public Player[] getPlayerList() {
		return this.playerList;
	}
	
	public int getPlayerNo() {
		return this.playerNo;
	}
	
	public void setPlayerNo(int count) {
		this.playerNo = count;
	}

	private int dice() {
		int min = 1, max = 6, range = max - min + 1;
		return (int)(Math.random() * range) + min;
	}
	
	private void gameRun() {
		
		int activePlayer = 0, dice1, dice2, turn = 0, doubleDice=0;
		System.out.printf("No of players is %d\n", this.getPlayerNo());
		
		while(turn <= 400) {
			System.out.printf("\nIt's players %d (%s) turn\n", activePlayer, this.getPlayerList()[activePlayer].getName());
			do {
				dice1 = dice();
				dice2 = dice();
				System.out.printf("dice count for %s is: %d with d1:%d and d2:%d\n", this.getPlayerList()[activePlayer].getName(), (dice1 + dice2), dice1, dice2);
				if(dice1 == dice2 && doubleDice < 3) doubleDice++;
				
				if(doubleDice == 3) {
					this.goToJail(activePlayer);
					break;
				}
				else {
					this.getStateHolder().setPos(activePlayer, updatePlayerPos(this.getStateHolder().getPos(activePlayer), (dice1 + dice2), activePlayer));
					
					System.out.printf("New position id for %s is %d\nCell title %s\n",
							this.getPlayerList()[activePlayer].getName(),
							this.getStateHolder().getPos(activePlayer),
							this.getBoard().getTitle(this.getStateHolder().getPos(activePlayer)));
				}
				if(dice1 == dice2 && doubleDice < 3)System.out.println(this.getPlayerList()[activePlayer].getName() + " plays again");
			}while(dice1 == dice2);
			
			activePlayer = this.updateActivePlayer(activePlayer, this.getPlayerNo());
			doubleDice = 0;
			turn++;
		}
	}
	
	private void goToJail(int activePlayer) {
		System.out.printf("%s goes to Jail\n", this.getPlayerList()[activePlayer].getName());
		this.getStateHolder().setJailStatus(activePlayer);
		
	}

	private int updatePlayerPos(int playerPos, int diceSum, int activePlayer) {
		if((playerPos + diceSum) > 39) {this.getStateHolder().passFromGo(activePlayer); return ((playerPos+diceSum)-39);}
		return (playerPos + diceSum);
	}
	
	private int updateActivePlayer(int activePlayer, int playerNo) {
		
		if(activePlayer == playerNo-1)return 0;
		return activePlayer+1;
		
	}

	public Board getBoard() {
		return b1;
	}
}
