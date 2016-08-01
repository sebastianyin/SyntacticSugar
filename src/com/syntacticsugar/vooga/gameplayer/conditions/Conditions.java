package com.syntacticsugar.vooga.gameplayer.conditions;

import com.syntacticsugar.vooga.gameplayer.event.IEventListener;
import com.syntacticsugar.vooga.gameplayer.manager.IEventManager;

public class Conditions implements IConditions, IEventListener {

	private IGameCondition myWinCondition;
	private IGameCondition myLossCondition;
	
	public Conditions(IGameCondition win, IGameCondition loss) {
		myWinCondition = win;
		myLossCondition = loss;
	}
	
	@Override
	public IGameCondition getWinCondition() {
		return myWinCondition;
	}

	@Override
	public IGameCondition getLossCondition() {
		return myLossCondition;
	}

	@Override
	public void registerEventManager(IEventManager eventmanager) {
		register(myWinCondition, eventmanager);
		register(myLossCondition, eventmanager);
	}		

	private void register(IGameCondition condition, IEventManager manager) {
		condition.registerManager(manager);
		manager.registerListener(condition);
	}

}
