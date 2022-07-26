package monopoly;

public class StateHolder {
	//money-pos-station#-utility#-JailStatus-Bankrupt-property#
	int[][] states;
	
	public StateHolder(int playerNo) {
		states = new int[playerNo][7];
		
		for(int row=0; row<playerNo; row++) {
			for(int col=0; col<7; col++) {
				
				if(col == 0) states[row][col] = 1500; continue;
				
			}
		}
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
		if(this.getStates()[player][4] == 1) return true;
		return false;
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
}
