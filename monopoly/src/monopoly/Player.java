package monopoly;

public class Player {
	
	private String name;
	private boolean humanPlayer;
	
	public Player(String name, boolean isHuman) {
		this.setName(name);
		this.setHumanPlayer(isHuman);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHumanPlayer() {
		return humanPlayer;
	}

	public void setHumanPlayer(boolean humanPlayer) {
		this.humanPlayer = humanPlayer;
	}
	
}
