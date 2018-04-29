package com.digitoygames;

import java.util.ArrayList;

public class Player {
	
	private int playerId;
	private String alias;
	private ArrayList<Stone> board;
	private int score;
	private boolean isHisTurn;
	private boolean isWinner;
	
	public Player() {
	}

	public Player(int playerId, String alias, ArrayList<Stone> board, int score, boolean isHisTurn, boolean isWinner) {
		super();
		this.playerId = playerId;
		this.alias = alias;
		this.board = board;
		this.score = score;
		this.isHisTurn = isHisTurn;
		this.isWinner = isWinner;
	}
	
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public ArrayList<Stone> getBoard() {
		return board;
	}

	public void setBoard(ArrayList<Stone> board) {
		this.board = board;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isHisTurn() {
		return isHisTurn;
	}

	public void setHisTurn(boolean isHisTurn) {
		this.isHisTurn = isHisTurn;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	@Override
	public String toString() {
		return "Player [playerId = " + playerId + "\n alias = " + alias + "\n board = " + board + "\n score = " + score
				+ "\n isHisTurn = " + isHisTurn + "\n isWinner = " + isWinner + "]\n\n";
	}
	
}
