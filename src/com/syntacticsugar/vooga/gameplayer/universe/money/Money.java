package com.syntacticsugar.vooga.gameplayer.universe.money;

import java.util.Observable;

import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.MoneyChangeEvent;

public class Money extends Observable implements IMoney{
	
	private int myMoney;
	
	public Money(int initialCash) {
		myMoney = initialCash;
	}

	@Override
	public void onEvent(IGameEvent e) {
		try {
			MoneyChangeEvent event = (MoneyChangeEvent) e;
			changeMoney(event.getMoney());
			setChanged();
			notifyObservers(this);
		}
		catch (ClassCastException ex) {
			
		}
	}
	
	private void changeMoney(int money) {
		myMoney += money;
	}

	@Override
	public int getMoney() {
		return myMoney;
	}

}
