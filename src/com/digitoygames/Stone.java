package com.digitoygames;

enum StoneType {
	SARI,
	MAVI,
	SIYAH,
	KIRMIZI,
	FAKE_OKEY;

	public static StoneType getTypeName(int stoneNum) {
		
		if(stoneNum >= 0 && stoneNum < 13){
			return StoneType.SARI;
		}else if(stoneNum >= 13 && stoneNum < 26){
			return StoneType.MAVI;
		}else if(stoneNum >= 26 && stoneNum < 39){
			return StoneType.SIYAH;
		}else if(stoneNum >= 39 && stoneNum < 52){
			return StoneType.KIRMIZI;
		}else if(stoneNum >= 52 && stoneNum < 54){
			return StoneType.FAKE_OKEY;
		}
		return null;
    }
	public static Stone getCloneOkeyStone(int stoneNum) {
		
		int numFactor = stoneNum % GameUtil.MAX_UNIQUE_TYPE_STONE;
		
		Stone stone = new Stone();
		stone.setIndicator(false);
		stone.setAtBoard(false);
		stone.setOkeyStone(true);
		stone.setStoneType(getTypeName(stoneNum));
		
		numFactor++;
		if(numFactor >= GameUtil.MAX_UNIQUE_TYPE_STONE ) {
			numFactor = 0;
		}
		numFactor += stone.getStoneType().ordinal() * GameUtil.MAX_UNIQUE_TYPE_STONE;
		stone.setStoneNum(numFactor);
		
		
		return stone;
	}
}

public class Stone {

	private int stoneNum;
	private StoneType stoneType;
	private boolean isOkeyStone;
	private boolean isIndicator;
	private boolean isAtBoard;
	
	public Stone() {
	}
	
	public Stone(int stoneNum, StoneType stoneType, boolean isOkeyStone) {
		this.stoneNum = stoneNum;
		this.stoneType = stoneType;
		this.isOkeyStone = isOkeyStone;
	}

	public int getStoneNum() {
		return stoneNum;
	}
	public void setStoneNum(int stoneNum) {
		this.stoneNum = stoneNum;
	}
	public StoneType getStoneType() {
		return stoneType;
	}
	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}
	public boolean isOkeyStone() {
		return isOkeyStone;
	}
	public void setOkeyStone(boolean isOkeyStone) {
		this.isOkeyStone = isOkeyStone;
	}
	
	public boolean isIndicator() {
		return isIndicator;
	}

	public void setIndicator(boolean isIndicator) {
		this.isIndicator = isIndicator;
	}

	public boolean isAtBoard() {
		return isAtBoard;
	}

	public void setAtBoard(boolean isAtBoard) {
		this.isAtBoard = isAtBoard;
	}

	@Override
	public String toString() {
		return "Stone is : [stoneNum=" + stoneNum + ", stoneType=" + stoneType + ", isOkeyStone=" + isOkeyStone
				+ ", isIndicator=" + isIndicator + ", isAtBoard=" + isAtBoard + "]\n";
	}
	
	
	
}
