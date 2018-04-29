package com.digitoygames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameUtil {

	// player alias
	public static final String PLAYER1_ALIAS = "OYUNCU-1";
	public static final int PLAYER1_ID = 0;
	public static final String PLAYER2_ALIAS = "OYUNCU-2";
	public static final int PLAYER2_ID = 1;
	public static final String PLAYER3_ALIAS = "OYUNCU-3";
	public static final int PLAYER3_ID = 2;
	public static final String PLAYER4_ALIAS = "OYUNCU-4";
	public static final int PLAYER4_ID = 3;

	// game constants
	public static final int MAX_GAME_STONE = 106;
	public static final int MAX_UNIQUE_STONE = 53;
	public static final int MAX_UNIQUE_TYPE_STONE = 13;
	public static final int MAX_STONE_AT_ONE_BOARD = 14;
	public static final int MAX_STONE_PAIR = 2;
	public static final int PLAYER_COUNT = 4;

	// error tags
	public static final String ERROR_TITLE = " Bir Hata Oluştu ! >> ";
	public static final String INIT_ERROR = " Oyun taş listesinin oluşturulması esnasında bir hata oluştu. ";
	public static final String SHUFFLE_ERROR = " Oyun taşlarının karıştırılması esnasında bir hata oluştu. ";
	public static final String RANDOM_ERROR = " Okey taşının belirlenmesi esnasında bir hata oluştu. ";

	public ArrayList<Stone> allStones;

	public GameUtil(ArrayList<Stone> stoneList) {
		this.allStones = stoneList;
	}

	/**
	 * sirasiyla tum taslari oyun listesine ekler.
	 * 
	 * @return boolean islem problemsiz tamamlandi ise true, degilse false
	 */
	public boolean generateGameStones() {

		boolean result = false;

		if (this.allStones != null) {
			this.allStones.clear();

			for (int i = 0; i <= MAX_GAME_STONE; i++) {
				Stone stone = new Stone();
				stone.setStoneNum(i % MAX_UNIQUE_STONE);
				stone.setStoneType(StoneType.getTypeName(i % MAX_UNIQUE_STONE));
				stone.setOkeyStone(false);
				stone.setAtBoard(false);
				stone.setIndicator(false);
				this.allStones.add(stone);
			}
			result = true;
		}

		return result;
	}

	/**
	 * sirali tas listesini karistirir.
	 * 
	 * @return boolean islem problemsiz tamamlandi ise true, degilse false
	 */
	public boolean shuffleTheStones() {

		boolean result = false;

		if (this.allStones != null && !this.allStones.isEmpty()) {
			try {
				Collections.shuffle(this.allStones);
			} catch (Exception ex) {
				System.out.println("Hata : " + ex.getMessage());
				result = false;
			} finally {
				result = true;
			}
		}
		return result;
	}

	/**
	 * liste icerisinden rastgele bir tasi secer once gosterge tasi olarak
	 * isaretler. Ardindan liste icerisinde rastgele secilen tas numarasindan bir
	 * fazlasina sahip olan iki tasin tipini okey tasi olarak belirler. ve liste
	 * icindeki tasin okey tasi oldugunu duzenle.
	 * 
	 * @return okey stone
	 */
	public Stone generateRandomStone() {
		Random random = new Random();
		int randNum = random.nextInt(MAX_GAME_STONE);

		if (this.allStones.get(randNum).getStoneType().equals(StoneType.FAKE_OKEY)) {
			generateRandomStone();
		}

		Stone stone = this.allStones.get(randNum);
		stone.setIndicator(true);

		Stone cloneOkey = StoneType.getCloneOkeyStone(stone.getStoneNum());

		for (Stone element : this.allStones) {
			if (element.equals(cloneOkey)) {
				element.setOkeyStone(true);
			}
		}

		return cloneOkey;
	}

	/**
	 * 
	 * @param isHisTurn : eger oyuncunun sirasi ise, 15 tas degilse 14 tas dagitsin degeri
	 * @return bir okey tahtasinda olabilcek rastgele taslari olusturur.
	 */
	public ArrayList<Stone> generatePlayerBoard(ArrayList<Stone> board, boolean isHisTurn) {

		while( board.size() < ( isHisTurn ? (MAX_STONE_AT_ONE_BOARD + 1) : MAX_STONE_AT_ONE_BOARD ) ) {

			Random random = new Random();
			int randNum = random.nextInt(MAX_GAME_STONE);

			if (this.allStones.get(randNum).isAtBoard()) {
				continue;
			}else {
				Stone stone = this.allStones.get(randNum);
				stone.setAtBoard(true);
				board.add(stone);
			}
		}
		
		return board;
	}
	
	public void setPlayerScore(Player player) {
		
		int result = 0;
		int colorMatches = 0;
		int numMatches = 0;
		
		ArrayList<Stone> board = player.getBoard();
		
		
		for(Stone element : board) {
			
			int numFactor = element.getStoneNum() % MAX_UNIQUE_TYPE_STONE;
			
			for(Stone element2 : board) {
				
				int numFactor2 = element2.getStoneNum() % MAX_UNIQUE_TYPE_STONE;
				
				// farkli renk ayni numara olanlari bul.
				if(numFactor == numFactor2 && !element2.getStoneType().equals(element.getStoneType())) {
					colorMatches++;
				}
				
				// ayni renk fakat oncesi veya sonrasi tas numarasina sahip ise,
				if(element2.getStoneType().equals(element.getStoneType()) && 
						( numFactor == (numFactor2 - 1) || numFactor == (numFactor2 + 1) )) {
					numMatches++;
				}
				
				// sayi 13 sonraki ve 1 var ise arka arkaya getir.
				if(element2.getStoneType().equals(element.getStoneType()) && 
						( ( numFactor == 13 && numFactor2 == 1 ) || ( numFactor == 1 && numFactor2 == 13 ) ) ) {
					numMatches++;
				}
			}
			
			// renk eslesmesi veya sirali sayi eslesmesi 2 den büyük ise toplam puani 1 artir.
			if(colorMatches > 2 || numMatches > 2) {
				result++;
			}
		}
		
		player.setScore(result);
		
	}

}
