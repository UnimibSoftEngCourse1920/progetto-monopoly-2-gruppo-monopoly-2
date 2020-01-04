package model.game;

import model.user.User;

public class MonopoliGame {
	
	private int gameId;
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	
	private Player playerTurn;
	
	
	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public Player getPlayer3() {
		return player3;
	}
	public Player getPlayer4() {
		return player4;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	public void setPlayer3(Player player3) {
		this.player3 = player3;
	}
	public void setPlayer4(Player player4) {
		this.player4 = player4;
	}
	
	public boolean setPlayer(Player player) {
		if(player1==null) { player1 = player; return true; }
		if(player2==null) { player2 = player; return true; }
		if(player3==null) { player3 = player; return true; }
		if(player4==null) { player4 = player; return true; }
		return false;
	}
	

}
