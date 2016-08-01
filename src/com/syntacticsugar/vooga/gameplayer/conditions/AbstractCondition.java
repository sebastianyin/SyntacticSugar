package com.syntacticsugar.vooga.gameplayer.conditions;

import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;

public abstract class AbstractCondition implements IGameCondition, IEventPoster {

	private ConditionType myType;
	private IEventPoster myManager;

	public AbstractCondition(ConditionType type) {
		myType = type;
		setDefaults();
	}

	/**
	 * Set default condition values.
	 */
	protected abstract void setDefaults();
	
	@Override
	public void registerManager(IEventPoster manager){
		myManager = manager;
	}
	
	public ConditionType returnType() {
		return myType;
	}
	
	@Override
	public void postEvent(IGameEvent event){
		myManager.postEvent(event);
	}
	
}
