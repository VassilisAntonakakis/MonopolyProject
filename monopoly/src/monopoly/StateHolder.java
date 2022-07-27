package monopoly;

import java.util.ArrayList;

public class StateHolder {
	//money-pos-station#-utility#-JailStatus-Bankrupt-property#-doublesInJail
	int[][] states;
	ArrayList<Integer>[] propertyOwnerships;
	
	@SuppressWarnings("unchecked")
	public StateHolder(int playerNo) {
		
		states = new int[playerNo][8];
		propertyOwnerships = new ArrayList[playerNo];
		
		for(int row=0; row<playerNo; row++) {
			propertyOwnerships[row] = new ArrayList<Integer>();
			for(int col=0; col<states[0].length; col++) {
				
				if(col == 0) states[row][col] = 1500; continue;
				
			}
		}
	}
	
	public int getTurnsInJail(int activePlayer) {
		return this.getStates()[activePlayer][7];
	}
	
	public void increseTurnsInJail(int activePlayer) {
		this.getStates()[activePlayer][7]++;
	}
	
	public void resetTurnsInJail(int activePlayer) {
		this.getStates()[activePlayer][7] = 0;
	}
	
	public int[][] getStates(){
		return this.states;
	}
	
	public void passFromGo(int player) {
		this.setMoney(player, 200);
	}
	
	public void setPos(int activePlayer, int newPosition) {
		this.getStates()[activePlayer][1] = newPosition;
	}
	
	public void setMoney(int activePlayer, int transaction) {
		
		if(this.getStates()[activePlayer][0] + transaction < 0) {
			System.out.println("Error! Not enough money!");
			
			if(this.getStates()[activePlayer][2] > 0 || this.getStates()[activePlayer][3] > 0 || this.getStates()[activePlayer][6] > 0) {
				System.out.println("Mortgage properties to pay your dept!");
			}
			else {
				System.out.println("Bankrupt!");
				this.setBunkruptPlayer(activePlayer);
			}
		}
		this.getStates()[activePlayer][0] += transaction;
		
	}
	
	public int getPos(int activePlayer) {
		return this.getStates()[activePlayer][1];
	}
	
	public int getMoney(int activePlayer) {
		return this.getStates()[activePlayer][0];
	}
	
	public void setJailStatus(int player) {
		if(this.getJailStatus(player) == 0) { this.getStates()[player][4] = 1; this.setPos(player, 10);}
		else this.getStates()[player][4] = 0;
	}
	
	public int getJailStatus(int player) {
		return this.getStates()[player][4];
	}
	
	public boolean isBankrupt(int player) {
		if(this.getStates()[player][5] == 1) return true;
		return false;
	}
	
	public void setBunkruptPlayer(int activePlayer) {
		this.getStates()[activePlayer][5] = 1;
	}
	
	public int getStationCount(int player) {
		return this.getStates()[player][2];
	}
	
	public void setStationCount(int player, int action) {
		if(action == 1) this.getStates()[player][2]++;
		else this.getStates()[player][2]--;
	}
	
	public int getUtilityCount(int player) {
		return this.getStates()[player][3];
	}
	
	public void setUtilityCount(int player, int action) {
		if(action == 1) this.getStates()[player][3]++;
		else this.getStates()[player][3]--;
	}
	
	public int getPropertyCount(int activePlayer) {
		return this.getStates()[activePlayer][6];
	}
	
	public void setPropertyCount(int activePlayer, int newCount) {
		this.getStates()[activePlayer][6] += newCount;
	}
	
	@SuppressWarnings("rawtypes")
	private ArrayList[] getPropertyOwnerships() {
		return this.propertyOwnerships;
	}
	
	public void getPlayersOwnerships(int activePlayer) {
		int index = 0;
		System.out.printf("Player %d's properties: ", activePlayer);
		while(this.getPropertyOwnerships()[activePlayer].get(index) != null)System.out.printf(" %d ", this.getPropertyOwnerships()[activePlayer].get(index)); index++;
	}
	
	public int getPropertyOwner(int propertyId) {
		
		for (int player = 0; player < this.getPropertyOwnerships().length; player++) {
            for (int property = 0; property < this.getPropertyOwnerships()[player].size(); property++) {
                if(this.getPropertyOwnerships()[player].get(property) == (Integer)propertyId) return player;
            }            
        }
		
		return -1;
	}
	
	private int getPropertyIndex(int propertyId){
		for (int player = 0; player < this.getPropertyOwnerships().length; player++) {
            for (int property = 0; property < this.getPropertyOwnerships()[player].size(); property++) {
                if(this.getPropertyOwnerships()[player].get(property) == (Integer)propertyId) return property;
            }            
        }
		
		return -1;
	}
	
	private int getPropertyIndex(int player, int propertyId){
        for (int property = 0; property < this.getPropertyOwnerships()[player].size(); property++) {
            if(this.getPropertyOwnerships()[player].get(property) == (Integer)propertyId) return property;
        }            
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public void addOwnership(int activePlayer, int propertyId) {
		this.getPropertyOwnerships()[activePlayer].add(propertyId);
	}
	
	public void removeOwnership(int activePlayer, int propertyId) {
		int index;
		if((index = this.getPropertyIndex(activePlayer, propertyId)) != -1) this.getPropertyOwnerships()[activePlayer].remove(index);
		else System.out.println("Property not found in player");
	}
}
