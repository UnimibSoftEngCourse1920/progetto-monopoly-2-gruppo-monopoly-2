package model.game;

import model.user.User;

public class MonopoliGame {
	
	private int idGame;
	
	private User player1;
	private User player2;
	private User player3;
	private User player4;
	
	private User playerTurn;
	
	
	public int getIdGame() {
		return idGame;
	}
	public User getPlayer1() {
		return player1;
	}
	public User getPlayer2() {
		return player2;
	}
	public User getPlayer3() {
		return player3;
	}
	public User getPlayer4() {
		return player4;
	}
	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}
	public void setPlayer1(User player1) {
		this.player1 = player1;
	}
	public void setPlayer2(User player2) {
		this.player2 = player2;
	}
	public void setPlayer3(User player3) {
		this.player3 = player3;
	}
	public void setPlayer4(User player4) {
		this.player4 = player4;
	}
	
	public boolean setPlayer(User player) {
		if(player1==null) { player1 = player; return true; }
		if(player2==null) { player2 = player; return true; }
		if(player3==null) { player3 = player; return true; }
		if(player4==null) { player4 = player; return true; }
		return false;
	}
	

}
