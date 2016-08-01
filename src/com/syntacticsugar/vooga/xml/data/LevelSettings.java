package com.syntacticsugar.vooga.xml.data;

import com.syntacticsugar.vooga.gameplayer.conditions.IGameCondition;

public class LevelSettings {

	private IGameCondition winCond;
	private IGameCondition lossCond;

	private int startingMoney;

	public LevelSettings(int money) {
		startingMoney = money;
	}

	public LevelSettings(IGameCondition winCond, IGameCondition lossCond, int money) {
		startingMoney = money;
		this.winCond = winCond;
		this.lossCond = lossCond;
	}

	public int getStartingMoney() {
		return startingMoney;
	}

	public void setStartingMoney(int startingMoney) {
		this.startingMoney = startingMoney;
	}
	
	public IGameCondition getWinCondition() {
		return this.winCond;
	}
	
	public IGameCondition getLossCondition() {
		return this.lossCond;
	}


}
