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
		System.out.println(b1.getClass().getName());
		deck = new Cards();
		initPlayers();
		stateHolder = new StateHolder(this.playerList.length);
		gameRun();
		input.close();
	}
	
	private void initPlayers() {
		
		/*System.out.println("Give # of players:");
		this.setPlayerNo(input.nextInt());
		int isHuman;
		input.nextLine();
		playerList = new Player[playerNo];
		String name;
		
		for(int index=0; index<playerNo; index++) {	
			System.out.printf("Give name for player %d:\n", index+1);
			name = input.nextLine();
			System.out.printf("Is player %d cpu controlled? (0 No/1 Yes):\n", index+1);
			isHuman = Integer.parseInt(input.nextLine());
			playerList[index] = new Player(name, ((isHuman == 0)?true:false));*/
			
			playerNo = 4;
			playerList = new Player[4];
			playerList[0] = new Player("Vassilis", true);
			playerList[1] = new Player("Eleni", true);
			playerList[2] = new Player("Makis", true);
			playerList[3] = new Player("Mitsos", true);
			
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
		
		while(turn <= 400 && allBankrupt() == false) {
			System.out.printf("\nIt's players %d (%s) turn\n", activePlayer, this.getPlayerList()[activePlayer].getName());
			do {
				
				if(this.getStateHolder().isBankrupt(activePlayer)) break;
				dice1 = dice();
				dice2 = dice();
				System.out.printf("dice count for %s is: %d with d1:%d and d2:%d\n", this.getPlayerList()[activePlayer].getName(), (dice1 + dice2), dice1, dice2);
				
				if(this.getStateHolder().getJailStatus(activePlayer) == 1 && dice1 != dice2 && this.getStateHolder().getTurnsInJail(activePlayer) == 1) {
					System.out.println("You didn't bring doubles so you stay in jail. Too bad!");
					this.getStateHolder().increseTurnsInJail(activePlayer);
					break;
				}
				else if(dice1 != dice2 && this.getStateHolder().getTurnsInJail(activePlayer) == 2) {
					System.out.printf("Player %s pays 50€ to get out of jail\n");
					this.getStateHolder().setMoney(activePlayer, -50);
					this.getStateHolder().setJailStatus(activePlayer);
					this.getStateHolder().resetTurnsInJail(activePlayer);
				}
				
				if(dice1 == dice2 && doubleDice < 3) doubleDice++;
				
				if(doubleDice == 3) {
					this.goToJail(activePlayer);
					break;
				}
				else {
					this.getStateHolder().setPos(activePlayer, updatePlayerPos(this.getStateHolder().getPos(activePlayer), (dice1 + dice2), activePlayer));
					
					if(this.getStateHolder().getPos(activePlayer) == 30) { this.goToJail(activePlayer); break;}
					
					System.out.printf("New position id for %s is %d\nCell title %s\n",
							this.getPlayerList()[activePlayer].getName(),
							this.getStateHolder().getPos(activePlayer),
							this.getBoard().getTitle(this.getStateHolder().getPos(activePlayer)));
				}
				
				this.ruleCheck(activePlayer);
				
				if(dice1 == dice2 && doubleDice < 3)System.out.println(this.getPlayerList()[activePlayer].getName() + " plays again");
			}while(dice1 == dice2);
			
			activePlayer = this.updateActivePlayer(activePlayer, this.getPlayerNo());
			doubleDice = 0;
			turn++;
		}
	}
	
	private boolean allBankrupt() {
		for(int index=0; index<this.getPlayerNo(); index++) {
			if(this.getStateHolder().isBankrupt(index) == false) return false;
		}
		System.out.println("All players are bankrupt!");
		return true;
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
	
	private Cards getCardsDeck() {
		return this.deck;
	}
	
	private void ruleCheck(int activePlayer) {
		int pos = this.getStateHolder().getPos(activePlayer);
		
		
		if(pos == 2 || pos == 18 || pos == 34) {//Απόφαση
			System.out.println("Card reads: " + this.getCardsDeck().getRandomCard(0));
		}
		
		if(pos == 7 || pos == 22 || pos == 36) {//Εντολή
			System.out.println("Card reads: " + this.getCardsDeck().getRandomCard(1));
		}
		
		if(pos == 4) {//φόρος εισοδήματος
			this.getStateHolder().setMoney(activePlayer, -200);
		}
		
		if(pos == 38) {//φόρος πολυτελείας
			this.getStateHolder().setMoney(activePlayer, -100);
		}
		
		if(pos == 30) {//Πήγαινε στην φυλακή
			this.getStateHolder().setJailStatus(activePlayer);
			this.getStateHolder().setPos(activePlayer, 10);
		}
		
		if(this.getBoard().getCellType(pos) == "monopoly.PropertyCell") this.checkProperty(activePlayer, pos);
		
		if(this.getBoard().getCellType(pos) == "monopoly.RailroadCell") this.checkStation(activePlayer, pos);
		
	}

	private void checkStation(int activePlayer, int pos) {
		int index = 0;
		
		while(this.getBoard().getRailroadCells()[index].getId() != pos && index < 22) index++;
		
		if(index < 22 && this.getBoard().getRailroadCells()[index].getOwner() != -1 && this.getBoard().getRailroadCells()[index].getOwner() != activePlayer) {
			System.out.printf("Player %s pays %d rent to %s",
					this.getPlayerList()[activePlayer].getName(),
					this.getBoard().getRailroadCells()[index].getBaseRent() * this.getStateHolder().getStationCount(activePlayer),
					this.getPlayerList()[this.getBoard().getRailroadCells()[index].getOwner()].getName());
			
			this.getStateHolder().setMoney(
					activePlayer,
					-(this.getBoard().getRailroadCells()[index].getBaseRent() * this.getStateHolder().getStationCount(activePlayer))
					);
			
			this.getStateHolder().setMoney(
					this.getBoard().getRailroadCells()[index].getOwner() * this.getStateHolder().getStationCount(activePlayer),
					this.getBoard().getRailroadCells()[index].getBaseRent()
					);
			
			System.out.printf("Player %d's (%s) new balance: %d\n",
					activePlayer, this.getPlayerList()[activePlayer].getName(),
					this.getStateHolder().getMoney(activePlayer)
					);
			
		}
		else {
			System.out.printf("Unowned station! Player %d (%s) buys it\n",
					activePlayer,
					this.getPlayerList()[activePlayer].getName()
					);
			
			this.getBoard().getRailroadCells()[index].setOwner(activePlayer);
			
			this.getStateHolder().setMoney(
					activePlayer,
					-(this.getBoard().getRailroadCells()[index].getValue())
					);
			
			System.out.printf("Player %d's (%s) new balance: %d\n",
					activePlayer, this.getPlayerList()[activePlayer].getName(),
					this.getStateHolder().getMoney(activePlayer)
					);
		}
		
	}

	private void checkProperty(int activePlayer, int pos) {
		int index = 0;
		
		while(this.getBoard().getProperties()[index].getId() != pos && index < 22) index++;
		
		if(index < 4 && this.getBoard().getProperties()[index].getOwner() != -1 && this.getBoard().getRailroadCells()[index].getOwner() != activePlayer) {
			System.out.printf("Player %s pays %d rent to %s",
					this.getPlayerList()[activePlayer].getName(),
					this.getBoard().getProperties()[index].getActiveRent(),
					this.getPlayerList()[this.getBoard().getProperties()[index].getOwner()].getName());
			
			this.getStateHolder().setMoney(
					activePlayer,
					-(this.getBoard().getProperties()[index].getActiveRent())
					);
			
			this.getStateHolder().setMoney(
					this.getBoard().getProperties()[index].getOwner(),
					this.getBoard().getProperties()[index].getActiveRent()
					);
			
			System.out.printf("Player %d's (%s) new balance: %d\n",
					activePlayer, this.getPlayerList()[activePlayer].getName(),
					this.getStateHolder().getMoney(activePlayer)
					);
			
		}
		else {
			System.out.printf("Unowned property! Player %d (%s) buys it\n",
					activePlayer,
					this.getPlayerList()[activePlayer].getName()
					);
			
			this.getBoard().getProperties()[index].setOwner(activePlayer);
			
			this.getStateHolder().setMoney(
					activePlayer,
					-(this.getBoard().getProperties()[index].getValue())
					);
			
			this.getStateHolder().setPropertyCount(
					activePlayer,
					this.getStateHolder().getPropertyCount(activePlayer) + 1
					);
			
			System.out.printf("Player %d's (%s) new balance: %d\n",
					activePlayer, this.getPlayerList()[activePlayer].getName(),
					this.getStateHolder().getMoney(activePlayer)
					);
		}
		
	}
}
