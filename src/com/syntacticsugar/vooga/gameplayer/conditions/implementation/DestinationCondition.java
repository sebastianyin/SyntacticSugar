package com.syntacticsugar.vooga.gameplayer.conditions.implementation;

import com.syntacticsugar.vooga.gameplayer.conditions.AbstractCondition;
import com.syntacticsugar.vooga.gameplayer.conditions.ConditionType;
import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.DestinationReachedEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.LevelChangeEvent;

public class DestinationCondition extends AbstractCondition {

	public DestinationCondition() {
		super(ConditionType.LOSING);
	}

	@Override
	protected void setDefaults() {
		
	}
	
	@Override
	public void onEvent(IGameEvent e) {
		try {
			@SuppressWarnings("unused")
			DestinationReachedEvent event = (DestinationReachedEvent) e;
			postEvent(new LevelChangeEvent(this.returnType()));

		} catch (ClassCastException ce) {

		}
	}

}
