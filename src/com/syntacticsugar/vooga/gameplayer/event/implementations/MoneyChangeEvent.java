package com.syntacticsugar.vooga.gameplayer.event.implementations;

import com.syntacticsugar.vooga.gameplayer.event.GameEvent;
import com.syntacticsugar.vooga.gameplayer.event.GameEventType;

public class MoneyChangeEvent extends GameEvent {
	
	private int money;
	
	public MoneyChangeEvent(int m) {
		super(GameEventType.MoneyChange);
		money = m;
	}
	
	public int getMoney() {
		return money;
	}

}
