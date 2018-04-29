package com.digitoygames;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	private static Player player1;
	private static Player player2;
	private static Player player3;
	private static Player player4;
	private static ArrayList<Player> playerList;
	
	private static ArrayList<Stone> allStones;

	private static ArrayList<Stone> board1;
	private static ArrayList<Stone> board2;
	private static ArrayList<Stone> board3;
	private static ArrayList<Stone> board4;
	
	private static GameUtil gmu;
	

	public static void main(String[] args) {
		
		initVariables();
		
		if(gmu.generateGameStones()){
			if(gmu.shuffleTheStones()){
				Stone okeyStone = gmu.generateRandomStone();
				System.out.println("The Okey " +okeyStone.toString());
				if(okeyStone != null && okeyStone.isOkeyStone()){
					
					
					board1 = gmu.generatePlayerBoard(board1, player1.isHisTurn());
					player1.setBoard(board1);
					gmu.setPlayerScore(player1);
					
					board2 = gmu.generatePlayerBoard(board2, player2.isHisTurn());
					player2.setBoard(board2);
					gmu.setPlayerScore(player2);
					
					board3 = gmu.generatePlayerBoard(board3, player3.isHisTurn());
					player3.setBoard(board3);
					gmu.setPlayerScore(player3);
					
					board4 = gmu.generatePlayerBoard(board4, player4.isHisTurn());
					player4.setBoard(board4);
					gmu.setPlayerScore(player4);
					
					int winnerId = selectWinner();
					playerList.get(winnerId).setWinner(true);
					System.out.println("Winner player is >> ");
					System.out.println(playerList.get(winnerId).toString());
					
				}else{
					showErrorMessage(GameUtil.RANDOM_ERROR);
				}
			}else{
				showErrorMessage(GameUtil.SHUFFLE_ERROR);
			}
		}else{
			showErrorMessage(GameUtil.INIT_ERROR);
		}
		
		
	}
	
	public static void initVariables(){
		allStones = new ArrayList<Stone>();
		board1 = new ArrayList<Stone>();
		board2 = new ArrayList<Stone>();
		board3 = new ArrayList<Stone>();
		board4 = new ArrayList<Stone>();
		
		gmu = new GameUtil(allStones);
		
		Random rand = new Random();
		int turnNum = rand.nextInt(GameUtil.PLAYER_COUNT);
		
		player1 = new Player(GameUtil.PLAYER1_ID, GameUtil.PLAYER1_ALIAS, board1, 0,turnNum==GameUtil.PLAYER1_ID, false);
		player2 = new Player(GameUtil.PLAYER2_ID, GameUtil.PLAYER2_ALIAS, board2, 0, turnNum==GameUtil.PLAYER2_ID, false);
		player3 = new Player(GameUtil.PLAYER3_ID, GameUtil.PLAYER3_ALIAS, board3, 0, turnNum==GameUtil.PLAYER3_ID, false);
		player4 = new Player(GameUtil.PLAYER4_ID, GameUtil.PLAYER4_ALIAS, board4, 0, turnNum==GameUtil.PLAYER4_ID, false);
		
		playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		playerList.add(player4);
		
	}

	public static void showErrorMessage(String errorMsg){
		System.out.println(GameUtil.ERROR_TITLE+errorMsg);
	}
	
	public static int selectWinner() {
		int maxScore = 0;
		int winnerId = 0;
		
		for(Player player : playerList) {
			if(player.getScore() > maxScore) {
				maxScore = player.getScore();
				winnerId = player.getPlayerId();
			}
		}
		
		return winnerId;
	}
}
